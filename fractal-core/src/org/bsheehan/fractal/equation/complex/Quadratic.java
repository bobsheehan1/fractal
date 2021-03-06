package org.bsheehan.fractal.equation.complex;

import org.bsheehan.fractal.equation.Equation;
import org.bsheehan.fractal.equation.EquationFactory;
import org.bsheehan.fractal.equation.Utils;

/**
 * Created by bob on 12/27/15.
 */
public class Quadratic implements Equation {

    @Override
    public short iterate(double x, double c) {
        return 0;
    }

    @Override
    public short iterate(ComplexNumber z, ComplexNumber c, short limit, double escape) {

        if (Utils.isPointInCardioidBulbs(c))
            return (short)(limit-1);

        for (short i = 0; i < limit; ++i) {
            z = z.mult(z).add(c);
            if (z.norm() > escape)
                return i;
        }
        return (short)(limit-1);
    }

    @Override
    public String toString(){
        return "Quadratic: f(z) = z^2 + c";
    }

    @Override
    public EquationFactory.EquationType getType() {
        return EquationFactory.EquationType.QUADRATIC;
    }

//    @Override
//    public Complex getLastRoot(){
//        return null;
//    }
}
