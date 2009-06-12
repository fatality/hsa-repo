package de.hsaugsburg.games.boardgames.scrabble.graphicalui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JComponent;

import de.hsaugsburg.games.boardgames.scrabble.LetterPiece;

public class DLetterPiece extends JComponent implements Transferable {
	
	private static final long serialVersionUID = 2761772336322153951L;
	public static final DataFlavor FLAVOR = new DataFlavor(DLetterPiece.class, "DLetterPiece");
	public static final DataFlavor[] FLAVORS = new DataFlavor[]{ FLAVOR };
	private LetterPiece piece;
	
	public DLetterPiece(LetterPiece piece) {
		this.piece = piece;
		this.setTransferHandler(new ScrabbleTransferHandler());
	}
	
	@Override
	public Dimension getPreferredSize() {
		if (getParent() instanceof DScrabbleBoard) {
			return ((DScrabbleBoard) getParent()).getSquareDimension();
		}
		return new Dimension(30, 30);
	};
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.DARK_GRAY);
		g.fill3DRect(0, 0, this.getWidth(), this.getHeight(), true);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Tahoma", Font.BOLD, (int)(this.getHeight()*0.4)));
		g.drawString(piece.name(), this.getWidth()/3 , this.getHeight()/2 );
		g.setFont(new Font("Tahoma", Font.PLAIN, (int)(this.getHeight()*0.3)));
		g.drawString(Integer.toString(piece.getPoints()), (this.getWidth()/3)*2, (this.getHeight()/3)*2);

	}

	@Override
	public Object getTransferData(DataFlavor df)
			throws UnsupportedFlavorException, IOException {
		if(!df.equals(FLAVOR)) {
			throw new UnsupportedFlavorException(df);
		}
		return this;
	}
	
	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return FLAVORS;
	}
	
	@Override
	public boolean isDataFlavorSupported(DataFlavor df) {
		return df.equals(FLAVOR);
	}
}
