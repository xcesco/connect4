package it.fmt.games.connect4.model.cpu;

import it.fmt.games.connect4.DecisionHandler;
import it.fmt.games.connect4.model.Coordinates;

import java.security.SecureRandom;
import java.util.List;

public class RandomDecisionHandler implements DecisionHandler {

    @Override
    public Coordinates compute(List<Coordinates> availableMoves) {
        if (availableMoves.size() == 1) return availableMoves.get(0);
        return availableMoves.get(new SecureRandom().nextInt(availableMoves.size() - 1));
    }
}