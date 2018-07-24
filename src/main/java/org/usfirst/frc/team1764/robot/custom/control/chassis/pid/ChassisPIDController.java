package org.usfirst.frc.team1764.robot.custom.control.chassis.pid;

import org.usfirst.frc.team1764.robot.custom.control.PIDControlInterface;
import org.usfirst.frc.team1764.robot.custom.control.chassis.ChassisController;
import org.usfirst.frc.team1764.robot.custom.control.signal.ChassisControlSignal;
import org.usfirst.frc.team1764.robot.custom.control.signal.GearboxControlSignal;
import org.usfirst.frc.team1764.robot.custom.subsystem.base.Controllable;
import org.usfirst.frc.team1764.robot.custom.subsystem.base.PIDControllable;
import org.usfirst.frc.team1764.robot.custom.subsystem.drivetrain.ControllableChassis;
import org.usfirst.frc.team1764.robot.custom.control.PIDControlInterface;
import org.usfirst.frc.team1764.robot.custom.control.chassis.ChassisController;
import org.usfirst.frc.team1764.robot.custom.subsystem.drivetrain.ControllableChassis;

/**
 * Subclasses ChassisController but also configures PIDF gains on the target
 * Has methods for pulling data from the target and routing it to the target's pid controller
 */
public abstract class ChassisPIDController extends ChassisController implements PIDControlInterface
{
    /**
     * All PID Gains are multiplied by their respective term and summed together to generate an output
     * E(t) = Error = position - setpoint
     * S(t) = Setpoint
     * @param name String to identify this controller by
     * @param target Chassis Subsystem that we want to operate on
     * @param p Proportional gain (kP)*E(t)
     * @param i Integral gain (kI)*integral(0, T){E(t)*dt}
     * @param d Differential gain (kD)*(dE(t)/dt)
     * @param f Feed forward gain (kF)*(S(t))
     */
    ChassisPIDController(String name, ControllableChassis<? extends Controllable<GearboxControlSignal>> target, double p, double i, double d, double f)
    {
        super(name, target);
        target.setPIDGains(p, i, d, f);
    }

    /**
     * Note that this does not set the setpoint, setSetpoint is managed by the target PIDSubsystem
     */
    public abstract void set(ChassisControlSignal c);

    /**
     * Pulls data from the target's available sensors
     * target reads return value of this function and passes it to its PIDController
     * @return value read by sensors that we want to feed into the PIDController
     */


    /**
     * Override onSelect so that it does not disable the target when it is selected
     */
    @Override
    public void onSelect()
    {
        init();
        target.enablePID();
    }

    @Override
    public void onDeselect()
    {
        target.disablePID();
        reset();

    }
    /*
    TODO: Maybe move disable into here?
     */
    @Override
    public void reset()
    {
        target.resetLeft();
        target.resetRight();
    }
}
