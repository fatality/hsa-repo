package de.fhaugsburg.games.boardgames.connect4.consoleui;

import de.fhaugsburg.games.boardgames.connect4.Connect4Board;

public class BoardView {
	
	public void render(Connect4Board whatRender) {
		int rows = whatRender.getHeight();
		int columns = whatRender.getWidth();
		
		for (int s = rows-1; s >= 0; s--) {
			for (int z = 0; z < columns; z++) {
				if (whatRender.getSquare(z, s) == null) {
					System.out.print(".");
				} else {
					System.out.print(whatRender.getSquare(z, s).getPiece());
				}
			}
			System.out.println();
		}
		
	}
	
}