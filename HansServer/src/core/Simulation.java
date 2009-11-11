/**
 * @author fatality
 * @date 2009-11-05
 */
package core;

/**
 * Calculates all relevant data and distributes them to multiple threads
 */
public class Simulation {

public final double G = 6.672E-11;
	

/**
 * Calculates the Speed for a new Planet.
 * @param Planets planet
 * @param Planets centralStar
 * @return Vector Speed
 */
public Vector getStartSpeed(Planets centralStar, Planets planet){
	
	double speedMath = ((centralStar.getMass() - planet.getMass())/centralStar.getMass())
						*Math.sqrt((G*centralStar.getMass())/planet.getPosition().getLenght());
	Vector speed = new Vector(0,speedMath,0);
	return speed;
	}

/**
 * Calculates the Gravitation between two Planets.
 * 
 * @param Planets m1
 * @param Planets m2
 * @return Vector
 */
	public Vector calcGravitation(Planets m1, Planets m2) {
		double m1m2 = m1.getMass() * m2.getMass();
		double lengthr2Minusr1 = m2.getPosition().subVec(m1.getPosition()).getLenght();
		Vector r2Minusr1 = m2.getPosition().subVec(m1.getPosition());

		Vector gravitation = r2Minusr1.multiplyVector(G * (m1m2 / (lengthr2Minusr1 * lengthr2Minusr1 * lengthr2Minusr1)));

		return gravitation;
	}
	
	
	

}
