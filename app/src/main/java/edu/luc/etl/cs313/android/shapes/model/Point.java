package edu.luc.etl.cs313.android.shapes.model;

/**
 * A point, implemented as a location without a shape.
 */
public class Point extends Location {
	//point on the grid?
	//fixed this
	//// TODO: your job 
	// HINT: use a circle with radius 0 as the shape!

	public Point(final int x, final int y) {

		super(x, y, new Circle(0)); //can you check this part

		assert x >= 0;
		assert y >= 0;
	}
}


