package it.fmt.games.connect4.exceptions;

public class InvalidInsertOperationException extends RuntimeException {
    public InvalidInsertOperationException() {
        super("Can not insert null piece");
    }
}
