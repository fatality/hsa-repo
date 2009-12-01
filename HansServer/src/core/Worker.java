/**
 * @author fatality, sschwarz22
 * @date 2009-11-23
 */
package core;

import java.util.ArrayList;

public class Worker extends Thread {

	public double f;
	public ArrayList<Planet> planets;
	public Planet centralPlanet;
	public Simulation sim;
	public int planetToCalc;
	public Management master;
	public double t;
	public Vector animationDir;

	/**
	 * Konstruktor für den Worker
	 * 
	 * @param t Zeitintervalle
	 * @param animationDir Richtung der Geschwindigkeit
	 * @param master Verweis auf den Master bzw. auf den Verteiler.
	 */
	public Worker(double t, Vector animationDir, Management master) {
		this.t = t;
		this.animationDir = animationDir;
		this.master = master;
	}

	/**
	 * Alauf des Threads Erstellt eine Simulation, schaut ob eine Workorder
	 * vorhanden ist. 
	 * Wenn ja: Berechnet er diese und gibt den neu berechneten
	 * Planeten zurück ins "berechnete" Array. 
	 * Wenn nein: schläft er für 100ms
	 */
	public void run() {
		while (true) {
			sim = new Simulation(t, animationDir);
			Workorder toDo = master.getWork();
			while (toDo == null) {
			try {
					Thread.sleep(100);
					toDo = master.getWork();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			planets = toDo.planets;
			centralPlanet = toDo.centralStar;
			planetToCalc = toDo.calculateThis;
			Planet temp = doSim();
			temp.toString();
			master.calculationDone(temp);
		}
	}

	/**
	 * Die Eigentliche Simulation
	 * 
	 * @return Planet neuberechneter Planet
	 */
	public Planet doSim() {
		Vector f = sim.calcGravitation(centralPlanet, planets.get(planetToCalc));
		for (int i = 0; i < planets.size(); i++) {
			if (i != planetToCalc) {
				f.addVec(sim.calcGravitation(planets.get(i), planets.get(planetToCalc)));
			} else {
				i++;
			}
		}
		Vector a = sim.calcAcc(planets.get(planetToCalc), f);
		Vector p = sim.simStep(centralPlanet, planets.get(planetToCalc), a);
		return new Planet(p, planets.get(planetToCalc).getMass(), planets.get(planetToCalc).getSpeed());
	}
}
