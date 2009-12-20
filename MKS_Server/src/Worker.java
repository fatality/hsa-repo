/**
 * ########################################################
 * Copyright (c) 2009
 * @author fatality
 * @date 2009-11-05
 * http://www.gironimo.org/
 * 
 * Lizenz: GPL 3
 * ########################################################
 */

import java.util.ArrayList;

/**
 * Anwendung des Master-Worker-Pattern!
 */
public class Worker extends Thread {
	
	public double f;
	public ArrayList<Planet> planets;
	public Planet centralPlanet;
	public Simulation sim;
	public int planetToCalc;
	public Management master;
	public double t;
	public Vector animationDir;
	public double M;

	/**
	 * <strong>Konstruktor</strong> für die <em>Worker-Klasse</em>.
	 * 
	 * @param t
	 * @param animationDir
	 * @param master
	 */
	public Worker( double t, Vector animationDir, Management master, double M ) {
		this.t = t;
		this.animationDir = animationDir;
		this.master = master;
		this.M = M;
	}

	/**
	 * Alauf des Threads:
	 * Erstellt eine Simulation, schaut ob eine Workorder vorhanden ist. 
	 * Wenn ja: Berechnet er diese und gibt den neu berechneten Planeten zurück ins "berechnete" Array. 
	 * Wenn nein: schläft er für 100ms.
	 */
	public void run() {
		while ( true ) {
			sim = new Simulation( t, animationDir, M, planets );
			Workorder toDo = master.getWork();
			while ( toDo == null ) {
				try {
					Thread.sleep( 0,1 );
					toDo = master.getWork();
				} catch ( InterruptedException e ) {
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
	 * @return Planet
	 */
	public Planet doSim() {
		Vector f = sim.calcGravitation( centralPlanet, planets.get( planetToCalc ) );
		for ( int i = 0; i < planets.size(); i++ ) {
			if ( i != planetToCalc ) {
				f = f.addVec( sim.calcGravitation( planets.get( i ), planets.get( planetToCalc ) ) );
			}
		}
		Planet calcPlanet = planets.get( planetToCalc );
		Vector a = sim.calcAcc( calcPlanet, f );
		Vector p = sim.simStep( centralPlanet, calcPlanet, a );
		return new Planet( p, calcPlanet.getMass(), calcPlanet.getSpeed(), calcPlanet.getNumber() );
	}

}