/**
 * @author fatality
 * @date 2009-11-05
 */
package core;

/**
 * Kunstruktor Klasse für Planeten Planeten werden im Management gespeichert.
 * Zentralstern wird in einem Extra Array im Management abgespeichert.
 */
public class Planet {

	private Vector position;
	private double speed;
	private double mass;

	/**
	 * Konstruktor für Planeten
	 * 
	 * @param Vector position
	 * @param double mass
	 */
	public Planet(Vector position, double mass) {
		this.position = position;
		this.mass = mass;
	}

	/**
	 * Überladener Konstruktor der Planeten. Wird benutzt um Planeten zu
	 * kopieren
	 * 
	 * @param Vector position
	 * @param double mass
	 * @param double speed
	 */
	public Planet(Vector position, double mass, double speed) {
		this.position = position;
		this.mass = mass;
		this.speed = speed;
	}

	/**
	 * Setter für Geschwindigkeit
	 * 
	 * @param speed
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	/**
	 * Getter für Geschwindigkeit, gibt die Geschwindigkeit als Vector zurück
	 * 
	 * @return Vector
	 */
	public double getSpeed() {
		return this.speed;
	}

	
	public void setPosition(Vector position) {
		this.position = position;
	}
	
	
	/**
	 * Getter für Position, gibt die Position als Vector zurück
	 * 
	 * @return Vector
	 */
	public Vector getPosition() {
		return this.position;
	}

	/**
	 * Getter für die Masse, gibt die Masse als double zurück
	 * 
	 * @return double
	 */
	public double getMass() {
		return this.mass;
	}
	
	public String toString(){
		System.out.println(position.x + " " + position.y + " " + position.z);
		return null;
	}

}
