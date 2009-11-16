package core;

import java.util.ArrayList;

import launcher.StartSim;

public class Worker extends Thread{
	
	public double f;
	public ArrayList<Planet> planets;
	public Planet centralPlanet;
	public Simulation sim;
	public int planetToCalc;
	public StartSim master;
	public double t;
	public Vector animationDir;
	
	
	public Worker(double t, Vector animationDir, StartSim master){
		this.t = t;
		this.animationDir = animationDir;
		this.master = master;
	}
	
	public void run() {
		while (true) {
			sim = new Simulation(t, animationDir);
			Workorder toDo = master.getWork();
			if(toDo == null){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}
			planets = toDo.planets;
			centralPlanet = toDo.centralStar;

			Planet temp = doSim();
			
			master.calculationDone(temp);
		}
	}
	

	
	public Planet doSim(){
	
		
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
		
		return new Planet(p, planets.get(planetToCalc).getMass(), planets.get(planetToCalc).getSpeed());
	
		
	
	}
}
