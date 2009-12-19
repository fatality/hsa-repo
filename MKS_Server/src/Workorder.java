import java.util.ArrayList;

/**
 * 
 */

/**
 * @author fatality
 * @date 2009-11-16
 */
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
