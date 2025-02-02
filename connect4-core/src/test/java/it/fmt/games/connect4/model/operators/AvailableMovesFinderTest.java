package it.fmt.games.connect4.model.operators;

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
    public void invalidPieceSelected() {
        assertThrows(InvalidPieceSelectedException.class, () -> {
            checkAvailableMovesFinder("available_moves_finder00", Piece.EMPTY, of("a1"));
        });
    }

    @Test
    public void testStartConfigBoard() throws Exception {
        checkAvailableMovesFinder("available_moves_finder00", Piece.PLAYER_1, of("D3"), of("C4"), of("F5"), of("E6"));
        checkAvailableMovesFinder("available_moves_finder00", Piece.PLAYER_2, of("E3"), of("F4"), of("C5"), of("D6"));
    }

    @Test
    public void testAvailableMoves1() throws Exception {
        checkAvailableMovesFinder("available_moves_finder01", Piece.PLAYER_1, of("D3"), of("C4"), of("F5"), of("E6"), of("F6"));
        checkAvailableMovesFinder("available_moves_finder01", Piece.PLAYER_2, of("A1"), of("E3"), of("F4"), of("C5"), of("D6"));
    }

    @Test
    public void testAvailableMoves2() throws Exception {
        checkAvailableMovesFinder("available_moves_finder02", Piece.PLAYER_1, of("C6"), of("D6"), of("E6"));
        checkAvailableMovesFinder("available_moves_finder02", Piece.PLAYER_2, of("D1"), of("B2"), of("F2"), of("B3"),
                of("F3"), of("A4"), of("G4"), of("B5"), of("F5"), of("B6"), of("F6"));
    }

    private void checkAvailableMovesFinder(String fileName, Piece piece, Coordinates... coordinates) throws Exception {
        Board board = BoardReader.read(fileName);
        List<Coordinates> availableMovesForPlayer = AvailableMovesFinder.findPositions(board, piece);
        List<Coordinates> aspectedResult = Arrays.asList(coordinates);
        assertThat(availableMovesForPlayer.size(), equalTo(aspectedResult.size()));
        assertEquals(availableMovesForPlayer, aspectedResult);
    }
}
