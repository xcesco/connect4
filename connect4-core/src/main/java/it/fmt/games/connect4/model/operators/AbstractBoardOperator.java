package it.fmt.games.connect4.model.operators;

import it.fmt.games.connect4.Pair;
import it.fmt.games.connect4.exceptions.InvalidPieceSelectedException;
import it.fmt.games.connect4.model.Board;
import it.fmt.games.connect4.model.Coordinates;
import it.fmt.games.connect4.model.Direction;
import it.fmt.games.connect4.model.Piece;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public abstract class AbstractBoardOperator {
    protected final Board board;
    protected final Coordinates searchOrigin;

    protected final static List<List<Direction>> directions = List.of(
            List.of(Direction.DOWN),
            List.of(Direction.LEFT, Direction.RIGHT),
            List.of(Direction.UP_LEFT, Direction.DOWN_RIGHT),
            List.of(Direction.DOWN_LEFT, Direction.UP_RIGHT)
    );

    public AbstractBoardOperator(Board board, Coordinates searchOrigin) {
        this.searchOrigin = searchOrigin;
        this.board = board;
    }

    protected boolean isLowerCellFilled(final Coordinates initialCoordinates) {
        return !initialCoordinates.translate(Direction.DOWN).isValid() ||
                !board.getCellContent(initialCoordinates.translate(Direction.DOWN)).equals(Piece.EMPTY);
    }
}
