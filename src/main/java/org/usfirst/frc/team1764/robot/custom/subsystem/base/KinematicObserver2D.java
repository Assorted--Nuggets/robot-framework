package org.usfirst.frc.team1764.robot.custom.subsystem.base;

import org.usfirst.frc.team1764.robot.custom.math.Complex;

public class KinematicObserver2D<T extends Movable & Orientable>
{
    private double lastDistance;

    private Complex position;
    private Complex heading;

    private T target;

    public KinematicObserver2D(T target)
    {
        this.target = target;
    }

    public void init()
    {
        this.lastDistance = 0;
        this.position = Complex.pureReal(0);
    }

    public void update()
    {
        double ds = target.getDistance() - lastDistance;

        lastDistance = target.getDistance();

        heading = Complex.normAtAngle(Math.toRadians(target.getCurrentAngle()));

        position  = position.add(heading.multiply(new Complex(ds, 0)));

    }

    public Complex getCurrentPosition()
    {
        return position;
    }

    public void setCurrentPosition(Complex position)
    {
        this.position = position;
    }
}
