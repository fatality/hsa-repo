package de.fhaugsburg.games.boardgames.connect4;

import de.fhaugsburg.games.boardgames.OutsideBoardException;
import de.fhaugsburg.games.boardgames.Board;
import de.fhaugsburg.games.boardgames.Piece;
import de.fhaugsburg.games.boardgames.PlayerMode;
import de.fhaugsburg.games.boardgames.Square;
import de.fhaugsburg.games.boardgames.connect4.strategy.RandomRobot;
import exceptions.GameOver;

public class Connect4Engine {
	
	private Piece currentPiece = Piece.X;
	public int rows;
	public int columns;
	public Connect4Board set = new Connect4Board(7, 6);
	private PlayerMode currentMode = PlayerMode.SINGLE;
	RandomRobot rb = new RandomRobot();
	Square[][] sq = new Square[columns][rows];
	int currentRow;
	
	
	public void turn(int column)  {
		if(currentMode == PlayerMode.SINGLE) {
			singlePlayer(column);
		} else {
			doToggle(column);
		}
	}
	
	
	private void singlePlayer(int column) {	
		currentRow = set.putPiece(new Square(currentPiece), column);
		checkerChanger(column, currentRow);
		currentPiece = Piece.O;
		currentRow = set.putPiece(new Square(currentPiece), rb.createRandomRow());
		checkerChanger(column, currentRow);
		currentPiece = Piece.X;
	}
	

	public void doToggle(int columns) {
		switch(currentPiece) {
		case X:
			currentRow = set.putPiece(new Square(currentPiece), columns);
			checkerChanger(columns, currentRow);
			currentPiece = Piece.O;
			break;
		case O:
			currentRow = set.putPiece(new Square(currentPiece), columns); //er fhrt die Methode aus und speichert den Rckgabewert F in der currentRow
			checkerChanger(columns, currentRow);
			currentPiece = Piece.X;
			break;
		}
	}
	
	
	public void setNewGame(String mode) {
		set = new Connect4Board(7,6);
		set.reset();
		currentMode = PlayerMode.valueOf(mode);
	}
	
	
	public void checkerChanger(int column, int row) {
		int verticalVektor[] = {0,-1};
		int horizontalVektor[] = {1,0};
		int diagonalVektorUp[] = {1,1};
		int diagonalVektorDown[] = {1,-1};
		int littleHelper;
		
		if (check4win(verticalVektor, column, row, 1) == 4) {
			System.out.println(currentPiece + " WINS THE MATCH");
			System.exit(1);
		}
		
		littleHelper = check4win(horizontalVektor, column, row, 1);
		
		if ((check4win(horizontalVektor, column, row, -1) + littleHelper) - 1 == 4) {
			System.out.println(currentPiece + " WINS THE MATCH");
			System.exit(1);
		}
		
		
		littleHelper = check4win(diagonalVektorUp, column, row, 1);
		
		if ((check4win(diagonalVektorUp, column, row, - 1) + littleHelper) - 1 == 4) {
			System.out.println(currentPiece + " WINS THE MATCH");
			System.exit(1);
		}
		
		
		littleHelper = check4win(diagonalVektorDown, column, row, 1);
		
		if((check4win(diagonalVektorDown, column, row, -1) + littleHelper) - 1 == 4) {
			System.out.println(currentPiece + " WINS THE MATCH");
			System.exit(1);
		}
		
	}
	
	
	public int check4win(int vector[], int columns, int rows, int direction) { // x und y sind die koordinaten des zuletzt gelegten steins
		int counter = 0;
		for(int x = columns, y = rows; y >= 0 && x >= 0 && x < set.getWidth() && y < set.getHeight() && set.getSquare(x, y) != null && set.getSquare(x, y).getPiece() == currentPiece; x += (vector[0]*direction), y += (vector[1] * direction)) {
			counter++;
		}
		return counter;
	}
	
	
	public void help() {
		System.out.println("Commands: \n NEWGAME <SP | MP>   *in Single Player or Multi Player mode \n ADD columnr         *an integer in range \n HELP \n EXIT");
	}
	
}