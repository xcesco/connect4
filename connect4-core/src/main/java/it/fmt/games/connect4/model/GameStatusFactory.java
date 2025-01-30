package it.fmt.games.connect4.model;

public abstract class GameStatusFactory {
    private GameStatusFactory() {

    }

    public static GameStatus create(AvailableMoves availableMoves, Score score) {
        if (score.getPlayer1Score() > 0) {
            return GameStatus.PLAYER1_WIN;
        } else if (score.getPlayer2Score() > 0) {
            return GameStatus.PLAYER2_WIN;
        } else {
            if (availableMoves == null || availableMoves.isAvailableMoves()) {
                return GameStatus.RUNNING;
            } else {
                return GameStatus.DRAW;
            }
        }
    }
}
