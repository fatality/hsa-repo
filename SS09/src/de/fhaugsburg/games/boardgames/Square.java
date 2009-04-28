package de.fhaugsburg.games.boardgames;

public class Square<T> {
	
	private T currentPiece;
	
	public SquareMultiplier multi;
	
	@SuppressWarnings("unused")
	private IPiece piece;
	
	
	public Square(T piece) {
		currentPiece = piece;
	}
	
	
	public Square(SquareMultiplier multi) {
		this.multi = multi;
	}
	
	
	public T getPiece() {
		return currentPiece;
	}
	
	
	public void put(T piece) {
		this.currentPiece = piece;
	}
	
	
	public T removePiece() {
		this.currentPiece = null;
		return currentPiece;
	}
	
	
	public int getMultiplier() {
		return multi.getMultiplier();
	}
	
}