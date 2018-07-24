package org.usfirst.frc.team1764.robot.custom.control;

public interface PIDControlInterface
{
    double getPIDInput();
    void usePIDOutput(double pidOutput);
}
