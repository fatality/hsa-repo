package de.fhaugsburg.games.boardgames.connect4.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import de.fhaugsburg.games.boardgames.OutsideBoardException;
import de.fhaugsburg.games.boardgames.PlayerMode;
import de.fhaugsburg.games.boardgames.connect4.Connect4Engine;
import exceptions.FullException;
import exceptions.GameOver;

public class CommandProcessor  {

	private Connect4Engine engine = new Connect4Engine(PlayerMode.SINGLE);
	private BoardView render = new BoardView();
	private String commandInput;
	private Command command;

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
				if (inputRefine[1].equals("MP")) {
					engine.setNewGame(PlayerMode.MULTI);
				} else if (inputRefine[1].equals("SP")) {
					engine.setNewGame(PlayerMode.SINGLE);
				} else {
					System.out.println("NEWGAME <SP|MP>");
					System.out.println("  SP = Single Player");
					System.out.println("  MP = Multi Player");
				}
			break;

			case ADD:
				try {
					engine.turn(Integer.parseInt(inputRefine[1]) -1);
					render.render(engine.set);
				} catch(OutsideBoardException e1) {
					System.out.println("Falsche Spalte");
				} catch(FullException e2) {
					System.out.println("Volle Spalte!");
				} catch(GameOver e3) {
					System.out.println("Game Over");
					System.exit(0);
				} catch(NumberFormatException e) {
					System.out.println("WRONG COMMAND!");
					command = Command.HELP;
				} catch(ArrayIndexOutOfBoundsException e) {
					System.out.println("WRONG COMMAND!");
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