package de.fhaugsburg.games.boardgames;

public class ScrabbleSquareDetails<D> {
	
	private SquareMultiplier letterMultiplier;
	private SquareMultiplier wordMultiplier;
	
	
	public int getWordMultiplier() {
		return SquareMultiplier.WORDMULTIPLIER.getWordMultiplier();
	}
	
	
	public void setWordMultiplier(SquareMultiplier wordMultiplier) {
		this.wordMultiplier = wordMultiplier;
	}
	
	
	public int getLetterMultiplier() {
		return SquareMultiplier.LETTERMULTIPLIER.getLetterMultiplier();
	}
	
	
	public void setLetterMultiplier(SquareMultiplier letterMultiplier) {
		this.letterMultiplier = letterMultiplier;
	}
	
	
	public void setPieceFixed(IPiece ipiece) {
		// not yet implemented (btw: ???)
	}
	
}