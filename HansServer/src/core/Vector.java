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
	
public float x = 0;
public float y = 0;
public float z = 0;

public Vector(float x, float y, float z){
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




	
	
}
