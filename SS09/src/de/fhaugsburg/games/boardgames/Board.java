package de.fhaugsburg.games.boardgames;

public class Board <P extends IPiece, D> {
	
	public int rows;
	public int columns;
	
	@SuppressWarnings("unchecked")
	public Square[][] sq;
	
	/**
	 * Konstruktor der Klasse Board.
	 * @param columns
	 * @param rows
	 */
	public Board (int columns, int rows) {
		this.rows = rows;
		this.columns = columns;
		sq = new Square[columns][rows];
	}
	/**
	 * 
	 */
	public void reset() {
		sq = new Square[columns][rows];
	}
	
	/**
	 * 
	 * @param columns
	 * @param row
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Square getSquare(int columns, int row) {
		return sq[columns][row];
	}
	
	/**
	 * 
	 * @param square
	 * @param columns
	 * @param row
	 */
	@SuppressWarnings("unchecked")
	public void putPiece(Square square, int columns, int row) {
		sq[columns][row] = square;
	}
	
	/**
	 * 
	 * @param fromRow
	 * @param fromColumn
	 * @param toRow
	 * @param toColumn
	 */
	public void movePiece(int fromRow, int fromColumn, int toRow, int toColumn) {
		sq[toColumn][toRow] = sq[fromColumn][fromRow];
		sq[fromColumn][fromRow] = null;
	}
	
	/**
	 * 
	 * @param row
	 * @param column
	 */
	public void removePiece(int row, int column) { // get piece back?
		sq[columns][rows] = null;
	}
	
	/**
	 * 
	 * @param columns
	 * @param rows
	 * @return
	 */
	public boolean isOnTheBoard(int columns, int rows) {
		if((this.rows > rows) || (this.columns > columns)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 
	 * @param columns
	 * @param rows
	 * @return
	 */
	public boolean isEmpty(int columns, int rows) {
		if(sq[columns][rows] == null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public int getHeight()  {
		return rows;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getWidth() {
		return columns;
	}
	
}