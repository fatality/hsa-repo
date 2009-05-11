package de.fhaugsburg.games.boardgames;

@SuppressWarnings("serial")
public class OutsideBoardException extends RuntimeException {
	
	public OutsideBoardException() {
		super("ROW DOESN'T EXIST");
	}
	
}