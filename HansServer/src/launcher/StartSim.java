/**
 * @author fatality
 * @date 2009-11-12
 */
package launcher;

import core.Management;
import core.Vector;

public class StartSim {

	public static void main(String[] args) {
		Management man = new Management(84600, new Vector(0, 0, 1), 1, 365);
		man.doSim();
	}

}
