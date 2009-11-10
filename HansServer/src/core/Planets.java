/**
 * @author fatality
 * @date 2009-11-05
 */
package core;

/**
 * lists every planet we use in this simulation
 * 
 * 06.11.09 Only a quick implementation - not Final!
 * Only a construction class for Planets. They will be
 * stored in the Management.
 * Central Planet will be stored in an extra array
 * in the Management.
 */
public class Planets {
	
	private Vector position;
	private final Vector speed;
	private final float mass;
	
	/**
	 * Contructor for Planets
	 * Gets a Vector(x,y,z) as float-Array for position and speed.
	 * Gets a float for the mass
	 */
	public Planets(Vector position, Vector speed, float mass){
		this.position = position;
		this.speed = speed;
		this.mass = mass;
	}
	
	
	/**
	 * Getter for Startposition
	 * Returns the Position as a Vector
	 * @return Vector
	 */
	public Vector getStart(){
		return this.position;
	}
	
	/**
	 * Getter for Speed
	 * Returns the Speed as a Vector
	 * @return Vector
	 */
	public Vector getSpeed(){
		return this.speed;
	}
	
	/**
	 * Getter for Mass
	 * Returns a float with the mass
	 * @return float
	 */
	public float getMass(){
		return this.mass;
	}
	
}
