package it.fmt.games.connect4.model.operators;

import it.fmt.games.connect4.model.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AvailableMovesFinder extends AbstractBoardOperator {

    private AvailableMovesFinder(Board board, Piece piece) {
        super(board, piece, null);
    }

    public static List<Coordinates> findPositions(Board board, Piece piece) {
        AvailableMovesFinder finder = new AvailableMovesFinder(board, piece);
        return finder.findPositions();
    }

    private List<Coordinates> findPositions() {
        return board.getCellStream()
                .parallel()
                .filter(this::isValidCellForInsert)
                .map(Cell::getCoordinates)
                .sorted(Comparator.comparing(Coordinates::toString))
                .collect(Collectors.toList());
    }

    private boolean isValidCellForInsert(Cell cell) {
        return board.isCellContentEqualsTo(cell.getCoordinates(), Piece.EMPTY) &&
                !board.isCellContentEqualsTo(cell.getCoordinates().translate(Direction.DOWN), Piece.EMPTY) &&
                board.isCellContentEqualsTo(cell.getCoordinates().translate(Direction.UP), Piece.EMPTY);
    }

}
