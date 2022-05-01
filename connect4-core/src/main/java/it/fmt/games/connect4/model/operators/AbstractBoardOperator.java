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
    protected final Piece piece;
    protected final Piece enemyPiece;
    protected final Board board;
    protected final Coordinates searchOrigin;

    protected final static List<List<Direction>> directions = List.of(
            List.of(Direction.DOWN),
            List.of(Direction.LEFT, Direction.RIGHT),
            List.of(Direction.UP_LEFT, Direction.DOWN_RIGHT),
            List.of(Direction.DOWN_LEFT, Direction.UP_RIGHT)
    );

    public AbstractBoardOperator(Board board, Piece piece, Coordinates searchOrigin) {
        this.searchOrigin = searchOrigin;
        if (piece == null || piece == Piece.EMPTY) throw (new InvalidPieceSelectedException());
        this.piece = piece;
        this.enemyPiece = piece == Piece.PLAYER_1 ? Piece.PLAYER_2 : Piece.PLAYER_1;
        this.board = board;
    }

    protected Pair<Stream<Coordinates>, Integer> findPiecesAlongDirection(final List<Direction> directions) {
        final AtomicInteger counter= new AtomicInteger(0);
        // done in this way for JDK1.8 compatibility
        final Stream.Builder<Coordinates> builder = Stream.builder();

        if (board.isCellContentEqualsTo(searchOrigin, piece)) {
            counter.getAndIncrement();
            builder.add(searchOrigin);
        }

        directions.forEach(direction -> {
            Coordinates current = searchOrigin.translate(direction);
            while (board.isCellContentEqualsTo(current, piece)) {
                counter.getAndIncrement();
                builder.add(current);
                current = current.translate(direction);
            }
        });

        return Pair.of(builder.build(), counter.intValue());
    }

    protected boolean isLowerCellFilled(final Coordinates initialCoordinates) {
        return !initialCoordinates.translate(Direction.DOWN).isValid() || !board.getCellContent(initialCoordinates.translate(Direction.DOWN)).equals(Piece.EMPTY);
    }
}
