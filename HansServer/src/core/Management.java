/**
 * @author fatality, sschwarz22
 * @date 2009-11-05
 */
package core;

import java.util.ArrayList;

public class Management {

	public Planet centralStar;
	public ArrayList<Planet> planets;
	public ArrayList<Planet> calculatedPlanets;
	public Simulation sim;
	public int simDuration;

	
	public int countDone;
	public int countTodo;
	

	public Management(int t, Vector animationDirection, int NumberOfPlanets, int simDuration){
		sim = new Simulation(t, animationDirection);
		calculatedPlanets = new ArrayList<Planet>();
		initCentral();
		initPlanets(NumberOfPlanets);
		countTodo = NumberOfPlanets;
	}
	
	/**
	 * Collection of methods to initialize the star system
	 * 
	 */
	public void startSimulation() {
		sim = new Simulation(84600, new Vector(0, 0, 1));
		calculatedPlanets = new ArrayList<Planet>();
		initCentral();
		initPlanets(1);
	}

	/**
	 * Inits the central Star, by saving it into its variable
	 */
	public void initCentral() {
		Planet central = new Planet(new Vector(0, 0, 0), 1.985E30);
		central.setSpeed(0);
		centralStar = central;
	}

	
	/**
	 * Inits the Planets which will be simulated and saves them into the planets arraylist
	 * @TODO Change to random generation of planets!
	 * @param planetCount (Number of planets)
	 */
	public void initPlanets(int planetCount) {
		ArrayList<Planet> god = new ArrayList<Planet>();
		for (int i = planetCount; i > 0; i--) {
			Planet temp = new Planet(new Vector(0, 150E6, 0), 5.976E24);
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
			Planet g = new Planet(p, planets.get(0).getMass(), planets.get(0).getSpeed());
			calculatedPlanets.add(g);
			System.out.println(p);
			planets = calculatedPlanets;
			calculatedPlanets = new ArrayList<Planet>();
		}
	}
	
}
