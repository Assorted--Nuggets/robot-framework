package org.usfirst.frc.team1764.robot.custom.subsystem.base;

public interface PID
{
    void setPIDGains(double kP, double kI, double kD, double kF);
    void enablePID();
    void disablePID();
    void setSetpoint(double setpoint);
    void setSetpointRelative(double setpoint);

    void setOutputRange(double lower, double upper);
    void setAbsoluteTolerance(double tolerance);

    boolean onTarget();
    double returnPIDInput();
    void usePIDOutput(double output);
}
