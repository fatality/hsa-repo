package de.hsaugsburg.games.boardgames.scrabble.consoleui;

import de.hsaugsburg.games.boardgames.ICommand;
import de.hsaugsburg.games.boardgames.exceptions.IllegalPieceOperationException;
import de.hsaugsburg.games.boardgames.exceptions.InvalidStateException;
import de.hsaugsburg.games.boardgames.exceptions.OutsideBoardException;
import de.hsaugsburg.games.boardgames.scrabble.IScrabbleEngine;
import de.hsaugsburg.games.boardgames.scrabble.LetterPiece;
import de.hsaugsburg.games.boardgames.scrabble.ScrabbleEngine.State;

/**
 * @author Marc Rochow, Anja Radtke
 */
public class CommandProcessor {
	
	private IScrabbleEngine engine;
	private BoardView view;
	
	public CommandProcessor(IScrabbleEngine engine, BoardView view) {
		this.engine = engine;
		this.view = view;
	}
	
	CommandScanner scanner = new CommandScanner(Command.values());
	public void process() {
		while (true) {
			Command command = (Command)scanner.next();  
			Object[] params = command.getParams();
			
			try {
				switch(command) {
					case NEWGAME:
						engine.engageState(State.INITIAL);
						view.render();
						break;
					case ADD:			
						engine.engageState(State.DROPPING);
						engine.addPiece((LetterPiece)params[0], (Integer)params[1], (Integer)params[2]);		
						view.render();
						break;
					case REMOVE:				
						engine.engageState(State.REMOVING);
						engine.removePiece((Integer)params[0], (Integer)params[1]);
						view.render();
						break;
					case COMMIT:
						engine.engageState(State.COMMITED);
						break;
					case AGREE:
						engine.engageState(State.AGREEING);
						break;
					case REJECT:
						engine.engageState(State.REJECTING);
						view.render();
						break;
					case RENDER:
						view.render();
						break;
					case HELP:
						view.printHelp();
						break;
					case EXIT:
						System.exit(0);
						break;
				}
			} catch (OutsideBoardException e) {
				System.out.println(e.getMessage());
			} catch (InvalidStateException e) {
				System.out.println(e.getMessage());
			} catch (IllegalPieceOperationException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public enum Command implements ICommand {
		NEWGAME ("newgame",  "<sp | mp> * start new game in Single Player or MultiPlayer Mode"  , true,  String.class),
		ADD     ("add"    ,  "<letter> <rowlt> <columnnr>  * the letter to place, an intger and a letter in range" , false,  LetterPiece.class, char.class, int.class),
		REMOVE	("remove" ,	 "<rowlt> <columnnr>  * a letter and an integer in range"           , false,  char.class, int.class),
		COMMIT	("commit" ,  "* commit word", false),
		AGREE	("agree"  ,  "* agree that the lastly commited word(s) is(are) real", false),
		REJECT	("reject",	 "* reject lastly commited word(s) ", false),
		RENDER  ("render" ,  "* render board", false), 
		HELP    ("help"   ,  "* list all commands", false),
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
			if (paramTypes.length > 0) {
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
