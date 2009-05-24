package de.hsaugsburg.games.boardgames.scrabble;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EventLogger implements InvocationHandler{

	private IScrabbleEngine ise;
	
	public EventLogger(IScrabbleEngine ise){
		this.ise = ise;
	}
	@Override
	public Object invoke(Object poxy, Method method, Object[] args)
			throws Throwable {
		 System.out.print("* calling method " + method + " with params ");
	        for (int i = 0; i < args.length; i++) 
	            System.out.print(" " + args[i]);
	        System.out.println();
	        
	        Object result = null;
	        try  {
	            result = method.invoke(ise, args);
	        } catch(IllegalAccessException ex)  {
	            ex.printStackTrace();
	        } catch(InvocationTargetException ex)  {
	            System.out.println("* exception:" + ex.getTargetException());
	            throw ex.getTargetException();
	        }

	        System.out.println("* result:" + result);
	        return result;
		
	}

}
