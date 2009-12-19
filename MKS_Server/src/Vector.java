/**
 * Eigene Vektor Implementation. Fehlende Operationen werden implementiert wenn
 * sie gebraucht werden.
 */

/**
 * @author fatality
 * @date 2009-11-16
 */
public class Vector {
	
	public double x = 0;
	public double y = 0;
	public double z = 0;

	public Vector( double x, double y, double z ) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Addiert zwei Vektoren und gibt einen neuen Vektor zurück
	 * 
	 * @param Vector toAdd
	 * @return Vector
	 */
	public Vector addVec( Vector toAdd ) {
		Vector sum = new Vector( this.x + toAdd.x, this.y + toAdd.y, this.z + toAdd.z );
		return sum;
	}
	
	/**
	 * Subtrahiert den gegebenen Vektor mit dem aktuellen
	 * 
	 * @param Vector subThis
	 * @return Vector
	 */
	public Vector subVec( Vector subThis ) {
		Vector sub = new Vector( this.x - subThis.x, this.y - subThis.y, this.z - subThis.z );
		return sub;
	}
	
	/**
	 * Negiert einen Vektor und gibt einen neuen negierten Vektor zurück
	 * 
	 * @return Vector
	 */
	public Vector negateVec() {
		return new Vector( -this.x, -this.y, -this.z );
	}
	
	/**
	 * Gibt die Länge des Vektors zurück
	 * 
	 * @return double
	 */
	public double getLenght() {
		return Math.sqrt( x * x + y * y + z * z );
	}
	
	/**
	 * Multipliziert einen Vektor mit einem gegebenen double
	 * 
	 * @param double multWith
	 * @return Vector
	 */
	public Vector multiplyVector( double multWith ) {
		Vector mult = new Vector( this.x * multWith, this.y * multWith, this.z * multWith);
		return mult;
	}
	
	/**
	 * Kreuzprodukt von zwei Vektoren
	 * 
	 * @param cross With
	 * @return Vector
	 */
	public Vector crossVec( Vector cWith ) {
		Vector cross = new Vector( this.y * cWith.z - this.z * cWith.y, this.z * cWith.x - this.x * cWith.z, this.x * cWith.y - this.y * cWith.x );
		return cross;
	}
	
	/**
	 * Simulationsausgabe
	 * 
	 * @Override
	 * @TODO Only used for programming! Will be deleted later.
	 */
	public String toString() {
		String s = "X-Koordinate: " + x + "   Y-Koordinate: " + y + "   Z-Koordinate: " + z;
		System.out.println( s );
		return s;
	}

}
