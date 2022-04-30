package it.fmt.games.connect4.model.operators;

import it.fmt.games.connect4.exceptions.InvalidPieceSelectedException;
import it.fmt.games.connect4.model.Board;
import it.fmt.games.connect4.model.Coordinates;
import it.fmt.games.connect4.model.Direction;
import it.fmt.games.connect4.model.Piece;

import java.util.List;
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

    protected Stream<Coordinates> findPiecesAlongDirection(final Coordinates coordinates, List<Direction> directions, final Piece pieceToFind) {
        // done in this way for JDK1.8 compatibility
        Stream.Builder<Coordinates> builder = Stream.builder();
        directions.forEach(direction -> {
            Coordinates current = coordinates.translate(direction);
            while (board.isCellContentEqualsTo(current, pieceToFind)) {
                builder.add(current);
                current = current.translate(direction);
            }
        });

        return builder.build();
    }


    protected boolean isLowerCellFilled(Coordinates initialCoordinates) {
        return !initialCoordinates.translate(Direction.DOWN).isValid() || !board.getCellContent(initialCoordinates.translate(Direction.DOWN)).equals(Piece.EMPTY);
    }

    protected Stream<Coordinates> findPiecesAlongDirection(List<Direction> directions) {
        return findPiecesAlongDirection(searchOrigin, directions, piece);
    }
}
