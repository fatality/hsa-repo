package de.hsaugsburg.games.boardgames.scrabble;

public class ScrabbleSquareDetails {

	private SquareMultiplier multiplier;
	private boolean pieceFixed;
	
	public ScrabbleSquareDetails() {
		this.pieceFixed = false;
	}
	
	public void setMultiplier(SquareMultiplier multiplier) {
		this.multiplier = multiplier;
	}
	
	public SquareMultiplier getMultiplier() {
		return multiplier;
	}
	
	public int getWordMultiplier() {
		return multiplier.getWordMultiplier();
	}
	
	public int getLetterMultiplier() {
		return multiplier.getLetterMultiplier();
	}
	
	public void setFixed() {
		this.pieceFixed = true;
	}
	
	public boolean isFixed() {
		return this.pieceFixed;
	}
}
