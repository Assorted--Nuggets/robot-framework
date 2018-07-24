package org.usfirst.frc.team1764.robot.custom.control.gearbox.pid;

import org.usfirst.frc.team1764.robot.custom.subsystem.gearbox.ControllableGearbox;
import org.usfirst.frc.team1764.robot.custom.subsystem.gearbox.ControllableGearbox;

public class GearboxSpeedController extends GearboxPIDController
{

    public GearboxSpeedController(String name, ControllableGearbox target, double p, double i, double d, double f)
    {
        super(name, target, p,i,d,f);
    }

    @Override
    public double getPIDInput()
    {
        return target.getSpeed();
    }

    @Override
    public void usePIDOutput(double output)
    {
        target.setMotorOutput(output);
    }


    public static GearboxSpeedController getLeftPrototype(ControllableGearbox target)
    {
        return new GearboxSpeedController("speed", target, 0.0045, 0, 0, 0.00833);
    }

    public static GearboxSpeedController getRightPrototype(ControllableGearbox target)
    {
        return new GearboxSpeedController("speed", target, 0.0045, 0, 0, 0.00833);
    }
}
