package model;

import java.util.List;

public class CPUPlayer extends BasePlayer {

	public CPUPlayer() {

	}

	public CPUPlayer(Stone stone, Board board) {
		super(stone, board);
	}

	@Override
	public void selectCell(int x, int y) {
		List<int[]> validMoves = board.getValidCells(this);
		if (!validMoves.isEmpty()) {
			int randomIndex = (int) (Math.random() * validMoves.size());
			int[] move = validMoves.get(randomIndex);
			board.tryPlaceStone(move[0], move[1], this.getStone());
		}
	}
}
