package org.usfirst.frc.team1764.robot.custom.control.chassis.pid;

import org.usfirst.frc.team1764.robot.custom.control.signal.ChassisControlSignal;
import org.usfirst.frc.team1764.robot.custom.control.signal.GearboxControlSignal;
import org.usfirst.frc.team1764.robot.custom.subsystem.base.Controllable;
import org.usfirst.frc.team1764.robot.custom.subsystem.base.PIDControllable;
import org.usfirst.frc.team1764.robot.custom.subsystem.drivetrain.ControllableChassis;
import org.usfirst.frc.team1764.robot.custom.subsystem.base.PIDControllable;
import org.usfirst.frc.team1764.robot.custom.subsystem.drivetrain.ControllableChassis;

/**
 * Uses difference in speed on each side of the chassis as an input
 * Setpoint will determine the desired difference in speed
 */
public class ChassisDeltaController extends ChassisPIDController
{
    private double additiveLeft, additiveRight; /* Added to the output so that the robot can move forward or backwards */

    public ChassisDeltaController(String name, ControllableChassis<? extends Controllable<GearboxControlSignal>> target, double p, double i, double d, double f)
    {
        super(name, target, p, i, d, f);
    }

    /**
     *
     */
    @Override
    public void set(ChassisControlSignal c)
    {
        this.additiveLeft = c.getLeft();
        this.additiveRight = c.getRight();
    }

    /**
     * configure the target's components for percent control and enable the target's PIDController
     */
    @Override
    public void init()
    {
        target.loadLeftController("percent");
        target.loadRightController("percent");
    }

    /**
     *
     * @return difference in speeds from the left and right side
     */
    @Override
    public double getPIDInput()
    {
        return target.getLeftSpeed() - target.getRightSpeed();
    }

    /**
     * sets each side's power to their respective additive value plus/minus the output
     * @param output the output from the target's PIDController
     */
    @Override
    public void usePIDOutput(double output)
    {
        /* add output to one side and subtract from the other so the chassis will turn */
        target.setLeft(additiveLeft + output);
        target.setRight(additiveRight - output);
    }

    public static ChassisDeltaController getPrototype(ControllableChassis<? extends Controllable<GearboxControlSignal>> target)
    {
        return new ChassisDeltaController("delta", target, 0.001, 0, 0, 0);
    }
}
