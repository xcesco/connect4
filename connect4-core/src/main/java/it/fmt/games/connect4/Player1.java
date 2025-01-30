package it.fmt.games.connect4;

import it.fmt.games.connect4.model.Piece;
import it.fmt.games.connect4.model.Player;

public class Player1 extends Player {
    public Player1() {
        this(null);
    }

    public Player1(DecisionHandler decisionHandler) {
        super(Piece.PLAYER_1, decisionHandler);
    }
}
