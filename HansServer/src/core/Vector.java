/**
 * @author sschwarz22, fatality
 * @date 2009-11-16
 */
package core;

/**
 * Vector implementation
 * Maybe some operations missing. Will be implemented when needed.
 */
public class Vector {

	public double x = 0;
	public double y = 0;
	public double z = 0;

	public Vector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Adds two Vector Objects with each other and returns a new Vector with the added parameters
	 * 
	 * @param Vector toAdd
	 * @return Vector
	 */
	public Vector addVec(Vector toAdd) {
		Vector sum = new Vector(this.x + toAdd.x, this.y + toAdd.y, this.z
				+ toAdd.z);
		return sum;
	}

	/**
	 * Subtracts the given Vector from the actual one
	 * 
	 * @param Vector subThis
	 * @return Vector
	 */
	public Vector subVec(Vector subThis) {
		Vector sub = new Vector(this.x - subThis.x, this.y - subThis.y, this.z - subThis.z);
		return sub;
	}

	/**
	 * Negates a Vector and returns the new negated Vector.
	 * 
	 * @return Vector
	 */
	public Vector negateVec() {
		return new Vector(-this.x, -this.y, -this.z);
	}

	/**
	 * Returns the length of a vector
	 * 
	 * @return double
	 */
	public double getLenght() {
		return Math.sqrt(x * x + y * y + z * z);
	}

	/**
	 * Multiplies a Vector with a given double
	 * 
	 * @param double multWith
	 * @return Vector
	 */
	public Vector multiplyVector(double multWith) {
		Vector mult = new Vector(this.x * multWith, this.y * multWith, this.z
				* multWith);
		return mult;
	}

	/**
	 * cross product of two Vectors
	 * 
	 * @param cross With
	 * @return Vector
	 */
	public Vector crossVec(Vector cWith) {
		Vector cross = new Vector(this.y * cWith.z - this.z * cWith.y, this.z * cWith.x - this.x * cWith.z, this.x * cWith.y - this.y * cWith.x);
		return cross;
	}

	/**
	 * @Override
	 * @TODO Only used for programming! Will be deleted later.
	 */
	public String toString() {
		System.out.println(x);
		System.out.println(y);
		System.out.println(z);
		System.out.println();
		return null;

	}

}
