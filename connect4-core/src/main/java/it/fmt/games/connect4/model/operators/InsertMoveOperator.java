package it.fmt.games.connect4.model.operators;

import it.fmt.games.connect4.exceptions.InvalidPlayerMoveException;
import it.fmt.games.connect4.exceptions.NullPieceInsertedException;
import it.fmt.games.connect4.model.Board;
import it.fmt.games.connect4.model.Coordinates;
import it.fmt.games.connect4.model.Piece;
import it.fmt.games.connect4.model.PlayerMove;

import java.util.List;

public abstract class InsertMoveOperator {

    private InsertMoveOperator() {
    }

    public static Board insertMove(Board board, PlayerMove move) {
        if (move==null)  {
            throw new NullPieceInsertedException();
        }
        return insertMove(board, move.getPiece(), move.getMoveCoords());
    }

    public static Board insertMove(Board board, Piece piece, Coordinates coordinates) {
        if (piece == null || piece == Piece.EMPTY) throw new NullPieceInsertedException();

        List<Coordinates> availableMoves = AvailableMovesFinder.findAvailableMoves(board, piece);
        if (!availableMoves.contains(coordinates)) {
            throw new InvalidPlayerMoveException(coordinates);
        }

        Board boardResult = board.copy();
        boardResult.setCell(coordinates, piece);
        return boardResult;
    }
}
