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
	public ArrayList<Workorder> workorder;

	/**
	 * Konstruktor für das Management(Master) initiiert das Sternensystem
	 * 
	 * @param t Zeitintervall
	 * @param animationDirection Richtung wie die Planeten fliegen
	 * @param NumberOfPlanets
	 * @param simDuration Wieviele Iterationen werden kalkuliert
	 */
	public Management(double t, Vector animationDir, int NumberOfPlanets, int simDuration) {
	
		this.simDuration = simDuration;
		this.sim = new Simulation(t, animationDir);
		calculatedPlanets = new ArrayList<Planet>();
		initCentral();
		initPlanets(NumberOfPlanets);
		countDone = 0;
		workorder = new ArrayList<Workorder>();
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
	 * Alle planeten werden im abstand von 0.5AU aufsteigend um den zentralen stern gestartet.
	 * Die maximale Masse der Planeten wird mit 1/2 der zentralen Sternmasse festgelegt.
	 * Die eigentliche Masse eines Planeten wird mit Math.Random * maxMass berechnet.
	 * 
	 * @TODO Bisher werden nur Erden erzeugt. Änderung auf Random
	 * @param numberOfPlanets Anzahl der Planeten
	 */
	public void initPlanets(int numberOfPlanets) {
		
		double distance = 75E6;
		double maxMass = centralStar.getMass()/2;
		
		
		ArrayList<Planet> god = new ArrayList<Planet>();
		for (int i = numberOfPlanets; i > 0; i--) {
			Planet temp = new Planet(new Vector(0, distance, 0), Math.random()* maxMass);
			temp.setSpeed(sim.getSpeed(centralStar, temp));
			god.add(temp);
			distance += 75E6;
		}
		planets = god;
	}



//
	// Implementierung des Master-Worker-Pattern ab hier.
//	

		public void doSim(double t, Vector animationDir) {
		for (int i = 0; i < 4; i++) {
			Worker temp = new Worker(t, animationDir, this);
			temp.start();
		}
		for (int i = 0; i < simDuration; i++) {
			distributeWork();
			while (calculatedPlanets.size() != planets.size()) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			workDone(calculatedPlanets);
			calculatedPlanets = new ArrayList<Planet>();
		}
	}
	
	/**
	 * Erstellt die Liste an Workorders die von den Workern abgearbeitet werden
	 * sollen.
	 */
	public void distributeWork() {
		ArrayList<Workorder> workorder = new ArrayList<Workorder>();
		for (int i = 0; i<planets.size(); i++) {
			workorder.add(new Workorder(planets, centralStar, countDone));
			countDone++;
		}
		this. workorder = workorder;
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

	/**
	 * Methode mit Hilfe der die Worker nachschauen ob Arbeit da ist.
	 * Synchronized!
	 * 
	 * @return temp
	 */
	public synchronized Workorder getWork() {
		if (workorder.isEmpty()) {
			return null;
		}
		Workorder temp = workorder.get(0);
		workorder.remove(0);
		return temp;
	}
	
	
	/**
	 * Methode mit Hilfe der die Worker ihre berechneten Planeten zurückgeben
	 * Synchronized!
	 * 
	 * @param planet
	 */
	public synchronized void calculationDone(Planet planet) {
		calculatedPlanets.add(planet);
	}

	
}
