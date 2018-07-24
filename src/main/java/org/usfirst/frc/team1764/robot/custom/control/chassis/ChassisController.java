package org.usfirst.frc.team1764.robot.custom.control.chassis;

import org.usfirst.frc.team1764.robot.custom.control.Controller;
import org.usfirst.frc.team1764.robot.custom.control.signal.ChassisControlSignal;
import org.usfirst.frc.team1764.robot.custom.control.signal.GearboxControlSignal;
import org.usfirst.frc.team1764.robot.custom.subsystem.base.Controllable;
import org.usfirst.frc.team1764.robot.custom.subsystem.base.PIDControllable;
import org.usfirst.frc.team1764.robot.custom.subsystem.drivetrain.ControllableChassis;
import org.usfirst.frc.team1764.robot.custom.control.Controller;
import org.usfirst.frc.team1764.robot.custom.subsystem.base.PIDControllable;
import org.usfirst.frc.team1764.robot.custom.subsystem.drivetrain.ControllableChassis;

public abstract class ChassisController extends Controller<ChassisControlSignal>
{
    private String name;    /* String to identify the controller by */
    protected ControllableChassis<? extends Controllable<GearboxControlSignal>> target;   /* Pointer to the Chassis that this operates on */

    /**
     * Constructs a new ChassisController object
     * @param name Unique String to identify this controller by. Keep track of it
     * @param target The Chassis object that we want this controller to output to
     */
    public ChassisController(String name, ControllableChassis<? extends Controllable<GearboxControlSignal>> target)
    {
        this.name = name;
        this.target = target;
    }

    @Override
    public ControllableChassis<? extends Controllable<GearboxControlSignal>> getTarget()
    {
        return target;
    }

    /**
     *
     */
    public abstract void set(ChassisControlSignal c);

    /*
    It's possible that we can just get rid of init and reset as they are only called by onselect and onDeselect
    however reset() is called by Chassis when reset is called on it so we would have to add functionality for deselecting
    on the Selector class, which would just call onDeselect on the selected object and then assign null to the selected object
     */





    /**
     * By default, when a control mode is selected, we wish to disable any pid control that are currently running on the chassis
     * Then we run init() which is defined by our subclass
     */
    @Override
    public void onSelect()
    {
        target.disablePID();
        init();
    }

    /**
     * When we deselect, we wish to run the reset routine which is defined by our subclass
     */
    @Override
    public void onDeselect()
    {
        reset();
    }

    /**
     * To be used by the parent Selector object to identify this controller
     * @return the name of this controller
     */
    @Override
    public String getName()
    {
        return this.name;
    }
}
