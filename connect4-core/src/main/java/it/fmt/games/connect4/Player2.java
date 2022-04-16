package it.fmt.games.connect4;

import it.fmt.games.connect4.model.Piece;
import it.fmt.games.connect4.model.Player;

public class Player2 extends Player {
    public Player2() {
        this(null);
    }

    public Player2(DecisionHandler decisionHandler) {
        super(Piece.PLAYER_2, decisionHandler);
    }

}
