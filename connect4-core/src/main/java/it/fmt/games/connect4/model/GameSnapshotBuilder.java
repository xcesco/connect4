package it.fmt.games.connect4.model;

import it.fmt.games.connect4.exceptions.InvalidInsertOperationException;

public class GameSnapshotBuilder {
    private Piece activePiece;
    private AvailableMoves availableMoves;
    private GameScore gameScore;
    private Board board;
    private PlayerMove lastMove;

    public GameSnapshotBuilder setActivePiece(Piece activePiece) {
        if (activePiece == Piece.EMPTY) throw new InvalidInsertOperationException();
        this.activePiece = activePiece;
        return this;
    }

    public GameSnapshotBuilder setAvailableMoves(AvailableMoves availableMoves) {
        this.availableMoves = availableMoves;
        return this;
    }

    public GameSnapshotBuilder setScore(GameScore gameScore) {
        this.gameScore = gameScore;
        return this;
    }

    public GameSnapshotBuilder setBoard(Board board) {
        this.board = board;
        return this;
    }

    public GameSnapshotBuilder setLastMove(PlayerMove move) {
        this.lastMove = move;
        return this;
    }

    public GameSnapshot build() {
        return new GameSnapshot(gameScore, lastMove, activePiece, availableMoves, board, GameStatusFactory.create(availableMoves, gameScore));
    }

}