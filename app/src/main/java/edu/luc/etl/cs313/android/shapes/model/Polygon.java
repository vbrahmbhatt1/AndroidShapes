package edu.luc.etl.cs313.android.shapes.model;

import java.util.List;

/**
 * A special case of a group consisting only of Points.
 *
 */
public class Polygon extends Group {

	public Polygon(final Point... points) {
		super(points);
	}

	@SuppressWarnings("unchecked")
	public List<? extends Point> getPoints() {
		return (List<? extends Point>) getShapes();
	}

	public int IntValue(){return 1;}

	@Override
	public <Result> Result accept(final Visitor<Result> v) {
		// TODO your job
		return v.onPolygon(this); //not sure about this one
	}
}
