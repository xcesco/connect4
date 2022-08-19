package it.fmt.games.connect4.model;

import it.fmt.games.connect4.exceptions.InvalidBoardStatusException;
import it.fmt.games.connect4.model.operators.AvailableMovesFinder;
import it.fmt.games.connect4.model.operators.ValidBoardChecker;
import it.fmt.games.connect4.support.BoardReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static it.fmt.games.connect4.model.Coordinates.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AvailableMovesTest {

  @Test
  public void available7MovesForCurrentPlayer01() throws Exception {
    AvailableMoves availableMoves = readAndCheck("available_7_moves01", true, true);
    List<Coordinates> result= List.of(of("A6"), of("B5"), of("C5"), of("D5"), of("E6"), of("F6"), of("G6"));
    Assertions.assertEquals(result, availableMoves.getMovesActivePlayer());
    assertThat(availableMoves.getMovesActivePlayer().size(), is(7));
  }

  @Test
  public void allPlayersCanMove() throws Exception {
    AvailableMoves availableMoves = readAndCheck("available_moves00", true, true);
    assertEquals(7, availableMoves.getMovesActivePlayer().size());
    assertEquals(7, availableMoves.getMovesActivePlayer().size(), availableMoves.getMovesOtherPlayer().size());
    assertEquals(availableMoves.getMovesActivePlayer().size(), availableMoves.getMovesOtherPlayer().size());
  }

  @Test
  public void noMovesForOtherPlayer() throws Exception {
    AvailableMoves availableMoves = readAndCheck("available_moves01", false, false);
    assertThat(availableMoves.getMovesOtherPlayer().size(), is(0));
  }

  @Test
  public void availableMovesForAllPlayers() throws Exception {
    AvailableMoves availableMoves = readAndCheck("available_moves03", true, true);
    assertThat(availableMoves.getMovesActivePlayer().size(), is(7));
    assertThat(availableMoves.getMovesOtherPlayer().size(), is(7));
  }

  @Test
  public void testEquals() throws Exception {
    AvailableMoves availableMoves1 = readAvailableMoves("available_moves01");
    AvailableMoves availableMoves1_1 = readAvailableMoves("available_moves01");
    AvailableMoves availableMoves2 = readAvailableMoves("available_moves03");
    AvailableMoves availableMoves3 = readAvailableMoves("available_moves02");

    assertThat(availableMoves1.getMovesActivePlayer().size(), is(0));
    assertThat(availableMoves2.getMovesActivePlayer().size(), is(7));
    assertThat(availableMoves3.getMovesActivePlayer().size(), is(7));

    assertThat(availableMoves1.equals(availableMoves1_1), is(true));
    assertThat(availableMoves1 == null, is(false));
    assertThat(availableMoves1.equals("dummy"), is(false));
    assertThat(availableMoves1.equals(availableMoves2), is(false));
    assertThat(availableMoves2.equals(availableMoves3), is(true));
    assertThat(availableMoves2.hashCode() == availableMoves3.hashCode(), is(true));
  }

  @Test
  public void testHashCode() {
    Coordinates coords1 = of("A1");
    Coordinates coords2 = of("a1");

    assertThat(coords1.hashCode(), equalTo(coords2.hashCode()));
  }

  private AvailableMoves readAvailableMoves(String fileName) throws Exception {
    Board board = BoardReader.read(fileName);

    if (!ValidBoardChecker.isValidBoardInState(board)) {
      throw new InvalidBoardStatusException(board);
    }

    List<Coordinates> availableMovesForPlayer1 = AvailableMovesFinder.findAvailableMoves(board, Piece.PLAYER_1);
    List<Coordinates> availableMovesForPlayer2 = AvailableMovesFinder.findAvailableMoves(board, Piece.PLAYER_2);

    return new AvailableMoves(availableMovesForPlayer1, availableMovesForPlayer2);
  }

  private AvailableMoves readAndCheck(String available_moves01, boolean isAvailableMoves, boolean isAvailableMovesForActivePlayer) throws Exception {
    AvailableMoves availableMoves = readAvailableMoves(available_moves01);
    assertThat(availableMoves.isAnyAvailableMoves(), is(isAvailableMoves));
    assertThat(availableMoves.isAvailableMovesForActivePlayer(), is(isAvailableMovesForActivePlayer));
    return availableMoves;
  }

}
