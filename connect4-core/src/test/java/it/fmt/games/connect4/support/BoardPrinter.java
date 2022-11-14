package it.fmt.games.connect4.support;

import it.fmt.games.connect4.MoveScore;
import it.fmt.games.connect4.model.Board;
import it.fmt.games.connect4.model.Coordinates;
import it.fmt.games.connect4.model.PlayerMove;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class BoardPrinter {
    public static void print(Board board) {
        System.out.println("Board@" + board.hashCode());
        System.out.print("  ");
        IntStream.range(0, Board.BOARD_COLUMNS).forEach(column -> {
            System.out.print((char) ('A' + column) + " ");
        });
        board.getCellStream().forEach(cell -> {
            if (cell.getCoordinates().getColumn() == 0) {
                System.out.println();
                System.out.print((cell.getCoordinates().getRow() + 1) + " ");
            }
            switch (cell.getPiece()) {
                case PLAYER_1:
                    System.out.print("1");
                    break;
                case PLAYER_2:
                    System.out.print("2 ");
                    break;
                case EMPTY:
                    System.out.print(". ");
                    break;
            }

        });
    }

    public static void print(Board board, MoveScore... moveScoreList) {
        print(board, Arrays.asList(moveScoreList));
    }

    public static void print(Board board, List<MoveScore> moveScoreList) {
        Map<Coordinates, MoveScore> moveScoreMap = new HashMap();
        moveScoreList.forEach(moveScore -> {
            moveScoreMap.put(moveScore.getPlayerMove().getMoveCoords(), moveScore);
        });
        System.out.println("Board@" + board.hashCode());
        System.out.print("  ");
        IntStream.range(0, Board.BOARD_COLUMNS).forEach(column -> {
            System.out.print((char) ('A' + column) + " ");
        });
        board.getCellStream().forEach(cell -> {
            if (cell.getCoordinates().getColumn() == 0) {
                System.out.println();
                System.out.print((cell.getCoordinates().getRow() + 1) + " ");
            }
            if (moveScoreMap.containsKey(cell.getCoordinates())) {
                System.out.print(moveScoreMap.get(cell.getCoordinates()).getScore()+" ");
            } else {
                switch (cell.getPiece()) {
                    case PLAYER_1:
                        System.out.print("o ");
                        break;
                    case PLAYER_2:
                        System.out.print("x ");
                        break;
                    case EMPTY:
                        System.out.print(". ");
                        break;
                }
            }
        });
    }
}
