package it.fmt.games.connect4.model;

import it.fmt.games.connect4.model.operators.AvailableMovesFinder;
import it.fmt.games.connect4.support.BoardReader;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AvailableMovesTest {

    @Test
    public void allPlayersCanMove() throws Exception {
        AvailableMoves availableMoves = readAndCheck("available_moves00", true);
        assertEquals(7, availableMoves.getAvailablePositions().size());
    }

    @Test
    public void noMovesForPlayers() throws Exception {
        AvailableMoves availableMoves = readAndCheck("available_moves02", false);
        assertThat(availableMoves.getAvailablePositions().size(), is(0));
    }

    @Test
    public void availableMovesForAllPlayers() throws Exception {
        AvailableMoves availableMoves = readAndCheck("available_moves03", true);
        assertThat(availableMoves.getAvailablePositions().size(), is(4));
    }

    @Test
    public void testEquals() throws Exception {
        AvailableMoves availableMoves1 = readAvailableMoves("available_moves03");
        // AvailableMoves availableMoves2 = readAvailableMoves("available_moves03");
        // AvailableMoves availableMoves3 = readAvailableMoves("available_moves02");

        assertThat(availableMoves1.equals(availableMoves1), is(true));
        // assertThat(availableMoves1 == null, is(false));
        // assertThat(availableMoves1.equals("dummy"), is(false));
        // assertThat(availableMoves1.equals(availableMoves2), is(false));
        // assertThat(availableMoves3.equals(availableMoves3), is(true));
        // assertThat(availableMoves1.hashCode() == availableMoves2.hashCode(), is(true));
    }

    @Test
    public void testHashCode() {
        Coordinates coords1 = Coordinates.of("A1");
        Coordinates coords2 = Coordinates.of("a1");

        assertThat(coords1.hashCode(), equalTo(coords2.hashCode()));
    }

    private AvailableMoves readAvailableMoves(String fileName) throws Exception {
        Board board = BoardReader.read(fileName);

        List<Coordinates> availableMovesForPlayer1 = AvailableMovesFinder.findPositions(board, Piece.PLAYER_1);

        return new AvailableMoves(Piece.PLAYER_1, availableMovesForPlayer1);
    }

    private AvailableMoves readAndCheck(String available_moves01, boolean isAvailableMoves) throws Exception {
        AvailableMoves availableMoves = readAvailableMoves(available_moves01);
        assertThat(availableMoves.isAnyAvailableMoves(), is(isAvailableMoves));
        return availableMoves;
    }

}
