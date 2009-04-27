package de.fhaugsburg.games.boardgames;

public class Board {

	public int rows;
	public int columns;
	public Square[][] sq;
//	private PlayerMode currentPlayer = PlayerMode.SINGLE;

	public Board (int columns, int rows) {
		this.rows = rows;
		this.columns = columns;
		sq = new Square[columns][rows];
	}


	public void reset() {
		sq = new Square[columns][rows];
	}


	public Square getSquare(int columns, int row) {
		return sq[columns][row];
	}


	public void putPiece(Square square, int columns, int rows) {
		sq[columns][rows] = square;
	}


	public void movePiece(int fromRow, int fromColumn, int toRow, int toColumn) {
		sq[toColumn][toRow] = sq[fromColumn][fromRow];
		sq[fromColumn][fromRow] = null;
	}


	public void removePiece(int columns, int rows) { // get Piece back?
		sq[columns][rows] = null;
	}


	public boolean isOnTheBoard(int columns, int rows) {
		if((this.rows > rows) || (this.columns > columns)) {
			return false;
		} else {
			return true;
		}
	}


	public boolean isEmpty(int columns, int rows) {
		if(sq[columns][rows] == null) {
			return true;
		} else {
			return false;
		}
	}


	public int getHeight()  {
		return rows;
	}


	public int getWidth() {
		return columns;
	}

}