package it.fmt.games.connect4.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.fmt.games.connect4.exceptions.InvalidCoordinatesException;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static it.fmt.games.connect4.model.Coordinates.of;

public class Board {
    public static final int BOARD_ROWS = 6;
    public static final int BOARD_COLUMNS = 7;
    public static final String DEFAULT_BOARD_NAME = "board";
    final Cell[] cells;
    final String name;

    public Board() {
        this.name = DEFAULT_BOARD_NAME;
        this.cells = init(coordinates -> new Cell(coordinates, Piece.EMPTY));
    }

    public Board(@JsonProperty("name") String name, @JsonProperty("cells") Cell[] cells) {
        this.name = name;
        this.cells = cells;
    }

    public String getName() {
        return name;
    }

    public Board copy() {
        return new Board(name, init(coordinates -> new Cell(coordinates, getCellContent(coordinates))));
    }

    public Stream<Cell> getCellStream() {
        return Arrays.stream(cells);
    }

    public void setCell(Coordinates coordinates, Piece content) {
        if (!coordinates.isValid()) throw new InvalidCoordinatesException(coordinates);
        cells[flatIndex(coordinates)] = new Cell(coordinates, content);
    }

    private Cell[] init(Function<Coordinates, Cell> filler) {
        return IntStream.range(0, BOARD_COLUMNS * BOARD_ROWS).mapToObj(this::asCoords).map(filler).toArray(Cell[]::new);
    }

    private int flatIndex(Coordinates coordinates) {
        return coordinates.getRow() * BOARD_COLUMNS + coordinates.getColumn();
    }

    private Coordinates asCoords(int flatIndex) {
        return of(flatIndex / BOARD_COLUMNS, flatIndex % BOARD_COLUMNS);
    }

    public Piece getCellContent(Coordinates coordinates) {
        if (!coordinates.isValid()) {
            throw new InvalidCoordinatesException(coordinates);
        }
        return cells[flatIndex(coordinates)].getPiece();
    }

    public boolean isCellContentEqualsTo(Coordinates coordinates, Piece currentPlayer) {
        return coordinates.isValid() && getCellContent(coordinates) == currentPlayer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;
        return Arrays.equals(cells, board.cells);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(cells);
    }
}


