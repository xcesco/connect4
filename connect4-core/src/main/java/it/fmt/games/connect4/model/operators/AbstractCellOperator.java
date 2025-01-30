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

public class AbstractCellOperator extends AbstractBoardOperator {
    protected final Piece piece;
    protected final Piece enemyPiece;

    public AbstractCellOperator(Board board, Piece piece, Coordinates searchOrigin) {
        super(board, searchOrigin);

        if (piece == null || piece == Piece.EMPTY) throw (new InvalidPieceSelectedException());
        this.piece = piece;
        this.enemyPiece = piece == Piece.PLAYER_1 ? Piece.PLAYER_2 : Piece.PLAYER_1;
    }

    protected Pair<Stream<Coordinates>, Integer> findPiecesAlongDirection(final List<Direction> directions) {
        final AtomicInteger counter = new AtomicInteger(0);
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
}
