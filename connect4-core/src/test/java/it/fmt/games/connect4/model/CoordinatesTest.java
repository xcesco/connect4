package it.fmt.games.connect4.model;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CoordinatesTest {

    @Test
    public void checkCoordinateInvalid() {
        checkCoordinateValidity(-1, -1, false, "xx");
    }

    @Test
    public void checkRow0Col0() {
        checkCoordinateValidity(0, 0, true, "a1");
    }

    @Test
    public void checkRow5Col6() {
        checkCoordinateValidity(5, 6, true, "G6");
    }

    @Test
    public void checkRow5Col7() {
        checkCoordinateValidity(5, 7, false, "G6");
    }

    @Test
    public void checkRow99Col99() {
        checkCoordinateValidity(99, 99, false, "G6");
    }

    @Test
    public void checkRow3Col3() {
        checkCoordinateValidity(3, 3, true, "D4");
    }

    @Test
    public void checkRow6Col7() {
        checkCoordinateValidity(5, 6, true, "G6");
    }

    @Test
    public void checkCase() {
        Coordinates coords1 = Coordinates.of("A1");
        Coordinates coords2 = Coordinates.of("a1");

        assertThat(coords1, equalTo(coords2));
    }

    @Test
    public void testHashCode() {
        Coordinates coords1 = Coordinates.of("A1");
        Coordinates coords2 = Coordinates.of("a1");

        assertThat(coords1.hashCode(), equalTo(coords2.hashCode()));
    }

    @Test
    public void checkProperties() {
        Coordinates coords1 = Coordinates.of("G3");

        assertThat(coords1.getColumn(), equalTo(6));
        assertThat(coords1.getRow(), equalTo(2));
    }

    @Test
    public void testConversionA1() {
        checkValidConversion("A1", 0, 0);
    }

    @Test
    public void testConversionH8() {
        checkInvalidConversion( "H7");
    }

    @Test
    public void testConversionAz() {
        checkInvalidConversion("Az");
    }

    @Test
    public void testConversion11() {
        checkInvalidConversion("11");
    }

    @Test
    public void testConversionAA() {
        checkInvalidConversion("AA");
    }

    @Test
    public void testConversionaaa() {
        checkInvalidConversion("aaa");
    }

    @Test
    public void testConversionSpecialChar() {
        checkInvalidConversion("!!");
    }

    @Test
    public void testConversionSpecialA1() {
        checkInvalidConversion("A     1");
    }

    @Test
    public void testEmptyString() {
        checkInvalidConversion("");
    }

    @Test
    public void testNegativeNumber() {
        checkInvalidConversion("-1");
        checkInvalidConversion("A-1");
    }

    @Test
    public void zeroCoordinates() {
        Coordinates coords = Coordinates.of("0");

        assertThat(coords.isValid(), is(false));
    }

    @Test
    public void longStringCoordinates() {
        Coordinates coords = Coordinates.of("A2E5");
        assertThat(coords.isValid(), is(false));
    }

    @Test
    public void validCoordinates() {
        Coordinates coords = Coordinates.of(0, 0);

        assertThat(coords.isValid(), is(true));
        assertThat(coords.toString(), is("A1"));
    }

    @Test
    public void nonValidCoordinates() {
        Coordinates coords = Coordinates.of(10, 0);

        assertThat(coords.isValid(), is(false));
    }

    @Test
    public void testEquals() {
        Coordinates coordinatesA1 = Coordinates.of("a1");
        Coordinates coordinatesEquals = Coordinates.of("a1");
        Coordinates coordinatesA2 = Coordinates.of("a2");
        Coordinates coordinatesB1 = Coordinates.of("b1");
        String dummyObject = "Not cooordinates";

        assertThat(coordinatesA1.equals(coordinatesA1), is(true));
        assertThat(coordinatesA1.equals(coordinatesEquals), equalTo(true));
        assertThat(coordinatesA1.equals(coordinatesA2), is(false));
        assertThat(coordinatesA1.equals(coordinatesB1), is(false));
        assertThat(coordinatesA1.equals(dummyObject), is(false));
        assertThat(coordinatesA1.equals(null), is(false));
    }

    @Test
    public void testHashcode() {
        Coordinates coordinatesA1 = Coordinates.of("a1");
        Coordinates coordinatesA2 = Coordinates.of("a2");

        assertThat(coordinatesA1.hashCode() == coordinatesA1.hashCode(), is(true));
        assertThat(coordinatesA1.hashCode() == coordinatesA2.hashCode(), is(false));
    }

    private void checkValidConversion(String label, int row, int col) {
        Coordinates coords1 = Coordinates.of(label);
        Coordinates coords2 = Coordinates.of(row, col);

        assertThat(coords1, equalTo(coords2));
        assertThat(coords1.isValid(), is(true));
    }

    private void checkInvalidConversion(String label) {
        Coordinates coords1 = Coordinates.of(label);
        assertThat(coords1.isValid(), is(false));
    }

    private void checkCoordinateValidity(int row, int col, boolean valid, String value) {
        Coordinates coords = Coordinates.of(row, col);
        assertThat(coords.isValid(), is(valid));

        if (coords.isValid()) {
            assertThat(coords.toString().toUpperCase(), is(value.toUpperCase()));
        }
    }

}