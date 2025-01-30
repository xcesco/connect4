package it.fmt.games.connect4.model.cpu;

import it.fmt.games.connect4.DecisionHandler;
import it.fmt.games.connect4.model.Board;
import it.fmt.games.connect4.model.Coordinates;
import it.fmt.games.connect4.model.Piece;

import java.util.List;

public class SimpleDecisionHandler implements DecisionHandler {

    @Override
    public Coordinates compute(Board board, List<Coordinates> availableMoves) {
        return availableMoves.get(0);
    }
}