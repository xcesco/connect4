package it.fmt.games.connect4.exceptions;

import it.fmt.games.connect4.model.Board;
import it.fmt.games.connect4.model.Piece;

import java.util.stream.Collectors;

public class InvalidBoardStatusException extends RuntimeException {

  public InvalidBoardStatusException(Board board) {
    super(convert(board));
  }

  private static String convert(Board board) {
    return "\n  A B C D E F G"+board.getCellStream().map(item -> {
      String line = item.getCoordinates().getColumn() == 0 ? "\n"+(item.getCoordinates().getRow()+1)+" " : "";
      if (item.getPiece() == Piece.PLAYER_1) {
        return line + "o";
      } else if (item.getPiece() == Piece.PLAYER_2) {
        return line + "x";
      } else {
        return line + ".";
      }
    }).collect(Collectors.joining(" "));
  }

}