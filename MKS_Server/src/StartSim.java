/**
 * Diese Klasse startet den Server und übergibt alle notwendigen Variablen ans Management.
 */

/**
 * @author fatality
 * @date 2009-11-12
 */
public class StartSim {
	
	public static int NumberOfPlanets = 1;
	public static Vector animationDir = new Vector(0, 0, 1);
	public static int simDuration = 20000000;
	public static double centralStarMass = 1.985E30;
	public static double maxPlanetMass = centralStarMass / 10000;
	public static Management man;

	/**
	 * @param args
	 */
	public static void main( String[] args ) {
		man = new Management();
		man.initManagement( NumberOfPlanets, simDuration, maxPlanetMass, centralStarMass );
		man.doSim();
	}

}
