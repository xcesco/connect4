package it.fmt.games.connect4.support;

import it.fmt.games.connect4.UserInputReader;
import it.fmt.games.connect4.model.Coordinates;
import it.fmt.games.connect4.model.Player;

import java.util.List;

import static it.fmt.games.connect4.model.Coordinates.of;

public class SomeInvalidUserInputReader implements UserInputReader {
    private int counter = 0;

    @Override
    public Coordinates readInputFor(Player currentPlayer, List<Coordinates> availableMoves) {
        if (counter++ % 2 == 0) return of(-1, -1);
        return availableMoves.get(0);
    }
}
