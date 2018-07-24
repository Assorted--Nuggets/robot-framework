package org.usfirst.frc.team1764.robot.custom.control.chassis;

import org.usfirst.frc.team1764.robot.custom.control.signal.ChassisControlSignal;
import org.usfirst.frc.team1764.robot.custom.control.signal.GearboxControlSignal;
import org.usfirst.frc.team1764.robot.custom.subsystem.base.Controllable;
import org.usfirst.frc.team1764.robot.custom.subsystem.base.PIDControllable;
import org.usfirst.frc.team1764.robot.custom.subsystem.drivetrain.ControllableChassis;
import org.usfirst.frc.team1764.robot.custom.subsystem.base.PIDControllable;
import org.usfirst.frc.team1764.robot.custom.subsystem.drivetrain.ControllableChassis;

public class ChassisSpeedController extends ChassisController
{
    public ChassisSpeedController(String name, ControllableChassis<? extends Controllable<GearboxControlSignal>> target)
    {
        super(name, target);
    }

    /**
     *
     */
    @Override
    public void set(ChassisControlSignal s)
    {
        target.setLeft(s.getLeft());
        target.setRight(s.getRight());
    }

    /**
     * Configures the target's gearboxes for speed control
     */
    @Override
    public void init()
    {
        target.loadLeftController("speed");
        target.loadRightController("speed");
    }

    /**
     * invokes reset on the target's gearboxes
     * TODO: Maybe just make this the default action and leave it to subclasses to override?
     */
    @Override
    public void reset()
    {
        target.resetLeft();
        target.resetRight();
    }

    /*
    TODO: remove this and statically declare. Refer to ChassisPercentController for details
     */
    public static ChassisSpeedController getPrototype(ControllableChassis<? extends Controllable<GearboxControlSignal>> target)
    {
        return new ChassisSpeedController("speed", target);
    }
}
