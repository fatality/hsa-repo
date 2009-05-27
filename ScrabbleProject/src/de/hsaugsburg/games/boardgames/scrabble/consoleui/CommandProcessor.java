package de.hsaugsburg.games.boardgames.scrabble.consoleui;

import de.hsaugsburg.games.boardgames.ICommand;
import de.hsaugsburg.games.boardgames.exceptions.GameException;
import de.hsaugsburg.games.boardgames.exceptions.IllegalPieceOperationException;
import de.hsaugsburg.games.boardgames.exceptions.InvalidStateException;
import de.hsaugsburg.games.boardgames.exceptions.OutsideBoardException;
import de.hsaugsburg.games.boardgames.scrabble.IScrabbleEngine;
import de.hsaugsburg.games.boardgames.scrabble.LetterPiece;
import de.hsaugsburg.games.boardgames.scrabble.ScrabbleEngine.State;
import de.hsaugsburg.games.boardgames.scrabble.strategy.GreedyScrabbleBot;

/**
 * @author Marc Rochow, Anja Radtke
 */

public class CommandProcessor {
	private IScrabbleEngine engine;
	private BoardView view;
	private TerminalServer server;
	
	public CommandProcessor(IScrabbleEngine engine, BoardView view) {
		this.engine = engine;
		this.view = view;
		this.server = new TerminalServer();
	}
	
	public void process() {
		while(true) {
			try {
			Command command = (Command)engine.next();  
			Object[] params = command.getParams();

				switch(command) {
					case NEWGAME:
						engine.engageState(State.INITIAL);
						engine.setMode((String)params[0]);
						engine.reset();
						engine.addDefaultPlayers(3);
						engine.fillPool();
						engine.getList().previous();
						engine.getManager().setPlayer(engine.getList().next());
						view.setPlayer(engine.getManager().getPlayer());
						engine.givePieces();
						view.render();
						break;
					case PLAYER:
						engine.engageState(State.PLAYER);
						engine.addPlayer((String)params[0]);
						break;
					case ADD:			
						engine.engageState(State.DROPPING);
						engine.addPiece((LetterPiece)params[0], 
								(Integer)params[1], (Integer)params[2]);		
						view.render();
						break;
					case REMOVE:				
						engine.engageState(State.REMOVING);
						engine.removePiece((Integer)params[0], (Integer)params[1]);
						view.render();
						break;
					case COMMIT:
						engine.engageState(State.COMMITED);
						engine.getManager().commitLetterSequence(engine.isFirst());
						view.printAgreementLine(engine.getManager().getProducedWords());
						break;
					case PASS:
						engine.engageState(State.PASSING);
						engine.getManager().removePreliminaryPieces();
						engine.getManager().calcScore();
						view.printPoints(engine.getManager().getPlayer());
						engine.getManager().setPlayer(engine.getList().next());
						view.setPlayer(engine.getManager().getPlayer());
						engine.givePieces();
						view.render();
						break;
					case AGREE:
						engine.engageState(State.AGREEING);
						engine.getManager().calcScore();
						engine.getManager().changePreliminaryStatus();
						view.printPoints(engine.getManager().getPlayer());
						engine.getManager().setPlayer(engine.getList().next());
						view.setPlayer(engine.getManager().getPlayer());
						engine.givePieces();
						view.render();
						break;
					case REJECT:
						engine.engageState(State.REJECTING);
						engine.getManager().removePreliminaryPieces();
						view.render();
						break;
					case RENDER:
						view.render();
						break;
					case HELP:
						view.printHelp();
						break;
					case SAVE:
						engine.save();
						break;
					case LOAD:
						this.engine = engine.load();
						view.setBoard(engine.getBoard());
						view.setPlayer(engine.getList().current());
						engine.getManager().resetLogger();
						GreedyScrabbleBot.loadList();
						view.render();
						break;
					case EXIT:
						view.close();
						System.exit(0);
						break;
				}
			} catch (OutsideBoardException e) {
				 view.printMsg(e.getMessage());
			} catch (InvalidStateException e) {
				view.printMsg(e.getMessage());
			} catch (IllegalPieceOperationException e) {
				view.printMsg(e.getMessage());
			} catch (GameException e) {
				view.printMsg(e.getMessage());
			} finally {
				String str = view.getUnicast();
				if (!engine.getTerminalIds().isEmpty() && str.isEmpty()) {
					server.sendMessage(view.getBroadcast(), engine.getTerminalIds());
				} else {
					server.sendMessage(view.getBroadcast() + str, engine.getCurrentTerminalId());
				}
			}
		} 
	}
	
	public enum Command implements ICommand {
		NEWGAME ("newgame",  "<sp | mp | cp> * start new game in single- or multiplayer mode", true,  String.class),
		PLAYER	("player" ,  "<name> <terminal> * add new players or else default players will be used", true, String.class),
		ADD     ("add"    ,  "<letter> <rowlt> <columnnr>  * a letter and coordinates in range", false,  LetterPiece.class, char.class, int.class),
		REMOVE	("remove" ,	 "<rowlt> <columnnr>  * a letter and an integer in range", false,  char.class, int.class),
		COMMIT	("commit" ,  "* commit word", false),
		PASS	("pass"   ,  "* skip one turn", false),
		AGREE	("agree"  ,  "* agree that the lastly commited word(s) is(are) real", false),
		REJECT	("reject" ,	 "* reject lastly commited word(s) ", false),
		RENDER  ("render" ,  "* render board", false), 
		HELP    ("help"   ,  "* list all commands", false),
		SAVE	("save"   ,  "* save current game", false),
		LOAD	("load"   ,  "* load lastly saved game", false),
		EXIT    ("exit"   ,  "* exit program", false);
		
		private String     token; 
		private String     helpText; 
		private Class<?>[] paramTypes;
		private Object[]   params;
		private boolean	   optionalParams;
		
		private Command(String token, String helpText, boolean optionalParams, Class<?>... paramTypes) {
			this.token = token;
			this.helpText = helpText;
			this.paramTypes = paramTypes;
			this.optionalParams  = optionalParams;
			if(paramTypes.length > 0) {
				params = new Object[paramTypes.length];
			}
		}
		
		public String getToken() {
			return token;
		}
		
		public String getHelpText() {
			return helpText;
		}
		public Class<?>[] getParamTypes() {
			return paramTypes;
		}
		
		public Object[] getParams() {
			return params;
		}
		
		public boolean hasOptionalParams() {
			return optionalParams;
		}
		
    }
	
}
