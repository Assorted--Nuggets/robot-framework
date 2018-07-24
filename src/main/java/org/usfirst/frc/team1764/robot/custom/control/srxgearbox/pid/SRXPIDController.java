package org.usfirst.frc.team1764.robot.custom.control.srxgearbox.pid;

import org.usfirst.frc.team1764.robot.custom.control.srxgearbox.SRXController;
import org.usfirst.frc.team1764.robot.custom.subsystem.gearbox.TalonSRXGearbox;

public abstract class SRXPIDController extends SRXController
{
    private double p, i, d, f;

    public SRXPIDController(String name, TalonSRXGearbox target, double p, double i, double d, double f)
    {
        super(name, target);

        this.p = p;
        this.i = i;
        this.d = d;
        this.f = f;
    }

    @Override
    public void init()
    {
        super.init();
        target.setPIDGains(p, i, d, f);
    }

    @Override
    public void reset()
    {
        super.reset();
        target.setPIDGains(0,0,0,0);
    }
}
