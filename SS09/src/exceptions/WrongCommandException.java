package exceptions;

import java.io.IOException;

@SuppressWarnings("serial")
public class WrongCommandException extends IOException {
	
	public WrongCommandException() {
		super("Fehler ist aufgetreten");
	}
	
}