package it.fmt.games.connect4.model.operators;

import it.fmt.games.connect4.exceptions.InvalidPieceSelectedException;
import it.fmt.games.connect4.model.Board;
import it.fmt.games.connect4.model.Coordinates;
import it.fmt.games.connect4.model.Direction;
import it.fmt.games.connect4.model.Piece;

import java.util.stream.Stream;

public abstract class AbstractBoardOperator {
    protected final Piece piece;
    protected final Piece enemyPiece;
    protected final Board board;

    public AbstractBoardOperator(Board board, Piece piece) {
        if (piece == null || piece == Piece.EMPTY) throw (new InvalidPieceSelectedException());
        this.piece = piece;
        this.enemyPiece = piece == Piece.PLAYER_1 ? Piece.PLAYER_2 : Piece.PLAYER_1;
        this.board = board;
    }

    protected Stream<Coordinates> findPiecesAlongDirection(Coordinates coordinates, Direction direction) {
        // done in this way for JDK1.8 compatibility
        Stream.Builder<Coordinates> builder = Stream.builder();
        Coordinates current = coordinates.translate(direction);

        while (board.isCellContentEqualsTo(current, this.enemyPiece)) {
            builder.add(current);
            current = current.translate(direction);
        }
        return builder.build();
    }


    protected boolean isLowerCellFilled(Coordinates initialCoordinates) {
        return !initialCoordinates.translate(Direction.DOWN).isValid() || !board.getCellContent(initialCoordinates.translate(Direction.DOWN)).equals(Piece.EMPTY);
    }
}
