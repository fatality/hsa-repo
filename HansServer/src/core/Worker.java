package core;

import java.util.ArrayList;

public class Worker {
	
	public double f;
	public ArrayList<Planet> planets;
	public Planet centralPlanet;
	public Simulation sim;
	public int planetToCalc;
	
	
	// Die Variablen werden durch abruf einer Workorder vom 
	// zentralen synchronisierten Abholplatz (Array) gefüllt
	// Hab grade ne blockade, kanns nicht implementieren.
	
	
	public Worker(double t, Vector animationDir){
		this.sim = new Simulation(t, animationDir);
	}
	
	public void doSim(){
	
		
		Vector f = sim.calcGravitation(centralPlanet, planets.get(planetToCalc));
		
		for(int i = 0; i < planets.size(); i++){
			if(i != planetToCalc){
				f.addVec(sim.calcGravitation(planets.get(i),planets.get(planetToCalc)));
			}
			else
				i++;
		}
		Vector a = sim.calcAcc(planets.get(planetToCalc), f);
		Vector p = sim.simStep(centralPlanet, planets.get(planetToCalc), a);
		
		Planet g = new Planet(p, planets.get(planetToCalc).getMass(), planets.get(planetToCalc).getSpeed());
	
		//Hier wird die Methode aufgerufen mit der, der Worker, den neu berechneten
		//Planeten in das Array Speichert das vom Master auf Vollständigkeit geprüft
		//wird.
	
	}
}
