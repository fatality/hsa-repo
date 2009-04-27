package de.fhaugsburg.games.boardgames.connect4;

import de.fhaugsburg.games.boardgames.Piece;
import de.fhaugsburg.games.boardgames.PlayerMode;
import de.fhaugsburg.games.boardgames.Square;
import de.fhaugsburg.games.boardgames.connect4.strategy.RandomRobot;

public class Connect4Engine {

	private Piece currentPiece = Piece.X;
	private PlayerMode mode;

	public int rows;
	public int columns;
	public Connect4Board set = new Connect4Board(8, 6);

	int count = 0;
	int currentRow;

	RandomRobot rb = new RandomRobot();


	public Connect4Engine(PlayerMode mode) {
		this.mode = mode;
	}


	public int count() {
		count++;
		return count;
	}


	public void turn(int column) {
		if(mode == PlayerMode.SINGLE) {
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
			currentRow = set.putPiece(new Square(currentPiece), columns); // starts the method and saves the return value of F in currentRow
			checkerChanger(columns, currentRow);
			currentPiece = Piece.X;
			break;
		}
	}


	public void setNewGame(PlayerMode mode) {
		set = new Connect4Board(8, 6);
		this.mode = mode;
		set.reset();
	}


	public void checkerChanger(int column, int row) {
		int verticalVektor[] = {0,-1};
		int horizontalVektor[] = {1,0};
		int diagonalVektorUp[] = {1,1};
		int diagonalVektorDown[] = {1,-1};
		int littleHelper;

		if (check4win(verticalVektor, column, row, 1) >= 4) {
			System.out.println(currentPiece + " hat gewonnen!");
			System.exit(0);
		}

		littleHelper = check4win(horizontalVektor, column, row, 1);

		if ((check4win(horizontalVektor, column, row, -1) + littleHelper) - 1 >= 4) {
			System.out.println(currentPiece + " hat gewonnen!");
			System.exit(0);
		}

		littleHelper = check4win(diagonalVektorUp, column, row, 1);

		if ((check4win(diagonalVektorUp, column, row, - 1) + littleHelper) - 1 >= 4) {
			System.out.println(currentPiece + " hat gewonnen!");
			System.exit(0);
		}

		littleHelper = check4win(diagonalVektorDown, column, row, 1);

		if((check4win(diagonalVektorDown, column, row, -1) + littleHelper) - 1 >= 4) {
			System.out.println(currentPiece + " hat gewonnen!");
			System.exit(0);
		}
	}


	public int check4win(int vector[], int columns, int rows, int direction) {

		// x and y are the coordinates of the last laid stone
		int counter = 0;
		for (int x = columns, y = rows; y >= 0 && x >= 0 && set.getSquare(x, y) != null && set.getSquare(x, y).getPiece() == currentPiece; x += (vector[0]*direction), y += (vector[1] * direction)) {
			if(counter == 4) {
				break;
			}
			counter++;
		}
		return counter;
	}


	public void help() {
		System.out.println("Commands: \n NEWGAME <SP | MP>   *in Single Player or Multi Player mode \n ADD columnr         *an integer in range \n HELP \n EXIT");
	}

}