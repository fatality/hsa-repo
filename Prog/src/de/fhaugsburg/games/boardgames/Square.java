package de.fhaugsburg.games.boardgames;


public class Square {

	private Piece currentPiece;
	public Object details;

	public Square(Piece piece) {
		currentPiece = piece;
	}




	public Piece getPiece() {
		return currentPiece;
	}


	public void putPiece(Piece piece) {
		this.currentPiece = piece;
	}


	public Piece removePiece() {
		this.currentPiece = null;
		return currentPiece;
	}


	public void layStone() {
		// yet not implemented
	}


	public Square(){
		this.details = details;
	}
	
	public Object getDetails() {
		return details;
	}

}