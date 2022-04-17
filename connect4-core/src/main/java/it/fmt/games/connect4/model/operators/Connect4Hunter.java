package it.fmt.games.connect4.model.operators;

import it.fmt.games.connect4.model.Board;
import it.fmt.games.connect4.model.Coordinates;
import it.fmt.games.connect4.model.Direction;
import it.fmt.games.connect4.model.Piece;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Connect4Hunter extends AbstractBoardOperator {
    private final Coordinates searchOrigin;

    private Connect4Hunter(Board board, Coordinates coords, Piece piece) {
        super(board, piece);
        this.searchOrigin = coords;
    }

    public static List<Coordinates> find(Board board, Coordinates newMoveCoords, Piece piece) {
        Connect4Hunter hunter = new Connect4Hunter(board, newMoveCoords, piece);
        return hunter.find();
    }

    private List<Coordinates> find() {
        return Stream.of(Direction.values()).filter(this::isAnyPieceToInvertAlongDirection)
                .map(this::findEnemyPiecesAlongDirection).flatMap(x -> x).sorted()
                .collect(Collectors.toList());
    }

    private boolean isAnyPieceToInvertAlongDirection(Direction direction) {
        return false;
        //return isLowerCellFilled(searchOrigin, direction);
    }

    private Stream<Coordinates> findEnemyPiecesAlongDirection(Direction direction) {
        return findPiecesAlongDirection(searchOrigin, direction);
    }
}