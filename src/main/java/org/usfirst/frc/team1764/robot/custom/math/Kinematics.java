package org.usfirst.frc.team1764.robot.custom.math;



public class Kinematics
{
    public static double[] inverseKinematics(double angularVel, double robotVel, double trackWidth)
    {
        double targetDeltaWheels = angularVel*trackWidth;

        double left = robotVel + targetDeltaWheels/2;
        double right = robotVel - targetDeltaWheels/2;

        return new double[] {left, right};
    }

    public static Complex inverseKinematics(Complex z, double trackWidth)
    {
        double[] wheelVels = inverseKinematics(z.real, z.imaginary, trackWidth);
        return new Complex(wheelVels[0], wheelVels[1]);
    }
}
