package edu.luc.etl.cs313.android.shapes.model;

/**
 * A rectangle with specified width and height.
 */
public class Rectangle implements Shape {

	protected final int width, height;
	//protected final int parameter;

	public Rectangle(final int width, final int height) {
		assert width >= 0;
		assert height >= 0;
		this.width = width;
		this.height = height;


	}

	public int getParameter(){
	//assert parameter >= 0;
	int parameter = ( width *2) + ( height*2);

		return parameter ;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int IntValue(){return 1;}

	@Override
	public <Result> Result accept(final Visitor<Result> v) {
		return v.onRectangle(this);
	}
}
