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
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PiecesAroundFinderTest {

    @Test
    public void failOnInvalidPiece() {
        assertThrows(InvalidPieceSelectedException.class, () -> {
            PiecesAroundFinder.findBestDirection(null, of("a1"), Piece.EMPTY);
        });

        assertThrows(InvalidPieceSelectedException.class, () -> {
            PiecesAroundFinder.findBestDirection(null, of("a1"), null);
        });
    }

    @Test
    public void find00() throws Exception {
        assertThrows(InvalidPieceSelectedException.class, () -> {
            PiecesAroundFinder.findBestDirection(null, of("a1"), Piece.EMPTY);
        });
    }

    @Test
    public void find01() throws Exception {
        evaluateMoveForPlayer1(of("a6"), "pieces_around_finder01");
        evaluateMoveForPlayer1(of("b6"), "pieces_around_finder01");
        evaluateMoveForPlayer1(of("c6"), "pieces_around_finder01");
        evaluateMoveForPlayer1(of("d6"), "pieces_around_finder01");
        evaluateMoveForPlayer1(of("e6"), "pieces_around_finder01");
        evaluateMoveForPlayer1(of("f6"), "pieces_around_finder01");
        evaluateMoveForPlayer1(of("g6"), "pieces_around_finder01");
    }

    @Test
    public void find02() throws Exception {

        evaluateMoveForPlayer1(of("A5"), "pieces_around_finder02", of("A6"));
        evaluateMoveForPlayer1(of("b6"), "pieces_around_finder02", of("A6"));
        evaluateMoveForPlayer1(of("c6"), "pieces_around_finder02");
        evaluateMoveForPlayer1(of("d6"), "pieces_around_finder02");
        evaluateMoveForPlayer1(of("e6"), "pieces_around_finder02");
        evaluateMoveForPlayer1(of("f6"), "pieces_around_finder02");
        evaluateMoveForPlayer1(of("g6"), "pieces_around_finder02");

        evaluateMoveForPlayer2(of("A5"), "pieces_around_finder02");
        evaluateMoveForPlayer2(of("b6"), "pieces_around_finder02");
        evaluateMoveForPlayer2(of("c6"), "pieces_around_finder02");
        evaluateMoveForPlayer2(of("d6"), "pieces_around_finder02");
        evaluateMoveForPlayer2(of("e6"), "pieces_around_finder02");
        evaluateMoveForPlayer2(of("f6"), "pieces_around_finder02");
        evaluateMoveForPlayer2(of("g6"), "pieces_around_finder02");

        evaluateMoveForPlayer1(1, of("A6"), "pieces_around_finder02", of("b6"));
        evaluateMoveForPlayer1(1,of("b5"), "pieces_around_finder02", of("b6"));
        evaluateMoveForPlayer1(1,of("c6"), "pieces_around_finder02", of("b6"));
        evaluateMoveForPlayer1(1,of("d6"), "pieces_around_finder02");
        evaluateMoveForPlayer1(1,of("e6"), "pieces_around_finder02");
        evaluateMoveForPlayer1(1,of("f6"), "pieces_around_finder02");
        evaluateMoveForPlayer1(1,of("g6"), "pieces_around_finder02");

        evaluateMoveForPlayer2(1,of("A6"), "pieces_around_finder02");
        evaluateMoveForPlayer2(1,of("b5"), "pieces_around_finder02");
        evaluateMoveForPlayer2(1,of("c6"), "pieces_around_finder02");
        evaluateMoveForPlayer2(1,of("d6"), "pieces_around_finder02");
        evaluateMoveForPlayer2(1,of("e6"), "pieces_around_finder02");
        evaluateMoveForPlayer2(1,of("f6"), "pieces_around_finder02");
        evaluateMoveForPlayer2(1,of("g6"), "pieces_around_finder02");

        evaluateMoveForPlayer1(2, of("A6"), "pieces_around_finder02");
        evaluateMoveForPlayer1(2,of("b6"), "pieces_around_finder02", of("c6"), of("d6"),of("e6"));
        evaluateMoveForPlayer1(2,of("c3"), "pieces_around_finder02", of("c4"), of("c5"), of("c6"));

        evaluateMoveForPlayer1(2,of("d5"), "pieces_around_finder02", of("c4"), of("e6"));
        evaluateMoveForPlayer1(2,of("e5"), "pieces_around_finder02", of("e6"));
        evaluateMoveForPlayer1(2,of("f6"), "pieces_around_finder02", of("c6"), of("d6"), of("e6"));
        evaluateMoveForPlayer1(2,of("g6"), "pieces_around_finder02");

        evaluateMoveForPlayer2(2,of("d5"), "pieces_around_finder02");
        evaluateMoveForPlayer2(2,of("e5"), "pieces_around_finder02");
        evaluateMoveForPlayer2(2,of("f6"), "pieces_around_finder02");
        evaluateMoveForPlayer2(2,of("g6"), "pieces_around_finder02");

        evaluateMoveForPlayer1(3,of("a6"), "pieces_around_finder02", of("b6"), of("c6"), of("d6"), of("e6"));
        evaluateMoveForPlayer1(3,of("b5"), "pieces_around_finder02", of("c5"), of("d5"), of("e5"));
        evaluateMoveForPlayer1(3,of("c4"), "pieces_around_finder02", of("d4"), of("e4"));
        evaluateMoveForPlayer1(3,of("e3"), "pieces_around_finder02", of("e4"), of("e5"), of("e6"));

        evaluateMoveForPlayer1(3,of("f6"), "pieces_around_finder02", of("b6"), of("c6"), of("d6"), of("e6"));
        evaluateMoveForPlayer1(3,of("g6"), "pieces_around_finder02");

    }


    private void evaluateMoveForPlayer1(Coordinates newMoveCoordinate, String fileName, Coordinates... coordinates) throws Exception {
        checkPiecesAround(0, Piece.PLAYER_1, newMoveCoordinate, fileName, coordinates);
    }

    private void evaluateMoveForPlayer2(Coordinates newMoveCoordinate, String fileName, Coordinates... coordinates) throws Exception {
        checkPiecesAround(0, Piece.PLAYER_2, newMoveCoordinate, fileName, coordinates);
    }

    private void evaluateMoveForPlayer1(int boardIndex, Coordinates newMoveCoordinate, String fileName, Coordinates... coordinates) throws Exception {
        checkPiecesAround(boardIndex, Piece.PLAYER_1, newMoveCoordinate, fileName, coordinates);
    }

    private void evaluateMoveForPlayer2(int boardIndex, Coordinates newMoveCoordinate, String fileName, Coordinates... coordinates) throws Exception {
        checkPiecesAround(boardIndex, Piece.PLAYER_2, newMoveCoordinate, fileName, coordinates);
    }

    private void checkPiecesAround(int boardIndex, Piece activePiece, Coordinates moveToCheck, String fileName, Coordinates... playerPiecesAround) throws Exception {
        List<Coordinates> aspectedResult0 = Arrays.asList(playerPiecesAround);
        Board board = BoardReader.readBoards(fileName)[boardIndex];

        List<Coordinates> availableMoves = AvailableMovesFinder.findAvailableMoves(board, activePiece);
        assertThat(availableMoves, hasItem(moveToCheck));

        List<Coordinates> capturedPiecesCoords = PiecesAroundFinder.findBestDirection(board, moveToCheck, activePiece);
        assertThat(capturedPiecesCoords.size(), equalTo(aspectedResult0.size()));
        assertEquals(aspectedResult0, capturedPiecesCoords);
    }
}