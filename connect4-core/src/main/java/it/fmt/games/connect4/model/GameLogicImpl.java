package it.fmt.games.connect4.model;

import it.fmt.games.connect4.UserInputReader;
import it.fmt.games.connect4.model.operators.MoveEvaluator;
import it.fmt.games.connect4.model.operators.ScoreCalculator;

import java.util.List;

import static it.fmt.games.connect4.model.operators.AvailableMovesFinder.findMoves;
import static it.fmt.games.connect4.model.operators.InsertMoveOperator.insertMove;

public class GameLogicImpl implements GameLogic {
    protected final GameSnapshotBuilder gameSnapshotBuilder;
    private final UserInputReader userInputReader;
    protected Board board;
    protected Player currentPlayer;
    private Player otherPlayer;

    public GameLogicImpl(Player player1, Player player2, UserInputReader userInputReader) {
        this.board = new Board();
        this.gameSnapshotBuilder = new GameSnapshotBuilder();
        this.currentPlayer = player1;
        this.otherPlayer = player2;
        this.userInputReader = userInputReader;
    }

    private void insertNewMove(Piece piece, List<Coordinates> insertedPieceCoords) {
        board = insertMove(board, piece, insertedPieceCoords);
    }

    private void insertNewMove(PlayerMove playerMove) {
        board = insertMove(board, playerMove);
    }

    @Override
    public AvailableMoves initialize() {
        //insertNewMoveAndCapturedPieces(Piece.PLAYER_1, Arrays.asList(of("e4"), of("d5")));
        //insertNewMoveAndCapturedPieces(Piece.PLAYER_2, Arrays.asList(of("d4"), of("e5")));

        gameSnapshotBuilder.setActivePiece(currentPlayer.getPiece())
                .setScore(ScoreCalculator.calculateScore(board, Coordinates.of("a1"), Piece.PLAYER_1))
                .setBoard(board.copy());
        return findMovesForPlayers();
    }

    @Override
    public AvailableMoves findMovesForPlayers() {
        AvailableMoves availableMoves = new AvailableMoves(findMoves(board, currentPlayer.getPiece()), findMoves(board, otherPlayer.getPiece()));

        gameSnapshotBuilder.setAvailableMoves(availableMoves);
        return availableMoves;
    }

    @Override
    public void insertSelectedMove(Coordinates moveCoords) {
        Piece currentPiece = currentPlayer.getPiece();
        PlayerMove playerMove = new PlayerMove(currentPiece, moveCoords, MoveEvaluator.findRow(board, moveCoords, currentPiece));
        insertNewMove(playerMove);

        gameSnapshotBuilder.setLastMove(playerMove).setBoard(board.copy()).setScore(ScoreCalculator.calculateScore(board, moveCoords, currentPiece));
    }

    @Override
    public void switchPlayers() {
        Player temp = currentPlayer;
        currentPlayer = otherPlayer;
        otherPlayer = temp;

        gameSnapshotBuilder.setActivePiece(currentPlayer.getPiece());
    }

    @Override
    public GameSnapshot getGameSnapshot() {
        return gameSnapshotBuilder.build();
    }

    @Override
    public Coordinates readActivePlayerMove(List<Coordinates> availableMoves) {
        if (currentPlayer.isHumanPlayer()) {
            Coordinates move;
            do {
                move = userInputReader.readInputFor(currentPlayer, availableMoves);
            } while (isInvalidMove(move, availableMoves));

            return move;
        } else {
            return currentPlayer.computeNextMove(availableMoves);
        }
    }

    private boolean isInvalidMove(Coordinates move, List<Coordinates> availableMoves) {
        return !availableMoves.contains(move);
    }
}
