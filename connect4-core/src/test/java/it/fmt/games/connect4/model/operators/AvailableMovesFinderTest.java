package it.fmt.games.connect4.model.operators;

import it.fmt.games.connect4.exceptions.InvalidBoardStatusException;
import it.fmt.games.connect4.exceptions.InvalidPieceSelectedException;
import it.fmt.games.connect4.model.Board;
import it.fmt.games.connect4.model.Coordinates;
import it.fmt.games.connect4.model.Piece;
import it.fmt.games.connect4.support.BoardReader;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static it.fmt.games.connect4.model.Coordinates.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AvailableMovesFinderTest {

    @Test
    public void invalidPieceSelected() throws Exception {
        checkAvailableMovesFinder("available_moves_finder00", of("a1"));
    }

    @Test
    public void testStartConfigBoard() throws Exception {
        checkAvailableMovesFinder("available_moves_finder00", of("a6"), of("b6"), of("c6"), of("d3"), of("e3"), of("f6"), of("g6"));
    }

    @Test
    public void testAvailableMoves1() throws Exception {
        checkAvailableMovesFinder("available_moves_finder01", of("a6"), of("C2"), of("d3"), of("e3"), of("F6"), of("g6"));
    }

    @Test
    public void testAvailableMoves2() throws Exception {
        checkAvailableMovesFinder("available_moves_finder02", of("b3"), of("c2"), of("d1"), of("e2"), of("f3"));
    }

    private void checkAvailableMovesFinder(String fileName, Coordinates... coordinates) throws Exception {
        Board board = BoardReader.read(fileName);
        if (!ValidBoardChecker.isValidBoardInState(board)) {
            throw new InvalidBoardStatusException(board);
        }
        List<Coordinates> availableMovesForPlayer = AvailableMovesFinder.findAvailableMoves(board);
        List<Coordinates> aspectedResult = Arrays.asList(coordinates);
        assertThat(availableMovesForPlayer.size(), equalTo(aspectedResult.size()));
        assertEquals(availableMovesForPlayer, aspectedResult);
    }
}
