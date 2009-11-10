/**
 * @author fatality
 * @date 2009-11-05
 */
package core;

/**
 * Calculates all relevant data and distributes them to multiple threads
 */
public class Simulation {

public final double G = 6.674E-11;
	

/**
 * Calculates the Speed for a new Planet.
 * @param double centralMass
 * @param double planetMass
 * @param double distance
 * @return Vector Speed
 */
public Vector getStartSpeed(double centralMass, double planetMass, double distance){
	
	double speedMath = ((centralMass - planetMass)/centralMass)*Math.sqrt((G*centralMass)/distance);
	Vector speed = new Vector(0,speedMath,0);
	return speed;
	}

}
