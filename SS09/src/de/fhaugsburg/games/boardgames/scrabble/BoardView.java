package de.fhaugsburg.games.boardgames.scrabble;

import de.fhaugsburg.games.boardgames.LetterPiece;
import de.fhaugsburg.games.boardgames.SquareMultiplier;

public class BoardView  {
	
	/**
	 * Gibt das Spielfeld aus
	 * @param whatRender
	 */
	public void render(ScrabbleBoard whatRender) {
		
		int labelRight= 'A';
		int rows = whatRender.getHeight();
		int columns = whatRender.getWidth();
		System.out.print(" ");
		
		for (int i = 1; i <= 15; i++) {
			if (i < 10) {
				System.out.print(" " + i + " ");
			} else {
				System.out.print(" " + i);
			}
		}
		
		System.out.println();
		
		for (int s = 0, i = 65; s < rows; s++, i++) {
			System.out.print((char)i);
			for (int z = 0; z < columns; z++) {
				if(whatRender.getSquare(z, s).getPiece() == null) {
					if(whatRender.getSquare(s, z).multi == SquareMultiplier.Dummy) {
						System.out.print(" . ");
					}
					if(whatRender.getSquare(s, z).multi== SquareMultiplier.LetterMultiplier_2) {
						System.out.print(".. ");
					}
					if(whatRender.getSquare(s, z).multi == SquareMultiplier.LetterMultiplier_3) {
						System.out.print("...");
					}
					if(whatRender.getSquare(s, z).multi == SquareMultiplier.WordMultiplier_3) {
						System.out.print("___");
					}
					if(whatRender.getSquare(s, z).multi == SquareMultiplier.WordMultiplier_2) {
						System.out.print("__ ");
					}
				} else {
					System.out.print(" " + ((LetterPiece)whatRender.getSquare(z, s).getPiece()).name() + " ");
				}
				if (z >= 14) {
					System.out.print((char) labelRight);
					labelRight++;
				}
			}
			System.out.println();
		}
		
		System.out.print(" ");
		
		for (int i = 1; i <= 15; i++) {
			if(i < 10) {
				System.out.print(" " + i + " ");
			} else {
				System.out.print(" " + i);
			}
		}
		
		System.out.println();
	}
	
}