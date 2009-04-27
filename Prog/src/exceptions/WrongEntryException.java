package exceptions;

public class WrongEntryException extends IllegalArgumentException {

	public WrongEntryException() {
		super("Ein Fehler ist aufgetreten!");
	}

}