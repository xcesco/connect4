package it.fmt.games.connect4.model.operators;

import it.fmt.games.connect4.model.Board;
import it.fmt.games.connect4.model.Coordinates;
import it.fmt.games.connect4.model.Direction;
import it.fmt.games.connect4.model.Piece;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MoveEvaluator extends AbstractBoardOperator {
    private final static List<List<Direction>> directions = List.of(
            List.of(Direction.DOWN),
            List.of(Direction.LEFT, Direction.RIGHT),
            List.of(Direction.UP_LEFT, Direction.DOWN_RIGHT),
            List.of(Direction.DOWN_LEFT, Direction.DOWN_LEFT)
    );
    private final Coordinates searchOrigin;

    private MoveEvaluator(Board board, Coordinates coords, Piece piece) {
        super(board, piece);
        this.searchOrigin = coords;
    }

    public static List<Coordinates> findRow(Board board, Coordinates newMoveCoords, Piece piece) {
        MoveEvaluator hunter = new MoveEvaluator(board, newMoveCoords, piece);
        return hunter.findRow();
    }

    public static int calculateScore(Board board, Coordinates newMoveCoords, Piece piece) {
        MoveEvaluator hunter = new MoveEvaluator(board, newMoveCoords, piece);
        return hunter.calculateScore();
    }

    private List<Coordinates> findRow() {
        return directions.stream()
                .map(this::findPiecesAlongDirection)
                .filter(row -> row.count() >= 4)
                .flatMap(Function.identity())
                .collect(Collectors.toList());
    }

    private int calculateScore() {
        return directions.stream()
                .map(this::findPiecesAlongDirection)
                .mapToInt(x -> (int) x.count())
                .peek(c -> System.out.println(c))
                .max()
                .orElse(0) + 1;
    }

    private Stream<Coordinates> findPiecesAlongDirection(List<Direction> directions) {
        return findPiecesAlongDirection(searchOrigin, directions, piece);
    }
}