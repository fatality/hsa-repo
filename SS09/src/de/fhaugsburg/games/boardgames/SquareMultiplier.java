package de.fhaugsburg.games.boardgames;
/**
 * 
 * @author P-chanxxx
 *
 */
public enum SquareMultiplier {
	
	// Anlegung der Konstanten
	WordMultiplier_2(2),
	WordMultiplier_3(3),
	LetterMultiplier_2(2),
	LetterMultiplier_3(3),
	Dummy(1);

	private int multiplier;
	
	/**
	 * 
	 * @param multiplier
	 */
	SquareMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getMultiplier() {
		return multiplier;
	}
	
}