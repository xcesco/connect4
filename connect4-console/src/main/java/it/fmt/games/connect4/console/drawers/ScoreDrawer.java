package it.fmt.games.connect4.console.drawers;

import it.fmt.games.connect4.model.Score;

import static it.fmt.games.connect4.console.drawers.TextDrawer.println;

public abstract class ScoreDrawer {

    private ScoreDrawer() {

    }

    public static void drawScore(Score score) {
        println("  SCORE:");
        println(2,String.format("- "+TextDrawer.player1AsString+" (O) : %d", score.getPlayer1Score()));
        println(2,String.format("- "+TextDrawer.player2AsString+" (X) : %d", score.getPlayer2Score()));
    }
}
