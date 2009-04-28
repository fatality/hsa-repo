package exceptions;

public class WrongEntryException extends IllegalArgumentException {
	
	public WrongEntryException() {
		super("Fehler ist aufgetreten");
	}
	
}