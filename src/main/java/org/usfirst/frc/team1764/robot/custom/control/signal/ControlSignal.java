package org.usfirst.frc.team1764.robot.custom.control.signal;

public abstract class ControlSignal
{
    private double[] inputs;

    public ControlSignal(int numInputs)
    {
        inputs = new double[numInputs];
    }

    protected void setValue(int inputIndex, double value)
    {
        this.inputs[inputIndex] = value;
    }

    protected double getValue(int inputIndex)
    {
        return this.inputs[inputIndex];
    }
}
