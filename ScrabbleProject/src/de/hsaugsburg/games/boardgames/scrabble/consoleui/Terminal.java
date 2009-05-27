package de.hsaugsburg.games.boardgames.scrabble.consoleui;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Terminal {

	private int id;
	
	public Terminal(int id) {
		this.id = id;
	}
	
	private String readMessage() {
		File file = new File(TerminalUtils.SERVER_MSG + id);
		if (file.exists() && file.canRead()) {
			String tmp = TerminalUtils.readFile(file);
			file.delete();
			return tmp;
		}
		return null;
	}
	
	private String checkRequest() {
		File file = new File(TerminalUtils.INPUT_REQUEST + id);
		if (file.exists() && file.canRead()) {
			String tmp = TerminalUtils.readFile(file);
			file.delete();
			return tmp;
		}
		return null;
	}
	
	private String getInput() {
		StringBuffer buffer = new StringBuffer();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			buffer.append(reader.readLine());
		} catch (IOException e) {
			
		}
		return buffer.toString();
	}
	
	private void run() {
		while (true) {
			String message = readMessage();
			if (message != null) {
				System.out.print(message);
			}
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				
			}
			
			String request = checkRequest();
			if (request != null) {
				System.out.print(request);
				String input = getInput();
				TerminalUtils.writeFile(new File(TerminalUtils.INPUT_RESPONSE + id), input);
			}
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				
			}
		}
	}

	public static void main(String[] args) {
		new Terminal(2).run();
	}
	
}
