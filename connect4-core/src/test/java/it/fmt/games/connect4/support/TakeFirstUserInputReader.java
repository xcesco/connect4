package it.fmt.games.connect4.support;

import it.fmt.games.connect4.UserInputReader;
import it.fmt.games.connect4.model.Coordinates;
import it.fmt.games.connect4.model.Player;

import java.util.List;

public class TakeFirstUserInputReader implements UserInputReader {
    @Override
    public Coordinates readInputFor(Player currentPlayer, List<Coordinates> availableMoves) {
        return availableMoves.get(0);
    }
}
