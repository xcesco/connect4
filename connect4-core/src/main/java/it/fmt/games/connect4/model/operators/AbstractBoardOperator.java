package it.fmt.games.connect4.model.operators;

import it.fmt.games.connect4.model.Board;
import it.fmt.games.connect4.model.Coordinates;
import it.fmt.games.connect4.model.Direction;
import it.fmt.games.connect4.model.Piece;

public abstract class AbstractBoardOperator {
    protected final Board board;

    protected AbstractBoardOperator(Board board) {
        this.board = board;
    }

    protected boolean isLowerCellFilled(Coordinates initialCoordinates) {
        return !initialCoordinates.translate(Direction.DOWN).isValid() || !board.getCellContent(initialCoordinates.translate(Direction.DOWN)).equals(Piece.EMPTY);
    }
}
