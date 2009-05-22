package de.hsaugsburg.games.boardgames.scrabble;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class EventLogger implements InvocationHandler{
private IScrabbleEngine ise;
	public EventLogger(IScrabbleEngine ise){
		this.ise = ise;
	}
	@Override
	public Object invoke(Object arg0, Method arg1, Object[] arg2)
			throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

}
