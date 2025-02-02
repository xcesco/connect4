package it.fmt.games.connect4;

import it.fmt.games.connect4.model.*;

public class Connect4 {
    private final GameRenderer renderer;
    private final GameLogic gameLogic;

    public Connect4(GameRenderer renderer, UserInputReader userInputReader, Player1 player1, Player2 player2) {
        this.gameLogic = new GameLogicImpl(player1, player2, userInputReader);
        this.renderer = renderer;
    }

    public Connect4(GameRenderer renderer, GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        this.renderer = renderer;
    }

    public GameSnapshot play() {
        AvailableMoves availableMoves = gameLogic.initialize();
        while (availableMoves.isAvailableMoves()) {
            GameSnapshot gameSnapshot = gameLogic.buildGameSnapshot();
            renderer.render(gameSnapshot);

            if (gameSnapshot.getStatus().isGameOver()) {
                break;
            }

            if (availableMoves.isAvailableMoves()) {
                Coordinates nextMove = gameLogic.readActivePlayerMove(availableMoves.getMovesActivePlayer());
                gameLogic.insertSelectedMove(nextMove);
            }

            gameLogic.switchPlayers();
            availableMoves = gameLogic.findMovesForPlayers();
        }

        renderer.render(gameLogic.buildGameSnapshot());
        return gameLogic.buildGameSnapshot();
    }
}
