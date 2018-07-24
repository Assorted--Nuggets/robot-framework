package org.usfirst.frc.team1764.robot.custom.control.signal;

public class GearboxControlSignal extends ControlSignal
{
    public static final GearboxControlSignal OFF = new GearboxControlSignal(0);

    private static GearboxControlSignal leftSignalInstance;
    private static GearboxControlSignal rightSignalInstance;

    public static GearboxControlSignal getLeftSignal()
    {
        if(leftSignalInstance != null)
        {
            return leftSignalInstance;
        }
        leftSignalInstance = new GearboxControlSignal(0);
        return leftSignalInstance;
    }

    public static GearboxControlSignal getRightSignal()
    {
        if(rightSignalInstance != null)
        {
            return rightSignalInstance;
        }
        rightSignalInstance = new GearboxControlSignal(0);
        return rightSignalInstance;
    }

    public GearboxControlSignal(double power)
    {
        super(1);
        setPower(power);
    }

    public void setPower(double power)
    {
        setValue(0, power);
    }

    public double getPower()
    {
        return getValue(0);
    }

    public static GearboxControlSignal generateLeftSignal(double power)
    {
        getLeftSignal().setPower(power);
        return getLeftSignal();
    }

    public static GearboxControlSignal generateRightSignal(double power)
    {
        getRightSignal().setPower(power);
        return getRightSignal();
    }
}
