/**
 * @author fatality
 * @date 2009-11-12
 */
package launcher;

import java.util.ArrayList;

import core.*;


public class StartSim {

	public static ArrayList<Workorder> workorder = new ArrayList<Workorder>();
	public static ArrayList<Planet> calculatedPlanets = new ArrayList<Planet>();
	public static int simDuration = 365;
	public static double t = 84600;
	public static Vector animationDir = new Vector(0,0,1);
	Management man = new Management(84600, new Vector(0, 0, 1), 1, simDuration);
	
	
	
	public static void main(String[] args) {
		//doSim();
		
	}
	
	
	public void doSim(){

		for(int i = 0; i<5; i++){
			Worker temp = new Worker(t, animationDir, this);
			temp.start();
		}
		
		for(int i = 0; i<simDuration; i++){
			workorder = man.distributeWork();
			
			while(calculatedPlanets.size() != workorder.size()){
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			
			man.workDone(calculatedPlanets);
			
			
		}
	}
	
	/**
	 * Methode mit hilfe der die Worker nachschaun ob arbeit da ist.
	 * Synchronized!
	 * @return
	 */
	public synchronized Workorder getWork(){
		if(workorder.isEmpty()){
			return null;
		}
		Workorder temp = workorder.get(0);
		workorder.remove(0);
		return temp;
	}
	
	/**
	 * Methode mit hilfe der die Worker ihre berechneten Planeten zurückgeben
	 * Synchronized!
	 * @param planet
	 */
	public synchronized void calculationDone(Planet planet){
		calculatedPlanets.add(planet);
	}

}
