package de.fhaugsburg.games.boardgames;

/**
 *
 * @param <D>
 */
public class ScrabbleSquareDetails<D> {

	@SuppressWarnings("unused")
	private SquareMultiplier letterMultiplier;
	@SuppressWarnings("unused")
	private SquareMultiplier wordMultiplier;
	
	/**
	 * @return
	 */
	public int getWordMultiplier() {
		return SquareMultiplier.WordMultiplier_2.getMultiplier();
	}
	
	/**
	 * @param wordMultiplier
	 */
	public void setWordMultiplier(SquareMultiplier wordMultiplier) {
		this.wordMultiplier = wordMultiplier;
	}
	
	/**
	 * @return
	 */
	public int getLetterMultiplier() {
		return SquareMultiplier.LetterMultiplier_2.getMultiplier();
	}
	
	/**
	 * @param letterMultiplier
	 */
	public void setLetterMultiplier(SquareMultiplier letterMultiplier) {
		this.letterMultiplier = letterMultiplier;
	}
	
	/**
	 * @param ipiece
	 */
	public void setPieceFixed(IPiece ipiece) {
		// not yet implemented (btw: ???)
	}
	
}