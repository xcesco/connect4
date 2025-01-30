package it.fmt.games.connect4.model.cpu;

import it.fmt.games.connect4.DecisionHandler;
import it.fmt.games.connect4.model.Board;
import it.fmt.games.connect4.model.Coordinates;
import it.fmt.games.connect4.model.Piece;
import it.fmt.games.connect4.model.operators.AvailableMovesFinder;

import java.util.List;

import static it.fmt.games.connect4.model.operators.InsertMoveOperator.insertMove;

public class MinMaxDecisionHandler implements DecisionHandler {
    private final Piece playerPiece;
    private final Piece enemyPiece;
    private static final int MAX_DEPTH = 5;

    public MinMaxDecisionHandler(Piece playerPiece) {
        this.playerPiece = playerPiece;
        this.enemyPiece = (playerPiece == Piece.PLAYER_1) ? Piece.PLAYER_2 : Piece.PLAYER_1;
    }

    @Override
    public Coordinates compute(Board board, List<Coordinates> availableMoves) {
        Coordinates bestMove = null;
        int bestScore = Integer.MIN_VALUE;

        for (Coordinates move : availableMoves) {
            Board currentBoard = insertMove(board, playerPiece, move);

            int score = minimax(currentBoard, MAX_DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }
        return bestMove;
    }

    private int minimax(Board board, int depth, int alpha, int beta, boolean isMaximizing) {
        if (checkWin(board, playerPiece)) return 1000;
        if (checkWin(board, enemyPiece)) return -1000;
        if (depth == 0 || board.isFull()) return evaluateBoard(board);

        List<Coordinates> moves = AvailableMovesFinder.findMoves(board);

        if (isMaximizing) {
            int maxEval = Integer.MIN_VALUE;
            for (Coordinates move : moves) {
                Board currentBoard = insertMove(board, playerPiece, move);
                int eval = minimax(currentBoard, depth - 1, alpha, beta, false);
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
                if (beta <= alpha) break;
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (Coordinates move : moves) {
                Board currentBoard = insertMove(board, enemyPiece, move);
                int eval = minimax(currentBoard, depth - 1, alpha, beta, true);
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
                if (beta <= alpha) break;
            }
            return minEval;
        }
    }

    private boolean makeMove(Board board, Coordinates move, Piece player) {
        for (int row = Board.BOARD_ROWS - 1; row >= 0; row--) {
            if (board.getCellContent(Coordinates.of(row, move.getColumn())) == null) {
                board.setCell(Coordinates.of(row, move.getColumn()), player);
                return true;
            }
        }
        return false; // La colonna è piena
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
}
