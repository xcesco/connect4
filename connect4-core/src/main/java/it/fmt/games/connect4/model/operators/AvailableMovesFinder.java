package it.fmt.games.connect4.model.operators;

import it.fmt.games.connect4.model.Board;
import it.fmt.games.connect4.model.Cell;
import it.fmt.games.connect4.model.Coordinates;
import it.fmt.games.connect4.model.Piece;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AvailableMovesFinder extends AbstractBoardOperator {

  private AvailableMovesFinder(Board board, Piece piece) {
    super(board, piece);
  }

  public static List<Coordinates> findMoves(Board board, Piece piece) {
    AvailableMovesFinder finder = new AvailableMovesFinder(board, piece);
    return finder.findMoves();
  }

  private List<Coordinates> findMoves() {
    return board.getCellStream()
            .parallel()
            .filter(this::isValidCellForInsert)
            .map(Cell::getCoordinates)
            .sorted(Comparator.comparing(Coordinates::toString))
            .collect(Collectors.toList());
  }

  private boolean isValidCellForInsert(Cell cell) {
    return board.isCellContentEqualsTo(cell.getCoordinates(), Piece.EMPTY) &&
            isLowerCellFilled(cell.getCoordinates());
  }


}
