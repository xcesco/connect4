package it.fmt.games.connect4;

import it.fmt.games.connect4.model.PlayerMove;

import java.util.Objects;

public class MoveScore {
    public PlayerMove getPlayerMove() {
        return playerMove;
    }

    private final PlayerMove playerMove;

    public int getScore() {
        return score;
    }

    private final int score;

    public MoveScore(PlayerMove playerMove, int score) {
        this.score=score;
        this.playerMove=playerMove;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoveScore moveScore = (MoveScore) o;
        return score == moveScore.score && Objects.equals(playerMove, moveScore.playerMove);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerMove, score);
    }
}
