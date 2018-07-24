package org.usfirst.frc.team1764.robot.custom.subsystem.gearbox;

import mockinputs.MockEncoder;
import mockoutputs.MockMotor;
import mockoutputs.MockPIDController;
import org.usfirst.frc.team1764.robot.custom.subsystem.gearbox.ControllableGearbox;

public class MockControllableGearbox extends ControllableGearbox
{
    private MockMotor motor;
    private MockEncoder encoder;
    private MockPIDController pidController;

    public MockControllableGearbox()
    {
        motor = new MockMotor();
        encoder = new MockEncoder(0, 1);
        pidController = new MockPIDController(source, output);
    }

    @Override
    public void setMotorOutput(double value)
    {
        motor.set(value);
    }

    @Override
    public double getMotorOutput()
    {
        return motor.get();
    }

    @Override
    public double getSpeed()
    {
        return encoder.getRate();
    }

    @Override
    public double getDistance()
    {
        return encoder.getDisplacement();
    }

    @Override
    public void resetDistance()
    {

    }

    @Override
    public void setPIDGains(double kP, double kI, double kD, double kF)
    {
//        System.out.println("Controller configured for gains: ");
//        System.out.println("KP = " + kP);
//        System.out.println("KI = " + kI);
//        System.out.println("KD = " + kD);
//        System.out.println("KF = " + kF);
    }

    @Override
    public void enablePID()
    {
        //System.out.println("PID Enabled");
    }

    @Override
    public void disablePID()
    {
        //System.out.println("PID Disabled");
    }

    @Override
    public void setSetpoint(double setpoint)
    {
//        System.out.println("Setting setpoint for: " + setpoint);
        pidController.setSetpoint(setpoint);
    }

    @Override
    public void setSetpointRelative(double setpoint)
    {
        //System.out.println("Setting setpoint relative: " + setpoint);
    }

    @Override
    public void setOutputRange(double lower, double upper)
    {
        //System.out.println("Setting output range <" + lower + ", " + upper + ">");
    }

    @Override
    public void setAbsoluteTolerance(double tolerance)
    {
        //System.out.println("Setting Absolute Tolerance: " + tolerance);
    }

    @Override
    public boolean onTarget()
    {
        return false;
    }

    public double getSetpoint()
    {
        return pidController.getSetpoint();
    }
}
