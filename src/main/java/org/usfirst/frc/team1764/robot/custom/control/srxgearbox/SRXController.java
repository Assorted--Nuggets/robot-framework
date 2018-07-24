package org.usfirst.frc.team1764.robot.custom.control.srxgearbox;

import com.ctre.phoenix.motorcontrol.ControlMode;
import org.usfirst.frc.team1764.robot.custom.control.Controller;
import org.usfirst.frc.team1764.robot.custom.control.signal.GearboxControlSignal;
import org.usfirst.frc.team1764.robot.custom.subsystem.base.Controllable;
import org.usfirst.frc.team1764.robot.custom.subsystem.gearbox.TalonSRXGearbox;

public abstract class SRXController extends Controller<GearboxControlSignal>
{
    protected TalonSRXGearbox target;
    private String name;
    protected ControlMode lastControlMode;

    public SRXController(String name, TalonSRXGearbox target)
    {
        this.target = target;
        this.name = name;
    }

    @Override
    public Controllable<GearboxControlSignal> getTarget()
    {
        return target;
    }

    @Override
    public void set(GearboxControlSignal gearboxControlSignal)
    {

    }

    @Override
    public void init()
    {
        lastControlMode = target.currentControlMode;
    }

    @Override
    public void reset()
    {
        target.currentControlMode = lastControlMode;
    }

    @Override
    public String getName()
    {
        return name;
    }

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
}
