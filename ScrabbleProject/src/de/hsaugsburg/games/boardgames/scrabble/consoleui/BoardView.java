package de.hsaugsburg.games.boardgames.scrabble.consoleui;

import java.util.Iterator;
import java.util.List;
import de.hsaugsburg.games.boardgames.*;
import de.hsaugsburg.games.boardgames.scrabble.LetterPiece;
import de.hsaugsburg.games.boardgames.scrabble.ScrabblePlayer;
import de.hsaugsburg.games.boardgames.scrabble.ScrabbleSquareDetails;
import de.hsaugsburg.games.boardgames.scrabble.consoleui.CommandProcessor.Command;

/**
 * @author Marc Rochow, Anja Radtke
 */
public class BoardView {
	
	private Board<LetterPiece, ScrabbleSquareDetails> board;
	private ScrabblePlayer player;
	private StringBuffer buffer;
	
	public BoardView(Board<LetterPiece, ScrabbleSquareDetails> board) {
		this.board = board;
	}
	
	public void render() {
		clearBuffer();
		printNumbers();
		printBoard();
		printNumbers();
		System.out.print(buffer);
		printPlayer();
	}
	
	private void printBoard() {
		for (int row = 0; row < board.getWidth(); row++) {
			buffer.append((char)(row + 'A'));
			for (int column = 0; column < board.getHeight(); column++) {
				if (board.getPiece(row, column) != null) {
					if (board.getDetails(row, column).isFixed()) {
						buffer.append("[" + board.getPiece(row, column) + "]");
					} else {
						buffer.append(" " + board.getPiece(row, column)+ " ");
					}
				} else if(row == 7 && column == 7) {
					buffer.append(" * ");
				} else if (board.getDetails(row, column).getWordMultiplier() == 2) {
					buffer.append(" __");
				} else if(board.getDetails(row, column).getWordMultiplier() == 3) {
					buffer.append("___");
				} else if(board.getDetails(row, column).getLetterMultiplier() == 1) {
					buffer.append(" . ");
				} else if(board.getDetails(row, column).getLetterMultiplier() == 2) {
					buffer.append(" ..");
				} else if(board.getDetails(row, column).getLetterMultiplier() == 3) {
					buffer.append("...");
				}
			}
			buffer.append((char)(row + 'A'));
			buffer.append("\n");
		}
	}
	
	private void printNumbers() {
		buffer.append(" ");
		for (int row = 1; row < 10; row++) {
			buffer.append(" " + row + " ");
		}
		for (int row = 10; row <= board.getWidth(); row++) {
			buffer.append(row + " ");
		}
		buffer.append("\n");
	}
	
	public void printPlayer() {
		if(player == null) return;
		System.out.println("Current player: " + player.getName() + "\nPieces: " + player.getMyPieces().toString());
	}
	
	public void setPlayer(ScrabblePlayer player) {
		this.player = player;
	}
	
	private void clearBuffer() {
		buffer = new StringBuffer(300);
	}
	
	public void printPoints(int points) {
		System.out.println("NEW POINTS: " + points);
	}
	
	public void printPoints(Player player) {
		System.out.println(player.getName() + " TOTAL: " + player.getPoints());
	}
	
	public void printAgreementLine(List<String> list){
		StringBuffer sb = new StringBuffer();
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			sb.append(" ");
			sb.append(it.next());
		}
		System.out.println("Word(s) to agree or reject:" + sb.toString());
	}
	
	public void printHelp() {
		StringBuffer buffer = new StringBuffer();
		ICommand[] commands = Command.values();
		for (int i = 0; i < commands.length; i++) {
			buffer.append(commands[i].getToken()+ " " + commands[i].getHelpText() + '\n');
		}
		System.out.print(buffer);
	}

}
