package it.fmt.games.connect4.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AvailableMoves {
    private final List<Coordinates> availablePositions;

    private Piece player;

    @JsonCreator
    public AvailableMoves(
            @JsonProperty("player") Piece player,
            @JsonProperty("movesCurrentPlayer") List<Coordinates> availablePositions) {
        this.availablePositions = availablePositions;
        this.player = player;
    }

    public List<Coordinates> getAvailablePositions() {
        return availablePositions;
    }

    public boolean isAvailableMoves() {
        return !(availablePositions.isEmpty());
    }

}
