package de.fhaugsburg.games.boardgames.scrabble;

//import de.fhaugsburg.games.boardgames.Board;
import de.fhaugsburg.games.boardgames.LetterPiece;
//import de.fhaugsburg.games.boardgames.Square;

public class BoardView {
	
	public void render(ScrabbleBoard whatRender) {
		
		int rows = whatRender.getHeight();
		int columns = whatRender.getWidth();
		
		for (int s = rows-1; s >= 0; s--) {
			for (int z = 0; z < columns; z++) {
				if(whatRender.getSquare(z, s).getPiece() == null) {
					System.out.print(" " + whatRender.getSquare(z, s).getMultiplier() + " ");
				} else {
					System.out.print(" " + ((LetterPiece)whatRender.getSquare(z, s).getPiece()).name() + " ");
				}
			}
			System.out.println();
		}
		
	}
	
}