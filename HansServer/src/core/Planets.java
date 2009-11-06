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
	
	public float[] startposition = new float[3];
	public float[] endposition = new float[3];
	public float[] speed = new float[3];
	public float mass;
	
	/**
	 * Contructor for Planets
	 * Gets a Vector(x,y,z) as float-Array for position and speed.
	 * Gets a float for the mass
	 */
	public Planets(short central, float[] position, float[] speed, float mass){
		this.startposition = position;
		this.speed = speed;
		this.mass = mass;
	}
	
	
	/**
	 * Getter for Startposition
	 * Returns an float-Array with the Coordinates(x,y,z)
	 * @return float[x,y,z]
	 */
	public float[] getStart(){
		return this.startposition;
	}
	
	/**
	 * Getter for Speed
	 * Returns an float-Array with the Speed-Vektor(x,y,z)
	 * @return float[x,y,z]
	 */
	public float[] getSpeed(){
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
    
	/**
	 * Setter for endposition
	 * Checks if the endpositions is null.
	 * If yes, stores the end position and returns true
	 * Else returns false.
	 * Maybe usefull for an exception later
	 * @param float[x,y,z]
	 * @return boolean
	 */
	public boolean setEnd(float[] end){
		if(this.endposition == null){
			this.endposition = end;
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Confirmation of endposition
	 * Sets the endposition as new startposition
	 */
	public void confirm(){
		this.startposition = this.endposition;
		this.endposition = null;
	}
	
}
