package it.fmt.games.connect4.model.operators;

import it.fmt.games.connect4.model.Board;
import it.fmt.games.connect4.model.Coordinates;
import it.fmt.games.connect4.model.Piece;
import it.fmt.games.connect4.model.Score;
import it.fmt.games.connect4.support.BoardReader;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static it.fmt.games.connect4.model.Coordinates.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoveEvaluatorTest {

//    @Test
//    public void failOnInvalidPiece() {
//        assertThrows(InvalidPieceSelectedException.class, () -> {
//            Connect4Checker.find(null, of("a1"), Piece.EMPTY);
//        });
//
//        assertThrows(InvalidPieceSelectedException.class, () -> {
//            Connect4Checker.find(null, of("a1"), null);
//        });
//    }

//    @Test
//    public void find00() throws Exception {
//        assertThrows(InvalidPieceSelectedException.class, () -> {
//            Connect4Checker.find(null, of("a1"), Piece.EMPTY);
//        });
//    }

    @Test
    public void find01() throws Exception {
        checkMoveScore(Piece.PLAYER_2, of("d3"), "score_checker01", 0, 2);
    }

    @Test
    public void find02() throws Exception {
        checkMoveScore(Piece.PLAYER_2, of("d3"), "score_checker01", 1, 3);
    }

    @Test
    public void find03() throws Exception {
        checkMoveScore(Piece.PLAYER_2, of("d3"), "score_checker01", 2, 4);
    }

    @Test
    public void find04() throws Exception {
        checkMoveScore(Piece.PLAYER_2, of("d3"), "score_checker01", 3, 4);
    }

    @Test
    public void find05() throws Exception {
        checkMoveScore(Piece.PLAYER_2, of("d3"), "score_checker01", 4, 1);
    }

    @Test
    public void find06() throws Exception {
        checkMoveScore(Piece.PLAYER_2, of("d4"), "score_checker01", 5, 4);
    }

    @Test
    public void find07() throws Exception {
        checkMoveScore(Piece.PLAYER_2, of("d4"), "score_checker01", 6, 5);
    }
//
//    @Test
//    public void find02() throws Exception {
//        checkEnemyPieceFinderForPlayer1(of("d1"), "enemy_pieces_to_capture_finder02");
//    }
//
//    @Test
//    public void find03() throws Exception {
//        checkEnemyPieceFinderForPlayer1(of("c4"), "enemy_pieces_to_capture_finder03", of("d4"));
//    }
//
//    @Test
//    public void find03Player2() throws Exception {
//        checkEnemyPieceFinderForPlayer2(of("f4"), "enemy_pieces_to_capture_finder03", of("e4"));
//    }
//
//    @Test
//    public void find04() throws Exception {
//        checkEnemyPieceFinderForPlayer1(of("c4"), "enemy_pieces_to_capture_finder04", of("c3"), of("d4"));
//    }
//
//    @Test
//    public void find05() throws Exception {
//        checkEnemyPieceFinderForPlayer1(of("c4"), "enemy_pieces_to_capture_finder05", of("c2"), of("c3"), of("d4"));
//    }
//
//    @Test
//    public void find06() throws Exception {
//        checkEnemyPieceFinderForPlayer1(of("c4"), "enemy_pieces_to_capture_finder06", of("c2"), of("c3"), of("d3"), of("d4"), of("e2"));
//    }

//    private void checkEnemyPieceFinderForPlayer1(Coordinates newMoveCoordinate, String fileName, Coordinates... coordinates) throws Exception {
//        checkPieceFinder(Piece.PLAYER_1, newMoveCoordinate, fileName, coordinates);
//    }
//
//    private void checkEnemyPieceFinderForPlayer2(Coordinates newMoveCoordinate, String fileName, Coordinates... coordinates) throws Exception {
//        checkPieceFinder(Piece.PLAYER_2, newMoveCoordinate, fileName, coordinates);
//    }

//    private void checkPieceFinder(Piece activePiece, Coordinates newMoveCoordinate, String fileName, Coordinates... coordinates) throws Exception {
//       checkMoveScore(activePiece, newMoveCoordinate, fileName, 0, coordinates);
//    }

//    private void checkMoveScore(Piece activePiece, Coordinates newMoveCoordinate, String fileName, int boardIndex, Coordinates... coordinates) throws Exception {
//        List<Coordinates> aspectedResult0 = Arrays.asList(coordinates);
//        Board board = BoardReader.readBoards(fileName)[boardIndex];
//        List<Coordinates> capturedPiecesCoords = MoveEvaluator.calculateScore(board, newMoveCoordinate, activePiece);
//        assertThat(capturedPiecesCoords.size(), equalTo(aspectedResult0.size()));
//        assertEquals(capturedPiecesCoords, aspectedResult0);
//    }

    private void checkMoveScore(Piece activePiece, Coordinates newMoveCoordinate, String fileName, int boardIndex, int aspectedScore) throws Exception {
        Board board = BoardReader.readBoards(fileName)[boardIndex];
        Score score = ScoreCalculator.calculateScore(board, newMoveCoordinate, activePiece);
        assertThat(score, equalTo(aspectedScore));
    }
}