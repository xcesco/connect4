package it.fmt.games.connect4.model;

public abstract class GameStatusFactory {
    private GameStatusFactory() {

    }

    public static GameStatus create(AvailableMoves availableMoves, GameScore gameScore) {
        if (availableMoves == null || availableMoves.isAnyAvailableMoves()) {
            return GameStatus.RUNNING;
        } else {
            int scoreDifference = gameScore.getPlayer1Score() - gameScore.getPlayer2Score();
            if (scoreDifference == 0) {
                return GameStatus.DRAW;
            } else if (scoreDifference > 0) {
                return GameStatus.PLAYER1_WIN;
            } else {
                return GameStatus.PLAYER2_WIN;
            }
        }
    }
}
