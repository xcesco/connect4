package it.fmt.games.connect4;

import it.fmt.games.connect4.model.Coordinates;

import java.util.List;

public interface DecisionHandler {
    Coordinates compute(List<Coordinates> availableMoves);
}
