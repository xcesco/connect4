package it.fmt.games.connect4.model.operators;

import it.fmt.games.connect4.exceptions.InvalidPieceSelectedException;
import it.fmt.games.connect4.model.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AvailableMovesFinder extends AbstractBoardOperator {

  private AvailableMovesFinder(Board board) {
    super(board);
  }

  public static List<Coordinates> findAvailableMoves(Board board) {
    AvailableMovesFinder finder = new AvailableMovesFinder(board);
    return finder.findAvailableMoves();
  }

  private List<Coordinates> findAvailableMoves() {
    return board.getCellStream()
            .parallel()
            .filter(this::isValidCellForInsert)
            .map(Cell::getCoordinates)
            .sorted(Comparator.comparing(Coordinates::toString))
            .collect(Collectors.toUnmodifiableList());
  }

  private boolean isValidCellForInsert(Cell cell) {
    return board.isCellContentEqualsTo(cell.getCoordinates(), Piece.EMPTY) &&
            isLowerCellFilled(cell.getCoordinates());
  }
}
