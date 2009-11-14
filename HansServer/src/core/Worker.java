package core;

import java.util.ArrayList;

public class Worker {
	
	public double f;
	public ArrayList<Planet> otherPlanets;
	public Planet centralPlanet;
	public Planet calcPlanet;
	public Simulation sim;
	
	
	public Worker(double t, Vector animationDir){
		this.sim = new Simulation(t, animationDir);
	}
}
