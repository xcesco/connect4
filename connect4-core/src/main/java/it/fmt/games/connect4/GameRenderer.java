package it.fmt.games.connect4;

import it.fmt.games.connect4.model.GameSnapshot;

public interface GameRenderer {
    void render(GameSnapshot gameSnapshot);
}
