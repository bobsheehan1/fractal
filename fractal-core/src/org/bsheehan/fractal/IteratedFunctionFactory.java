package org.bsheehan.fractal;

/**
 * @author bsheehan@baymoon.com
 * @date April 12, 2011
 * 
 * @name IteratedFunctionFactory
 * @description Base class for iteration function systems on the complex plane. This factory returns 
 * an iterative function class that can be used to create a RGB texture of a fractal image. 
 */
public class IteratedFunctionFactory {
	/** enum for different fractal interated systme types **/
	public enum FractalType {
		NONE,
		MANDELBROT,
		MANDELBROT_CUBIC,
		MANDELBROT_JULIA,
		MANDELBROT_CUBIC_JULIA,
		MANDELBROT_QUARTIC,
		MANDELBROT_QUARTIC_JULIA
	};

	/**
	 * Create an instance of a complex plane iterative method class
	 * @param type
	 * @return
	 */
	static public IIteratedFunction createIteratedFunction(FractalType type){
		switch(type){
		case MANDELBROT:
			return new MandelbrotFunction();
		case MANDELBROT_CUBIC:
			return new MandelbrotCubicFunction();
		case MANDELBROT_JULIA:
			return new MandelbrotJuliaFunction();
		case MANDELBROT_CUBIC_JULIA:
			return new MandelbrotCubicJuliaFunction();
		case MANDELBROT_QUARTIC:
			return new MandelbrotQuarticFunction();
		case MANDELBROT_QUARTIC_JULIA:
			return new MandelbrotQuarticJuliaFunction();
		}
		return null;
	}

	/**
	 * Randomly create and return a complex plane iterated method instance.
	 * @return
	 */
	static public IIteratedFunction createRandomFunction(){
		final int index = (int) (Math.random() * FractalType.values().length);
		final FractalType type = FractalType.values()[index];
		final IIteratedFunction function = createIteratedFunction(type);
		return function;
	}

}
