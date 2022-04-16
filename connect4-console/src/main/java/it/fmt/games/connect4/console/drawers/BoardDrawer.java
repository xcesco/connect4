package it.fmt.games.connect4.console.drawers;

import it.fmt.games.connect4.model.Board;
import it.fmt.games.connect4.model.Coordinates;
import it.fmt.games.connect4.model.Piece;

import java.util.List;
import java.util.stream.IntStream;

import static it.fmt.games.connect4.console.drawers.TextDrawer.*;

public class BoardDrawer {
    private final Board board;
    private final List<Coordinates> availableMoves;
    static final String ROW_SEPARATOR = "  +-----+-----+-----+-----+-----+-----+-----+";

    public BoardDrawer(Board board, List<Coordinates> availableMoves) {
        this.availableMoves = availableMoves;
        this.board = board;
    }

    public static void drawBoard(Board board, List<Coordinates> availableMoves) {
        BoardDrawer drawer = new BoardDrawer(board, availableMoves);
        drawer.draw();
    }

    private void draw() {
        print("  ");
        IntStream.range(0, Board.BOARD_COLUMNS).forEach(col ->
                System.out.print(String.format("   %s  ", (char) ('A' + col))));
        println("");
        println(ROW_SEPARATOR);
        IntStream.range(0, Board.BOARD_ROWS).forEach(row -> {
            System.out.print(String.format(PREFIX + "%s |", row + 1));
            IntStream.range(0, Board.BOARD_COLUMNS).forEach(col ->
                    System.out.print(String.format("  %s  |", getSymbol(Coordinates.of(row, col)))));
            println("");
            println(ROW_SEPARATOR);
        });
    }

    private String getSymbol(Coordinates coordinates) {
        return board.getCellContent(coordinates) == Piece.EMPTY ?
                getMovesOnBoard(coordinates) : getPlayerSymbol(coordinates);
    }

    public String getMovesOnBoard(Coordinates coordinates) {
        return isAvailableMove(coordinates) ? "?" : " ";
    }
    
    public boolean isAvailableMove(Coordinates coordinates) {
        return availableMoves.indexOf(coordinates) != -1;
    }

    private String getPlayerSymbol(Coordinates coordinates) {
        return board.getCellContent(coordinates) == Piece.PLAYER_1 ? "O" : "X";
    }
}
