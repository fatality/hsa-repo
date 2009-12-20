/**
 * @author fatality, sschwarz22
 * @date 2009-11-05
 */
package core;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Management {

	public Vector animationDir = new Vector(0, 0, 1);
	public double t = 60;
	
	public Planet centralStar;
	public static ArrayList<Planet> planets;
	public ArrayList<Planet> calculatedPlanets;
	public Simulation sim;
	public int simDuration;
	public int countDone;
	public ArrayList<Workorder> workorder;
	public double M;
	double[] planetCoords;
	BufferedWriter bx;
	BufferedWriter by;

	/**
	 * Konstruktor für das Management(Master) initiiert das Sternensystem
	 * 
	 * @param t
	 *            Zeitintervall
	 * @param animationDirection
	 *            Richtung wie die Planeten fliegen
	 * @param NumberOfPlanets
	 * @param simDuration
	 *            Wieviele Iterationen werden kalkuliert
	 */
	public Management() {
	
	}
	
	public void initManagement(int NumberOfPlanets, int simDuration, 
					  double maxPlanetMass, double centralStarMass){
		try {
			bx = new BufferedWriter(new FileWriter("px.txt"));
			by = new BufferedWriter(new FileWriter("py.txt"));
		} catch (Exception e) {
			System.out.println("FEHLER");
		}

		this.simDuration = simDuration;
		calculatedPlanets = new ArrayList<Planet>();
		initCentral(centralStarMass);
		initPlanets(NumberOfPlanets, maxPlanetMass);
		this.sim = new Simulation(this.t, this.animationDir, M, planets);
		initPlanetSpeed();
		countDone = 0;
		workorder = new ArrayList<Workorder>();
	}
	

	/**
	 * Initiiert den Zentralstern und speichert ihn in seine Variable
	 */
	public void initCentral(double centralStarMass) {
		Planet central = new Planet(new Vector(0, 0, 0), centralStarMass, 0);
		central.setSpeed(0);
		centralStar = central;
	}

	/**
	 * Initiiert die Planeten für die Simulation Alle planeten werden im abstand
	 * von 0.5AU aufsteigend um den zentralen stern gestartet. Die maximale
	 * Masse der Planeten wird mit 1/2 der zentralen Sternmasse festgelegt. Die
	 * eigentliche Masse eines Planeten wird mit Math.Random * maxMass
	 * berechnet.
	 * 
	 * @TODO Bisher werden nur Erden erzeugt. Änderung auf Random
	 * @param numberOfPlanets
	 *            Anzahl der Planeten
	 */
	public void initPlanets(int numberOfPlanets, double maxPlanetMass) {

		double distance = 90E9;
		double maxMass = maxPlanetMass;
		double mass;

		ArrayList<Planet> god = new ArrayList<Planet>();
		for (int i = 0; i < numberOfPlanets; i++) {
			mass = Math.random() * maxMass;
			Planet temp = new Planet(new Vector(0, distance, 0), mass, i);
			god.add(temp);
			distance += 90E9;
			M += mass;
		}
		M += centralStar.getMass();
		planets = god;
	}
	
	public void initPlanetSpeed(){
		for(int i = 0; i<planets.size(); i++){
			planets.get(i).setSpeed(sim.getSpeed(centralStar, planets.get(i)));
		}
	}

	//
	// Implementierung des Master-Worker-Pattern ab hier.
	//	

	public void doSim() {
		for (int i = 0; i < 50; i++) {
			Worker temp = new Worker(t, animationDir, this, M);
			temp.start();
		}
		for (int i = 0; i < simDuration; i++) {
			distributeWork();
			while (calculatedPlanets.size() != planets.size()) {
				try {
					Thread.sleep(0,1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			this.planetCoords = workDone(calculatedPlanets);
			
			this.planetCoords = null;
			
			while (this.planetCoords != null)
				try{
					Thread.sleep(0,1);
				} catch (InterruptedException e){
					e.printStackTrace();
				}
			
			
			countDone = 0;
			planets = calculatedPlanets;
			calculatedPlanets = new ArrayList<Planet>();
		}
	}

	/**
	 * Erstellt die Liste an Workorders die von den Workern abgearbeitet werden
	 * sollen.
	 */
	public void distributeWork() {
		ArrayList<Workorder> workorder = new ArrayList<Workorder>();
		for (int i = 0; i < planets.size(); i++) {
			workorder.add(new Workorder(planets, centralStar, countDone));
			countDone++;
		}
		this.workorder = workorder;
	}

	/**
	 * Beendet den Zyklus.
	 * 
	 * @param calculatedPlanets
	 */
	@SuppressWarnings("unchecked")
	public double[] workDone(ArrayList<Planet> calculatedPlanets) {
		Collections.sort(calculatedPlanets);
		
		// Umschreiben der planeten Koordinaten in ein array aus doubles 
		// zur uebertragingsmoeglichkeit fuer ice
		double[] planetCoords = new double[calculatedPlanets.size()*3];
		int temp = 0;
		for ( Planet p: calculatedPlanets){
			planetCoords[temp] = p.getPosition().x;
			planetCoords[temp+1] = p.getPosition().y;
			planetCoords[temp+2] = p.getPosition().z;
			temp+=3;
		}
		
		// Behelfsausgabe der Werte des 1. Planeten zur Ueberpruefung weil Ice nicht laeuft
		try {
			bx.append(String.valueOf(planets.get(0).getPosition().x) + "\n");
			by.append(String.valueOf(planets.get(0).getPosition().y) + "\n");
			bx.flush();
			by.flush();
		} catch (Exception e) {
			System.out.println("FEHLER");
		}
		
		

		return planetCoords;
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
	
	/**
	 * Methode die der Client aufruft um alles zu bekommen was er pro zyklus
	 * braucht.
	 */
	public void clientGetsWhatHeNeeds(){
		
	}

}
