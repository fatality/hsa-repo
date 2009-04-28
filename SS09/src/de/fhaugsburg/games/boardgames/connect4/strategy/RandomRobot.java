package de.fhaugsburg.games.boardgames.connect4.strategy;

public class RandomRobot {
	
	public int createRandomRow() {
		return (int)(Math.random() * 7);
	}
	
}