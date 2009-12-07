/**
 * @author fatality, sschwarz22
 * @date 2009-11-12
 */
package launcher;

import java.util.ArrayList;
import core.*;

public class StartSim {

	// Simulations Angaben
	public static int NumberOfPlanets = 1;

	public static Management man;
	public static boolean working = true;

	public static void main(String[] args) {
		{
			int status = 0;
			Ice.Communicator ic = null;
			try {
				ic = Ice.Util.initialize(args);
				Ice.ObjectAdapter adapter = ic
						.createObjectAdapterWithEndpoints(
								"MehrKoerperSimulationAdapter",
								"default ‑p 10003");
				Ice.Object object = new StartSimI();
				adapter.add(object, ic.stringToIdentity("KoerperSimulation"));
				adapter.activate();
				ic.waitForShutdown();
			} catch (Ice.LocalException e) {
				e.printStackTrace();
				status = 1;
			} catch (Exception e) {
				System.err.println(e.getMessage());
				status = 1;
			}
			if (ic != null) {
				// Clean up
				//
				try {
					ic.destroy();
				} catch (Exception e) {
					System.err.println(e.getMessage());
					status = 1;
				}
			}
			System.exit(status);
		}

	}

}