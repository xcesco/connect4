package it.fmt.games.connect4.exceptions;

import it.fmt.games.connect4.model.Coordinates;

import java.util.List;
import java.util.stream.Collectors;

public class InconsistentBoardException extends RuntimeException {
    public InconsistentBoardException(List<Coordinates> coordinates) {
        super(String.format("Invalid values found at: %s", coordinates.stream().map(cell -> "" + cell).collect(Collectors.joining(", "))));
    }
}
