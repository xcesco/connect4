package it.fmt.games.connect4.model.operators;

import it.fmt.games.connect4.exceptions.InvalidInsertOperationException;
import it.fmt.games.connect4.exceptions.InvalidPlayerMoveException;
import it.fmt.games.connect4.exceptions.NullPieceInsertedException;
import it.fmt.games.connect4.model.*;
import it.fmt.games.connect4.support.BoardReader;
import org.junit.jupiter.api.Test;

import java.util.List;

import static it.fmt.games.connect4.model.Coordinates.of;
import static it.fmt.games.connect4.model.operators.InsertMoveOperator.*;
import static it.fmt.games.connect4.support.BoardReader.readBoards;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InsertMoveOperatorTest {

    @Test
    public void insert() throws Exception {
        Board[] snapshots = readBoards("insert_piece01");

        checkMove(snapshots, Piece.PLAYER_1, "c6", 1);
        checkMove(snapshots, Piece.PLAYER_2, "d6", 2);
        checkMove(snapshots, Piece.PLAYER_1, "d5", 3);
    }

    @Test
    public void insertEmpty() {
        Board board = new Board();
        assertThrows(NullPieceInsertedException.class, () -> insertMove(board, null));
        assertThrows(NullPieceInsertedException.class, () -> insertMove(board, new PlayerMove(Piece.EMPTY, of("a1"))));
    }

    @Test
    public void insertInInvalidPosition() {
        Board board = new Board();
        assertThrows(InvalidPlayerMoveException.class, () -> insertMove(board, new PlayerMove(Piece.PLAYER_1, of("a1"))));
    }

    @Test
    public void insertAndWin() throws Exception {
        Board[] boards = BoardReader.readBoards("insert_piece02");

        List<Coordinates> availableMoves = AvailableMovesFinder.findMoves(boards[0], Piece.PLAYER_1);
        insertMove(boards[0], new PlayerMove(Piece.PLAYER_1, availableMoves.get(0)));
        assertThat(availableMoves.size(), equalTo(0));
    }

    @Test
    public void insertPiecesAndWin() throws Exception {
        Board[] boards = BoardReader.readBoards("insert_piece02");

        Board board=insertAndCheckBoardStatus(boards, 0, PlayerMove.create(Piece.PLAYER_2, of("d6")));
        Score score=ScoreCalculator.computeScore(board);

        assertThat(score.getPlayer2Score(), is(4));
    }

    private Board insertAndCheckBoardStatus(Board[] boards, int startIndex, PlayerMove playerMove) {
        int finalIndex = startIndex + 1;
        List<Coordinates> availableMoves = AvailableMovesFinder.findMoves(boards[startIndex], Piece.PLAYER_1);
        assertThat(availableMoves, hasItem(playerMove.getMoveCoords()));

        Board result = insertMove(boards[startIndex], playerMove.getPiece(), playerMove.getMoveCoords());
        assertEquals(result, boards[finalIndex]);

        return boards[finalIndex];
    }

    private void checkMove(Board[] snapshots, Piece piece, String coords, int boardIndex) {
        Coordinates move = Coordinates.of(coords);

        Board result = insertMove(snapshots[boardIndex - 1], piece, move);
        assertThat(result, equalTo(snapshots[boardIndex]));
    }
}