package org.usfirst.frc.team1764.robot.custom.control;

import org.usfirst.frc.team1764.robot.custom.control.signal.ControlSignal;
import org.usfirst.frc.team1764.robot.custom.subsystem.base.Controllable;
import org.usfirst.frc.team1764.robot.custom.util.Selectable;
import org.usfirst.frc.team1764.robot.custom.subsystem.base.Controllable;

public abstract class Controller<T extends ControlSignal> implements Selectable
{
    public abstract Controllable<T> getTarget();
    public abstract void set(T signal);

    /**
     * Called when the controller is selected
     * Used to run any pre configuration routines prior to routing input data through the controller
     */
    public abstract void init();

    /**
     * Called when the controller is deselected
     * Used to run any cleanup routines to undo any configuration changes made to the target while this controller was bound
     */
    public abstract void reset();
}
