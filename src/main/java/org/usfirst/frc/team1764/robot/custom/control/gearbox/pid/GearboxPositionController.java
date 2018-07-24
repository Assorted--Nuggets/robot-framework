package org.usfirst.frc.team1764.robot.custom.control.gearbox.pid;

import org.usfirst.frc.team1764.robot.custom.subsystem.gearbox.ControllableGearbox;
import org.usfirst.frc.team1764.robot.custom.subsystem.gearbox.ControllableGearbox;

public class GearboxPositionController extends GearboxPIDController
{
    public GearboxPositionController(String name, ControllableGearbox target, double p, double i, double d, double f)
    {
        super(name, target, p, i, d, f);
    }

    @Override
    public double getPIDInput()
    {
        return target.getDistance();
    }

    @Override
    public void usePIDOutput(double output)
    {
        target.setMotorOutput(output);
    }


    public static GearboxPositionController getPrototype(ControllableGearbox target)
    {
        return new GearboxPositionController("position", target, 0, 0, 0, 0);
    }
}
