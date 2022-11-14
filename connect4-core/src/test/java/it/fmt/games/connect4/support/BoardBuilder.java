package it.fmt.games.connect4.support;

import it.fmt.games.connect4.model.*;

import java.util.ArrayList;
import java.util.List;

public class BoardBuilder {
    private List<PlayerMove> moves;

    private Board board;

    private BoardBuilder(Board board) {
        this.board=board;
        this.moves = new ArrayList<>();
    }

    public static BoardBuilder create() {
        return new BoardBuilder(new Board());
    }

    public static BoardBuilder create(Board board) {
        return new BoardBuilder(board.copy());
    }

    public BoardBuilder move(Piece piece, Coordinates coordinates) {
        moves.add(new PlayerMove(piece, coordinates));
        return this;
    }

    public BoardBuilder move(PlayerMove move) {
        moves.add(move);
        return this;
    }

    public Board build() {
        moves.stream().forEach(move -> board.setCell(move.getMoveCoords(), move.getPiece()));
        return board;
    }
}
