/**
 * @author fatality
 * @date 2009-11-12
 */
package launcher;

import core.Management;

public class StartSim {
	
	public static void main(String[] args) {
		
		Management man = new Management();
		
		man.startSimulation();
		man.doSim();
		
	}

}
