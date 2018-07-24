package org.usfirst.frc.team1764.robot.custom.math;

public class ComplexOperations
{
    public static ComplexMap derivative(ComplexMap f, Complex delta)
    {
        return (input) -> (f.apply(input.add(delta)).subtract(f.apply(input)).divide(delta));
    }

    public static ComplexFunction derivative(ComplexFunction f, double delta)
    {
        return (input) -> (f.apply(input+delta).subtract(f.apply(input)).divide(Complex.pureReal(delta)));
    }

    public static Complex integral(ComplexFunction f, double start, double end, double resolution)
    {
        Complex result = Complex.pureReal(0);
        for(double i = start; i < end; i += resolution)
        {
            result.add(f.apply(i).multiply(Complex.pureReal(resolution)));
        }
        return result;
    }
    /* Input is a complex function in the arclength domain
     * Output is also a complex function in the arclength domain */

    /**
     *
     * @param curvatureProfile Input of arclength domain complex function which outputs complex in the form of
     *                         angularVelocity + (tangentialVelocity)i
     * @return ComplexFunction velocityProfile to be used. output of velocityProfile is a complex with real part being left velocity
     * and imaginary part being right wheel velocity
     */
    public static ComplexFunction genVelocityProfile(ComplexFunction curvatureProfile, double trackWidth)
    {
        /* input of inverseKinematics is a complexMap in the complex phase space of the system */
        return (t) -> Kinematics.inverseKinematics(curvatureProfile.apply(t), trackWidth);
    }
}
