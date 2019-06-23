package edu.luc.etl.cs313.android.shapes.android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;

import java.util.ArrayList;
import java.util.List;

import edu.luc.etl.cs313.android.shapes.model.*;

/**
 * A Visitor for drawing a shape to an Android canvas.
 */
public class Draw implements Visitor<Void> {

	// TODO entirely your job (except onCircle)

	private final Canvas canvas;

	private final Paint paint;

	public Draw(final Canvas canvas, final Paint paint) {
		this.canvas = canvas; // FIXME
		this.paint = paint; // FIXME
		paint.setStyle(Style.STROKE);
	}

	@Override
	public Void onCircle(final Circle c) {
		canvas.drawCircle(0, 0, c.getRadius(), paint);
		return null;
	}

	@Override
	public Void onStroke(final Stroke c) {
		int getColor = paint.getColor();
		paint.setColor(c.getColor());
		c.getShape().accept(this);
		paint.setColor(getColor);
		return null;
	}

	@Override
	public Void onFill(final Fill f) {
		Style Getstyle = paint.getStyle();
		paint.setStyle(Style.FILL_AND_STROKE);
		f.getShape().accept(this);
		paint.setStyle(Getstyle);

		return null;
	}

	@Override
	public Void onGroup(final Group g) {

		for (Shape s : g.getShapes()) s.accept(this);
		return null;
	}


	@Override
	public Void onLocation(final Location l) {
		canvas.translate(l.getX() , l.getY() );
		l.getShape().accept(this);
		canvas.translate( -(l.getX()) , -(l.getY()) );
		return null;
	}
	@Override
	public Void onRectangle(final Rectangle r) {
		canvas.drawRect( 0 , 0 , r.getWidth(), r.getHeight(), paint );
		return null;
	}
	@Override
	public Void onOutline(Outline o) {
		Style GetSyle = paint.getStyle();
		paint.setStyle(Style.STROKE);
		o.getShape().accept(this);
		paint.setStyle(GetSyle);
		return null;
	}
	@Override
	public Void onPolygon(final Polygon s) {
		final float [] pts = new float[s.getPoints().size() * 4];
		for (int i = 0; i< s.getPoints().size() - 1; i++  ){
			Point p1 = s.getPoints().get(i);
			Point p2 = s.getPoints().get(i +1);
			pts[i*4] = p1.getX();
			pts [i*4+1] = p1.getY();
			pts[i*4+2] = p2.getX();
			pts[i*4+3] = p2.getY();
		}
		pts[pts.length -4] = s.getPoints().get(s.getPoints().size()-1).getX();
		pts[pts.length -3] = s.getPoints().get(s.getPoints().size()-1).getY();
		pts[pts.length -2] = s.getPoints().get(0).getX();
		pts[pts.length -1] = s.getPoints().get(0).getY();
		canvas.drawLines(pts, paint);
		return null;
	}
}
