package it.fmt.games.connect4.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class AvailableMoves {
    private final List<Coordinates> movesCurrentPlayer;

    @JsonCreator
    public AvailableMoves(@JsonProperty("movesCurrentPlayer") List<Coordinates> availableMovesCurrentPlayer) {
        this.movesCurrentPlayer = availableMovesCurrentPlayer;
    }

    public boolean isAnyAvailableMoves() {
        return isAvailableMovesForActivePlayer();
    }

    public List<Coordinates> getMovesActivePlayer() {
        return movesCurrentPlayer;
    }

    public boolean isAvailableMovesForActivePlayer() {
        return !(movesCurrentPlayer.isEmpty());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvailableMoves that = (AvailableMoves) o;
        return Objects.equals(movesCurrentPlayer, that.movesCurrentPlayer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movesCurrentPlayer);
    }
}
