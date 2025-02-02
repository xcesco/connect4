package it.fmt.games.connect4.console;

import it.fmt.games.connect4.DecisionHandlerType;
import it.fmt.games.connect4.Player1;
import it.fmt.games.connect4.Player2;
import it.fmt.games.connect4.PlayerFactory;
import it.fmt.games.connect4.console.drawers.TextDrawer;

import java.util.Scanner;

import static it.fmt.games.connect4.console.NumberUtility.isInInterval;
import static it.fmt.games.connect4.console.NumberUtility.toNumber;

public abstract class PlayersSelector extends TextDrawer {

    static final String P1_VS_P2 = "  ************** PLAYER_1 vs PLAYER_2 *************";
    static final String P1_VS_CPU = "  **************** PLAYER_1 vs CPU ****************";
    static final String CPU_VS_P2 = "  **************** CPU vs PLAYER_2 ****************";
    static final String CPU_VS_CPU = "  ****************** CPU vs CPU *******************";

    private PlayersSelector() {

    }

    public static int selectPlayers(App app) {
        Scanner scanner=app.getScanner();
        int value;
        do {
            println("");
            println("  --------------- CHOOSE GAME TYPE ----------------");
            println("  1 - PLAYER 1 vs PLAYER 2");
            println("  2 - PLAYER 1 vs CPU");
            println("  3 - CPU vs PLAYER 2");
            println("  4 - CPU vs CPU");
            println("  0 - Exit");
            print("  Insert the number of game type: ");

            String input = scanner.nextLine().trim();
            value = toNumber(input);
        } while (!isInInterval(value,0, 4));
        println("");
        definePlayers(app, value);
        return value;
    }

    private static void definePlayers(App app, int choice) {
        DecisionHandlerType decisionHandlerType=DecisionHandlerType.MINIMAX;
        switch (choice) {
            case 1:
                setPlayers(app, P1_VS_P2, PlayerFactory.createHumanPlayer1(), PlayerFactory.createHumanPlayer2());
                setPlayersAsString("PLAYER_1", "PLAYER_2");
                break;
            case 2:
                setPlayers(app, P1_VS_CPU, PlayerFactory.createHumanPlayer1(), PlayerFactory.createCpuPlayer2(decisionHandlerType));
                setPlayersAsString("PLAYER_1", "CPU_2");
                break;
            case 3:
                setPlayers(app, CPU_VS_P2, PlayerFactory.createCpuPlayer1(decisionHandlerType), PlayerFactory.createHumanPlayer2());
                setPlayersAsString("CPU_1", "PLAYER_2");
                break;
            case 4:
                setPlayers(app, CPU_VS_CPU, PlayerFactory.createCpuPlayer1(decisionHandlerType), PlayerFactory.createCpuPlayer2(decisionHandlerType));
                setPlayersAsString("CPU_1", "CPU_2");
                break;
            default:
                break;
        }
    }

    private static void setPlayers(App app, String p1VsP2, Player1 player1, Player2 player2) {
        println(p1VsP2);
        app.setPlayer1(player1);
        app.setPlayer2(player2);
    }

}
