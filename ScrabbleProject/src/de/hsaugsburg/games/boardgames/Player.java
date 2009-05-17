package de.hsaugsburg.games.boardgames;

public class Player {

	private int points;
	private String name;
	private final int ID;
	private static int numberOfInstances;
	
	public Player(String name) {
		this.name = name;
		this.ID = ++numberOfInstances;
	}
	
	public void addPoints(int newPoints) {
		this.points += newPoints;
	}
	
	public int getPoints() {
		return points;
	}
	
	public String getName() {
		return name;
	}
	
	public int getId() {
		return ID;
	}
	
	public void finalize() {
		--numberOfInstances;
	}
	
}
