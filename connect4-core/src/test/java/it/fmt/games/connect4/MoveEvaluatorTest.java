package it.fmt.games.connect4;

import it.fmt.games.connect4.model.Board;
import it.fmt.games.connect4.model.Coordinates;
import it.fmt.games.connect4.model.Piece;
import it.fmt.games.connect4.model.PlayerMove;
import it.fmt.games.connect4.model.operators.AvailableMovesFinder;
import it.fmt.games.connect4.support.BoardBuilder;
import it.fmt.games.connect4.support.BoardPrinter;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static it.fmt.games.connect4.model.Coordinates.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class MoveEvaluatorTest {

    @Test
    void evaluateNoElement() {
        Board board = BoardBuilder.create().build();
        MoveScore score = MoveEvaluator.evaluate(board, PlayerMove.create(Piece.PLAYER_1, of("A6")));
        assertThat(score.getScore(), is(1));
        assertThat(score.getPlayerMove().getMoveCoords(), is(of("a6")));
        assertThat(score.getPlayerMove().getPiece(), is(Piece.PLAYER_1));
        BoardPrinter.print(board, score);
    }

    @Test
    void evaluate1Element() {
        Board board = BoardBuilder.create()
                .move(Piece.PLAYER_1, of("A6"))
                .build();
        MoveScore score = MoveEvaluator.evaluate(board, PlayerMove.create(Piece.PLAYER_1, of("A5")));
        assertThat(score.getScore(), is(2));
        assertThat(score.getPlayerMove().getMoveCoords(), is(of("a5")));
        assertThat(score.getPlayerMove().getPiece(), is(Piece.PLAYER_1));
        BoardPrinter.print(board, score);
    }

    @Test
    void evaluate2Element() {
        Board board = BoardBuilder.create()
                .move(Piece.PLAYER_1, of("A6"))
                .move(Piece.PLAYER_1, of("A5"))
                .build();
        MoveScore score3 = MoveEvaluator.evaluate(board, PlayerMove.create(Piece.PLAYER_1, of("A4")));
        assertThat(score3.getScore(), is(3));

        MoveScore score1 = MoveEvaluator.evaluate(board, PlayerMove.create(Piece.PLAYER_1, of("B6")));
        assertThat(score1.getScore(), is(2));

        assertThat(score1.getPlayerMove().getMoveCoords(), is(of("b6")));
        assertThat(score1.getPlayerMove().getPiece(), is(Piece.PLAYER_1));
        BoardPrinter.print(board, score3, score1);
    }

    @Test
    void evaluateAllAvailableMoves() {
        Board board = BoardBuilder.create()
                .move(Piece.PLAYER_1, of("A6"))
                .move(Piece.PLAYER_1, of("A5"))
                .build();

        List<Coordinates> availableMoves = AvailableMovesFinder.findAvailableMoves(board, Piece.PLAYER_1);

        List<MoveScore> scores = availableMoves.stream().map(move -> MoveEvaluator.evaluate(board, PlayerMove.create(Piece.PLAYER_1, move))).collect(Collectors.toList());
        BoardPrinter.print(board, scores);

        assertThat(scores.get(0).getPlayerMove().getMoveCoords(), is(of("a4")));
        assertThat(scores.get(0).getScore(), is(3));
        assertThat(scores.get(1).getPlayerMove().getMoveCoords(), is(of("b6")));
        assertThat(scores.get(1).getScore(), is(2));
        assertThat(scores.get(6).getPlayerMove().getMoveCoords(), is(of("g6")));
        assertThat(scores.get(6).getScore(), is(1));
    }

    @Test
    void evaluateBestAvailableMoves() {
        Board board = BoardBuilder.create()
                .move(Piece.PLAYER_1, of("A6"))
                .move(Piece.PLAYER_1, of("A5"))
                .build();

        MoveScore bestScore = MoveEvaluator.findBestMove(board, Piece.PLAYER_1);
        assertThat(bestScore.getPlayerMove().getMoveCoords(), is(of("a4")));

        Board nextBoard=BoardBuilder.create(board).move(bestScore.getPlayerMove()).build();

        BoardPrinter.print(nextBoard);
    }
}