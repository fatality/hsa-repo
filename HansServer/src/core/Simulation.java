/**
 * @author fatality, sschwarz22
 * @date 2009-11-05
 */
package core;

import java.util.ArrayList;

import launcher.StartSim;

/**
 * Berechnet alle relevanten Daten
 */
public class Simulation {

	public final double G = 6.672E-11;
	public final double t;
	public final Vector animationDirection;
	public ArrayList<Planet> planets;

	/**
	 * Konstruktor der Simulation
	 * 
	 * @param t
	 *            Zeitintervall
	 */
	public Simulation(double t, Vector animationDirection) {
		this.t = t;
		this.animationDirection = animationDirection;
	}

	/**
	 * Berechnet die "optimale" Geschwindigkeit für einen neuen Planeten
	 * 
	 * @param Planet
	 *            planet
	 * @param Planet
	 *            centralStar
	 * @return Vector Speed
	 */
	public double getSpeed(Planet centralStar, Planet planet) {
		double speedMath = (((Management.M - planet.getMass())) / centralStar.getMass())
				* Math.sqrt((G * Management.M) / (planet.getPosition().subVec(getRSL(centralStar, planet, Management.planets)).getLenght()));
		System.out.println(speedMath);
		return speedMath;
	}

	/**
	 * Berechnet die Richtung des Geschwindigkeitsvektors
	 * 
	 * @param centralStar
	 * @param planet
	 * @return Vector
	 */
	public Vector speedDir(Planet centralStar, Planet planet) {
		Vector vi = planet.getPosition().subVec(centralStar.getPosition()).crossVec(animationDirection);
		Vector speed = vi.multiplyVector(1 / planet.getSpeed());
		return speed;
	}

	/**
	 * Berechnet die Gravitation zwischen zwei Planeten
	 * 
	 * @param Planet
	 *            m1
	 * @param Planet
	 *            m2
	 * @return Vector
	 */
	public Vector calcGravitation(Planet m1, Planet m2) {
		double m1m2 = m1.getMass() * m2.getMass();
		double lengthr2Minusr1 = m2.getPosition().subVec(m1.getPosition()).getLenght();
		Vector r2Minusr1 = m2.getPosition().subVec(m1.getPosition());
		Vector gravitation = r2Minusr1.multiplyVector(G * (m1m2 / (lengthr2Minusr1 * lengthr2Minusr1 * lengthr2Minusr1)));
		return gravitation;
	}

	/**
	 * Berechnet die Beschleunigung zwischen zwei Planeten
	 * 
	 * @param planet
	 * @param Vector
	 *            f Komplette Gravitation zusammen genommen
	 * @return Vector
	 */
	public Vector calcAcc(Planet planet, Vector f) {
		return f.multiplyVector((1 / planet.getMass()));
	}

	/**
	 * Berechnet die neue Position eines Planeten
	 * 
	 * @param planet
	 * @param acceleration
	 * @return Vector
	 */
	public Vector simStep(Planet centralStar, Planet planet, Vector acc) {
		Vector newPos = planet.getPosition().addVec(speedDir(centralStar, planet).multiplyVector(t)).addVec(acc.multiplyVector((t * t) / 2));
		return newPos;
	}

	public Vector getRSL(Planet centralStar, Planet currPlanet, ArrayList<Planet> planets) {

		Vector sum = new Vector(0, 0, 0);

		for (int i = 0; i < StartSim.NumberOfPlanets; i++) {
			// if ( planets.get(i).equals(currPlanet) == true ) continue;
			sum = currPlanet.getPosition().multiplyVector(currPlanet.getMass());
		}

		sum = sum.multiplyVector(Math.pow(Management.M, -1));
		return sum;
	}

}
