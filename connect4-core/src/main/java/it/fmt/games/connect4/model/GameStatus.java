package it.fmt.games.connect4.model;

public enum GameStatus {
    RUNNING(false),
    PLAYER1_WIN(true),
    PLAYER2_WIN(true),
    DRAW(true);

    public boolean isGameOver() {
        return gameOver;
    }

    private boolean gameOver;

    GameStatus(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
