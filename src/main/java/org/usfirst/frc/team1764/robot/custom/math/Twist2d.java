package org.usfirst.frc.team1764.robot.custom.math;

public class Twist2d
{
    public double theta;
    public Vector2d v;

    public Twist2d(Vector2d v, double theta)
    {
        this.v = v;
        this.theta = theta;
    }

    public Twist2d add(Twist2d t)
    {
        this.theta += t.theta;
        this.v.add(t.v);
        return this;
    }

    public Twist2d scale(double factor)
    {
        this.theta *= factor;
        this.v.scale(factor);
        return this;
    }

    public Twist2d integrate(Twist2d v, double dt)
    {
        this.theta += v.theta*dt;
        this.v.add(v.getResultant());
        return this;
    }

    public Vector2d getResultant()
    {
        return new Vector2d(v).rotate(this.theta);
    }
}
