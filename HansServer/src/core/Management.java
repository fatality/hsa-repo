/**
 * @author fatality
 * @date 2009-11-05
 */
package core;

import java.util.ArrayList;
import core.Planets;

public class Management {

	public Planets centralStar;
	public ArrayList<Planets> planets;
	public ArrayList<Planets> newPosPlanet;
	public Simulation sim;
	
	
	public void startSimulation(){
		
	sim = new Simulation(84600, new Vector(0,0,1));
    	
    	
    }
	
	public Planets initCentral(){
		Planets central = new Planets(new Vector(0,0,0), 1.985E30);
		central.setSpeed(0);
		return central;
	}
	
	
	public ArrayList<Planets> initPlanets(){
		ArrayList<Planets> god = new ArrayList<Planets>();
		god.add(new Planets(new Vector(0,150E6,0), 5.976E24));
		
		return null;
	}

}
