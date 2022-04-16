package it.fmt.games.connect4.support;

import it.fmt.games.connect4.UserInputReader;
import it.fmt.games.connect4.model.AvailableMoves;
import it.fmt.games.connect4.model.Board;
import it.fmt.games.connect4.model.GameLogicImpl;
import it.fmt.games.connect4.model.Player;

import static it.fmt.games.connect4.model.operators.ScoreCalculator.computeScore;

public class GameLogicForTest extends GameLogicImpl {
    public GameLogicForTest(Board initialBoard, Player player1, Player player2, UserInputReader userInputReader) {
        super(player1, player2, userInputReader);
        this.board = initialBoard;
    }

    @Override
    public AvailableMoves initialize() {
        gameSnapshotBuilder.setActivePiece(currentPlayer.getPiece()).setScore(computeScore(board)).setBoard(board.copy());
        return findMovesForPlayers();
    }
}
