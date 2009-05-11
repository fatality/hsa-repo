package de.fhaugsburg.games.boardgames.scrabble.management;

import java.util.ArrayList;
import java.util.List;

public class CircularList<T> {
	
	private int current = 0;
	private List<T> list;
	
	public CircularList() {
		list = new ArrayList<T>(0);
		this.current = current;
	}
	
	/**
	 * Spieler hinzufügen.
	 * @param t
	 */
	public void add(T t) {
		list.add(t);
	}
	
	/**
	 * Zu nächstem Spieler gehen.
	 * Von list.size() muss 1 abgezogen werden, da list.size() die volle Listenlänge + 0 zurückgibt.
	 * @return
	 */
	public T next() {
		if (current < list.size() - 1) {
			current++;
		} else {
			current = 0;
		}
		return list.get(current);
	}
	
	/**
	 * Resetten der Spielerliste
	 */
	public void reset() {
		list = new ArrayList<T>(0);
		current = 0;
	}
	
	/**
	 * Getter für den vorangegangenen Spieler
	 * @return
	 */
	public T prev() {
		if(current > 0) {
			current--;
		} else {
			current = list.size() - 1;
		}
		return list.get(current);
	}
	
	/**
	 * Rückgabe der Spieler in der Liste
	 * @return
	 */
	public List<T> getAll() {
		return list;
	}
	
	/**
	 * Liefert den aktuellen Spieler.
	 * @return
	 */
	public T getCurrent() {
		return list.get(current);
	}
	
}