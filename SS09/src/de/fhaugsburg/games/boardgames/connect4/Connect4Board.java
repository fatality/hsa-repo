package de.fhaugsburg.games.boardgames.connect4;

import de.fhaugsburg.games.boardgames.Board;
import de.fhaugsburg.games.boardgames.Square;
import de.fhaugsburg.games.boardgames.OutsideBoardException;
import de.fhaugsburg.games.boardgames.connect4.consoleui.ColumnOverrunException;
import exceptions.FullException;
import exceptions.GameOver;

@SuppressWarnings("unchecked")
public class Connect4Board extends Board {
	
	public Connect4Board(int columns, int rows) {
		super(columns, rows);
	}
	
	int count = 0;
	int availableTurns = columns * rows;
	
	public int putPiece(Square squares, int column) throws OutsideBoardException, FullException, GameOver {
		
		if(count == availableTurns) {
			throw new GameOver();
		}
		
		if(column > rows) { //original length is 6
			throw new OutsideBoardException();
		}
		
		int f = 0;
		while (sq[column][f] != null) {
			if(f > 5) {
				throw new ColumnOverrunException();
			}
			f++;
		}
		count++;
		sq[column][f] = squares;
		return f;
		
	}
	
	public void movePiece(int fromColumn, int fromRow, int toColumn, int toRow) {
		// not yet implemented
	}
	
}