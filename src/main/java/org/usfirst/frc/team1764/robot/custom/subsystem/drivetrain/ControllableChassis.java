package org.usfirst.frc.team1764.robot.custom.subsystem.drivetrain;

import org.usfirst.frc.team1764.robot.custom.control.gearbox.GearboxPercentController;
import org.usfirst.frc.team1764.robot.custom.control.gearbox.pid.GearboxFollowController;
import org.usfirst.frc.team1764.robot.custom.control.gearbox.pid.GearboxPositionController;
import org.usfirst.frc.team1764.robot.custom.control.gearbox.pid.GearboxSpeedController;
import org.usfirst.frc.team1764.robot.custom.control.signal.ChassisControlSignal;
import org.usfirst.frc.team1764.robot.custom.control.signal.GearboxControlSignal;
import org.usfirst.frc.team1764.robot.custom.subsystem.base.Controllable;
import org.usfirst.frc.team1764.robot.custom.subsystem.base.Movable;
import org.usfirst.frc.team1764.robot.custom.subsystem.base.Orientable;
import org.usfirst.frc.team1764.robot.custom.subsystem.base.PIDControllable;
import org.usfirst.frc.team1764.robot.custom.subsystem.gearbox.ControllableGearbox;

public abstract class ControllableChassis<T extends Controllable<GearboxControlSignal> & Movable>
        extends PIDControllable<ChassisControlSignal>
        implements Movable, Orientable
{
    protected T left;
    protected T right;

    public ControllableChassis(T l, T r)
    {
        super();
        this.left = l;
        this.right = r;
        initSubControllers();
    }

    public abstract void initSubControllers();

    public void loadLeftController(String controllerName)
    {
        left.loadController(controllerName);
    }

    public void loadRightController(String controllerName)
    {
        right.loadController(controllerName);
    }

    public void setLeft(double signal)
    {
        left.set(GearboxControlSignal.generateLeftSignal(signal));
    }

    public void setRight(double signal)
    {
        right.set(GearboxControlSignal.generateRightSignal(signal));
    }

    public void resetLeft()
    {
        left.reset();
    }

    public void resetRight()
    {
        right.reset();
    }

    public double getLeftSpeed()
    {
        return left.getSpeed();
    }

    public double getRightSpeed()
    {
        return right.getSpeed();
    }

    public double getLeftDistance()
    {
        return left.getDistance();
    }

    public double getRightDistance()
    {
        return right.getDistance();
    }

    @Override
    public double getDistance()
    {
        return (left.getDistance() + right.getDistance())/2;
    }

    @Override
    public double getSpeed()
    {
        return (right.getSpeed() + left.getSpeed())/2;
    }

    public void arcadeDrive(double forward, double turn)
    {
        set(ChassisControlSignal.generateSignal((forward + turn) * 0.25, (forward - turn) * 0.25));
    }

}
