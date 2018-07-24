package org.usfirst.frc.team1764.robot.custom.math;

public class Complex
{
    public double real;
    public double imaginary;

    public Complex(double real, double imaginary)
    {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Complex add(Complex c)
    {
        return new Complex(this.real + c.real, this.imaginary + c.imaginary);
    }

    public Complex subtract(Complex c)
    {
        return this.add(c.multiply(Complex.pureReal(-1)));
    }

    public Complex multiply(Complex c)
    {
        double newReal = this.real*c.real - this.imaginary*c.imaginary;
        double newImaginary = this.real*c.imaginary + this.imaginary*c.real;
        return new Complex(newReal, newImaginary);
    }

    public Complex divide(Complex c)
    {
        Complex conj = c.conjugate();
        double denom = c.multiply(conj).real;
        return this.multiply(conj).multiply(Complex.pureReal(1/denom));
    }

    public Complex conjugate()
    {
        return new Complex(this.real, -this.imaginary);
    }

    public double getAbs()
    {
        return Math.sqrt(this.real*this.real + this.imaginary*this.imaginary);
    }

    public double getPhase()
    {
        return Math.atan2(this.imaginary, this.real);
    }

    /*
    Same as get phase, but this time our phase is centered on the positive imaginary axis
    rather than the positive real
     */
    public double getPhaseInverse()
    {
        return Math.atan2(this.real, this.imaginary);
    }

    public static Complex normAtAngle(double angle)
    {
        return new Complex(Math.sin(angle), Math.cos(angle));
    }

    public static Complex pureReal(double real)
    {
        return new Complex(real, 0);
    }

    /**
     *
     * @param imaginary
     * @return Nathan's Job
     */
    public static Complex pureImaginary(double imaginary)
    {
        return new Complex(0, imaginary);
    }

    @Override
    public String toString()
    {
        return this.real + " + " + this.imaginary + "i";
    }
}
