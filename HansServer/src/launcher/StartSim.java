/**
 * @author fatality, sschwarz22
 * @date 2009-11-12
 */
package launcher;

import java.util.ArrayList;
import core.*;

public class StartSim {

	// Simulations Angaben
	public static int NumberOfPlanets = 20;
	public static Vector animationDir = new Vector(0, 0, 1);
	public static int simDuration = 50000;
	public static double t = 3600;
	public static double centralStarMass = 1.985E30;
	public static double maxPlanetMass = centralStarMass / 10000;

	public static Management man;
	
	public static void main(String[] args) {
		man = new Management(NumberOfPlanets, simDuration, maxPlanetMass, centralStarMass);
		man.doSim(t, animationDir);

	}

}