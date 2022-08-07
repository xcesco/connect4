package it.fmt.games.connect4.model.operators;

import it.fmt.games.connect4.model.Board;
import it.fmt.games.connect4.model.Coordinates;
import it.fmt.games.connect4.model.Direction;
import it.fmt.games.connect4.model.Piece;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PiecesAroundFinder extends AbstractBoardOperator {
    private final Coordinates searchOrigin;

    private PiecesAroundFinder(Board board, Coordinates positionToEvaluate, Piece piece) {
        super(board, piece);
        this.searchOrigin = positionToEvaluate;
    }

    public static List<Coordinates> findPiecesAlongDirections(Board board, Coordinates moveToEvaluate, Piece piece) {
        PiecesAroundFinder hunter = new PiecesAroundFinder(board, moveToEvaluate, piece);
        return hunter.findPiecesAlongDirections();
    }

    private List<Coordinates> findPiecesAlongDirections() {
        List<Coordinates> horizontalList = buildCoordinateListOfPlayerPieces(Direction.LEFT, Direction.RIGHT);
        List<Coordinates> verticalList = buildCoordinateListOfPlayerPieces(Direction.DOWN, Direction.UP);
        List<Coordinates> dialog1 = buildCoordinateListOfPlayerPieces(Direction.DOWN_LEFT, Direction.UP_RIGHT);
        List<Coordinates> dialog2 = buildCoordinateListOfPlayerPieces(Direction.UP_LEFT, Direction.DOWN_RIGHT);

        return Stream.of(horizontalList, verticalList, dialog1, dialog2)
                .max(Comparator.comparing(List::size)).get();
    }

    private List<Coordinates> buildCoordinateListOfPlayerPieces(Direction left, Direction right) {
        return Stream.of(left, right)
                .flatMap(this::findPlayerPiecesAlongDirection)
                .sorted()
                .collect(Collectors.toList());
    }

    private Stream<Coordinates> findPlayerPiecesAlongDirection(Direction direction) {
        return findPiecesAlongDirection(searchOrigin, direction);
    }
}