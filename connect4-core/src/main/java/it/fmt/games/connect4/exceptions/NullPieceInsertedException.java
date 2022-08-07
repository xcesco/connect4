package it.fmt.games.connect4.exceptions;

public class NullPieceInsertedException extends RuntimeException {

    public NullPieceInsertedException() {
        super("Can not insert null movement");
    }

}
