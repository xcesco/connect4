package it.fmt.games.connect4.model.operators;

import it.fmt.games.connect4.model.Board;
import it.fmt.games.connect4.model.Coordinates;
import it.fmt.games.connect4.model.Piece;

import java.util.stream.Collectors;

public abstract class ValidBoardChecker extends AbstractBoardOperator {

  private ValidBoardChecker(Board board, Piece piece) {
    super(board, piece);
  }

  public static boolean isValidBoardInState(Board board) {
    return AvailableMovesFinder
            .findMoves(board, Piece.PLAYER_1)
            .stream()
            .collect(Collectors
                    .groupingBy(Coordinates::getColumn, Collectors.counting()))
            .values()
            .stream()
            .filter(value -> value > 1)
            .findAny()
            .isEmpty();
  }

}
