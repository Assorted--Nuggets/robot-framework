package org.usfirst.frc.team1764.robot.custom.subsystem.drivetrain;

import mockinputs.MockGyro;
import mockoutputs.MockPIDController;
import org.usfirst.frc.team1764.robot.custom.control.gearbox.GearboxPercentController;
import org.usfirst.frc.team1764.robot.custom.control.gearbox.pid.GearboxFollowController;
import org.usfirst.frc.team1764.robot.custom.control.gearbox.pid.GearboxPositionController;
import org.usfirst.frc.team1764.robot.custom.control.gearbox.pid.GearboxSpeedController;
import org.usfirst.frc.team1764.robot.custom.control.signal.GearboxControlSignal;
import org.usfirst.frc.team1764.robot.custom.subsystem.drivetrain.ControllableChassis;
import org.usfirst.frc.team1764.robot.custom.subsystem.gearbox.MockControllableGearbox;
import org.usfirst.frc.team1764.robot.custom.control.signal.GearboxControlSignal;

public class MockControllableChassis extends ControllableChassis<MockControllableGearbox>
{
    private MockGyro gyro;
    private MockPIDController pidController;

    public MockControllableChassis()
    {
        super(new MockControllableGearbox(), new MockControllableGearbox());
        pidController = new MockPIDController(source, output);
        gyro = new MockGyro();
    }

    @Override
    public double getCurrentAngle()
    {
        return gyro.getAngle();
    }

    @Override
    public void resetHeading()
    {

    }

    @Override
    public void setPIDGains(double kP, double kI, double kD, double kF)
    {
        pidController.setPID(kP, kI, kD, kF);
    }

    @Override
    public void enablePID()
    {
        //System.out.println("Enabling PID on Chassis");
    }

    @Override
    public void disablePID()
    {
        //System.out.println("Disabling PID on Chassis");
    }

    @Override
    public void setSetpoint(double setpoint)
    {
        pidController.setSetpoint(setpoint);
    }

    @Override
    public void setSetpointRelative(double setpoint)
    {
        //System.out.println("Setting setpoint relative");
        pidController.setSetpoint(setpoint);
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

    public double getLeftOutput()
    {
        return left.getMotorOutput();
    }

    public double getRightOutput()
    {
        return right.getMotorOutput();
    }

    @Override
    public void initSubControllers()
    {
        this.left.getControllerSelector().addAll(GearboxPercentController.getPrototype(left), GearboxSpeedController
                                                         .getLeftPrototype(left),
                                                 GearboxPositionController.getPrototype(left), GearboxFollowController
                                                         .getPrototype(left, right));
        this.right.getControllerSelector().addAll(GearboxPercentController.getPrototype(right), GearboxSpeedController.getRightPrototype(right),
                                                  GearboxPositionController.getPrototype(right), GearboxFollowController.getPrototype(right, left));
    }

    @Override
    public void setLeft(double signal)
    {
        left.set(new GearboxControlSignal(signal));
    }

    @Override
    public void setRight(double signal)
    {
        right.set(new GearboxControlSignal(signal));
    }

    @Override
    public void resetDistance()
    {

    }
}
