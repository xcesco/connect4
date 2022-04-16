package it.fmt.games.connect4.exceptions;

import it.fmt.games.connect4.model.Piece;

public class HumanPlayerNeedUserInputException extends RuntimeException {
    public HumanPlayerNeedUserInputException(Piece player) {
        super(String.format("Player %s need user input to decide next move", player));
    }
}
