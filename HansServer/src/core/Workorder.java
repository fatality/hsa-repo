/**
 * @author fatality, sschwarz22
 * @date 2009-11-16
 */
package core;

import java.util.ArrayList;

public class Workorder {

	public ArrayList<Planet> planets;
	public Planet centralStar;
	public int calculateThis;

	public Workorder(ArrayList<Planet> planets, Planet centralStar, int calculateThis) {
		this.planets = planets;
		this.calculateThis = calculateThis;
	}
}
