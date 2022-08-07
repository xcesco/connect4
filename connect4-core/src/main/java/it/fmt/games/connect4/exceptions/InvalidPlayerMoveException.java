package it.fmt.games.connect4.exceptions;

import it.fmt.games.connect4.model.Coordinates;

public class InvalidPlayerMoveException extends RuntimeException {

    public InvalidPlayerMoveException(Coordinates coordinates) {
        super(String.format("Can not insert movement in %s position", coordinates));
    }

}
