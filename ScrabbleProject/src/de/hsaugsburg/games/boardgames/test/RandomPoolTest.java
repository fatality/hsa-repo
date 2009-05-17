package de.hsaugsburg.games.boardgames.test;

import de.hsaugsburg.games.boardgames.scrabble.LetterPiece;
import de.hsaugsburg.games.boardgames.scrabble.RandomPool;
import de.hsaugsburg.games.boardgames.scrabble.ScrabblePlayer;

public class RandomPoolTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		RandomPool<LetterPiece> pool = new RandomPool<LetterPiece>();
		LetterPiece[] pieces = LetterPiece.values();
		ScrabblePlayer player = new ScrabblePlayer("player");
		
		
		for (int i = 0; i < pieces.length; i++) {
			for (int j = 0; j < pieces[i].getCount(); j++) {
				pool.put(pieces[i]);
			}
		}
		
		System.out.println(pool);
		System.out.println(pool.getCollection().size()); //Usually 100 letters.
		
		for (int i = player.getMyPieces().size(); i < 7 && !pool.empty(); i++) {
			player.receive(pool.take());
		}
		
		System.out.println(player.getMyPieces());
		System.out.println(pool);
	}

}
