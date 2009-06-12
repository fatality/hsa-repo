package de.hsaugsburg.games.boardgames.scrabble.graphicalui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import javax.swing.JPanel;
import de.hsaugsburg.games.boardgames.scrabble.LetterPiece;
import de.hsaugsburg.games.boardgames.scrabble.ScrabbleBoard;

public class DScrabbleBoard extends JPanel {
	
	private static final long serialVersionUID = 880738091454614694L;
	private ScrabbleBoard board;
	private int width;
	private int height;
	
	public DScrabbleBoard(ScrabbleBoard board) {
		this.board = board;
	}
	
	public void setBoard(ScrabbleBoard board) {
		this.board = board;
	}
	
	public void render() {
		this.setVisible(true);
		repaint();
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(450, 450);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		if (board == null) return;
		super.paintComponent(g);

		this.width = this.getWidth()/board.getWidth();
		this.height = this.getHeight()/board.getHeight();
		
		for (int row = 0; row < board.getWidth(); row++) {
			for (int column = 0; column < board.getHeight(); column++) {
			
				int x = column*width;
				int y = row*height;
				
				if (board.getPiece(row, column) != null) {		
					if (board.getDetails(row, column).isFixed()) {
						g.setColor(Color.DARK_GRAY);
					} else {
						g.setColor(Color.GRAY);
					}
					g.fill3DRect(x, y, width, height, true);
					g.setColor(Color.WHITE);
					g.setFont(new Font("Tahoma", Font.BOLD, (int)(height*0.4)));
					g.drawString(board.getPiece(row, column).name(), x+width/3 , y+height/2 );
					g.setFont(new Font("Tahoma", Font.PLAIN, (int)(this.height*0.3)));
					g.drawString(Integer.toString(board.getPiece(row, column).getPoints()), x+(width/3)*2, y+(height/3)*2);
				} else if(row == 7 && column == 7) {
					g.setColor(new Color(150, 150, 10));
					g.fill3DRect(x, y, width, height, true);
				} else if (board.getDetails(row, column).getWordMultiplier() == 2) {
					g.setColor(new Color(150, 120, 70));
					g.fill3DRect(x, y, width, height, true);
				} else if(board.getDetails(row, column).getWordMultiplier() == 3) {
					g.setColor(new Color(150, 70, 50));
					g.fill3DRect(x, y, width, height, true);
				} else if(board.getDetails(row, column).getLetterMultiplier() == 1) {
					g.setColor(new Color(80, 100, 80));
					g.fill3DRect(x, y, width, height, true);
				} else if(board.getDetails(row, column).getLetterMultiplier() == 2) {
					g.setColor(new Color(40, 80, 150));
					g.fill3DRect(x, y, width, height, true);
				} else if(board.getDetails(row, column).getLetterMultiplier() == 3) {
					g.setColor(new Color(40, 120, 150));
					g.fill3DRect(x, y, width, height, true);
				}
			}
		}
	}
	
	public Dimension getSquareDimension() {
		return new Dimension(width, height);
	}
	
	public class ScrabbleDropTargetListener extends DropTargetAdapter {
	    private DropTarget dropTarget;
	    private JPanel panel;

	    public ScrabbleDropTargetListener() {
	    	this.panel = DScrabbleBoard.this;
	    	dropTarget = new DropTarget(panel, DnDConstants.ACTION_MOVE, this, true, null);
	    }

	    public void drop(DropTargetDropEvent event) {
	    	try {
	    		Transferable tr = event.getTransferable();
	    		DLetterPiece piece = (DLetterPiece) tr.getTransferData(DLetterPiece.FLAVOR);
	    		if (event.isDataFlavorSupported(DLetterPiece.FLAVOR)) {
	    			event.acceptDrop(DnDConstants.ACTION_MOVE);
//	    			event.getLocation()
	    			event.dropComplete(true);
	    			return;
	    		}
	    		event.rejectDrop();
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    		event.rejectDrop();
	    	}
	    }
	}
	
}
