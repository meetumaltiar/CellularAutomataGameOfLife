package com.meetu.gameoflife;

public class GameOfLifeApp {

	public static void main(String[] args) {
		int size = 3;
		Coordinate[] initialState = new Coordinate[] { new Coordinate(0, 1),
				new Coordinate(1, 1), new Coordinate(2, 1) };
		Universe initialGrid = new Grid(size, initialState);
		displayStates(initialGrid, size, 4);
	}

	private static void displayStates(Universe grid, int size, int generations) {
		for (int i = 0; i < generations; i++) {
			System.out.println("Generation " + i);
			displayState(grid, size);
			grid.update();
		}
	}

	private static void displayState(Universe grid, int size) {
		for (int i = 0; i < size; i++) {
			System.out.println("");
			for (int j = 0; j < size; j++) {
				if (grid.isAlive(new Coordinate(i, j))) {
					System.out.print("X");
				} else {
					System.out.print("-");
				}
			}
		}
		System.out.println("");
	}
}
