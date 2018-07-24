package org.usfirst.frc.team1764.robot.custom.math;

public class Vector2d
{
    public double x, y;

    public Vector2d(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector2d(Vector2d v)
    {
        this.x = v.x;
        this.y = v.y;
    }

    public Vector2d add(Vector2d v)
    {
        this.x += v.x;
        this.y += v.y;

        return this;
    }

    public Vector2d scale(double s)
    {
        this.x *= s;
        this.y *= y;

        return this;
    }

    public double magnitude()
    {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public Vector2d rotate(double angle)
    {
        double rotX = Math.sin(angle);
        double rotY = Math.cos(angle);
        double mag = this.magnitude();
        this.x = mag * rotX;
        this.y = mag * rotY;

        return this;
    }
}
