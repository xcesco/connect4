package it.fmt.games.connect4.console.drawers;

import it.fmt.games.connect4.model.GameScore;

import static it.fmt.games.connect4.console.drawers.TextDrawer.println;

public abstract class ScoreDrawer {

    private ScoreDrawer() {

    }

    public static void drawScore(GameScore gameScore) {
        println("  SCORE:");
        println(2,String.format("- "+TextDrawer.player1AsString+" (O) : %d", gameScore.getPlayer1Score()));
        println(2,String.format("- "+TextDrawer.player2AsString+" (X) : %d", gameScore.getPlayer2Score()));
    }
}
