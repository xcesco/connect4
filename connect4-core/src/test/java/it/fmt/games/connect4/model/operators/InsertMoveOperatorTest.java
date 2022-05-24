package it.fmt.games.connect4.model.operators;

import it.fmt.games.connect4.exceptions.InvalidInsertOperationException;
import it.fmt.games.connect4.model.Board;
import it.fmt.games.connect4.model.Coordinates;
import it.fmt.games.connect4.model.Piece;
import it.fmt.games.connect4.support.BoardReader;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static it.fmt.games.connect4.model.Coordinates.of;
import static it.fmt.games.connect4.model.operators.InsertMoveOperator.*;
import static it.fmt.games.connect4.support.BoardReader.readBoards;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InsertMoveOperatorTest {

    @Test
    public void insert() throws Exception {
        Board[] snapshots = readBoards("insert_piece01");

        checkRow(snapshots, Piece.PLAYER_2, "d4", 1);
        //  checkMove(snapshots, Piece.PLAYER_2, "d5", 2);
        //  checkMove(snapshots, Piece.PLAYER_1, "d6", 3);
    }

    @Test
    public void insertEmpty() {
        Board board = new Board();
        assertThrows(InvalidInsertOperationException.class, () -> insertMove(board, null));
        assertThrows(InvalidInsertOperationException.class, () -> insertMove(board, Piece.EMPTY, null));
    }

    @Test
    public void noAvailableMoves() throws Exception {
        Board[] boards = BoardReader.readBoards("insert_piece02");

        List<Coordinates> availableMoves = AvailableMovesFinder.findPositions(boards[0], Piece.PLAYER_1);
        assertThat(availableMoves.size(), equalTo(0));
    }

    @Test
    public void insertOnlyInOneDirection() throws Exception {
        Board[] boards = BoardReader.readBoards("insert_piece02");

        checkRightInsertion(boards, 1);
        checkRightInsertion(boards, 3);
    }

    private void checkRightInsertion(Board[] boards, int startIndex) {
        int finalIndex = startIndex + 1;
        List<Coordinates> availableMoves = AvailableMovesFinder.findPositions(boards[startIndex], Piece.PLAYER_1);
        assertThat(availableMoves.size(), equalTo(1));
        assertEquals(availableMoves, Collections.singletonList(of("e4")));

        Board result = insertMove(boards[startIndex], Piece.PLAYER_1, of("e4"));
        assertEquals(result, boards[finalIndex]);
    }

    private void checkRow(Board[] snapshots, Piece piece, String coords, int boardIndex) {
        Coordinates move = Coordinates.of(coords);
        List<Coordinates> emptyRow = MoveEvaluator.findRow(snapshots[boardIndex - 1], move, piece);

        assertThat(emptyRow.size(), equalTo(0));
        Board result = insertMove(snapshots[boardIndex - 1], piece, move);
        assertThat(result, equalTo(snapshots[boardIndex]));
        List<Coordinates> row = MoveEvaluator.findRow(snapshots[boardIndex], move, piece);
        assertThat(row.size(), equalTo(4));
    }
}