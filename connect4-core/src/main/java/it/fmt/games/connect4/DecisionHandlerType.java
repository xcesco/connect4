package it.fmt.games.connect4;

import it.fmt.games.connect4.model.Piece;
import it.fmt.games.connect4.model.cpu.MinMaxDecisionHandler;
import it.fmt.games.connect4.model.cpu.RandomDecisionHandler;
import it.fmt.games.connect4.model.cpu.SimpleDecisionHandler;

public enum DecisionHandlerType {
    SIMPLE(SimpleDecisionHandler.class),
    RANDOM(RandomDecisionHandler.class),
    MINIMAX(MinMaxDecisionHandler.class);

    private final Class<? extends DecisionHandler> decisionHandlerClazz;

    DecisionHandlerType(Class<? extends DecisionHandler> decisionHandlerClazz) {
        this.decisionHandlerClazz = decisionHandlerClazz;
    }

    public DecisionHandler buildDecisionHandler(Piece playerPiece) {
        try {
            return decisionHandlerClazz.getConstructor(Piece.class).newInstance(playerPiece);
        } catch (Exception e) {
            throw new RuntimeException("Error creating DecisionHandler instance", e);
        }
    }

}
