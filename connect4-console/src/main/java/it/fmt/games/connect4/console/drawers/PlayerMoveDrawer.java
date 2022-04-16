package it.fmt.games.connect4.console.drawers;

import it.fmt.games.connect4.model.Piece;
import it.fmt.games.connect4.model.PlayerMove;

import static it.fmt.games.connect4.console.drawers.TextDrawer.println;

public abstract class PlayerMoveDrawer {

    private PlayerMoveDrawer() {
    }

    public static void drawPlayerMove(PlayerMove move) {
        if (move != null) {
            println("  PREVIOUS TURN:");
            println(2,String.format("- %s moved on %s", (move.getPiece() == Piece.PLAYER_1 ? TextDrawer.player1AsString :
                    TextDrawer.player2AsString), move.getMoveCoords()));
        }
    }
}
