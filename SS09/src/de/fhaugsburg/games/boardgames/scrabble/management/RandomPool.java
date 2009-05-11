package de.fhaugsburg.games.boardgames.scrabble.management;

import java.util.List;
import java.util.Vector;
import de.fhaugsburg.games.boardgames.LetterPiece;

public class RandomPool<T> {
	List<T> collection = new Vector<T>(0);

	@SuppressWarnings("unchecked")
	public RandomPool() {
		for (char c = 'A'; c <= 'Z'; c++) {
			String letterToInitiate = Character.toString(c);
			for (int i = 0; i < LetterPiece.valueOf(letterToInitiate).getAmountLetters(); i++) {
				put((T) LetterPiece.valueOf(letterToInitiate));
			}
		}
		put((T) LetterPiece.Ä);
		put((T) LetterPiece.Ö);
		put((T) LetterPiece.Ü);
	}
	
	/**
	 * Ein Stein wird wieder dem Sack zurückgeführt.
	 * @param piece
	 */
	public void put(T piece) {
		collection.add(piece);
	}
	
	/**
	 * Frei nach einer Zufallszahl wird ein Stein aus dem Sack entnommen.
	 * @return
	 */
	public T take() {
		int random = (int) (Math.random() * (collection.size()));
		T take = collection.get(random);
		collection.remove(collection.get(random));
		return take;
	}
	
	/**
	 * Sind noch Steine im Beutel vorhanden?
	 * @return
	 */
	public boolean empty() {
		if(collection.size() == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Gibt die noch vorhandenen Spielsteine aus.
	 */
	public String toString() {
		return String.valueOf(collection);
	}
	
}