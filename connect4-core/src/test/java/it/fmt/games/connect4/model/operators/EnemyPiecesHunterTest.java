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

public class EnemyPiecesHunterTest {

    @Test
    public void failOnInvalidPiece() {
        assertThrows(InvalidPieceSelectedException.class, () -> {
            Connect4Hunter.find(null, of("a1"), Piece.EMPTY);
        });

        assertThrows(InvalidPieceSelectedException.class, () -> {
            Connect4Hunter.find(null, of("a1"), null);
        });
    }

    @Test
    public void find00() throws Exception {
        assertThrows(InvalidPieceSelectedException.class, () -> {
            Connect4Hunter.find(null, of("a1"), Piece.EMPTY);
        });
    }

    @Test
    public void find01() throws Exception {
        checkEnemyPieceFinderForPlayer1(of("d1"), "enemy_pieces_to_capture_finder01", of("c1"));
    }

    @Test
    public void find02() throws Exception {
        checkEnemyPieceFinderForPlayer1(of("d1"), "enemy_pieces_to_capture_finder02");
    }

    @Test
    public void find03() throws Exception {
        checkEnemyPieceFinderForPlayer1(of("c4"), "enemy_pieces_to_capture_finder03", of("d4"));
    }

    @Test
    public void find03Player2() throws Exception {
        checkEnemyPieceFinderForPlayer2(of("f4"), "enemy_pieces_to_capture_finder03", of("e4"));
    }

    @Test
    public void find04() throws Exception {
        checkEnemyPieceFinderForPlayer1(of("c4"), "enemy_pieces_to_capture_finder04", of("c3"), of("d4"));
    }

    @Test
    public void find05() throws Exception {
        checkEnemyPieceFinderForPlayer1(of("c4"), "enemy_pieces_to_capture_finder05", of("c2"), of("c3"), of("d4"));
    }

    @Test
    public void find06() throws Exception {
        checkEnemyPieceFinderForPlayer1(of("c4"), "enemy_pieces_to_capture_finder06", of("c2"), of("c3"), of("d3"), of("d4"), of("e2"));
    }

    private void checkEnemyPieceFinderForPlayer1(Coordinates newMoveCoordinate, String fileName, Coordinates... coordinates) throws Exception {
        checkEnemyPieceFinder(Piece.PLAYER_1, newMoveCoordinate, fileName, coordinates);
    }

    private void checkEnemyPieceFinderForPlayer2(Coordinates newMoveCoordinate, String fileName, Coordinates... coordinates) throws Exception {
        checkEnemyPieceFinder(Piece.PLAYER_2, newMoveCoordinate, fileName, coordinates);
    }

    private void checkEnemyPieceFinder(Piece activePiece, Coordinates newMoveCoordinate, String fileName, Coordinates... coordinates) throws Exception {
        List<Coordinates> aspectedResult0 = Arrays.asList(coordinates);
        Board board = BoardReader.read(fileName);
        List<Coordinates> capturedPiecesCoords = Connect4Hunter.find(board, newMoveCoordinate, activePiece);
        assertThat(capturedPiecesCoords.size(), equalTo(aspectedResult0.size()));
        assertEquals(capturedPiecesCoords, aspectedResult0);
    }
}