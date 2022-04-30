package it.fmt.games.connect4.model.operators;

import it.fmt.games.connect4.model.Board;
import it.fmt.games.connect4.model.Coordinates;
import it.fmt.games.connect4.model.Direction;
import it.fmt.games.connect4.model.Piece;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PieceFinder extends AbstractBoardOperator {
    private final Coordinates searchOrigin;

    private PieceFinder(Board board, Coordinates coords, Piece piece) {
        super(board, piece);
        this.searchOrigin = coords;
    }

    public static List<Coordinates> find(Board board, Coordinates newMoveCoords, Piece piece) {
        PieceFinder hunter = new PieceFinder(board, newMoveCoords, piece);
        return hunter.find();
    }

    private List<Coordinates> find() {
        return Stream.of(Direction.values()).filter(this::isAnyPieceAlongDirection)
                .flatMap(this::findPiecesAlongDirection).sorted()
                .collect(Collectors.toList());
    }

    private boolean isAnyPieceAlongDirection(Direction direction) {
        //return  isLowerCellFilled(searchOrigin, direction);
        return true;
    }

    private Stream<Coordinates> findPiecesAlongDirection(Direction direction) {
        return findPiecesAlongDirection(searchOrigin, direction, piece);
    }
}