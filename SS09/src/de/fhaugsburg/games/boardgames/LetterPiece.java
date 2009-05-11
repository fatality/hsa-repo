package de.fhaugsburg.games.boardgames;

public enum LetterPiece implements IPiece {
	
	 A(1,5), D(1,4), E(1,15), I(1,6), N(1,4), R(1,6), S(1,7), T(1,6), U(1,6),
	 G(2,3), H(2,4), L(2,3), O(2,3),
	 B(3,2), M(3,4), W(3,1), Z(3,1),
	 C(4,2), F(4,2), K(4,2), P(4,1),
	 J(6,1), V(6,1), Ä(6,1), Ö(6,1),
	 X(8,1), Ü(8,1),
	 Q(10,1), Y(10,1);
	 
	 
	 private int points;
	 private int amountLetters;
	 
	 
	 LetterPiece(int var, int var2) {
		 points = var;
		 amountLetters = var2;
	 }
	 
	 
	 public int getPoints()
	 {
		 return points;
	 }
	 
	 
	 public int getAmountLetters()
	 {
		 return amountLetters;
	 }
	 
}