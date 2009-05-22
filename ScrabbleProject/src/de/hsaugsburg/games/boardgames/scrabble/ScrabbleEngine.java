package de.hsaugsburg.games.boardgames.scrabble;

import de.hsaugsburg.games.boardgames.GridPoint;
import de.hsaugsburg.games.boardgames.scrabble.consoleui.BoardView;
import de.hsaugsburg.games.boardgames.exceptions.IllegalPieceOperationException;
import de.hsaugsburg.games.boardgames.exceptions.InvalidStateException;
import de.hsaugsburg.games.boardgames.exceptions.OutsideBoardException;

public class ScrabbleEngine implements IScrabbleEngine {
	
	private RandomPool<LetterPiece> pool;
	private WordManager manager;
	private CircularList<ScrabblePlayer> list;
	private ScrabbleBoard board;
	private BoardView view;
	private boolean notInitialized = true;
	private boolean first = true;
	private boolean agreement = false;
	
	public ScrabbleEngine(ScrabbleBoard board, BoardView view) {
		list = new CircularList<ScrabblePlayer>();
		pool = new RandomPool<LetterPiece>();
		manager = new WordManager(board);
		this.board = board;
		this.view = view;
	}
	
	/* (non-Javadoc)
	 * @see de.hsaugsburg.games.boardgames.scrabble.IScrabbleEngine#engageState(de.hsaugsburg.games.boardgames.scrabble.ScrabbleEngine.State)
	 */
	public void engageState(State newState) throws InvalidStateException, IllegalPieceOperationException, OutsideBoardException {
		State currentState = newState;
		boolean running = true;
		if (notInitialized && currentState != State.INITIAL) {
			throw new InvalidStateException("Start a new game first.");
		}
		if (agreement && (currentState != State.AGREEING && currentState != State.REJECTING)) {
			throw new InvalidStateException("Please agree or reject...");
		}
		if (!agreement && (currentState == State.AGREEING || currentState == State.REJECTING)) {
			throw new InvalidStateException("Can't agree or reject before commit...");
		}
		while(running) {
			switch(currentState) {
				case INITIAL:
					notInitialized = false;
					agreement = false;
					first = true;
					list.reset();
					board.reset();
					list.add(new ScrabblePlayer("Marc"));
					list.add(new ScrabblePlayer("Anja"));
					fillPool();
					list.previous();
					manager.setPlayer(list.next());
					view.setPlayer(manager.getPlayer());
					givePieces();
					running = false;
					break;
				case DROPPING:
					running = false;
					break;
				case REMOVING:
					running = false;
					break;
				case COMMITED:
					manager.commitLetterSequence(first);
					view.printAgreementLine(manager.getPorducedWords());
					agreement = true;
					running = false;
					break;
				case REJECTING:
					manager.removePreliminaryPieces();
					agreement = false;
					running = false;
					break;
				case AGREEING:		
					manager.calcScore();
					manager.changePreliminaryStatus();
					view.printPoints(manager.getPoints());
					view.printPoints(manager.getPlayer());
					manager.setPlayer(list.next());
					view.setPlayer(manager.getPlayer());
					givePieces();
					view.printPlayer();
					agreement = false;
					first = false;
					running = false;
					break;
			}
		}
	}
	
	private void fillPool() {
		LetterPiece[] pieces = LetterPiece.values();
		for (int i = 0; i < pieces.length; i++) {
			for (int j = 0; j < pieces[i].getCount(); j++) {
				pool.put(pieces[i]);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see de.hsaugsburg.games.boardgames.scrabble.IScrabbleEngine#givePieces()
	 */
	public void givePieces() {
		for (int i = manager.getPlayer().getMyPieces().size(); i < 7 && !pool.empty(); i++) {
			manager.getPlayer().receive(pool.take());
		}
	}
	
	/* (non-Javadoc)
	 * @see de.hsaugsburg.games.boardgames.scrabble.IScrabbleEngine#addPiece(de.hsaugsburg.games.boardgames.scrabble.LetterPiece, int, int)
	 */
	public void addPiece(LetterPiece piece, int row, int column) throws IllegalPieceOperationException, OutsideBoardException {
		manager.addPiece(piece, new GridPoint(row, column));
	}
	
	/* (non-Javadoc)
	 * @see de.hsaugsburg.games.boardgames.scrabble.IScrabbleEngine#removePiece(int, int)
	 */
	public void removePiece(int row, int column) throws IllegalPieceOperationException, OutsideBoardException {
		manager.removePiece(new GridPoint(row, column));
	}
	
	public enum State {
		INITIAL, DROPPING, REMOVING, COMMITED, REJECTING, AGREEING;
	}
	
}
