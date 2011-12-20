package com.meetu.gameoflife;

public class GameOfLifeApplication {
	static final boolean ALIVE = true;
	static final boolean EMPTY = false;

	public static void main(String[] args) throws java.io.IOException {
		int size = 10;
		boolean[][] currentGeneration, nextGeneration;
		currentGeneration = new boolean[size][size];
		nextGeneration = new boolean[size][size];

		readInitialConfiguration(currentGeneration);
		int cycles = 4;
		printState(currentGeneration);

		for (int i = 1; i < cycles; i++) {
			System.out.println("Cycle = " + i + "\n\n");
			advanceOneGeneration(currentGeneration, nextGeneration);
			printState(nextGeneration);
			boolean[][] tempGeneration = nextGeneration;
			nextGeneration = currentGeneration;
			currentGeneration = tempGeneration;
		}

	}

	static void readInitialConfiguration(boolean[][] w)
			throws java.io.IOException {
		for (int i = 0; i < w.length; i++) {
			for (int j = 0; j < w.length; j++) {
				char c = (char) System.in.read();
				// skip illegal characters
				while (c != '.' && c != 'X') {
					c = (char) System.in.read();
				}
				if (c == '.') {
					w[i][j] = EMPTY;
				} else {
					w[i][j] = ALIVE;
				}
			}
		}
		// set border cells as empty
		int border = w.length - 1;
		for (int i = 0; i < w.length; i++) {
			w[i][0] = w[0][i] = EMPTY;
			w[i][border] = w[border][i] = EMPTY;
		}
	}

	static void printState(boolean[][] w) {
		for (int i = 0; i < w.length; i++) {
			System.out.println("");
			for (int j = 0; j < w.length; j++) {
				if (w[i][j] == ALIVE) {
					System.out.println("X");
				} else {
					System.out.println(".");
				}
			}
		}
		System.out.println("");
	}

	static int neighbors(int row, int column, boolean[][] w) {
		int neighbourCount = 0;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (w[row + i][column + j] == ALIVE) {
					neighbourCount = neighbourCount + 1;
				}
			}
		}
		if (w[row][column] == ALIVE) {
			neighbourCount--;
		}
		return neighbourCount;
	}

	static void advanceOneGeneration(boolean[][] wOld, boolean[][] wNew) {
		int neighbourCount;
		for (int i = 1; i < wOld.length - 1; i++) {
			for (int j = 1; j < wOld[i].length - 1; j++) {
				neighbourCount = neighbors(i, j, wOld);
				if (neighbourCount == 3)
					wNew[i][j] = ALIVE;
				else if (wOld[i][j] == ALIVE && neighbourCount == 2)
					wNew[i][j] = ALIVE;
				else
					wNew[i][j] = EMPTY;
			}
		}
	}

}
