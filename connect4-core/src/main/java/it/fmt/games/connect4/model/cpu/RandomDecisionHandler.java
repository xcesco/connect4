package it.fmt.games.connect4.model.cpu;

import it.fmt.games.connect4.DecisionHandler;
import it.fmt.games.connect4.model.Board;
import it.fmt.games.connect4.model.Coordinates;
import it.fmt.games.connect4.model.Piece;

import java.security.SecureRandom;
import java.util.List;

public class RandomDecisionHandler implements DecisionHandler {

    @Override
    public Coordinates compute(Board board, List<Coordinates> availableMoves) {
        if (availableMoves.size() == 1) return availableMoves.get(0);
        return availableMoves.get(new SecureRandom().nextInt(availableMoves.size() - 1));
    }
}