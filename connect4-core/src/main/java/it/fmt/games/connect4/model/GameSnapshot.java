package it.fmt.games.connect4.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GameSnapshot {
    private final GameScore gameScore;
    private final Piece activePiece;
    private final AvailableMoves availableMoves;
    private final Board board;
    private final GameStatus status;
    private final PlayerMove lastMove;

    @JsonCreator
    public GameSnapshot(@JsonProperty("score") GameScore gameScore,
                        @JsonProperty("lastMove") PlayerMove lastMove,
                        @JsonProperty("activePiece") Piece activePiece,
                        @JsonProperty("availableMoves") AvailableMoves availableMoves,
                        @JsonProperty("board") Board board,
                        @JsonProperty("status") GameStatus status) {
        this.gameScore = gameScore;
        this.activePiece = activePiece;
        this.availableMoves = availableMoves;
        this.board = board;
        this.status = status;
        this.lastMove = lastMove;
    }

    public PlayerMove getLastMove() {
        return lastMove;
    }

    public GameScore getScore() {
        return gameScore;
    }

    public Piece getActivePiece() {
        return activePiece;
    }

    public AvailableMoves getAvailableMoves() {
        return availableMoves;
    }

    public Board getBoard() {
        return board;
    }

    public GameStatus getStatus() {
        return status;
    }

    public Piece getOtherPlayer() {
        return activePiece == Piece.PLAYER_1 ? Piece.PLAYER_2 : Piece.PLAYER_1;
    }
}
