package org.bsheehan.fractal;

import org.bsheehan.fractal.equation.Equation;
import org.bsheehan.fractal.equation.complex.ComplexNumber;
import org.bsheehan.fractal.equation.complex.Quadratic;

import java.awt.*;

public class FractalConfig {

	public FractalConfig()
	{
		this.setFractalRegion(new Rectangle.Double(-2.0, -2.0, 4.0, 4.0));
		this.equation = new Quadratic();
	}

	public FractalConfig(Equation equation, double left, double top, double right, double bottom)
	{
		this.setFractalRegion(new Rectangle.Double(left, top, right - left, bottom - top));
		this.equation = equation;
	}

	public Equation equation;

	/** This is a limit for the iteration to break at. Successive iterations will
	 * converge or diverge at a particular rate based on the initial location and iterative
	 * function in the complex plane. The 'velocity' of the iteration escape is the metric used to
	 * map an RGB color for function display **/
	protected double escapeRadius = 4.0;

	/** This is a maximum limit on number of iterations if escape radius not met. This is a critical
	 * parameter and directly maps to the runtime memory footprint of the application. Larger values
	 * allow for finer grain detail iteration orbits before they are considered escaped.   **/
	protected short maxIterations = 2048;

	/** complex plane region iterated over **/
	protected Rectangle.Double fractalRegion;

	/** The complex quadratic constant used during iteration **/
	public ComplexNumber zConstant = new ComplexNumber(0, 0);

	/** The complex starting origin used during iteration **/
	public ComplexNumber zOrigin = new ComplexNumber(0, 0);

	public short getMaxIterations() {
		return maxIterations;
	}

	public Rectangle.Double getFractalRegion() {
		return this.fractalRegion;
	}

	public void setFractalRegion(Rectangle.Double region) {
		this.fractalRegion = new Rectangle.Double();
		this.fractalRegion.setRect(region);
	}

	/**
	 * For each iteration for the function set the initial values on the complex plane.
	 */
	public void setInitialConditions(ComplexNumber z0, ComplexNumber c) {
		this.zOrigin = z0;
		this.zConstant = c;
	}

	public short iterate(ComplexNumber z, ComplexNumber c) {
		return equation.iterate(z, c, maxIterations, escapeRadius);
	}

	//public Complex getLastRoot(){
//		return equation.getLastRoot();
//	}
}
