package de.fhaugsburg.games.boardgames;

/**
 * 
 * @author P-chanxxx
 *
 */
public enum LetterPiece implements IPiece {
	
	 A(1), D(1), E(1), I(1), N(1), R(1), S(1), T(1), U(1),
	 G(2), H(2), L(2), O(2),
	 B(3), M(3), W(3), Z(3),
	 C(4), F(4), K(4), P(4),
	 J(6), V(6), Ä(6), Ö(6),
	 X(8), Ü(8),
	 Q(10), Y(10); 
	 
	 
	 private int count;
	 private int points;
	 /**
	  * 
	  * @param var
	  */
	 LetterPiece(int var) {
		 points = var;
	 }
	 
	 /**
	  * 
	  */
	 public int getPoints() {
		 return points;
	 }
	 
	 /**
	  * 
	  */
	 public int getCount() {
		 return count;
	 }

	
	 
}