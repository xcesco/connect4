package it.fmt.games.connect4.model;

import it.fmt.games.connect4.DecisionHandler;
import it.fmt.games.connect4.exceptions.HumanPlayerNeedUserInputException;

import java.util.List;

public abstract class Player {
    private final Piece piece;
    private final DecisionHandler decisionHandler;

    public Player(Piece piece, DecisionHandler decisionHandler) {
        this.piece = piece;
        this.decisionHandler = decisionHandler;
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean isHumanPlayer() {
        return this.decisionHandler == null;
    }

    public Coordinates computeNextMove(Board board, List<Coordinates> availableMoves) {
        if (isHumanPlayer()) {
            throw new HumanPlayerNeedUserInputException(getPiece());
        }
        return this.decisionHandler.compute(board, availableMoves);
    }
}
