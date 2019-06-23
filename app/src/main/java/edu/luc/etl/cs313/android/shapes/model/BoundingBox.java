package edu.luc.etl.cs313.android.shapes.model;

import java.util.ArrayList;
import java.util.Collections;
import  java.util.List;


/**
 * A shape visitor for calculating the bounding box, that is, the smallest
 * rectangle containing the shape. The resulting bounding box is returned as a
 * rectangle at a specific location.
 */
public class BoundingBox implements Visitor<Location> {

	// TODO entirely your job (except onCircle)

	@Override
	public Location onCircle(final Circle c) {
		final int radius = c.getRadius();
		return new Location(-radius, -radius, new Rectangle(2 * radius, 2 * radius));
	}

	@Override
	public Location onFill(final Fill f) {
		return f.getShape().accept(this) ;
	}

	@Override
	public Location onGroup(final Group g) {
		Integer x1 = null, y1 = null, x2 = null, y2 = null;
		Location Box = g.getShapes().get(0).accept(this);
		x1 = Box.getX();
		y1 = Box.getY();
		x2 = x1 + ((Rectangle) Box.getShape()).getWidth();
		y2 = y1 + ((Rectangle) Box.getShape()).getHeight();

		for (int i = 1; i < g.getShapes().size(); i++) {
			Location nextBox = g.getShapes().get(i).accept(this);
			x1 = x1 < nextBox.getX() ? x1 : nextBox.getX();
			y1 = y1 < nextBox.getY() ? y1 : nextBox.getY();
			x2 = x2 > nextBox.getX() + ((Rectangle) nextBox.getShape()).getWidth() ? x2 : nextBox.getX() + ((Rectangle) nextBox.getShape()).getWidth();
			y2 = y2 > nextBox.getY() + ((Rectangle) nextBox.getShape()).getWidth() ? y2 : nextBox.getY() + ((Rectangle) nextBox.getShape()).getHeight();
			Box = new Location(x1, y1, new Rectangle(x2 - x1, y2 - y1));
		}
		return Box;
	}

	@Override
	public Location onLocation(final Location l) {

		final int x = l.getX();
		final int y = l.getY();
		Location nl = l.getShape().accept(this);

		return new Location(x + nl.getX() , y+ nl.getY(), nl.getShape()  );
	}

	@Override
	public Location onRectangle(final Rectangle r) {
		//Todo Check
		final int width = r.getWidth();
		final int height = r.getHeight();
		return new Location (0 , 0 , new Rectangle(width, height ) );
	}

	@Override
	public Location onStroke(final Stroke c) {
		return c.getShape().accept(this);
	}

	@Override
	public Location onOutline(final Outline o) {
		return o.getShape().accept(this);
	}

	@Override
	public Location onPolygon(final Polygon s) {

		return onGroup((Group)s);
	}
}
