package it.fmt.games.connect4.model.operators;

import it.fmt.games.connect4.exceptions.InvalidPieceSelectedException;
import it.fmt.games.connect4.model.Board;
import it.fmt.games.connect4.model.Coordinates;
import it.fmt.games.connect4.model.Direction;
import it.fmt.games.connect4.model.Piece;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PiecesAroundFinder extends AbstractBoardOperator {
    protected final Piece piece;
    protected final Piece enemyPiece;
    private final Coordinates searchOrigin;

    private PiecesAroundFinder(Board board, Coordinates positionToEvaluate, Piece piece) {
        super(board);
        if (piece == null || piece == Piece.EMPTY) throw (new InvalidPieceSelectedException());
        this.piece = piece;
        this.enemyPiece = piece == Piece.PLAYER_1 ? Piece.PLAYER_2 : Piece.PLAYER_1;
        this.searchOrigin = positionToEvaluate;
    }

    public static List<Coordinates> findBestDirection(Board board, Coordinates moveToEvaluate, Piece piece) {
        PiecesAroundFinder hunter = new PiecesAroundFinder(board, moveToEvaluate, piece);
        return hunter.findBestDirection();
    }

    private List<Coordinates> findBestDirection() {
        List<Coordinates> horizontalList = buildCoordinateListOfPlayerPieces(Direction.LEFT, Direction.RIGHT);
        List<Coordinates> verticalList = buildCoordinateListOfPlayerPieces(Direction.DOWN, Direction.UP);
        List<Coordinates> dialog1 = buildCoordinateListOfPlayerPieces(Direction.DOWN_LEFT, Direction.UP_RIGHT);
        List<Coordinates> dialog2 = buildCoordinateListOfPlayerPieces(Direction.UP_LEFT, Direction.DOWN_RIGHT);

        return Stream.of(horizontalList, verticalList, dialog1, dialog2)
                .max(Comparator.comparing(List::size)).get();
    }

    private List<Coordinates> buildCoordinateListOfPlayerPieces(Direction before, Direction after) {
        return Stream.of(before, after)
                .flatMap(this::findPlayerPiecesAlongDirection)
                .sorted()
                .collect(Collectors.toList());
    }

    private Stream<Coordinates> findPlayerPiecesAlongDirection(Direction direction) {
        return findPiecesAlongDirection(searchOrigin, direction);
    }

    private Stream<Coordinates> findPiecesAlongDirection(Coordinates coordinates, Direction direction) {
        // done in this way for JDK1.8 compatibility
        Stream.Builder<Coordinates> builder = Stream.builder();
        Coordinates current = coordinates.translate(direction);

        while (board.isCellContentEqualsTo(current, this.piece)) {
            builder.add(current);
            current = current.translate(direction);
        }
        return builder.build();
    }
}