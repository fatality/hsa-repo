package de.hsaugsburg.games.boardgames.scrabble.graphicalui;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

public class ScrabbleTransferHandler extends TransferHandler {

	private static final long serialVersionUID = -4727985808798002129L;

	public class ListTransferHandler extends TransferHandler {
		
		public int getSourceActions(JComponent c) {
		    return MOVE;
		}

		public Transferable createTransferable(JComponent c) {
			DLetterPiece piece = null;
			try {
				piece = (DLetterPiece)((DLetterPiece) c).getTransferData(new DataFlavor(DLetterPiece.class, "DLetterPiece"));
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		    return piece;
		}

		public void exportDone(JComponent c, Transferable t, int action) {
		    if (action == MOVE) {
		        c.getParent().remove(c);
		    }
		}
	}
	
}
