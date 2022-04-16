package it.fmt.games.connect4.model;

import java.util.List;

public interface GameLogic {
    AvailableMoves initialize();

    AvailableMoves findMovesForPlayers();

    void insertSelectedMove(Coordinates moveCoords);

    void switchPlayers();

    GameSnapshot getGameSnapshot();

    Coordinates readActivePlayerMove(List<Coordinates> availableMoves);
}
