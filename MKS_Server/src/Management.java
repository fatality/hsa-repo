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

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Verwaltet die Daten die zur Simulation verwendet werden.
 */
public class Management {
	
	public Vector animationDir = new Vector( 0, 0, 1 );
	public double t = 60;
	public Planet centralStar;
	public static ArrayList<Planet> planets;
	public ArrayList<Planet> calculatedPlanets;
	public Simulation sim;
	public int simDuration;
	public int countDone;
	public ArrayList<Workorder> workorder;
	public double M;
	BufferedWriter bx;
	BufferedWriter by;
	
	public Management() {
		
	}
	
	/**
	 * <strong>Konstruktor</strong> der <em>Management-Klasse</em>.
	 * 
	 * @param NumberOfPlanets
	 * @param simDuration
	 * @param maxPlanetMass
	 * @param centralStarMass
	 */
	public void initManagement( int NumberOfPlanets, int simDuration, double maxPlanetMass, double centralStarMass ) {
		try {
			bx = new BufferedWriter( new FileWriter( "px.txt" ) );
			by = new BufferedWriter( new FileWriter( "py.txt" ) );
		} catch ( Exception e ) {
			System.out.println( "FEHLER" );
		}
		
		this.simDuration = simDuration;
		calculatedPlanets = new ArrayList<Planet>();
		initCentral( centralStarMass );
		initPlanets( NumberOfPlanets, maxPlanetMass );
		this.sim = new Simulation( this.t, this.animationDir, M, planets );
		initPlanetSpeed();
		countDone = 0;
		workorder = new ArrayList<Workorder>();
	}
	
	/**
	 * Initiiert den Zentralstern und speichert ihn in seine Variable
	 */
	public void initCentral( double centralStarMass ) {
		Planet central = new Planet( new Vector( 0, 0, 0 ), centralStarMass, 0 );
		central.setSpeed( 0 );
		centralStar = central;
	}
	
	/**
	 * Initiiert die Planeten für die Simulation.
	 * Alle planeten werden im Abstand von 0.5AU aufsteigend um den Zentralstern gestartet.
	 * Die maximale Masse der Planeten wird mit 1/2 der zentralen Sternmasse festgelegt.
	 * Die eigentliche Masse eines Planeten wird mit Math.Random * maxMass berechnet.
	 * 
	 * @param numberOfPlanets Anzahl der Planeten
	 */
	public void initPlanets( int numberOfPlanets, double maxPlanetMass ) {
		double distance = 90E9;
		double maxMass = maxPlanetMass;
		double mass;
		ArrayList<Planet> god = new ArrayList<Planet>();
		for ( int i = 0; i < numberOfPlanets; i++ ) {
			mass = Math.random() * maxMass;
			Planet temp = new Planet( new Vector( 0, distance, 0 ), mass, i );
			god.add( temp );
			distance += 90E9;
			M += mass;
		}
		M += centralStar.getMass();
		planets = god;
	}
	
	/**
	 * Initalisiert die Geschwindigkeit eines Planeten.
	 */
	public void initPlanetSpeed(){
		for ( int i = 0; i < planets.size(); i++ ) {
			planets.get( i ).setSpeed( sim.getSpeed( centralStar, planets.get( i ) ) );
		}
	}
	
	/**
	 * Implementierung des Master-Worker-Pattern
	 */
	public void doSim() {
		for ( int i = 0; i < 50; i++ ) {
			Worker temp = new Worker( t, animationDir, this, M );
			temp.start();
		}	
		for ( int i = 0; i < simDuration; i++ ) {
			distributeWork();
			while ( calculatedPlanets.size() != planets.size() ) {
				try {
					Thread.sleep( 0,1 );
				} catch ( InterruptedException e ) {
					e.printStackTrace();
				}
			}
			workDone( calculatedPlanets );
			calculatedPlanets = new ArrayList<Planet>();
		}
	}
	
	/**
	 * Erstellt die Liste an Workorders die von den Workern abgearbeitet werden
	 * sollen.
	 */
	public void distributeWork() {
		ArrayList<Workorder> workorder = new ArrayList<Workorder>();
		for ( int i = 0; i < planets.size(); i++ ) {
			workorder.add( new Workorder( planets, centralStar, countDone ) );
			countDone++;
		}
		this.workorder = workorder;
	}
	
	/**
	 * Beendet den Zyklus und setzt die neuen Positionen als die aktuellen
	 * Postionen
	 * 
	 * @param calculatedPlanets
	 */
	@SuppressWarnings("unchecked")
	public void workDone( ArrayList<Planet> calculatedPlanets ) {
		Collections.sort( calculatedPlanets );
		try {
			bx.append( String.valueOf( planets.get( 0 ).getPosition().x) + "\n" );
			by.append( String.valueOf( planets.get( 0 ).getPosition().y) + "\n" );
			bx.flush();
			by.flush();
		} catch ( Exception e ) {
			System.out.println( "FEHLER" );
		}
		countDone = 0;
		planets = calculatedPlanets;
	}
	
	/**
	 * Methode mit Hilfe der die Worker nachschauen ob Arbeit da ist.
	 * <em>Synchronized!</em>
	 * 
	 * @return temp
	 */
	public synchronized Workorder getWork() {
		if ( workorder.isEmpty() ) {
			return null;
		}
		Workorder temp = workorder.get( 0 );
		workorder.remove( 0 );
		return temp;
	}
	
	/**
	 * Methode mit Hilfe der die Worker ihre berechneten Planeten zurückgeben
	 * <em>Synchronized!</em>
	 * 
	 * @param planet
	 */
	public synchronized void calculationDone( Planet planet ) {
		calculatedPlanets.add( planet );
	}

}
