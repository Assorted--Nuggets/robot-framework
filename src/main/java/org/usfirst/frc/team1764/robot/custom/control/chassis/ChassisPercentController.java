package org.usfirst.frc.team1764.robot.custom.control.chassis;

import org.usfirst.frc.team1764.robot.custom.control.signal.ChassisControlSignal;
import org.usfirst.frc.team1764.robot.custom.control.signal.GearboxControlSignal;
import org.usfirst.frc.team1764.robot.custom.subsystem.base.Controllable;
import org.usfirst.frc.team1764.robot.custom.subsystem.base.PIDControllable;
import org.usfirst.frc.team1764.robot.custom.subsystem.drivetrain.ControllableChassis;
import org.usfirst.frc.team1764.robot.custom.subsystem.base.PIDControllable;
import org.usfirst.frc.team1764.robot.custom.subsystem.drivetrain.ControllableChassis;

public class ChassisPercentController extends ChassisController
{
    public ChassisPercentController(String name, ControllableChassis<? extends Controllable<GearboxControlSignal>> target)
    {
        super(name, target);
    }

    /**
     * Configures the targets gearbox control for percent control
     */
    @Override
    public void init()
    {
        //System.out.println("LOADING PERCENT CONTROLLERS ONTO TARGET");
        target.loadLeftController("percent");
        target.loadRightController("percent");
    }

    /**
     *

     */
    @Override
    public void set(ChassisControlSignal c)
    {
        target.setLeft(c.getLeft());
        target.setRight(c.getRight());
    }

    /**
     * cuts power to targets gearboxes
     */
    @Override
    public void reset()
    {
        //TODO: Maybe just call reset on each side since we already know that the left and right side have percent control loaded in?
        target.setLeft(0);
        target.setRight(0);
    }

    //TODO: Remove the getPrototype method and just use static finals to declare our control
    /*
    to avoid null pointers, declare a static final of this in Robot or a class outside of robot, then construct it after constructing our target
     */
    public static ChassisPercentController getPrototype(ControllableChassis<? extends Controllable<GearboxControlSignal>> target)
    {
        return new ChassisPercentController("percent", target);
    }
}
