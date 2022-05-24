package it.fmt.games.connect4.model;

import it.fmt.games.connect4.exceptions.InconsistentBoardException;
import it.fmt.games.connect4.exceptions.InvalidInsertOperationException;
import it.fmt.games.connect4.support.BoardConsistencyChecker;
import it.fmt.games.connect4.support.BoardReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class InconsistencyBoardTest {

    @Test
    public void validBoard00() throws Exception {
        readBoard("incosistency_boards00");
    }

    @Test
    public void validBoard01() throws Exception {
        readBoard("incosistency_boards01");
    }

    @Test
    public void validBoard02() throws Exception {
        readBoard("incosistency_boards02");
    }

    @Test
    public void validBoard03() throws Exception {
        readBoard("incosistency_boards03");
    }

    @Test
    public void validBoard04() {
        assertThrows(InconsistentBoardException.class, () -> {
            readBoard("incosistency_boards04");
        }, "Invalid values found at: B3, E4, D5");

    }

    private void readBoard(String fileName) throws Exception {
        BoardReader.readBoards(fileName);
    }

}
