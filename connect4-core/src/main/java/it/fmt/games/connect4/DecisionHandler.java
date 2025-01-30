package it.fmt.games.connect4;

import it.fmt.games.connect4.model.Board;
import it.fmt.games.connect4.model.Coordinates;
import it.fmt.games.connect4.model.Piece;

import java.util.List;

public interface DecisionHandler {
    Coordinates compute(Board board, List<Coordinates> availableMoves);
}
