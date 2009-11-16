/**
 * @author fatality, sschwarz22
 * @date 2009-11-05
 */
package core;

import java.util.ArrayList;

public class Management {

	public Planet centralStar;
	public ArrayList<Planet> planets;
	// Wird bei neuer implementierung nichtmehr gebraucht
	public ArrayList<Planet> calculatedPlanets;
	public Simulation sim;
	public int simDuration;
	public int countDone;

	/**
	 * Konstruktor für das Management(Master) initiiert das Sternensystem
	 * 
	 * @param t	= time Intervals
	 * @param animationDirection = the direction the planets will fly in the system
	 * @param NumberOfPlanets
	 * @param simDuration = how many iterations will be calculated
	 */
	public Management(int t, Vector animationDirection, int NumberOfPlanets, int simDuration) {
		sim = new Simulation(t, animationDirection);
		calculatedPlanets = new ArrayList<Planet>();
		initCentral();
		initPlanets(NumberOfPlanets);
		countDone = NumberOfPlanets;
	}

	/**
	 * Initiiert den Zentralen stern und speichert ihn in seine variable
	 */
	public void initCentral() {
		Planet central = new Planet(new Vector(0, 0, 0), 1.985E30);
		central.setSpeed(0);
		centralStar = central;
	}

	/**
	 * Initiiert die Planeten für die Simulation
	 *
	 * 
	 * @TODO Bisher werden nur Erden erzeugt. Änderung auf Random
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
	 * Dummy implementierung der Simulation. Arbeitet ohne Master/Worker 
	 * zum testen der Simulation.
	 * 
	 * @TODO Insert Master-Worker here
	 */
	public void doSim() {
		for (int i = 0; i <= 365; i++) {
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

	/**
	 * @TODO Alte master worker implementierung 
	 */
//	public void master() {
//		// Start of a new cycle by adding all workorders in the workorder array.
//		while (countDone != planets.size() - 1) {
//			workorder.add(new Workorder(planets, centralStar, countDone));
//			countDone++;
//		}
//
//		// Waiting for every workorder to be done
//		while (calculatedPlanets.size() != planets.size()) {
//			try {
//				Thread.sleep(50);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//
//		// Finish the cycle and set the new coordinates as the actual ones.
//		countDone = 0;
//		planets = calculatedPlanets;
//		calculatedPlanets = new ArrayList<Planet>();
//
//	}
	
	
	/**
	 * Erstellt die Liste an Workorders die von den Workern abgearbeitet werden soll.
	 */
	public ArrayList<Workorder> distributeWork(){
		ArrayList<Workorder> workorder = new ArrayList<Workorder>();
		while(countDone != planets.size()-1){
			workorder.add(new Workorder(planets, centralStar, countDone));
			countDone++;
		}
		return workorder;
	}
	
	/**
	 * Beendet den zyklus und setzt die neuen Positionen als die aktuellen
	 * @param calculatedPlanets
	 */
	public void workDone(ArrayList<Planet> calculatedPlanets){
		countDone = 0;
		planets = calculatedPlanets;
	}

}