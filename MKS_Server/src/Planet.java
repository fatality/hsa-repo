/**
 * ########################################################
 * Copyright (c) 2009
 * @author fatality
 * @date 2009-11-05
 * http://www.gironimo.org/
 * 
 * Lizenz: GPL 3
 * ########################################################
 */

/**
 * Kunstruktor Klasse für Planeten Planeten werden im Management gespeichert.
 * Zentralstern wird in einem Extra Array im Management abgespeichert.
 */
@SuppressWarnings("unchecked")
public class Planet implements Comparable {

	private Vector position;
	private double speed;
	private double mass;
	private int planetNumber;

	/**
	 * <strong>Konstruktor</strong> der <em>Planeten-Klasse</em>.
	 * 
	 * @param Vector position
	 * @param double mass
	 */
	public Planet( Vector position, double mass, int planetNumber ) {
		this.position = position;
		this.mass = mass;
		this.planetNumber = planetNumber;
	}

	/**
	 * Überladener Konstruktor der Planeten. Wird benutzt um Planeten zu kopieren.
	 * 
	 * @param Vector position
	 * @param double mass
	 * @param double speed
	 */
	public Planet( Vector position, double mass, double speed, int planetNumber ) {
		this.position = position;
		this.mass = mass;
		this.speed = speed;
		this.planetNumber = planetNumber;
	}

	/**
	 * Setter für Geschwindigkeit.
	 * 
	 * @param speed
	 */
	public void setSpeed( double speed ) {
		this.speed = speed;
	}

	/**
	 * Getter für Geschwindigkeit, gibt die Geschwindigkeit als Vector zurück.
	 * 
	 * @return Vector
	 */
	public double getSpeed() {
		return this.speed;
	}

	/**
	 * Setter für die Position.
	 * 
	 * @param position
	 */
	public void setPosition( Vector position ) {
		this.position = position;
	}

	/**
	 * Getter für Position, gibt die Position als Vector zurück.
	 * 
	 * @return Vector
	 */
	public Vector getPosition() {
		return this.position;
	}

	/**
	 * Getter für die Masse, gibt die Masse als double zurück.
	 * 
	 * @return double
	 */
	public double getMass() {
		return this.mass;
	}

	/**
	 * Ausgabe der Positionen in der Konsole.
	 * 
	 * @return null
	 */
	public String toString() {
		System.out.println( position.x + " " + position.y + " " + position.z );
		return null;
	}

	/**
	 * Gibt die Anzahl der Planeten zurück.
	 * 
	 * @return planetNumber
	 */
	public int getNumber() {
		return planetNumber;
	}

	/**
	 * Vergleicht ob der vorhandene Planet gleich dem berechnetem ist.
	 * 
	 * @return
	 */
	@Override
	public int compareTo( Object arg0 ) {
		Planet compare = ( Planet ) arg0;
		if ( getNumber() < compare.getNumber() ) {
			return -1;
		} else if ( getNumber() == compare.getNumber() ) {
			return 0;
		} else {
			return 1;
		}
	}

}
