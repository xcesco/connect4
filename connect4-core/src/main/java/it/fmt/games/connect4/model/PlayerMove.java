package it.fmt.games.connect4.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.fmt.games.connect4.exceptions.InvalidInsertOperationException;

import java.util.List;

public class PlayerMove {
    private final Piece piece;
    private final Coordinates moveCoords;

    @JsonCreator
    public PlayerMove(@JsonProperty("piece") Piece piece,
                      @JsonProperty("moveCoords") Coordinates moveCoords) {
        if (piece==null || Piece.EMPTY==piece) throw new InvalidInsertOperationException();
        this.piece = piece;
        this.moveCoords = moveCoords;
    }

    public Piece getPiece() {
        return piece;
    }

    public Coordinates getMoveCoords() {
        return moveCoords;
    }
}
