package de.hsaugsburg.games.boardgames.scrabble.graphicalui;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import de.hsaugsburg.games.boardgames.scrabble.consoleui.Terminal;
import de.hsaugsburg.games.boardgames.scrabble.consoleui.TerminalUtils;

public class SimpleGuiClient extends Terminal {
	
	private JFrame frame;
	private JTextArea boardArea;
	private JTextField textInput;
	private JLabel label;
	private boolean request;
	
	public SimpleGuiClient(int id) {
		super(id);
		frame = new JFrame("Scrabble Terminal");
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		boardArea = new JTextArea();
		boardArea.setEditable(false);
		boardArea.setFont(new Font(Font.MONOSPACED,Font.PLAIN,14));
		boardArea.setPreferredSize(new Dimension(380, 440));
		textInput = new JTextField();
		textInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(request) {
					TerminalUtils.writeFile(new File(TerminalUtils.INPUT_RESPONSE + ID), textInput.getText());
					textInput.setText("");
					SimpleGuiClient.this.request = false;
				}
				
			}
			
		});
		
		
		frame.add(BorderLayout.SOUTH, textInput);
		frame.add(BorderLayout.CENTER, boardArea);
		frame.setLocation(200, 200);
		frame.pack();
		frame.setVisible(true);
	}

	
	private void run() {
		while(true) {
			String message = readMessage();
			if(message != null) {
				boardArea.setText(message);
			}
			try {Thread.sleep(5);}
			catch (InterruptedException e) {}
			
			String request = checkRequest();
			if(request != null) {
				textInput.setText(request);	
				this.request = true;
			}
			try {Thread.sleep(5);}
			catch (InterruptedException e) {}
		}
	}
	
	public static void main(String[] args) {
		SimpleGuiClient gui = new SimpleGuiClient(1);
		gui.run();
	}
	
}