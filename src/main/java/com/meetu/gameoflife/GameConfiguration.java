package com.meetu.gameoflife;

public class GameConfiguration {
	private String initialFilePath;
	private int gridSize;
	private int numberOfStates;
	
	public GameConfiguration(String initialFilePath, int gridSize, int numberOfStates) {
		this.initialFilePath = initialFilePath;
		this.gridSize = gridSize;
		this.numberOfStates = numberOfStates;
	}

	public String getInitialFilePath() {
		return initialFilePath;
	}

	public void setInitialFilePath(String initialFilePath) {
		this.initialFilePath = initialFilePath;
	}

	public int getGridSize() {
		return gridSize;
	}

	public void setGridSize(int gridSize) {
		this.gridSize = gridSize;
	}

	public int getNumberOfStates() {
		return numberOfStates;
	}

	public void setNumberOfStates(int numberOfStates) {
		this.numberOfStates = numberOfStates;
	}

}
