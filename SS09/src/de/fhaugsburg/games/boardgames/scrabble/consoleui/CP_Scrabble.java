package de.fhaugsburg.games.boardgames.scrabble.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import de.fhaugsburg.games.boardgames.LetterPiece;
//import de.fhaugsburg.games.boardgames.OutsideBoardException;
import de.fhaugsburg.games.boardgames.connect4.consoleui.Command;
import de.fhaugsburg.games.boardgames.scrabble.BoardView;
import de.fhaugsburg.games.boardgames.scrabble.ScrabbleEngine;
//import exceptions.GameOver;

public class CP_Scrabble {
	
	@SuppressWarnings("unused")
	private BoardView render = new BoardView();
	
	private String commandInput;
	private Command command;
	ScrabbleEngine scrabbleengine = new ScrabbleEngine();
	private BoardView boardview = new BoardView();
	
	
	public void scrabble() throws IOException {
		while(true) {
			command = null;
			System.out.println("PLEASE INSERT COMMAND");
			BufferedReader read = new BufferedReader (new InputStreamReader(System.in));
			this.commandInput = read.readLine();
		
			String[] inputRefine = commandInput.split(" ");
			command = Command.valueOf(inputRefine[0].toUpperCase());

			switch(command) {
			case ADD:
				scrabbleengine.setPiece(Integer.parseInt(inputRefine[1])-1, Integer.parseInt(inputRefine[2])-1, LetterPiece.valueOf(inputRefine[3]));
				boardview.render(scrabbleengine.board);
				break;
			case COMMIT:
				scrabbleengine.finishedWord();
				break;
			case EXIT:
				System.out.println("Good bye!");
				System.exit(0);
				break;
			}
		}
	}
	
}