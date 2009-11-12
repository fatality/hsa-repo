/**
 * @author fatality, sschwarz22
 * @date 2009-11-05
 */
package core;

/**
 * Calculates all relevant data and distributes them to multiple threads
 */
public class Simulation {

	public final double G = 6.672E-11;
	public final double t;
	public final Vector speedDirection;

	/**
	 * Constructor for Simulation
	 * 
	 * @param t for time intervals
	 */
	public Simulation(double t, Vector speedDirection) {
		this.t = t;
		this.speedDirection = speedDirection;
	}

	/**
	 * Calculates the Speed for a new Planet.
	 * 
	 * @param Planets planet
	 * @param Planets
	 *            centralStar
	 * @return Vector Speed
	 */
	public double getStartSpeed(Planets centralStar, Planets planet) {

		double speedMath = ((centralStar.getMass() - planet.getMass()) / centralStar
				.getMass())
				* Math.sqrt((G * centralStar.getMass())
						/ planet.getPosition()
								.subVec(centralStar.getPosition()).getLenght());

		return speedMath;
	}

	/**
	 * Calculates the direction of the Speed Vector
	 * 
	 * @param centralStar
	 * @param planet
	 * @return Vector
	 */
	public Vector speedDir(Planets centralStar, Planets planet) {
		Vector vi = planet.getPosition().subVec(centralStar.getPosition())
				.crossVec(speedDirection);
		Vector speed = vi.multiplyVector(1 / planet.getSpeed());
		return speed;
	}

	/**
	 * Calculates the Gravitation between two Planets.
	 * 
	 * @param Planets
	 *            m1
	 * @param Planets
	 *            m2
	 * @return Vector
	 */
	public Vector calcGravitation(Planets m1, Planets m2) {
		double m1m2 = m1.getMass() * m2.getMass();
		double lengthr2Minusr1 = m2.getPosition().subVec(m1.getPosition())
				.getLenght();
		Vector r2Minusr1 = m2.getPosition().subVec(m1.getPosition());

		Vector gravitation = r2Minusr1
				.multiplyVector(G
						* (m1m2 / (lengthr2Minusr1 * lengthr2Minusr1 * lengthr2Minusr1)));

		return gravitation;
	}

	/**
	 * Calculates the acceleration for a planet
	 * 
	 * @param planet
	 * @param Vector
	 *            f (all gravitation together)
	 * @return Vector
	 */
	public Vector calcAcc(Planets planet, Vector f) {
		return f.multiplyVector((1 / planet.getMass()));
	}

	/**
	 * Calculates the new position for a planet
	 * 
	 * @param planet
	 * @param acceleration
	 * @return Vector
	 */
	public Vector simStep(Planets centralStar, Planets planet, Vector acc) {
		Vector newPos = planet.getPosition().addVec(
				speedDir(centralStar, planet).multiplyVector(t)).addVec(
				acc.multiplyVector((t * t) / 2));
		return newPos;
	}

}
