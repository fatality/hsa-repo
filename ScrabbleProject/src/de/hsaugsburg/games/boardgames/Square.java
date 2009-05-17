package de.hsaugsburg.games.boardgames;

/**
 * @author Marc Rochow, Anja Radtke
 */
public class Square <P,D> {
	
	private P piece;
	private D details;
	
	public void putPiece(P piece)  {
		this.piece = piece;
	}
	
	public P getPiece()  {
		return piece;
	}
	
	public P removePiece()  {
		P piece = this.piece;
		this.piece = null;
		return piece;
	}
	
	public void putDetails(D details) {
		this.details = details;
	}
	
	public D getDetails(){
		return details;
	}
	
}
