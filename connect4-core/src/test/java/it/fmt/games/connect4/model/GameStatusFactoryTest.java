package it.fmt.games.connect4.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static it.fmt.games.connect4.model.Coordinates.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GameStatusFactoryTest {

    @Test
    public void createRunningStatus() {
        checkStatus(Arrays.asList(of("a1")), Collections.emptyList(), 12, 24, false, GameStatus.RUNNING);
    }

    @Test
    public void createPlayer1Wins() {
        checkStatus(Collections.emptyList(), Collections.emptyList(), 36, 24, true, GameStatus.PLAYER1_WIN);
    }

    @Test
    public void createPlayer2Wins() {
        checkStatus(Collections.emptyList(), Collections.emptyList(), 12, 24, true, GameStatus.PLAYER2_WIN);
    }

    @Test
    public void createDraw() {
        checkStatus(Collections.emptyList(), Collections.emptyList(), 12, 12, true, GameStatus.DRAW);
    }

    @Test
    public void emptyAvailableMoves() {
        GameStatus gameStatus = GameStatusFactory.create(null, new Score(12, 4));
        assertThat(gameStatus.isGameOver(), is(false));
        assertThat(gameStatus, is(GameStatus.RUNNING));
    }


    private void checkStatus(List<Coordinates> player1AvailMoves, List<Coordinates> player2AvailMoves, int player1Score, int player2Score, boolean finished, GameStatus aspectedStatus) {
        GameStatus gameStatus = GameStatusFactory.create(new AvailableMoves(Piece.PLAYER_1, player1AvailMoves), new Score(player1Score, player2Score));
        assertThat(gameStatus.isGameOver(), is(finished));
        assertThat(gameStatus, is(aspectedStatus));
    }
}