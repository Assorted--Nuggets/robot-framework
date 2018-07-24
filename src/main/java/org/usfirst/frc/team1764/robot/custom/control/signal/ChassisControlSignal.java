package org.usfirst.frc.team1764.robot.custom.control.signal;

public class ChassisControlSignal extends ControlSignal
{
    private static ChassisControlSignal signalInstance;

    public static ChassisControlSignal getInstance()
    {
        if(signalInstance != null)
        {
            return signalInstance;
        }

        signalInstance = new ChassisControlSignal();
        return signalInstance;
    }
    public ChassisControlSignal()
    {
        super(2);
    }

    public double getLeft()
    {
        return getValue(0);
    }

    public double getRight()
    {
        return getValue(1);
    }

    public void setLeft(double value)
    {
        setValue(0, value);
    }

    public void setRight(double value)
    {
        setValue(1, value);
    }

    public static ChassisControlSignal generateSignal(double left, double right)
    {
        getInstance().setLeft(left);
        getInstance().setRight(right);
        return getInstance();
    }
}
