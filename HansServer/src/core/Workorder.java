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

	/**
	 * Konstruktor der Workorder
	 * 
	 * @param planets ArrayListe der Vorhanden Planeten(nur Referenz)
	 * @param centralStar Referenz auf den zentralen Planeten
	 * @param calculateThis Zahl welcher Planet berechnet werden soll
	 */
	public Workorder(ArrayList<Planet> planets, Planet centralStar,
			int calculateThis) {
		this.planets = planets;
		this.centralStar = centralStar;
		this.calculateThis = calculateThis;
	}
}
