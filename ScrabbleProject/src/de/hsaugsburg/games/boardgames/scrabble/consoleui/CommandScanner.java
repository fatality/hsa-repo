package de.hsaugsburg.games.boardgames.scrabble.consoleui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import de.hsaugsburg.games.boardgames.ICommand;
import de.hsaugsburg.games.boardgames.scrabble.LetterPiece;

public class CommandScanner {
	
	private String[] strings;
	private ICommand[] commands;
	private ICommand command;
	
	public CommandScanner(ICommand[] commands) {
		this.commands = commands;
	}
	
	public Object next() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			String string = reader.readLine();
			strings = string.split(" ");
			command = null;
			for (int i = 0; i < commands.length; i++) {
				if (commands[i].getToken().compareToIgnoreCase(strings[0]) == 0) {
					command = commands[i];
					break;
				}
			}
			if(strings.length-1 < command.getParamTypes().length && command.hasOptionalParams()) {
				return command;
			}
			for (int j = 0; j < command.getParamTypes().length; j++) {
				if (command.getParamTypes()[j] == int.class) {
					command.getParams()[j] = Integer.parseInt(strings[j+1])-1;
				} else if (command.getParamTypes()[j] == char.class) {
					Character c = strings[j+1].toUpperCase().charAt(0);
					command.getParams()[j] = (int)(c-'A');
				} else if (command.getParamTypes()[j] == LetterPiece.class) {
					command.getParams()[j] = LetterPiece.valueOf(strings[j+1].toUpperCase());
				} else if (command.getParamTypes()[j] == String.class) {
					command.getParams()[j] = strings[j+1];
				}
			}
		} catch(Exception ex) {
			System.out.print("Unknown command: " + strings[0] + " ");
			if (command != null) {
				System.out.println(command.getHelpText());
			} else {
				System.out.println();
			}
			next();
		}
		return command;
	}
	
}
