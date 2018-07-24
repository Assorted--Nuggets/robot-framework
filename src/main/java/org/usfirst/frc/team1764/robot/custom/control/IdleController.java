package org.usfirst.frc.team1764.robot.custom.control;

import org.usfirst.frc.team1764.robot.custom.control.signal.ControlSignal;
import org.usfirst.frc.team1764.robot.custom.subsystem.base.Controllable;
import org.usfirst.frc.team1764.robot.custom.subsystem.base.Controllable;


public class IdleController<T extends ControlSignal> extends Controller<T>
{
    public Controllable<T> target;

    public IdleController(Controllable<T> target)
    {
        this.target = target;
    }

    @Override
    public Controllable<T> getTarget()
    {
        return target;
    }

    @Override
    public void init()
    {
    }

    @Override
    public void reset()
    {

    }

    @Override
    public void set(ControlSignal signal)
    {

    }

    @Override
    public String getName()
    {
        return "idle";
    }

    @Override
    public void onSelect()
    {
        System.out.println("IDLE CONTROL LOADED");
    }

    @Override
    public void onDeselect()
    {

    }
}
