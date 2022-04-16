package it.fmt.games.connect4;

import it.fmt.games.connect4.model.Piece;
import it.fmt.games.connect4.model.Player;
import it.fmt.games.connect4.model.cpu.RandomDecisionHandler;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PlayerFactoryTest {

    @Test
    public void createUserPlayer2() {
        Player2 player = PlayerFactory.createHumanPlayer2();
        check(player, Piece.PLAYER_2);
    }

    @Test
    public void createUserPlayer1() {
        Player1 player = PlayerFactory.createHumanPlayer1();
        check(player, Piece.PLAYER_1);
    }

    @Test
    public void createCpuPlayer2() {
        Player2 player = PlayerFactory.createCpuPlayer2();
        check(player, Piece.PLAYER_2);
    }

    @Test
    public void createCpuPlayer2WithDecisionHandler() {
        Player2 player = PlayerFactory.createCpuPlayer2(DecisionHandlerType.RANDOM);
        check(player, Piece.PLAYER_2);
    }

    @Test
    public void createCpuPlayer21() {
        Player2 player = PlayerFactory.createCpuPlayer2(new RandomDecisionHandler());
        check(player, Piece.PLAYER_2);
    }

    @Test
    public void createCpuPlayer1() {
        Player1 player = PlayerFactory.createCpuPlayer1();
        check(player, Piece.PLAYER_1);
    }

    @Test
    public void createCpuPlayer1WithDecisionHandler() {
        Player1 player = PlayerFactory.createCpuPlayer1(DecisionHandlerType.RANDOM);
        check(player, Piece.PLAYER_1);
    }

    @Test
    public void createCpuPlayer1WithDecisionHandler2() {
        Player1 player = PlayerFactory.createCpuPlayer1(new RandomDecisionHandler());
        check(player, Piece.PLAYER_1);
    }

    private void check(Player player, Piece piece) {
        assertThat(player.getPiece(), is(piece));
    }
}