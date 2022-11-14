package it.fmt.games.connect4.model;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GameScoreTest {

    @Test
    public void playersScoresTest() {
        GameScore scores = new GameScore(6, 7);

        assertThat(scores.getPlayer1Score(), is(6));
        assertThat(scores.getPlayer2Score(), is(7));
    }

    @Test
    public void playersScoresEqualsAndHashcodeTest() {
        GameScore gameScore1 = new GameScore(6, 7);
        GameScore gameScore2 = new GameScore(6, 7);
        GameScore gameScore3 = new GameScore(16, 27);
        GameScore gameScore4 = new GameScore(6, 27);

        assertThat(gameScore1.equals(gameScore1), is(true));
        assertThat(gameScore1.equals(gameScore3), is(false));
        assertThat(gameScore1.equals(gameScore4), is(false));
        assertThat(gameScore1.equals(null), is(false));
        assertThat(gameScore1.equals("dummy"), is(false));
        assertThat(gameScore1.equals(gameScore2), is(true));
        assertThat(gameScore1.hashCode() == gameScore2.hashCode(), is(true));
    }

}
