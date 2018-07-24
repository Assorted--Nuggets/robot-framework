package org.usfirst.frc.team1764.robot.custom.subsystem.gearbox;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import org.usfirst.frc.team1764.robot.custom.control.Controller;
import org.usfirst.frc.team1764.robot.custom.control.signal.GearboxControlSignal;
import org.usfirst.frc.team1764.robot.custom.subsystem.base.Controllable;
import org.usfirst.frc.team1764.robot.custom.subsystem.base.Movable;
import org.usfirst.frc.team1764.robot.custom.util.Selector;

public class TalonSRXGearbox implements Controllable<GearboxControlSignal>, Movable
{
    private TalonSRX master;
    private TalonSRX slave;

    private int currentPIDSlot = 0;
    public ControlMode currentControlMode = ControlMode.PercentOutput;

    private Selector<Controller<GearboxControlSignal>> controllerSelector;

    public TalonSRXGearbox(int port, int portSlave)
    {
        master = new TalonSRX(port);
        slave = new TalonSRX(portSlave);
        controllerSelector = new Selector<>();
    }


    public void setPIDGains(double v, double v1, double v2, double v3)
    {
        master.config_kP(currentPIDSlot, v, 1000);
        master.config_kI(currentPIDSlot, v1, 1000);
        master.config_kD(currentPIDSlot, v2, 1000);
        master.config_kF(currentPIDSlot, v3, 1000);
        slave.follow(master);
    }


    @Override
    public Selector<Controller<GearboxControlSignal>> getControllerSelector()
    {
        return controllerSelector;
    }

    @Override
    public boolean isLocked()
    {
        return false;
    }

    @Override
    public void set(GearboxControlSignal signal)
    {
        master.set(currentControlMode, signal.getPower());
    }

    @Override
    public double getSpeed()
    {
        return 0;
    }

    @Override
    public double getDistance()
    {
        return 0;
    }

    @Override
    public void resetDistance()
    {

    }
}
