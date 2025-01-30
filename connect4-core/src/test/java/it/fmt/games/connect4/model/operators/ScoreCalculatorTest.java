package it.fmt.games.connect4.model.operators;

import it.fmt.games.connect4.model.Board;
import it.fmt.games.connect4.model.Coordinates;
import it.fmt.games.connect4.model.Piece;
import it.fmt.games.connect4.model.Score;
import it.fmt.games.connect4.support.BoardReader;
import org.junit.jupiter.api.Test;

import static it.fmt.games.connect4.model.Coordinates.of;
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

    @Test
    public void onePiecePlayer1_01() {
        Board board = new Board();
        board.setCell(of("a1"), Piece.PLAYER_1);
        board.setCell(of("b1"), Piece.PLAYER_1);
        board.setCell(of("c1"), Piece.PLAYER_1);
        board.setCell(of("d1"), Piece.PLAYER_1);

        Score score = ScoreCalculator.calculateScore(board, of("a1"), Piece.PLAYER_1);

        assertThat(score.getPlayer1Score(), equalTo(1));
        assertThat(score.getPlayer2Score(), equalTo(0));
    }

    @Test
    public void onePiecePlayer1_02() {
        Board board = new Board();
        board.setCell(of("a1"), Piece.PLAYER_1);
        board.setCell(of("a2"), Piece.PLAYER_1);
        board.setCell(of("a3"), Piece.PLAYER_1);
        board.setCell(of("a4"), Piece.PLAYER_1);

        Score score = ScoreCalculator.calculateScore(board, of("a1"), Piece.PLAYER_1);

        assertThat(score.getPlayer1Score(), equalTo(1));
        assertThat(score.getPlayer2Score(), equalTo(0));
    }

    @Test
    public void onePiecePlayer1_03() {
        Board board = new Board();
        board.setCell(of("a1"), Piece.PLAYER_1);
        board.setCell(of("b2"), Piece.PLAYER_1);
        board.setCell(of("c3"), Piece.PLAYER_1);
        board.setCell(of("d4"), Piece.PLAYER_1);

        Score score = ScoreCalculator.calculateScore(board, of("a1"), Piece.PLAYER_1);

        assertThat(score.getPlayer1Score(), equalTo(1));
        assertThat(score.getPlayer2Score(), equalTo(0));
    }

    @Test
    public void onePiecePlayer1_04() {
        Board board = new Board();
        board.setCell(of("a1"), Piece.PLAYER_1);
        board.setCell(of("b2"), Piece.PLAYER_1);
        board.setCell(of("c3"), Piece.PLAYER_1);

        Score score = ScoreCalculator.calculateScore(board, of("a1"), Piece.PLAYER_1);

        assertThat(score.getPlayer1Score(), equalTo(0));
        assertThat(score.getPlayer2Score(), equalTo(0));
    }

    @Test
    public void onePiecePlayer1_05() {
        Board board = new Board();
        board.setCell(of("a6"), Piece.PLAYER_2);
        board.setCell(of("b5"), Piece.PLAYER_2);
        board.setCell(of("c4"), Piece.PLAYER_2);
        board.setCell(of("d3"), Piece.PLAYER_2);

        Score score = ScoreCalculator.calculateScore(board, of("c4"), Piece.PLAYER_2);

        assertThat(score.getPlayer1Score(), equalTo(0));
        assertThat(score.getPlayer2Score(), equalTo(1));
    }

    @Test
    public void onePiecePlayer1_06() {
        Board board = new Board();
        board.setCell(of("b6"), Piece.PLAYER_2);
        board.setCell(of("c6"), Piece.PLAYER_2);
        board.setCell(of("d6"), Piece.PLAYER_2);
        board.setCell(of("e6"), Piece.PLAYER_2);

        Score score = ScoreCalculator.calculateScore(board, of("d6"), Piece.PLAYER_2);

        assertThat(score.getPlayer1Score(), equalTo(0));
        assertThat(score.getPlayer2Score(), equalTo(1));
    }

    @Test
    public void onePiecePlayer2_07() {
        Board board = new Board();
        board.setCell(of("a3"), Piece.PLAYER_2);
        board.setCell(of("a2"), Piece.PLAYER_2);
        board.setCell(of("a1"), Piece.PLAYER_2);
        board.setCell(of("a4"), Piece.PLAYER_2);

        Score score = ScoreCalculator.calculateScore(board, of("a1"), Piece.PLAYER_2);

        assertThat(score.getPlayer1Score(), equalTo(0));
        assertThat(score.getPlayer2Score(), equalTo(1));
    }

    @Test
    public void onePiecePlayer2_08() {
        Board board = new Board();
        board.setCell(of("a3"), Piece.PLAYER_2);
        board.setCell(of("a2"), Piece.PLAYER_2);
        board.setCell(of("a1"), Piece.PLAYER_2);
        board.setCell(of("a4"), Piece.PLAYER_2);

        Score score = ScoreCalculator.calculateScore(board, of("a2"), Piece.PLAYER_2);

        assertThat(score.getPlayer1Score(), equalTo(0));
        assertThat(score.getPlayer2Score(), equalTo(0));
    }

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
            board.setCell(of("a9"), Piece.PLAYER_2);
        });
    }
}