package it.fmt.games.connect4.console;

import java.util.Scanner;

import static it.fmt.games.connect4.console.NumberUtility.isInInterval;
import static it.fmt.games.connect4.console.NumberUtility.toNumber;
import static it.fmt.games.connect4.console.drawers.TextDrawer.print;
import static it.fmt.games.connect4.console.drawers.TextDrawer.println;

public abstract class PlayAgainOrExitSelector {
    private PlayAgainOrExitSelector() {

    }

    public static boolean playAgain(Scanner scanner) {
        int value;
        do {
            println("");
            println("  Do you want to play again?");
            println("  1 - YES");
            println("  0 - EXIT");
            print("  Insert your choice: ");

            String input = scanner.nextLine().trim();
            value = toNumber(input);
        } while (!isInInterval(value, 0, 1));
        println("");
        return value > 0;
    }

}
