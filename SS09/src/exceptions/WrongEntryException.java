package exceptions;

@SuppressWarnings("serial")
public class WrongEntryException extends IllegalArgumentException {
	
	public WrongEntryException() {
		super("Fehler ist aufgetreten");
	}
	
}