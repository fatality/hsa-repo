package de.hsaugsburg.games.boardgames.scrabble;

import java.util.ArrayList;
import java.util.List;

public class CircularList<T> {
	
	private int current;
	private List<T> list = new ArrayList<T>();
	
	public void add(T t) {
		list.add(t);
	}
	
	public T next() {
		if (list.size() != 0) {
			return list.get(current = ++current%list.size());
		} else {
			return null;
		}
	}
	
	public T previous() {
		if (list.size() != 0) {
			return list.get(current =(--current+list.size())%list.size());
		} else {
			return null;
		}
	}
	
	public List<T> getAll() {
		return list;
	}
	
	public void reset() {
		list.clear();
		current = 0;
	}
	
}
