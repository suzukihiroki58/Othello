package model;

public class HumanPlayer extends BasePlayer {

	public HumanPlayer(Stone stone, Board board) {
		super(stone, board);
	}

	public HumanPlayer() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public void selectCell(int x, int y) {
		if (board.isValidMove(x, y, this.getStone())) {
			board.tryPlaceStone(x, y, this.getStone());
		}

	}
}
