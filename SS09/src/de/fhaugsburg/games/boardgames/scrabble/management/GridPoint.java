package de.fhaugsburg.games.boardgames.scrabble.management;

import java.util.Set;

public class GridPoint {
	
	public final int row;
	public final int column;
	
	public GridPoint(int column, int row){
		this.column = column;
		this.row = row;
	}
	
	private GridPoint gridPoint;
	
	/**
	 * Vergleicht zwei Gridpoints miteinander.
	 * Um zwei GridPoints zu vergleichen ist ein entsprechender Cast des Objekts von Nöten.
	 */
	public boolean equals(Object o)
	{
		if((this.column == ((GridPoint) o).column) && (this.row == ((GridPoint) o).row)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Negiert einen GridPoint z.B. wird aus GridPoint mit den Koordinaten (1/2) der GridPoint (-1/-2)
	 * @return
	 */
	public GridPoint neg() {
		gridPoint = new GridPoint(column * (-1), row * (-1));
		return gridPoint;
	}
	
	/**
	 * Für Addition von zwei GridPoints, aufgefasst als Vektoren.
	 * @param delta
	 * @return
	 */
	public GridPoint plus(GridPoint delta) {
		delta = new GridPoint(delta.column + column, delta.row + row);
		return delta;
	}
	
	/**
	 * Für Subtraktion zweier GridPoints.
	 * @param delta
	 * @return
	 */
	public GridPoint minus(GridPoint delta) {
		delta = new GridPoint(delta.column - column, delta.row - row);
		return delta;
	}
	
	/**
	 * Überschreibt die entsprechende Methode im Interface Comparable und entscheidet, ob ein Punkt
	 * links von bzw. unter einem anderen liegt.
	 * @param rc
	 * @return
	 */
	public int compareTo(GridPoint rc) {
		//der darunterliegende Stein
		if((rc.column - column == -1) && (rc.row - row == 0)) {
			return 0;
		}
		//der links daneben liegende Stein
		if((rc.column - column == 0) && (rc.row - row == -1)) {
			return -1;
		}
		//der rechts daneben liegende Stein
		if((rc.column - column == 0) && (rc.row - row == 1)) {
			return 1;
		}
		//der dar�berliegende Stein
		if((rc.column - column == 1) && (rc.row - row == 0)) {
			return 2;
		} else {
			return -2;
		}
	}
	
	/**
	 * Klont einen Spielstein.
	 */
	public Object clone() {
		Object o = new GridPoint(column, row);
		return o;
	}
	
	/**
	 * Überprüft ob der Gridpoint horizontal oder vertikal zu einem bestehendem Point liegt.
	 * @param points
	 * @return
	 */
	public boolean isManhattenColinearWith(Set<GridPoint> points) {
		for(GridPoint gridPoint : points) {
			ManhattenDirection manhattenDirection = ManhattenDirection.calcDir(this, gridPoint);
			if(manhattenDirection != null) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gibt einen GridPoint aus.
	 */
	public String toString() {
		return String.valueOf(gridPoint);
	}
	
}