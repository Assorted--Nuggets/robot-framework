package org.usfirst.frc.team1764.robot.custom.subsystem.gearbox;

import org.usfirst.frc.team1764.robot.custom.control.signal.GearboxControlSignal;
import org.usfirst.frc.team1764.robot.custom.subsystem.base.Movable;
import org.usfirst.frc.team1764.robot.custom.subsystem.base.PIDControllable;

public abstract class ControllableGearbox extends PIDControllable<GearboxControlSignal> implements Movable
{
    public abstract void setMotorOutput(double value);
    public abstract double getMotorOutput();
}
