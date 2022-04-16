package it.fmt.games.connect4;

import it.fmt.games.connect4.model.Board;
import it.fmt.games.connect4.model.GameLogic;
import it.fmt.games.connect4.model.GameSnapshot;
import it.fmt.games.connect4.model.GameStatus;
import it.fmt.games.connect4.support.*;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class Connect4Test {
    @Test
    public void checkGameSnapshot() {
        Connect4 connect4 = new Connect4(gameSnapshot -> {
            assertThat(gameSnapshot, notNullValue());
            assertThat(gameSnapshot.getStatus(), notNullValue());
            assertThat(gameSnapshot.getScore(), notNullValue());
            assertThat(gameSnapshot.getActivePiece(), notNullValue());
            assertThat(gameSnapshot.getAvailableMoves(), notNullValue());
            assertThat(gameSnapshot.getAvailableMoves().getMovesActivePlayer(), notNullValue());
            assertThat(gameSnapshot.getAvailableMoves().getMovesOtherPlayer(), notNullValue());
            assertThat(gameSnapshot.getBoard(), notNullValue());
            assertThat(gameSnapshot.getStatus(), notNullValue());
        }, new TakeFirstUserInputReader(), PlayerFactory.createCpuPlayer1(), PlayerFactory.createCpuPlayer2());
        GameSnapshot result = connect4.play();

        assertThat(result.getStatus().isGameOver(), is(true));
    }

    @Test
    public void playCpuVsCpu() throws Exception {
        Connect4 connect4 = new Connect4(new DummyRenderer(), new TakeFirstUserInputReader(), PlayerFactory.createCpuPlayer1(),
                PlayerFactory.createCpuPlayer2());
        GameSnapshot result = connect4.play();
        checkEndGame(result, "connect4_final_state00", GameStatus.PLAYER2_WIN, 19, 45);
    }

    @Test
    public void playP1VsCpu() throws Exception {
        Connect4 connect4 = new Connect4(new DummyRenderer(), new TakeFirstUserInputReader(), PlayerFactory.createHumanPlayer1(),
                PlayerFactory.createCpuPlayer2());
        GameSnapshot result = connect4.play();

        checkEndGame(result, "connect4_final_state00", GameStatus.PLAYER2_WIN, 19, 45);
    }

    @Test
    public void draw() throws Exception {
        GameLogic gameLogic = new GameLogicForTest(BoardReader.read("connect4_draw"), PlayerFactory.createHumanPlayer1(),
                PlayerFactory.createCpuPlayer2(), new TakeFirstUserInputReader());
        Connect4 connect4 = new Connect4(new DummyRenderer(), gameLogic);
        GameSnapshot result = connect4.play();

        checkEndGame(result, "connect4_draw", GameStatus.DRAW, 0, 0);
    }

    @Test
    public void player1Wins() throws Exception {
        GameLogic gameLogic = new GameLogicForTest(BoardReader.read("connect4_player1_wins_start"), PlayerFactory.createHumanPlayer1(),
                PlayerFactory.createCpuPlayer2(), new TakeFirstUserInputReader());
        Connect4 connect4 = new Connect4(new DummyRenderer(), gameLogic);
        GameSnapshot result = connect4.play();

        checkEndGame(result, "connect4_player1_wins_end", GameStatus.PLAYER1_WIN, 3, 0);
    }

    @Test
    public void sometimesUserInsertBadInput() throws Exception {
        GameLogic gameLogic = new GameLogicForTest(BoardReader.read("connect4_player1_wins_start"), PlayerFactory.createHumanPlayer1(),
                PlayerFactory.createCpuPlayer2(), new SomeInvalidUserInputReader());
        Connect4 connect4 = new Connect4(new DummyRenderer(), gameLogic);
        GameSnapshot result = connect4.play();

        checkEndGame(result, "connect4_player1_wins_end", GameStatus.PLAYER1_WIN, 3, 0);
    }

    @Test
    public void player2Wins() throws Exception {
        GameLogic gameLogic = new GameLogicForTest(BoardReader.read("connect4_player2_wins_start"), PlayerFactory.createHumanPlayer1(),
                PlayerFactory.createCpuPlayer2(), new TakeFirstUserInputReader());
        Connect4 connect4 = new Connect4(new DummyRenderer(), gameLogic);
        GameSnapshot result = connect4.play();

        checkEndGame(result, "connect4_player2_wins_end", GameStatus.PLAYER2_WIN, 0, 3);
    }

    private void checkEndGame(GameSnapshot result, String finalStateFilename, GameStatus gameStatus, int scorePlayer1, int scorePlayer2) throws Exception {
        Board board = BoardReader.read(finalStateFilename);
        assertThat(result.getBoard().equals(board), is(true));
        assertThat(result.getStatus().isGameOver(), is(true));
        assertThat(result.getStatus(), is(gameStatus));
        assertThat(result.getScore().getPlayer1Score(), is(scorePlayer1));
        assertThat(result.getScore().getPlayer2Score(), is(scorePlayer2));
    }
}