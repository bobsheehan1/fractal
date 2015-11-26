package org.bsheehan.fractal;


import java.awt.*;

/**
 * @author bsheehan@baymoon.com
 * @date April 12, 2011
 * 
 * @name AbstractFractalFunction
 * @description Base class for iteration functions on the complex plane. 
 */
public abstract class AbstractFractalFunction implements IIteratedFunction {

	/** This is a limit for the iteration to break at. Successive iterations will
	 * converge or diverge at a particular rate based on the initial location and iterative 
	 * function in the complex plane. The 'velocity' of the iteration escape is the metric used to
	 * map an RGB color for function display **/
	protected double escapeRadius = 4.0;

	/** This is a maximum limit on number of iterations if escape radius not met. This is a critical
	 * parameter and directly maps to the runtime memory footprint of the application. Larger values
	 * allow for finer grain detail iteration orbits before they are considered escaped.   **/
	protected short maxIterations = 2048;
	
	protected FractalConfig config = new FractalConfig();

	/** the rectangular region that the iterative method is defined over **/
	//protected float top, bottom, left, right;

	/** complex plane region iterated over **/
	protected Rectangle.Double fractalRegion;

	/** The complex quadratic constant used during iteration **/
	public Complex c = new Complex(0, 0);

	/** The complex starting origin used during iteration **/
	public Complex z0 = new Complex(0, 0);

	public short getMaxIterations() {
		return this.maxIterations;
	}

	public Rectangle.Double getFractalRegion() {
		return this.fractalRegion;
	}
	
	public void setFractalRegion(Rectangle.Double region) {
		this.fractalRegion.setRect(region);
	}

	/**
	 * For each iteration for the function set the initial values on the complex plane.
	 */
	public void setInitialConditions(Complex z0, Complex c) {
		this.z0 = z0;
		this.c = c;
	}

	
	public void setInitialConditions(IIteratedFunction func) {
		this.z0.setValues(((AbstractFractalFunction)(func)).z0.r, ((AbstractFractalFunction)(func)).z0.i);
		this.c.setValues(((AbstractFractalFunction) (func)).c.r, ((AbstractFractalFunction) (func)).c.i);
	}

	/** 
	 * Set the center point of where a particular rectangular region of the function is to be calculated.
	 **/
	public void setCenter(Point.Double center) {
		double offsetX = center.x - (double)fractalRegion.getCenterX();
		double offsetY = center.y - (double)fractalRegion.getCenterY();

		this.fractalRegion.setRect(fractalRegion.x - offsetX,
				fractalRegion.y - offsetY,
				this.fractalRegion.getWidth(),
				this.fractalRegion.getHeight());

		//this.fractalRegion.offset(center.x - this.fractalRegion.centerX(),
		//		center.y - this.fractalRegion.centerY());
	}

	/** offset region by percentage **/
	public void setOffset(double x, double y){
		double xOffset = (double)(fractalRegion.getWidth() * x);
		double yOffset = (double)(fractalRegion.getHeight() * y);
		setCenter(new Point.Double((double) (fractalRegion.getCenterX() - xOffset), (double) (fractalRegion.getCenterY() - yOffset)));

		//if (x != 0.0f )
		//	setZoom(.1f);
	}


	/**
	 * Randomly create a region in the complex boundary of interest.
	 */
	public void setRandomRegion(boolean centered) {
//		reset();
//		boolean validCenter = false;
//		final Complex center = new Complex(0,0);
//
//		if (!centered){
//			while (!validCenter){
//				center.setValues(Math.random() * (this.right-this.left) - this.right, Math.random() * (this.bottom-this.top) - this.bottom);
//				validCenter = !isPointInCardioidBulbs(center);
//			}
//
//			setCenter(new Point.Float((float)center.getReal(), (float)center.getImaginary()));
//			}
//		final float zoom = (float) Math.random();
//		setZoom(zoom);
	}

	/**
	 * Lifted this optimization off Wikipedia. The central cardioid bulbs in the Mandelbrot set are guaranteed to generate
	 * orbits that converge. This is the least optimal type of iteration that will always hit kMaxIterations.
	 * If we detect a calculation in these bulbs, we can just set the iteration to kMaxIterations and move on.
	 * @param z
	 * @return
	 */
	protected boolean isPointInCardioidBulbs(Complex z) {
		final double r = z.getReal();
		final double i = z.getImaginary();
		final double term1 = r-.25;
		final double term2 = i*i;
		final double q = term1*term1 + term2;
		return q*(q+term1) < .25*term2;
	}

	/**
	 * Set region zoom about the center point of defined boundary region.
	 */
	public void setZoom(double zoom) {
		final double centerX = (double)this.fractalRegion.getCenterX();
		final double centerY = (double)this.fractalRegion.getCenterY();
		final double halfWidth = (double)this.fractalRegion.getWidth() * zoom * .5f;
		final double halfHeight = (double)this.fractalRegion.getHeight() * zoom * .5f;

		this.fractalRegion.setRect(centerX - halfWidth, centerY - halfHeight, halfWidth*2f, halfHeight*2f );
		//this.fractalRegion.left = centerX - halfWidth;
		//this.fractalRegion.right = centerX + halfWidth;
		//this.fractalRegion.top = centerY - halfheight;
		//this.fractalRegion.bottom = centerY + halfheight;
	}
	
	public void setScale(double screenAspectRatio)
	{
		double left =  (double)this.fractalRegion.getX()*screenAspectRatio;
		double right =  (double)this.fractalRegion.getY()*screenAspectRatio;

		this.fractalRegion.setRect(left, this.fractalRegion.getY(), right-left, this.fractalRegion.getHeight());

		//this.fractalRegion.left *= screenAspectRatio;
		//this.fractalRegion.right *= screenAspectRatio;
	}
	
	@Override
	public FractalConfig getFractalConfig() {
		return this.config;
	}

}
