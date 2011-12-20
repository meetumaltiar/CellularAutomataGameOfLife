package com.meetu.gameoflife;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GameOfLifeApp {

	public static void main(String[] args) throws IOException {
		GameConfiguration conf = getGameConfiguration("src/main/resources/gameoflife.properties");
		List<Coordinate> initialState = readInitialConfiguration(conf.getInitialFilePath());
		Universe initialGrid = new Grid(conf.getGridSize(),initialState.toArray(new Coordinate[initialState.size()]));
		displayStates(initialGrid, conf.getGridSize(), conf.getNumberOfStates());
	}
	
	private static GameConfiguration getGameConfiguration(String filePath) throws IOException {
		Properties props = new Properties();
		props.load(new FileInputStream(filePath));
		String initialFilePath = props.getProperty("initialFilePath");
		int gridSize = Integer.parseInt(props.getProperty("gridSize"));
		int numberOfStates = Integer.parseInt(props.getProperty("numberOfStates"));
		return new GameConfiguration(initialFilePath, gridSize, numberOfStates);
	}

	private static List<Coordinate> readInitialConfiguration(String filePath)
			throws IOException {
		List<Coordinate> liveCellCoordinates = new ArrayList<Coordinate>();
		FileInputStream fstream = new FileInputStream(filePath);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		int row = 0;
		while ((strLine = br.readLine()) != null) {
			char[] charsInaRow = strLine.toCharArray();
			liveCellCoordinates.addAll(getLiveCellsOfARow(row, charsInaRow));
			row++;
		}
		return liveCellCoordinates;
	}

	private static List<Coordinate> getLiveCellsOfARow(int row,
			char[] charsInaRow) {
		List<Coordinate> liveCellCoordinatesForARow = new ArrayList<Coordinate>();

		for (int column = 0; column < charsInaRow.length; column++) {
			char character = charsInaRow[column];
			if (character == 'X') {
				liveCellCoordinatesForARow.add(new Coordinate(row, column));
			}
		}
		return liveCellCoordinatesForARow;
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
