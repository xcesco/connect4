package it.fmt.games.connect4.exceptions;

public class InvalidPieceSelectedException extends RuntimeException {
    public InvalidPieceSelectedException() {
        super("Invalid piece selected");
    }
}
