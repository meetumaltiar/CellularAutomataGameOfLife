package com.meetu.gameoflife;

import org.junit.Test;
import org.mockito.InOrder;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GridTest {

	@Test
	public void new_grid_should_have_only_dead_cells() throws Exception {
		int size = 3;
		Universe grid = new Grid(3);
		assertEquals(size * 3, grid.deadCellsCount());
	}

	@Test
	public void grid_initialized_with_3_live_cells_should_have_3_live_cells()
			throws Exception {
		Universe grid = new Grid(3, new Coordinate(0, 0),
				new Coordinate(0, 1), new Coordinate(0, 2));
		assertEquals(3, grid.liveCellsCount());
	}

	@Test
	public void grid_initialized_with_1_live_cells_should_have_this_cell_alive()
			throws Exception {
		Grid grid = new Grid(5, new Coordinate(3, 4));
		assertTrue(grid.isAlive(3, 4));
	}

	@Test
	public void grid_initialized_with_1_live_cell_should_have_other_cells_dead()
			throws Exception {
		int size = 5;
		Grid grid = new Grid(size, new Coordinate(3, 4));
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (x != 3 && y != 4) {
					assertFalse(grid.isAlive(x, y));
				}
			}
		}
	}

	@Test
	public void updating_the_grid_should_update_then_commit_each_cell() {
		int gridSize = 2;
		Map<Coordinate, Cell> cells = new HashMap<Coordinate, Cell>();
		Cell cell = mock(Cell.class);
		for (int x = 0; x < gridSize; x++) {
			for (int y = 0; y < gridSize; y++) {
				cells.put(new Coordinate(x, y), cell);
			}
		}
		Universe grid = new Grid(cells);
		grid.update();
		InOrder inOrder = inOrder(cell);
		inOrder.verify(cell, times(gridSize * gridSize)).computeNextState();
		inOrder.verify(cell, times(gridSize * gridSize)).commitNewState();
	}

	@Test
	public void cell_outside_grid_should_be_dead() {
		Grid grid = new Grid(3);
		assertFalse(grid.isAlive(0, -1));
		assertFalse(grid.isAlive(0, 3));
	}

}