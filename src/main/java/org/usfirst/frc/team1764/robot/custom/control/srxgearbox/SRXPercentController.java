package org.usfirst.frc.team1764.robot.custom.control.srxgearbox;

import com.ctre.phoenix.motorcontrol.ControlMode;
import org.usfirst.frc.team1764.robot.custom.subsystem.gearbox.TalonSRXGearbox;

public class SRXPercentController extends SRXController
{
    public SRXPercentController(String name, TalonSRXGearbox target)
    {
        super(name, target);
    }

    @Override
    public void init()
    {
        super.init();
        target.currentControlMode = ControlMode.PercentOutput;
    }
}
