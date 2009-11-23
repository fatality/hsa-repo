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
	 * @param t Zeitintervall
	 * @param animationDirection Richtung wie die Planeten fliegen
	 * @param NumberOfPlanets
	 * @param simDuration Wieviele Iterationen werden kalkuliert
	 */
	public Management(int t, Vector animationDirection, int NumberOfPlanets,
			int simDuration) {
		sim = new Simulation(t, animationDirection);
		calculatedPlanets = new ArrayList<Planet>();
		initCentral();
		initPlanets(NumberOfPlanets);
		countDone = NumberOfPlanets;
	}

	/**
	 * Initiiert den Zentralstern und speichert ihn in seine Variable
	 */
	public void initCentral() {
		Planet central = new Planet(new Vector(0, 0, 0), 1.985E30);
		central.setSpeed(0);
		centralStar = central;
	}

	/**
	 * Initiiert die Planeten für die Simulation
	 * 
	 * @TODO Bisher werden nur Erden erzeugt. Änderung auf Random
	 * @param planetCount Anzahl der Planeten
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
	 * Dummy Implementierung der Simulation. Arbeitet ohne Master/Worker zum
	 * Testen der Simulation.
	 * 
	 * @TODO Master-Worker einfügen
	 */
	public void doSim() {
		for (int i = 0; i <= 365; i++) {
			Vector f = sim.calcGravitation(centralStar, planets.get(0));
			Vector a = sim.calcAcc(planets.get(0), f);
			Vector p = sim.simStep(centralStar, planets.get(0), a);
			Planet g = new Planet(p, planets.get(0).getMass(), planets.get(0)
					.getSpeed());
			calculatedPlanets.add(g);
			System.out.println(p);
			planets = calculatedPlanets;
			calculatedPlanets = new ArrayList<Planet>();
		}
	}

	/**
	 * @TODO Alte Master-Worker Implementierung
	 */
	// public void master() {
	// // Start of a new cycle by adding all workorders in the workorder array.
	// while (countDone != planets.size() - 1) {
	// workorder.add(new Workorder(planets, centralStar, countDone));
	// countDone++;
	// }
	//
	// // Waiting for every workorder to be done
	// while (calculatedPlanets.size() != planets.size()) {
	// try {
	// Thread.sleep(50);
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// // Finish the cycle and set the new coordinates as the actual ones.
	// countDone = 0;
	// planets = calculatedPlanets;
	// calculatedPlanets = new ArrayList<Planet>();
	//
	// }

	/**
	 * Erstellt die Liste an Workorders die von den Workern abgearbeitet werden
	 * sollen.
	 */
	public ArrayList<Workorder> distributeWork() {
		ArrayList<Workorder> workorder = new ArrayList<Workorder>();
		while (countDone != planets.size() - 1) {
			workorder.add(new Workorder(planets, centralStar, countDone));
			countDone++;
		}
		return workorder;
	}

	/**
	 * Beendet den Zyklus und setzt die neuen Positionen als die aktuellen
	 * Postionen
	 * 
	 * @param calculatedPlanets
	 */
	public void workDone(ArrayList<Planet> calculatedPlanets) {
		countDone = 0;
		planets = calculatedPlanets;
	}

}
