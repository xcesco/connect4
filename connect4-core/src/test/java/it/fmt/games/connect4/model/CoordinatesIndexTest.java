package it.fmt.games.connect4.model;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CoordinatesIndexTest {

    @Test
    public void checkA1() {
        checkCoordinateIndex("a1", 0, 0, 0);
    }

    @Test
    public void checkA2() {
        checkCoordinateIndex("a2", 1, 0, 7);
    }

    @Test
    public void checkG6() {
        checkCoordinateIndex("g6", 5, 6, 41);
    }



    private void checkCoordinateIndex(String label, int row, int col, int  index) {
        Coordinates coordLabel = Coordinates.of(label.toUpperCase());
        Coordinates coord = Coordinates.of(row, col);
        Coordinates coordAbs = new Coordinates(index / Board.BOARD_COLUMNS, index % Board.BOARD_COLUMNS);

        assertThat(coordLabel, equalTo(coord));
        assertThat(coord, equalTo(coordAbs));
    }
}