package org.usfirst.frc.team1764.robot.custom.control.gearbox;

import org.usfirst.frc.team1764.robot.custom.control.signal.GearboxControlSignal;
import org.usfirst.frc.team1764.robot.custom.subsystem.gearbox.ControllableGearbox;
import org.usfirst.frc.team1764.robot.custom.subsystem.gearbox.ControllableGearbox;

public class GearboxPercentController extends GearboxController
{
    public GearboxPercentController(String name, ControllableGearbox target)
    {
        super(name, target);
    }

    @Override
    public void set(GearboxControlSignal signal)
    {
        target.setMotorOutput(signal.getPower());
    }

    @Override
    public void init()
    {

    }

    @Override
    public void reset()
    {
        set(GearboxControlSignal.OFF);
    }

    public static GearboxPercentController getPrototype(ControllableGearbox target)
    {
        return new GearboxPercentController("percent", target);
    }
}
