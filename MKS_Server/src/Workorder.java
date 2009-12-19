/**
 * ########################################################
 * Copyright (c) 2009
 * @author fatality
 * @date 2009-11-16
 * http://www.gironimo.org/
 * 
 * Lizenz: GPL 3
 * ########################################################
 */

import java.util.ArrayList;

/**
 * Konstruktor Klasse für das Master-Worker-Pattern.
 */
public class Workorder {
	
	public ArrayList<Planet> planets;
	public Planet centralStar;
	public int calculateThis;

	/**
	 * <strong>Konstruktor</strong> der <em>Workorder-Klasse</em>.
	 * 
	 * @param planets
	 * @param centralStar
	 * @param calculateThis
	 */
	public Workorder(ArrayList<Planet> planets, Planet centralStar,
			int calculateThis) {
		this.planets = planets;
		this.centralStar = centralStar;
		this.calculateThis = calculateThis;
	}

}
