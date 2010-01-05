import Ice.Current;

/**
 * ########################################################
 * Copyright (c) 2009
 * @author fatality
 * @date 2009-12-19
 * http://www.gironimo.org/
 * 
 * Lizenz: GPL 3
 * ########################################################
 */

public class ManagementI extends MksIce._ManagementDisp {
	
	public Management man;
	
    public void initManagement( int NumberOfPlanets, int simDuration, double maxPlanetMass, double centralStarMass, Ice.Current current ) {
    	man = new Management();
    	man.initManagement( NumberOfPlanets, simDuration, maxPlanetMass, centralStarMass );
//    	man.doSim();
    }

	public String getData( Ice.Current current ) {
		return man.getData();
	}
    
}
