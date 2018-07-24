package org.usfirst.frc.team1764.robot.custom.subsystem.drivetrain;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SPI;
import org.usfirst.frc.team1764.robot.custom.control.chassis.ChassisPercentController;
import org.usfirst.frc.team1764.robot.custom.control.chassis.ChassisSpeedController;
import org.usfirst.frc.team1764.robot.custom.control.chassis.pid.ChassisAngleController;
import org.usfirst.frc.team1764.robot.custom.control.srxgearbox.SRXPercentController;
import org.usfirst.frc.team1764.robot.custom.subsystem.base.KinematicObserver2D;
import org.usfirst.frc.team1764.robot.custom.subsystem.gearbox.TalonSRXGearbox;

public class SRXControllableChassis extends ControllableChassis<TalonSRXGearbox>
{
    private ADXRS450_Gyro gyro;
    private PIDController pidController;

    private KinematicObserver2D<SRXControllableChassis> kinematicsObserver;

    public SRXControllableChassis(int masterLeft, int slaveLeft, int masterRight, int slaveRight)
    {
        super(new TalonSRXGearbox(masterLeft, slaveLeft), new TalonSRXGearbox(masterRight, slaveRight));
        gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);

        pidController = new PIDController(0, 0, 0, source, output);
        pidController.setOutputRange(-0.15, 0.15);
        kinematicsObserver = new KinematicObserver2D<>(this);
        addAttribute("XPOS", () -> kinematicsObserver.getCurrentPosition().real);
        addAttribute("YPOS", () -> kinematicsObserver.getCurrentPosition().imaginary);
        createSubsystem();
        getTarget().setName("chassis");
        kinematicsObserver.init();

        getControllerSelector().addAll(ChassisSpeedController.getPrototype(this),
                                       ChassisPercentController.getPrototype(this),
                                       ChassisAngleController.getPrototype(this));
    }

    @Override
    public double getCurrentAngle()
    {
        return gyro.getAngle();
    }

    @Override
    public void resetHeading()
    {
        gyro.reset();
    }

    @Override
    public void setPIDGains(double kP, double kI, double kD, double kF)
    {
        pidController.setPID(kP, kI, kD, kF);
    }

    @Override
    public void enablePID()
    {
        pidController.enable();
    }

    @Override
    public void disablePID()
    {
        pidController.disable();
    }

    @Override
    public void setSetpoint(double setpoint)
    {
        pidController.setSetpoint(setpoint);
    }

    @Override
    public void setSetpointRelative(double setpoint)
    {
        pidController.setSetpoint(pidController.getSetpoint() + setpoint);
    }

    @Override
    public void setOutputRange(double lower, double upper)
    {
        pidController.setOutputRange(lower, upper);
    }

    @Override
    public void setAbsoluteTolerance(double tolerance)
    {
        pidController.setAbsoluteTolerance(tolerance);
    }

    @Override
    public boolean onTarget()
    {
        return pidController.onTarget();
    }

    @Override
    public void resetDistance()
    {
        left.resetDistance();
        right.resetDistance();
    }

    public void updateKinematicsObserver()
    {
        kinematicsObserver.update();
    }

    public KinematicObserver2D<SRXControllableChassis> getKinematicsObserver()
    {
        return kinematicsObserver;
    }

    @Override
    public void initSubControllers()
    {
        left.getControllerSelector().addAll(new SRXPercentController("percent", left));
        right.getControllerSelector().addAll(new SRXPercentController("percent", right));
    }
}
