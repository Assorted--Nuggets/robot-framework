package org.usfirst.frc.team1764.robot.custom.control.srxgearbox.pid;

import com.ctre.phoenix.motorcontrol.ControlMode;
import org.usfirst.frc.team1764.robot.custom.subsystem.gearbox.TalonSRXGearbox;

public class SRXSpeedController extends SRXPIDController
{
    public SRXSpeedController(String name, TalonSRXGearbox target, double p, double i, double d, double f)
    {
        super(name, target, p, i, d, f);
    }

    @Override
    public void init()
    {
        super.init();
        target.currentControlMode = ControlMode.Velocity;
    }
}
