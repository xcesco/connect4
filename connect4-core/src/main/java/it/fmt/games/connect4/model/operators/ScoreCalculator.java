package it.fmt.games.connect4.model.operators;

import it.fmt.games.connect4.Pair;
import it.fmt.games.connect4.model.Board;
import it.fmt.games.connect4.model.Coordinates;
import it.fmt.games.connect4.model.Piece;
import it.fmt.games.connect4.model.Score;

public class ScoreCalculator extends AbstractCellOperator {

    private ScoreCalculator(Board board, Coordinates coords, Piece piece) {
        super(board, piece, coords);
    }

    public static Score calculateScore(Board board, Coordinates evaluatedNextMove, Piece piece) {
        ScoreCalculator calculator = new ScoreCalculator(board, evaluatedNextMove, piece);
        return calculator.calculateScore();
    }

    public Score calculateScore() {
        if (this.searchOrigin == null) {
            return new Score(0, 0);
        } else {
            int score = score() >= 4 ? 1 : 0;
            return new Score(this.piece == Piece.PLAYER_1 ? score : 0, this.piece == Piece.PLAYER_2 ? score : 0);
        }
    }

    private int score() {
        return directions.stream()
                .map(this::findPiecesAlongDirection)
                .mapToInt(Pair::getValue2)
                .max().orElse(0);
    }

}
