package org.usfirst.frc.team1764.robot.custom.control.gearbox.pid;

import org.usfirst.frc.team1764.robot.custom.control.PIDControlInterface;
import org.usfirst.frc.team1764.robot.custom.control.gearbox.GearboxController;
import org.usfirst.frc.team1764.robot.custom.control.signal.GearboxControlSignal;
import org.usfirst.frc.team1764.robot.custom.subsystem.gearbox.ControllableGearbox;
import org.usfirst.frc.team1764.robot.custom.control.PIDControlInterface;
import org.usfirst.frc.team1764.robot.custom.control.gearbox.GearboxController;
import org.usfirst.frc.team1764.robot.custom.subsystem.gearbox.ControllableGearbox;

public abstract class GearboxPIDController extends GearboxController implements PIDControlInterface
{
    private double p,i,d,f;

    GearboxPIDController(String name, ControllableGearbox target, double p, double i, double d, double f)
    {
        super(name, target);
        this.p = p;
        this.i = i;
        this.d = d;
        this.f = f;
    }

    public void set(GearboxControlSignal signal)
    {
        target.setSetpoint(signal.getPower());
    }

    @Override
    public void init()
    {
        target.setPIDGains(p, i, d, f);
        target.enablePID();
    }

    @Override
    public void reset()
    {
        target.disablePID();
    }

}
