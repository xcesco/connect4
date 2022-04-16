package it.fmt.games.connect4.exceptions;

public class InvalidCoordinatesException extends RuntimeException {

    public InvalidCoordinatesException() {
        super("Invalid coordinates");
    }

}
