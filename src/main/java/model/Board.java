package model;

import java.util.ArrayList;
import java.util.List;

public class Board {
	private Stone[][] grid;
	private int blackScore;
	private int whiteScore;

	public Board() {
		grid = new Stone[8][8];
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				grid[x][y] = Stone.EMPTY;
			}
		}
		grid[3][3] = Stone.WHITE;
		grid[3][4] = Stone.BLACK;
		grid[4][3] = Stone.BLACK;
		grid[4][4] = Stone.WHITE;
	}

	public boolean tryPlaceStone(int x, int y, Stone stone) {
		if (isValidMove(x, y, stone)) {
			doFlip(x, y, stone);
			return true;
		}
		return false;
	}

	public boolean isValidMove(int x, int y, Stone stone) {
		if (x < 0 || x >= 8 || y >= 8 || grid[x][y] != Stone.EMPTY) {
			return false;
		}

		int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, -1 }, { 1, 1 }, { -1, 1 }, { 1, -1 } };

		for (int[] dir : directions) {
			int dx = dir[0];
			int dy = dir[1];
			int i = x + dx;
			int j = y + dy;

			boolean foundOpposite = false;
			while (i >= 0 && i < 8 && j >= 0 && j < 8 && grid[i][j] != Stone.EMPTY) {
				if (grid[i][j] == stone) {
					if (foundOpposite) {
						return true;
					} else {
						break;
					}
				}
				foundOpposite = true;
				i += dx;
				j += dy;
			}
		}
		return false;
	}

	public List<int[]> getValidCells(BasePlayer basePlayer) {
		List<int[]> validMoves = new ArrayList<>();
		Stone playerStone = basePlayer.getStone();

		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (isValidMove(x, y, playerStone)) {
					validMoves.add(new int[] { x, y });
				}
			}
		}
		return validMoves;
	}

	public void calculateScore() {
		blackScore = 0;
		whiteScore = 0;

		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (grid[x][y] == Stone.BLACK) {
					blackScore++;
				} else if (grid[x][y] == Stone.WHITE) {
					whiteScore++;
				}
			}
		}

	}

	public int getBlackScore() {
		return blackScore;
	}

	public int getWhiteScore() {
		return whiteScore;
	}

	public void doFlip(int x, int y, Stone stone) {
		grid[x][y] = stone;

		int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, -1 }, { 1, 1 }, { -1, 1 }, { 1, -1 } };

		for (int[] dir : directions) {
			int dx = dir[0];
			int dy = dir[1];
			int i = x + dx;
			int j = y + dy;

			boolean shouldFlip = false;
			while (i >= 0 && i < 8 && j >= 0 && j < 8 && grid[i][j] != Stone.EMPTY) {
				if (grid[i][j] == stone) {
					if (shouldFlip) {
						break;
					} else {
						shouldFlip = false;
						break;
					}
				}
				shouldFlip = true;
				i += dx;
				j += dy;
			}
			if (shouldFlip) {
				i = x + dx;
				j = y + dy;
				while (grid[i][j] != stone) {
					grid[i][j] = stone;
					i += dx;
					j += dy;
				}
			}
		}
	}

	public Stone getStoneAt(int x, int y) {
		return grid[x][y];
	}
}
