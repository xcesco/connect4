package it.fmt.games.connect4;

import it.fmt.games.connect4.model.Coordinates;
import it.fmt.games.connect4.model.Player;

import java.util.List;

public interface UserInputReader {
    Coordinates readInputFor(Player currentPlayer, List<Coordinates> availableMoves);
}
