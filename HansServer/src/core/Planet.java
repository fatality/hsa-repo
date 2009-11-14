/**
 * @author fatality
 * @date 2009-11-05
 */
package core;

/**
 * 06.11.09 Only a quick implementation - not Final!
 * Only a construction class for Planets. They will be
 * stored in the Management.
 * Central Planet will be stored in an extra array
 * in the Management.
 */
public class Planet {
	
	private Vector position;
	private double speed;
	private double mass;
	
	/**
	 * Contructor for Planets
	 * Gets Vectors for position and speed
	 * Gets a double for the mass
	 */
	public Planet(Vector position, double mass){
		this.position = position;
		this.mass = mass;
	}
	
	/**
	 * Overloaded Constructor for Planets. Used to copy known planets
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
	 * Setter for speed
	 * @param speed
	 */
	public void setSpeed(double speed){
		this.speed = speed;
	}
	
	/**
	 * Getter for Position
	 * Returns the Position as a Vector
	 * @return Vector
	 */
	public Vector getPosition(){
		return this.position;
	}
	
	public void setPosition(Vector position) {
		this.position = position;
	}
	
	/**
	 * Getter for Speed
	 * Returns the Speed as a Vector
	 * @return Vector
	 */
	public double getSpeed(){
		return this.speed;
	}
	
	/**
	 * Getter for Mass
	 * Returns a double with the mass
	 * @return double
	 */
	public double getMass(){
		return this.mass;
	}
	
}
