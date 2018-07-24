package org.usfirst.frc.team1764.robot.custom.control.gearbox.pid;

import org.usfirst.frc.team1764.robot.custom.subsystem.gearbox.ControllableGearbox;
import org.usfirst.frc.team1764.robot.custom.subsystem.gearbox.ControllableGearbox;

public class GearboxFollowController extends GearboxPIDController
{
    private ControllableGearbox master;

    public GearboxFollowController(String name, ControllableGearbox target, ControllableGearbox master, double p, double i,
                                   double d, double f)
    {
        super(name, target, p, i, d, f);
        this.master = master;
    }

    public static GearboxFollowController getPrototype(ControllableGearbox target, ControllableGearbox master)
    {
        return new GearboxFollowController("follow", target, master, 0.009, 0, 0.003, 0.00833);
    }

    @Override
    public double getPIDInput()
    {
        return master.getSpeed() - target.getSpeed();
    }

    @Override
    public void usePIDOutput(double output)
    {
        master.setMotorOutput(-output/2);
        target.setMotorOutput(output/2);
    }
}
