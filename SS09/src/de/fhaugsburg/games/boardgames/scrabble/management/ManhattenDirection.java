package de.fhaugsburg.games.boardgames.scrabble.management;

public enum ManhattenDirection {
	
	DOWN(0,-1), UP(0,1), RIGHT(1,0), LEFT(-1, 0);
	
	public GridPoint gridPoint;
	
	ManhattenDirection(int column, int row) {		
		this.gridPoint = new GridPoint(column, row);
	}
	
	public static ManhattenDirection calcDir(GridPoint a, GridPoint b) {
		final int direction = a.compareTo(b);
		
		switch (direction) {
			case 0:
				return DOWN;
			case -1:
				return LEFT;
			case 1:
				return RIGHT;
			case 2:
				return UP;		
			default:
				return null;
		}
		
	}
	
}