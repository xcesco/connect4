package it.fmt.games.connect4.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.fmt.games.connect4.exceptions.InvalidInsertOperationException;
import it.fmt.games.connect4.exceptions.NullPieceInsertedException;

import java.util.List;

public class PlayerMove {
    private final Piece piece;
    private final Coordinates moveCoords;

    @JsonCreator
    public PlayerMove(@JsonProperty("piece") Piece piece,
                      @JsonProperty("moveCoords") Coordinates moveCoords) {
        if (piece==null || Piece.EMPTY==piece) throw new NullPieceInsertedException();
        this.piece = piece;
        this.moveCoords = moveCoords;
    }

    public static PlayerMove create(Piece piece, Coordinates coordinates) {
        return new PlayerMove(piece, coordinates);
    }

    public Piece getPiece() {
        return piece;
    }

    public Coordinates getMoveCoords() {
        return moveCoords;
    }
}
