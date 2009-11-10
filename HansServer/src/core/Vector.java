package core;

/**
 * Vector implementation
 * @author sscwharz22
 *
 *Maybe some operations missing.
 *Will be implemented when needed.
 *
 */

public class Vector {
	
public double x = 0;
public double y = 0;
public double z = 0;

public Vector(double x, double y, double z){
	this.x = x;
	this.y = y;
	this.z = z;
}

/**
 * Adds two Vector Objects with each other
 * and returns a new Vector with the added
 * parameters
 * @param Vector toAdd
 * @return Vector
 */
public Vector addVec(Vector toAdd){
	Vector sum = new Vector(this.x + toAdd.x,
							this.y + toAdd.y,
							this.z + toAdd.z);
	return sum;
}


/**
 * Negates a Vector and returns the new negated Vector.
 * @return Vector
 */
public Vector negateVec(){
	return new Vector(-this.x, -this.y, -this.z);
}
	
}
