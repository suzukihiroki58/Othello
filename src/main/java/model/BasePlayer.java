package model;

public class BasePlayer {
	protected Stone stone;
	protected Board board;

	public BasePlayer() {
	}

	public BasePlayer(Stone stone, Board board) {
		this.stone = stone;
		this.board = board;
	}

	public void selectCell(int x, int y) {

	}

	public Stone getStone() {
		return stone;
	}
}