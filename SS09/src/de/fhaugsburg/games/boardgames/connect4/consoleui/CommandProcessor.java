package de.fhaugsburg.games.boardgames.connect4.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import de.fhaugsburg.games.boardgames.OutsideBoardException;
import de.fhaugsburg.games.boardgames.connect4.Connect4Engine;
import exceptions.GameOver;

public class CommandProcessor {
	private Connect4Engine engine = new Connect4Engine();
	private BoardView render = new BoardView();
	private String commandInput;
	private Command command;
	
	/**
	 * @throws IOException
	 */
	public void game() throws IOException {
		
		while(true) {
			command = null;
			System.out.println("Kommando eingeben!");
			BufferedReader read = new BufferedReader (new InputStreamReader(System.in));
			this.commandInput = read.readLine();
	
			String[] inputRefine = commandInput.split(" ");
			try {
				command = Command.valueOf(inputRefine[0].toUpperCase());
			} catch(IllegalArgumentException e) {
				System.out.println("WRONG COMMAND!");
				command = Command.HELP;
			}
			
			switch(command) {
			case NEWGAME:
				engine.setNewGame(inputRefine[1]);
				break;
			case ADD:
				try {
					engine.turn(Integer.parseInt(inputRefine[1]) -1);
					render.render(engine.set);
				} catch(OutsideBoardException e) {
					System.err.println(e.getMessage());
				} catch(ColumnOverrunException e) {
					System.out.println(e.getMessage());
				} catch(GameOver e3) {
					System.out.println("Game over");
					System.exit(0);
				}
				break;
			case HELP:
				engine.help();
				break;
			case EXIT:
				System.out.println("Good bye!");
				System.exit(0);
				break;
			}
		}
		
	}
	
}