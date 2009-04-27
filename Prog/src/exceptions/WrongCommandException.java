package exceptions;

import java.io.IOException;

public class WrongCommandException extends IOException {

	public WrongCommandException() {
		super("Ein Fehler ist aufgetreten!");
	}

}