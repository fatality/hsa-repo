/**
 * @author fatality, sschwarz22
 * @date 2009-11-12
 */
package launcher;

import java.util.ArrayList;
import core.*;

public class StartSim {

	
	// Simulations Angaben
	public static int NumberOfPlanets = 10000;
	public static Vector animationDir = new Vector(0, 0, 1);
	public static int simDuration = 365;
	public static double t = 84600;



	public static Management man;
	

	public static void main(String[] args) {
		man = new Management(t, animationDir, NumberOfPlanets, simDuration);
		man.doSim(t, animationDir);
		
	}






}
