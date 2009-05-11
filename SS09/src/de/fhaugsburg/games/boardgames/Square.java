package de.fhaugsburg.games.boardgames;

/**
 *
 * @param <T>
 */
public class Square<T> {
	
	private T currentPiece;
	
	public SquareMultiplier multi;
	@SuppressWarnings("unused")
	private IPiece piece;
	
	/**
	 * @param piece
	 */
	public Square(T piece) {
		currentPiece = piece;
	}
	
	/**
	 * @param multi
	 */
	public Square(SquareMultiplier multi) {
		this.multi = multi;
	}
	
	/**
	 * @return
	 */
	public T getPiece() {
		return currentPiece;
	}
	
	/**
	 * @param piece
	 */
	public void put(T piece) {
		this.currentPiece = piece;
	}
	
	/**
	 * @return currentPiece
	 */
	public T removePiece() {
		this.currentPiece = null;
		return currentPiece;
	}
	
	/**
	 * @return multi.getMultiplier
	 */
	public int getMultiplier() {
		return multi.getMultiplier();
	}
	
}