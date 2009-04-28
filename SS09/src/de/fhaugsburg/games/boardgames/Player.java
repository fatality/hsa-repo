package de.fhaugsburg.games.boardgames;

public class Player {
	
    private String name;
    public final int id;
    private static int nextId = 1;
    
    
    public Player(String name) {
    	this.name = name;
    	this.id = nextId++;
    }
    
    
    private int score = 0;
	
    
	public void addPoints(int newPoints) {
		score += newPoints;
	}
	
	
	public int getPoints() {
		return score;
	}
	
	
	public String getName() {
		return name;
	}
	
	
	public int getId() {
		return id;
	}
	
}