package org.usfirst.frc.team1764.robot.custom.subsystem.base;

import org.usfirst.frc.team1764.robot.custom.control.Controller;
import org.usfirst.frc.team1764.robot.custom.control.signal.ControlSignal;
import org.usfirst.frc.team1764.robot.custom.util.Selector;

public interface Controllable<T extends ControlSignal>
{
    Selector<Controller<T>> getControllerSelector();

    boolean isLocked();
    default void loadController(String s)
    {
        if(!isLocked())
            getControllerSelector().select(s);
    }
    default void set(T signal)
    {
        if(getControllerSelector().hasSelected())
        {
            getControllerSelector().getSelected().set(signal);
        }
    }

    default void set(T signal, String controllerName)
    {
        loadController(controllerName);
        set(signal);
    }

    default void reset()
    {
        if(getControllerSelector().hasSelected())
        {
            getControllerSelector().getSelected().reset();
        }
    }
}
