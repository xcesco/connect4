package it.fmt.games.connect4.support;

import it.fmt.games.connect4.exceptions.InconsistentBoardException;
import it.fmt.games.connect4.model.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Verifica che la board abbia uno stato consistente, ovvero che tutte le pedine abbiano di sotto un
 * un altro elemento o siano nel bordo inferiore.
 */
public abstract class BoardConsistencyChecker {
    private BoardConsistencyChecker() {
    }

    public static void check(Board board) {
        List<Coordinates> invalidCells = board.getCellStream().filter(cell -> isNotEmpty(cell) && isLowerCellNotEmpty(board, cell)).map(Cell::getCoordinates).collect(Collectors.toList());

        if (invalidCells.size() > 0) {
            throw new InconsistentBoardException(invalidCells);
        }
    }

    private static boolean isNotEmpty(Cell cell) {
        return cell.getPiece() != Piece.EMPTY;
    }

    private static boolean isLowerCellNotEmpty(Board board, Cell cell) {
        Coordinates lowerCellCoords = cell.getCoordinates().translate(Direction.DOWN);
        return lowerCellCoords.isValid() &&
                board.getCellContent(lowerCellCoords) == Piece.EMPTY;
    }

}
