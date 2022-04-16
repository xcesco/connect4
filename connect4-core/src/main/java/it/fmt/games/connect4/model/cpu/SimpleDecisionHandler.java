package it.fmt.games.connect4.model.cpu;

import it.fmt.games.connect4.DecisionHandler;
import it.fmt.games.connect4.model.Coordinates;

import java.util.List;

public class SimpleDecisionHandler implements DecisionHandler {

    @Override
    public Coordinates compute(List<Coordinates> availableMoves) {
        return availableMoves.get(0);
    }
}