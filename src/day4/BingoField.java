package day4;

import java.util.Arrays;

public class BingoField {

	private int [][] field = new int[5][5];
	private boolean [][] check = new boolean[5][5];
	private int addedRowCount = 0;
	private boolean alrdyWon = false;
	private int winValue = 0;
	public int[][] getField() {
		return field;
	}

	public void addRow(int[] row) {
		for (int j = 0; j < row.length; j++) {
			field[addedRowCount][j] = row[j];
		}
		addedRowCount++;
	}
	
	public String toString() {
		String board = Arrays.deepToString(field);
		String checkBoard = Arrays.deepToString(check);
		StringBuilder sb = new StringBuilder(board);
		sb.append("\n");
		sb.append(checkBoard);
		if(alrdyWon) {
			sb.append("\n" +winValue);
		}
		return sb.toString();
	}

	public int check(int draw) {
		if(alrdyWon) {
			return -1;
		}
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if(field[i][j] == draw) {
					check[i][j] = true;
					return hasWon(i, j, draw);
				}
			}
		}
		return -1;
	}

	private int hasWon(int row, int column, int draw) {
		if(isRowWin(row) || isColumnWin(column)) {
			alrdyWon = true;
			winValue = getUnmarkedFields() * draw;
			return winValue;
		} else {
			return -1;
		}
	}

	private int getUnmarkedFields() {
		int sumUnmarked = 0;
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if(!check[i][j]) {
					sumUnmarked += field[i][j];
				}
			}
		}
		return sumUnmarked;
	}

	private boolean isColumnWin(int column) {
		boolean hasWon = true;
		for(int i = 0; i < 5; i++) {
			hasWon = check[i][column] && hasWon;
		}
		return hasWon;
	}

	private boolean isRowWin(int row) {
		boolean hasWon = true;
		for(int j = 0; j < 5; j++) {
			hasWon = check[row][j] && hasWon;
		}
		return hasWon;
	}
}
