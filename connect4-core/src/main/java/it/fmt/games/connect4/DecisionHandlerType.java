package it.fmt.games.connect4;

import it.fmt.games.connect4.model.cpu.RandomDecisionHandler;
import it.fmt.games.connect4.model.cpu.SimpleDecisionHandler;

public enum DecisionHandlerType {
    SIMPLE(new SimpleDecisionHandler()),
    RANDOM(new RandomDecisionHandler());

    private final DecisionHandler decisionHandler;

    DecisionHandlerType(DecisionHandler decisionHandler) {
        this.decisionHandler = decisionHandler;
    }

    public DecisionHandler getDecisionHandler() {
        return decisionHandler;
    }

}
