/**
 * @author fatality, sschwarz22
 * @date 2009-11-05
 */
package core;

import java.util.ArrayList;

public class Management {

	public Planets centralStar;
	public ArrayList<Planets> planets;
	public ArrayList<Planets> newPosPlanet = new ArrayList<Planets>();
	public Simulation sim;

	/**
	 * Collection of methods to initialize the star system
	 * 
	 */
	public void startSimulation() {
		sim = new Simulation(84600, new Vector(0, 0, 1));
		initCentral();
		initPlanets(1);
	}

	/**
	 * Inits the central Star, by saving it into its variable
	 */
	public void initCentral() {
		Planets central = new Planets(new Vector(0, 0, 0), 1.985E30);
		central.setSpeed(0);
		centralStar = central;
	}

	
	/**
	 * Inits the Planets which will be simulated and saves them into the planets arraylist
	 * @param planetCount (Number of planets)
	 */
	public void initPlanets(int planetCount) {
		ArrayList<Planets> god = new ArrayList<Planets>();
		for (int i = planetCount; i > 0; i--) {
			Planets temp = new Planets(new Vector(0, 150E6, 0), 5.976E24);
			temp.setSpeed(sim.getStartSpeed(centralStar, temp));
			god.add(temp);
		}

		planets = god;
	}
	
	
	/**
	 * Responsable for the simulation.
	 * @TODO Insert Master-Worker here
	 * 
	 */
	public void doSim() {
		for (int i = 0; i <=365; i++) {
			Vector f = sim.calcGravitation(centralStar, planets.get(0));
			Vector a = sim.calcAcc(planets.get(0), f);
			Vector p = sim.simStep(centralStar, planets.get(0), a);
			Planets g = new Planets(p, planets.get(0).getMass(), planets.get(0).getSpeed());
			newPosPlanet.add(g);
			System.out.println(p);
			planets = newPosPlanet;
			newPosPlanet = new ArrayList<Planets>();
		}
	}

}
