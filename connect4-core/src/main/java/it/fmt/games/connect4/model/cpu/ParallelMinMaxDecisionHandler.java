package it.fmt.games.connect4.model.cpu;

import it.fmt.games.connect4.DecisionHandler;
import it.fmt.games.connect4.model.Board;
import it.fmt.games.connect4.model.Coordinates;
import it.fmt.games.connect4.model.Piece;
import it.fmt.games.connect4.model.operators.AvailableMovesFinder;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

import static it.fmt.games.connect4.model.operators.InsertMoveOperator.insertMove;

public class ParallelMinMaxDecisionHandler implements DecisionHandler {
    private final Piece playerPiece;
    private final Piece enemyPiece;
    private static final int MAX_DEPTH = 5;
    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();
    private static final ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
    private static final Map<Long, Integer> zobristTable = new ConcurrentHashMap<>();
    private static final ReentrantLock zobristLock = new ReentrantLock();
    private static final long[][][] zobristKeys;

    static {
        Random random = new Random();
        zobristKeys = new long[Board.BOARD_ROWS][Board.BOARD_COLUMNS][2];
        for (int row = 0; row < Board.BOARD_ROWS; row++) {
            for (int col = 0; col < Board.BOARD_COLUMNS; col++) {
                for (int piece = 0; piece < 2; piece++) {
                    zobristKeys[row][col][piece] = random.nextLong();
                }
            }
        }
    }

    public ParallelMinMaxDecisionHandler(Piece playerPiece) {
        this.playerPiece = playerPiece;
        this.enemyPiece = (playerPiece == Piece.PLAYER_1) ? Piece.PLAYER_2 : Piece.PLAYER_1;
    }

    @Override
    public Coordinates compute(Board board, List<Coordinates> availableMoves) {
        Coordinates bestMove = null;
        int bestScore = Integer.MIN_VALUE;
        List<Future<MoveScore>> futures = new CopyOnWriteArrayList<>();

        for (Coordinates move : availableMoves) {
            futures.add(executor.submit(() -> {
                Board newBoard = insertMove(board, playerPiece, move);
                int score = minimax(newBoard, MAX_DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
                return new MoveScore(move, score);
            }));
        }

        for (Future<MoveScore> future : futures) {
            try {
                MoveScore result = future.get();
                if (result.score > bestScore) {
                    bestScore = result.score;
                    bestMove = result.move;
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return bestMove;
    }

    private int minimax(Board board, int depth, int alpha, int beta, boolean isMaximizing) {
        long hash = computeZobristHash(board);
        if (zobristTable.containsKey(hash)) {
            return zobristTable.get(hash);
        }

        if (checkWin(board, playerPiece)) return 1000;
        if (checkWin(board, enemyPiece)) return -1000;
        if (depth == 0 || board.isFull()) return evaluateBoard(board);

        List<Coordinates> moves = AvailableMovesFinder.findMoves(board);
        int bestEval = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (Coordinates move : moves) {
            Board newBoard = insertMove(board, isMaximizing ? playerPiece : enemyPiece, move);
            int eval = minimax(newBoard, depth - 1, alpha, beta, !isMaximizing);
            bestEval = isMaximizing ? Math.max(bestEval, eval) : Math.min(bestEval, eval);

            if (isMaximizing) {
                alpha = Math.max(alpha, eval);
            } else {
                beta = Math.min(beta, eval);
            }
            if (beta <= alpha) break;
        }

        zobristLock.lock();
        try {
            zobristTable.put(hash, bestEval);
        } finally {
            zobristLock.unlock();
        }

        return bestEval;
    }

    private long computeZobristHash(Board board) {
        long hash = 0;
        for (int row = 0; row < Board.BOARD_ROWS; row++) {
            for (int col = 0; col < Board.BOARD_COLUMNS; col++) {
                Piece piece = board.getCellContent(Coordinates.of(row, col));
                if (piece != null) {
                    int pieceIndex = (piece == Piece.PLAYER_1) ? 0 : 1;
                    hash ^= zobristKeys[row][col][pieceIndex];
                }
            }
        }
        return hash;
    }

    private boolean checkWin(Board board, Piece player) {
        for (int row = 0; row < Board.BOARD_ROWS; row++) {
            for (int col = 0; col < Board.BOARD_COLUMNS; col++) {
                if (col + 3 < Board.BOARD_COLUMNS &&
                        board.getCellContent(Coordinates.of(row, col)) == player &&
                        board.getCellContent(Coordinates.of(row, col + 1)) == player &&
                        board.getCellContent(Coordinates.of(row, col + 2)) == player &&
                        board.getCellContent(Coordinates.of(row, col + 3)) == player)
                    return true;

                if (row + 3 < Board.BOARD_ROWS &&
                        board.getCellContent(Coordinates.of(row, col)) == player &&
                        board.getCellContent(Coordinates.of(row + 1, col)) == player &&
                        board.getCellContent(Coordinates.of(row + 2, col)) == player &&
                        board.getCellContent(Coordinates.of(row + 3, col)) == player)
                    return true;

                if (col + 3 < Board.BOARD_COLUMNS && row + 3 < Board.BOARD_ROWS &&
                        board.getCellContent(Coordinates.of(row, col)) == player &&
                        board.getCellContent(Coordinates.of(row + 1, col + 1)) == player &&
                        board.getCellContent(Coordinates.of(row + 2, col + 2)) == player &&
                        board.getCellContent(Coordinates.of(row + 3, col + 3)) == player)
                    return true;

                if (col - 3 >= 0 && row + 3 < Board.BOARD_ROWS &&
                        board.getCellContent(Coordinates.of(row, col)) == player &&
                        board.getCellContent(Coordinates.of(row + 1, col - 1)) == player &&
                        board.getCellContent(Coordinates.of(row + 2, col - 2)) == player &&
                        board.getCellContent(Coordinates.of(row + 3, col - 3)) == player)
                    return true;
            }
        }
        return false;
    }

    private int evaluateBoard(Board board) {
        int score = 0;
        for (int row = 0; row < Board.BOARD_ROWS; row++) {
            for (int col = 0; col < Board.BOARD_COLUMNS; col++) {
                Piece cell = board.getCellContent(Coordinates.of(row, col));
                if (cell == playerPiece) {
                    score += countPotentialWins(board, row, col, playerPiece);
                } else if (cell == enemyPiece) {
                    score -= countPotentialWins(board, row, col, enemyPiece);
                }
            }
        }
        return score;
    }

    private int countPotentialWins(Board board, int row, int col, Piece player) {
        int count = 0;
        count += checkLine(board, row, col, 0, 1, player);
        count += checkLine(board, row, col, 1, 0, player);
        count += checkLine(board, row, col, 1, 1, player);
        count += checkLine(board, row, col, 1, -1, player);
        return count;
    }

    private int checkLine(Board board, int row, int col, int dr, int dc, Piece player) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (row + i * dr >= 0 && row + i * dr < Board.BOARD_ROWS &&
                    col + i * dc >= 0 && col + i * dc < Board.BOARD_COLUMNS &&
                    board.getCellContent(Coordinates.of(row + i * dr, col + i * dc)) == player) count++;
        }
        return count;
    }

    private static class MoveScore {
        final Coordinates move;
        final int score;

        MoveScore(Coordinates move, int score) {
            this.move = move;
            this.score = score;
        }
    }
}
