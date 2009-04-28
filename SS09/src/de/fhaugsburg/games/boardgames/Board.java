package de.fhaugsburg.games.boardgames;

public class Board <P extends IPiece, D> {
	
	public int rows;
	public int columns;
	public Square[][] sq;
	
	
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
	
	
	public void putPiece(Square square, int columns, int row) {
		sq[columns][row] = square;
	}
	
	
	public void movePiece(int fromRow, int fromColumn, int toRow, int toColumn) {
		sq[toColumn][toRow] = sq[fromColumn][fromRow];
		sq[fromColumn][fromRow] = null;
	}
	
	
	public void removePiece(int row, int column) { // get piece back?
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