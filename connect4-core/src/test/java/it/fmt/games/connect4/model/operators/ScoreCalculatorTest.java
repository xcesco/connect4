package it.fmt.games.connect4.model.operators;

import it.fmt.games.connect4.model.Board;
import it.fmt.games.connect4.model.Coordinates;
import it.fmt.games.connect4.model.Piece;
import it.fmt.games.connect4.model.Score;
import it.fmt.games.connect4.support.BoardReader;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ScoreCalculatorTest {

    @Test
    public void noScore() {
        Board board = new Board();

        Score score = ScoreCalculator.calculateScore(board, null, Piece.PLAYER_1);

        assertThat(score.getPlayer1Score(), equalTo(0));
        assertThat(score.getPlayer2Score(), equalTo(0));
    }

//    @Test
//    public void onePiecePlayer1() {
//        Board board = new Board();
//        board.setCell(Coordinates.of("a1"), Piece.PLAYER_1);
//
//        Score score = ScoreCalculator.calculateScore(board, null, Piece.PLAYER_1);
//
//        assertThat(score.getPlayer1Score(), equalTo(1));
//        assertThat(score.getPlayer2Score(), equalTo(0));
//    }

//    @Test
//    public void onePiecePlayer2() {
//        Board board = new Board();
//        board.setCell(Coordinates.of("d5"), Piece.PLAYER_2);
//
//        Score score = ScoreCalculator.calculateScore(board, null, Piece.PLAYER_2);
//
//        assertThat(score.getPlayer1Score(), equalTo(0));
//        assertThat(score.getPlayer2Score(), equalTo(0));
//    }

//    @Test
//    public void readPieces() throws Exception {
//        Board board = BoardReader.read("boardScoreTest1");
//
//        Score score = ScoreCalculator.calculateScore(board, moveCoords, currentPiece);
//
//        assertThat(score.getPlayer1Score(), equalTo(1));
//        assertThat(score.getPlayer2Score(), equalTo(3));
//    }

//    @Test
//    public void readConsecutiveAndAlternatePieces() throws Exception {
//        Board board = BoardReader.read("boardScoreTest2");
//
//        Score score = ScoreCalculator.calculateScore(board, moveCoords, currentPiece);
//
//        assertThat(score.getPlayer1Score(), equalTo(8));
//        assertThat(score.getPlayer2Score(), equalTo(10));
//    }

    @Test
    public void invalidCoordinates() {
        Board board = new Board();

        assertThrows(RuntimeException.class, () -> {
            board.setCell(Coordinates.of("a9"), Piece.PLAYER_2);
        });
    }
}