package de.fhaugsburg.games.boardgames.scrabble.management;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;
import de.fhaugsburg.games.boardgames.LetterPiece;
import de.fhaugsburg.games.boardgames.scrabble.ScrabbleBoard;

/**
 * @ToDo this file needs to be configured, everything else works fine
 */
public class WordManager {
	
	private ScrabbleBoard board;
	@SuppressWarnings("unused")
	private ManhattenDirection manhattenDirection;
	@SuppressWarnings("unused")
	private GridPoint gridPoint;
	
	public WordManager(ScrabbleBoard board) {
		this.board = board;
		this.placedGPs = new TreeSet<GridPoint>();
	}
	
	/**
	 * Die Buchstaben, die ein Spieler ablegt, müssen in einer horizontalen oder vertikalen Linie liegen.
	 * Beim ersten Ablegen muss der Mittelpunkt des Spielbretts mit abgedeckt sein.
	 * @param piece
	 * @param point
	 */
	// Ob der erste Punkt gesetzt wurde muss noch über die STATES reingeholt werden
	@SuppressWarnings("unchecked")
	private SortedSet placedGPs;
	private int column, row;
	
	@SuppressWarnings("unchecked")
	public void addPiece(LetterPiece piece, GridPoint point){
		if(point.isManhattenColinearWith(placedGPs)) {
			board.putPiece(column, row, piece);
			placedGPs.add(point);
		}
	}
	
	/**
	 * Entfernen eines Spielsteines.
	 * @param gp
	 */
	public void removePiece(GridPoint gp) {
		// Gridpoint löschen
		placedGPs.remove(gp);
		// Das darin enthaltene Piece löschen
		board.getSquare(gp.column, gp.row).removePiece();
	}
	
	/**
	 * Liefert alle jemals gesetzten, akzeptierten Wörter zurück.
	 * @return
	 */
	public List<String> getProducedWords() {
		List<String> words = new Vector<String>();
		
		for (@SuppressWarnings("unused") String gpList : getProducedWords()) {
			
		}
		return words;
	}
	
	public void commitLetterSequence(boolean first) {
		
	}
	
	public List<LetterPiece> removePreliminaryPieces() {
		return null;
		
	}
	
	public int changePreliminaryStatus() {
		return 0;
		
	}
	
	public int calcScore() {
		return 0;
		
	}
}