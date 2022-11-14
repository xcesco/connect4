package it.fmt.games.connect4.console.drawers;

import it.fmt.games.connect4.model.GameStatus;
import it.fmt.games.connect4.model.GameScore;

import static it.fmt.games.connect4.console.drawers.TextDrawer.*;

public class EndGameDrawer {

    public static void drawEndGame(GameStatus status, GameScore gameScore) {
        println("");
        switch (status) {
            case DRAW:
                println(3,"!!!!!!!!!!!!!!!!!!!!!");
                println(3,"!!!!!!! DRAW !!!!!!!!");
                println(3,String.format("!! (O) %d : %d (X) !!", gameScore.getPlayer1Score(), gameScore.getPlayer2Score()));
                println(3,"!!!!!!!!!!!!!!!!!!!!!");
                break;
            case PLAYER1_WIN:
                println(3,"!!!!!!!!!!!!!!!!!!!!!!!!!");
                if ("CPU_1".equals(player1AsString))
                    println(3,"!!!!!! "+player1AsString+"  WINS !!!!!!");
                else
                    println(3,"!!!!! "+player1AsString+" WINS !!!!!");
                println(3,String.format("!!!! (O) %d : %d (X) !!!!", gameScore.getPlayer1Score(), gameScore.getPlayer2Score()));
                println(3,"!!!!!!!!!!!!!!!!!!!!!!!!!");
                break;
            case PLAYER2_WIN:
                println(3,"!!!!!!!!!!!!!!!!!!!!!!!!!");
                if ("CPU_2".equals(player2AsString))
                    println(3,"!!!!!! "+player2AsString+"  WINS !!!!!!");
                else
                    println(3,"!!!!! "+player2AsString+" WINS !!!!!");
                println(3,String.format("!!!! (O) %d : %d (X) !!!!", gameScore.getPlayer1Score(), gameScore.getPlayer2Score()));
                println(3,"!!!!!!!!!!!!!!!!!!!!!!!!!");
                break;
        }
    }
}
