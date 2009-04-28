package exceptions;

import java.io.IOException;

public class WrongCommandException extends IOException {
	
	public WrongCommandException() {
		super("Fehler ist aufgetreten");
	}
	
}