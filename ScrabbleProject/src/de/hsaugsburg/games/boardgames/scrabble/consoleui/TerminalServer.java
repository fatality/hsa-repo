package de.hsaugsburg.games.boardgames.scrabble.consoleui;

import java.io.File;
import java.io.Serializable;
import java.util.List;

public class TerminalServer implements Serializable {
	
	private static final long serialVersionUID = -9018733712413879187L;
	
	public void sendMessage(String msg, List<Integer> terminalIds) {
		for (Integer id : terminalIds) {
			sendMessage(msg, id);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				
			}
		}
	}
	
	public void sendMessage(String msg, int id) {
		File file = new File(TerminalUtils.SERVER_MSG + id);
		TerminalUtils.writeFile(file, msg);
	}
	
	public String getInputLine(int id, String request) {
		String inputLine;	
		TerminalUtils.writeFile(new File(TerminalUtils.INPUT_REQUEST + id), request);
		File file = new File(TerminalUtils.INPUT_RESPONSE + id);
		while (!file.exists() && !file.canRead()) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				
			}
		}	
		inputLine = TerminalUtils.readFile(file);
		file.delete();
		return inputLine;
	}

}
