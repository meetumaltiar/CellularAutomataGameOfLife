package com.meetu.gameoflife;

import org.junit.Test;

import java.util.Arrays;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameOfLifeITest {

	@Test
	public void dead_after_one_round_game() {
		int size = 3;
		Coordinate[] initialState = new Coordinate[] { new Coordinate(0, 0) };
		Coordinate[] expectedState = new Coordinate[] {};

		Universe grid = createGrid(size, initialState);
		runAndVerify(grid, expectedState);
	}

	@Test
	public void simple_game() {
		int size = 3;
		Coordinate[] initialState = new Coordinate[] { new Coordinate(0, 0),
				new Coordinate(0, 1), new Coordinate(0, 2) };
		Coordinate[] expectedState = new Coordinate[] { new Coordinate(0, 1),
				new Coordinate(1, 1) };

		Universe grid = createGrid(size, initialState);
		runAndVerify(grid, expectedState);
	}

	@Test
	public void still_game() {
		int size = 3;
		Coordinate[] initialState = new Coordinate[] { new Coordinate(1, 0),
				new Coordinate(2, 1), new Coordinate(1, 1),
				new Coordinate(2, 1) };

		Universe grid = createGrid(size, initialState);
		runAndVerify(grid, initialState);
	}

	@Test
	public void another_still_game() {
		int size = 4;
		Coordinate[] initialState = new Coordinate[] { new Coordinate(0, 1),
				new Coordinate(0, 2), new Coordinate(1, 0),
				new Coordinate(1, 3), new Coordinate(2, 1),
				new Coordinate(2, 2) };

		Universe grid = createGrid(size, initialState);
		runAndVerify(grid, initialState);
	}

	@Test
	public void oscillator_game() {
		int size = 3;
		Coordinate[] initialState = new Coordinate[] { new Coordinate(0, 1),
				new Coordinate(1, 1), new Coordinate(2, 1) };
		Coordinate[] expectedState = new Coordinate[] { new Coordinate(1, 0),
				new Coordinate(1, 1), new Coordinate(1, 2) };

		Universe grid = createGrid(size, initialState);
		runAndVerify(grid, expectedState);
		runAndVerify(grid, initialState);
	}

	@Test
	public void another_oscillator_game() {
		int size = 4;
		Coordinate[] initialState = new Coordinate[] { new Coordinate(0, 1),
				new Coordinate(1, 1), new Coordinate(1, 2),
				new Coordinate(2, 1), new Coordinate(2, 2),
				new Coordinate(3, 2) };
		Coordinate[] expectedState = new Coordinate[] { new Coordinate(0, 1),
				new Coordinate(0, 2), new Coordinate(1, 0),
				new Coordinate(2, 3), new Coordinate(3, 1),
				new Coordinate(3, 2) };

		Universe grid = createGrid(size, initialState);
		runAndVerify(grid, expectedState);
		runAndVerify(grid, initialState);
	}

	protected Universe createGrid(int size, Coordinate[] initialState) {
		Universe grid = new Grid(size, initialState);
		return grid;
	}

	private Universe runAndVerify(Universe grid, Coordinate[] expectedState) {
		grid.update();
		Set<Coordinate> liveCells = grid.liveCells();
		assertEquals(expectedState.length, liveCells.size());
		assertTrue(liveCells.containsAll(Arrays.asList(expectedState)));
		return grid;
	}

}