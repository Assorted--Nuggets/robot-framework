package org.usfirst.frc.team1764.robot.custom.control.gearbox;

import org.usfirst.frc.team1764.robot.custom.control.Controller;
import org.usfirst.frc.team1764.robot.custom.control.signal.GearboxControlSignal;
import org.usfirst.frc.team1764.robot.custom.subsystem.base.Controllable;
import org.usfirst.frc.team1764.robot.custom.subsystem.gearbox.ControllableGearbox;
import org.usfirst.frc.team1764.robot.custom.control.Controller;
import org.usfirst.frc.team1764.robot.custom.subsystem.base.Controllable;
import org.usfirst.frc.team1764.robot.custom.subsystem.gearbox.ControllableGearbox;

/* Implements Selectable so that we can create a Selector object to contain all of the possible control */

/*
TODO: Make this structure more like the proposed structure in ChassisController
The only major difference between GearboxController and ChassisController is how it manages PIDs
We could possibly have a Superclass called Controller that implements the same methods that ChassisController and GearboxController
implement to ensure that changes to how control are managed are reflected in GearboxController and ChassisController
 */
public abstract class GearboxController extends Controller<GearboxControlSignal>
{
    protected String name;
    protected ControllableGearbox target;

    public GearboxController(String name, ControllableGearbox target)
    {
        this.name = name;
        this.target = target;
    }

    @Override
    public Controllable<GearboxControlSignal> getTarget()
    {
        return target;
    }

    /* this should be overridden by subclasses and directly accesses the PIDGearbox components */
    /* called by the target when the Client of the target calls to set a value */

    @Override
    public void onSelect()
    {
        init();
    }

    @Override
    public void onDeselect()
    {
        reset();
    }

    @Override
    public String getName()
    {
        return this.name;
    }

}
