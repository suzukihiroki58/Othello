package model;

public class Othello {
	private BasePlayer firstPlayer;
	private BasePlayer secondPlayer;
	private BasePlayer currentPlayer;
	private Board board;

	public void startGame() {
		decideFirstPlayer();
		board = new Board();
	}

	public void decideFirstPlayer() {
		if (Math.random() < 0.5) {
			firstPlayer = new HumanPlayer();
			secondPlayer = new CPUPlayer();
		} else {
			firstPlayer = new CPUPlayer();
			secondPlayer = new HumanPlayer();
		}
		currentPlayer = firstPlayer;
	}

	public BasePlayer getFirstPlayer() {
		return firstPlayer;

	}

	public BasePlayer getSecondPlayer() {
		return secondPlayer;
	}

	public BasePlayer getCurrentPlayer() {
		return currentPlayer;

	}

	public void switchTurn() {
		currentPlayer = currentPlayer.equals(firstPlayer) ? secondPlayer : firstPlayer;
	}

	public Board getBoard() {
		return board;
	}

	public boolean isGameOver() {
		boolean isBoardFull = true;
		for (int x = 0; x <8; x++) {
			for(int y = 0; y < 8; y++) {
				if (board.getStoneAt(x,y) == Stone.EMPTY) {
					isBoardFull = false;
					break;
				}
			}
			if (!isBoardFull) {
				break;
			}
		}
		
		boolean hasValidMoveFirstPlayer = !board.getValidCells(firstPlayer).isEmpty();
		boolean hasValidMoveSecondPlayer = !board.getValidCells(secondPlayer).isEmpty();
		
		if (!isBoardFull && (hasValidMoveFirstPlayer || hasValidMoveSecondPlayer)) {
			return false;
		} else {
			return true;
		}
	}

	public enum GameResult {
		PLAYER_WIN, DRAW, CPU_WIN
	}

	public GameResult checkWinner() {

		int blackScore = board.getBlackScore();
		int whiteScore = board.getWhiteScore();

		if (blackScore > whiteScore) {
			return firstPlayer instanceof HumanPlayer ? GameResult.PLAYER_WIN : GameResult.CPU_WIN;
		} else if (whiteScore > blackScore) {
			return secondPlayer instanceof HumanPlayer ? GameResult.PLAYER_WIN : GameResult.CPU_WIN;
		} else {
			return GameResult.DRAW;
		}
	}

}
