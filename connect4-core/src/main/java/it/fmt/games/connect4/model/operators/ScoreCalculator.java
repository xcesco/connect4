package it.fmt.games.connect4.model.operators;

import it.fmt.games.connect4.model.Board;
import it.fmt.games.connect4.model.Piece;
import it.fmt.games.connect4.model.Score;

public abstract class ScoreCalculator {

    private ScoreCalculator() {

    }

    public static Score computeScore(Board board) {
        int[] score = new int[Piece.values().length];
        board.getCellStream().forEach(cell -> score[cell.getPiece().ordinal()]++);
        return new Score(score[Piece.PLAYER_1.ordinal()], score[Piece.PLAYER_2.ordinal()]);
    }

}
