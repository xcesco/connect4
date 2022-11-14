package it.fmt.games.connect4;

import it.fmt.games.connect4.model.Board;
import it.fmt.games.connect4.model.Coordinates;
import it.fmt.games.connect4.model.Piece;
import it.fmt.games.connect4.model.PlayerMove;
import it.fmt.games.connect4.model.operators.AvailableMovesFinder;
import it.fmt.games.connect4.model.operators.PiecesAroundFinder;

import java.util.Comparator;
import java.util.List;

public class MoveEvaluator {
    public static MoveScore evaluate(Board board, PlayerMove playerMove) {
        int score=PiecesAroundFinder.findBestDirection(board, playerMove.getMoveCoords(), playerMove.getPiece()).size()+1;
        return new MoveScore(playerMove, score);
    }

    public static MoveScore findBestMove(Board board, Piece piece) {
        List<Coordinates> availableMoves = AvailableMovesFinder.findAvailableMoves(board, piece);

        MoveScore bestScore = availableMoves.stream()
                .map(move -> MoveEvaluator.evaluate(board, PlayerMove.create(piece, move)))
                .max(Comparator.comparingInt(MoveScore::getScore)).orElse(null);

        return bestScore;
    }
}
