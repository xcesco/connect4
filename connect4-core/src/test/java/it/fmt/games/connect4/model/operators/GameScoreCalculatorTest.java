package it.fmt.games.connect4.model.operators;

import it.fmt.games.connect4.model.Board;
import it.fmt.games.connect4.model.Coordinates;
import it.fmt.games.connect4.model.Piece;
import it.fmt.games.connect4.model.GameScore;
import it.fmt.games.connect4.support.BoardReader;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameScoreCalculatorTest {

    @Test
    public void noScore() {
        Board board = new Board();

        GameScore gameScore = ScoreCalculator.computeScore(board);

        assertThat(gameScore.getPlayer1Score(), equalTo(0));
        assertThat(gameScore.getPlayer2Score(), equalTo(0));
    }

    @Test
    public void onePiecePlayer1() {
        Board board = new Board();
        board.setCell(Coordinates.of("a1"), Piece.PLAYER_1);

        GameScore gameScore = ScoreCalculator.computeScore(board);

        assertThat(gameScore.getPlayer1Score(), equalTo(1));
        assertThat(gameScore.getPlayer2Score(), equalTo(0));
    }

    @Test
    public void onePiecePlayer2() {
        Board board = new Board();
        board.setCell(Coordinates.of("d5"), Piece.PLAYER_2);

        GameScore gameScore = ScoreCalculator.computeScore(board);

        assertThat(gameScore.getPlayer1Score(), equalTo(0));
        assertThat(gameScore.getPlayer2Score(), equalTo(1));
    }

    @Test
    public void readPieces() throws Exception {
        Board board = BoardReader.read("boardScoreTest1");

        GameScore gameScore = ScoreCalculator.computeScore(board);

        assertThat(gameScore.getPlayer1Score(), equalTo(1));
        assertThat(gameScore.getPlayer2Score(), equalTo(3));
    }

    @Test
    public void readConsecutiveAndAlternatePieces() throws Exception {
        Board board = BoardReader.read("boardScoreTest2");

        GameScore gameScore = ScoreCalculator.computeScore(board);

        assertThat(gameScore.getPlayer1Score(), equalTo(8));
        assertThat(gameScore.getPlayer2Score(), equalTo(10));
    }

    @Test
    public void invalidCoordinates() {
        Board board = new Board();

        assertThrows(RuntimeException.class, () -> {
            board.setCell(Coordinates.of("a9"), Piece.PLAYER_2);
        });
    }
}