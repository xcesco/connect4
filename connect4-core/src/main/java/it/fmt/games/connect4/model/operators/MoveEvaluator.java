package it.fmt.games.connect4.model.operators;

import it.fmt.games.connect4.model.Board;
import it.fmt.games.connect4.model.Coordinates;
import it.fmt.games.connect4.model.Piece;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MoveEvaluator extends AbstractBoardOperator {

    private MoveEvaluator(Board board, Coordinates coords, Piece piece) {
        super(board, piece, coords);
    }

    public static List<Coordinates> findRow(Board board, Coordinates newMoveCoords, Piece piece) {
        MoveEvaluator hunter = new MoveEvaluator(board, newMoveCoords, piece);
        return hunter.findRow();
    }

    private List<Coordinates> findRow() {
        return directions.stream()
                .map(this::findPiecesAlongDirection)
                .filter(row -> row.count() >= 4)
                .flatMap(Function.identity())
                .collect(Collectors.toList());
    }
}