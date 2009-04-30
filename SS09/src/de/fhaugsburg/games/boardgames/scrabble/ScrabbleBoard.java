package de.fhaugsburg.games.boardgames.scrabble;

import de.fhaugsburg.games.boardgames.Board;
//import de.fhaugsburg.games.boardgames.IPiece;
import de.fhaugsburg.games.boardgames.LetterPiece;
//import de.fhaugsburg.games.boardgames.OutsideBoardException;
import de.fhaugsburg.games.boardgames.Square;
import de.fhaugsburg.games.boardgames.SquareMultiplier;
//import exceptions.FullException;
//import exceptions.GameOver;

@SuppressWarnings("unchecked")
public class ScrabbleBoard extends Board  {
	/**
	 * Erstellt das spezielle ScrabbleBoard
	 * @param columns
	 * @param rows
	 */
	public ScrabbleBoard(int columns, int rows) {
		super(columns, rows);
		
		//Setzen WordMultiplier dreifach
		for (int a = 0; a <= 7; a+=7) {
			for (int b = 0; b <= 7; b+=7) {
				sq[a][b] = new Square(SquareMultiplier.WordMultiplier_3);
			}
		}
		
		//Setzen WordMultiplier zweifach
		for (int a = 1; a <= 4; a++) {
			sq[a][a] = new Square(SquareMultiplier.WordMultiplier_2);
		}
		
		sq[1][5] = new Square(SquareMultiplier.LetterMultiplier_3);
		sq[5][1] = new Square(SquareMultiplier.LetterMultiplier_3);
		sq[5][5] = new Square(SquareMultiplier.LetterMultiplier_3);
		
		sq[0][3] = new Square(SquareMultiplier.LetterMultiplier_2);
		sq[2][6] = new Square(SquareMultiplier.LetterMultiplier_2);
		sq[3][0] = new Square(SquareMultiplier.LetterMultiplier_2);
		sq[3][7] = new Square(SquareMultiplier.LetterMultiplier_2);
		sq[6][2] = new Square(SquareMultiplier.LetterMultiplier_2);
		sq[6][6] = new Square(SquareMultiplier.LetterMultiplier_2);
		sq[7][3] = new Square(SquareMultiplier.LetterMultiplier_2);
		
 		for (int i = 0; i <= 7; i++) {
			for (int j = 0; j <= 7; j++) {
				if(sq[i][j] == null) {
					sq[i][j] = new Square(SquareMultiplier.Dummy);
				}
			}
		}
		
 		for (int fromi = 0, toi = 0; fromi <= 7; fromi++, toi++) { //Kopieren von rechts nach links
 			for (int fromj = 0, toj = 14; fromj <= 7; fromj++, toj--) {
 				sq[toi][toj] = new Square(sq[fromi][fromj].multi);
 			}
 		}
 		
 		for (int fromj = 0, toj = 0; fromj <= 14; fromj++, toj++) { // Kopieren von oben nach unten
 			for (int fromi = 0, toi = 14; fromi <= 7; fromi++, toi--) {
 				sq[toi][toj] = new Square(sq[fromi][fromj].multi);
 			}
 		}
 		sq[7][7] = new Square(SquareMultiplier.LetterMultiplier_2);
 		
	}
	
	
	@SuppressWarnings("unused")
	private final int availableTurns = 110;
	
	private int count = 0;
	
	public void putPiece(int columns, int rows, LetterPiece piece) {
		count++;
		sq[columns][rows].put(piece);
	}
	
}