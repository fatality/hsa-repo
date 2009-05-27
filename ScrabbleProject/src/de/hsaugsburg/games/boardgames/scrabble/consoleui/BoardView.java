package de.hsaugsburg.games.boardgames.scrabble.consoleui;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import de.hsaugsburg.games.boardgames.*;
import de.hsaugsburg.games.boardgames.scrabble.consoleui.CommandProcessor.Command;

/**
 * @author Marc Rochow, Anja Radtke
 */
public class BoardView {
	
	private Board<?, ?> board;
	private Player currentPlayer;
	private StringBuffer buffer;
	private PrintWriter broadcast;
	private PrintWriter unicast;
	private StringWriter unistring;
	private StringWriter brostring;

	public BoardView(Board<?, ?> board) {
		this.board = board;
		broadcast = new PrintWriter(brostring = new StringWriter());
		unicast = new PrintWriter(unistring = new StringWriter());
	}
	
	public void setBoard(Board<?, ?> board) {
		this.board = board;
	}
	
	public void render() {
		clearBuffer();
		printBoard();
		printPlayer();
		broadcast.print(buffer);
		System.out.print(buffer);
	}
	
	private void printBoard() {
		buffer.append(board.toString());
	}
	
	public void printPlayer() {
		if (currentPlayer == null) return;
		buffer.append("Current player: " + currentPlayer.toString() + "\n");
	}
	
	public void setPlayer(Player player) {
		this.currentPlayer = player;
	}
	
	private void clearBuffer() {
		buffer = new StringBuffer(300);
	}

	public void printPoints(Player player) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("NEW POINTS: " + player.getLastPoints() + player.getName() + " TOTAL: " + player.getPoints());
		broadcast.println(buffer);
		System.out.println(buffer);
	}
	
	public void printAgreementLine(List<String> list){
		StringBuffer buffer = new StringBuffer();
		buffer.append("Word(s) to agree or reject:");
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			buffer.append(" ");
			buffer.append(it.next());
		}
		broadcast.println(buffer);
		System.out.println(buffer);
	}
	
	public void printHelp() {
		StringBuffer buffer = new StringBuffer();
		ICommand[] commands = Command.values();
		for (int i = 0; i < commands.length; i++) {
			buffer.append(commands[i].getToken()+ " " + commands[i].getHelpText() + '\n');
		}
		broadcast.print(buffer);
		System.out.print(buffer);
	}
	
	public void printMsg(String msg) {
		unicast.println(msg);
		System.out.println(msg);
	}
	
	public String getBroadcast() {
		broadcast.flush();
		brostring.flush();
		String tmp = brostring.toString();
		brostring.getBuffer().delete(0, brostring.getBuffer().capacity());
		return tmp;
	}
	
	public String getUnicast() {
		unicast.flush();
		unistring.flush();
		String tmp = unistring.toString();
		unistring.getBuffer().delete(0, unistring.getBuffer().capacity());
		return tmp;
	}
	
	public void close() {
		unicast.close();
		broadcast.close();
	}

}
