package de.hsaugsburg.games.boardgames.scrabble.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import javax.swing.JFrame;
import javax.swing.JPanel;
import de.hsaugsburg.games.boardgames.scrabble.LetterPiece;
import de.hsaugsburg.games.boardgames.scrabble.ScrabbleBoard;
import de.hsaugsburg.games.boardgames.scrabble.graphicalui.DLetterPiece;
import de.hsaugsburg.games.boardgames.scrabble.graphicalui.DScrabbleBoard;

public class ScrabbleGuiTest extends JFrame implements DragGestureListener {
	
	private static final long serialVersionUID = -1646645392100796432L;

	public ScrabbleGuiTest() {
		add(BorderLayout.CENTER, new DScrabbleBoard(new ScrabbleBoard()));
		JPanel piecesHull = new JPanel();
		piecesHull.add(createPiecePanel());
		add(BorderLayout.SOUTH, piecesHull);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	private JPanel createBoardPanel() {
		DScrabbleBoard dBoard = new DScrabbleBoard(new ScrabbleBoard());
		JPanel boardPanel = new JPanel();
		boardPanel.add(dBoard);
		return boardPanel;
	}
	
	private JPanel createPiecePanel() {
		JPanel pieces = new JPanel();
		pieces.setLayout(new GridLayout(1, 7));
		for (int j = 0; j < 7; j++) {
			pieces.add(new DLetterPiece(LetterPiece.values()[(int) (Math.random() * 28)]));
		}
		
		return pieces;
	}
	
	public static void main(String[] args) {
		new ScrabbleGuiTest();
	}

	@Override
	public void dragGestureRecognized(DragGestureEvent event) {
		Cursor cursor = null;
	    DLetterPiece piece = (DLetterPiece) event.getComponent();
	    if (event.getDragAction() == DnDConstants.ACTION_MOVE) {
	      cursor = DragSource.DefaultMoveDrop;
	    }
	    event.startDrag(cursor, piece);
		
	}
	
}
