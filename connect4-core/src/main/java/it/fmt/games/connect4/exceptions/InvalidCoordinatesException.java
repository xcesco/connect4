package it.fmt.games.connect4.exceptions;

import it.fmt.games.connect4.model.Coordinates;

public class InvalidCoordinatesException extends RuntimeException {

    public InvalidCoordinatesException(Coordinates coordinates) {
        super(String.format("Invalid coordinates %s [row: %s, col: %s]", coordinates.toString(), coordinates.getRow(), coordinates.getColumn()));
    }

}
