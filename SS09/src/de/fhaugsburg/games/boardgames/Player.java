package de.fhaugsburg.games.boardgames;

public class Player {
	
    private String name;
    public final int id;
    private static int nextId = 1;
    
    /**
     * 
     * @param name
     */
    public Player(String name) {
    	this.name = name;
    	this.id = nextId++;
    }
    
 
    private int score = 0;
	
    /**
     * 
     * @param newPoints
     */
	public void addPoints(int newPoints) {
		score += newPoints;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getPoints() {
		return score;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}
	
}