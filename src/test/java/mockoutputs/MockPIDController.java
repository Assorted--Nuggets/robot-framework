package mockoutputs;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

public class MockPIDController
{
    private double kP, kI, kD, kF;

    private PIDSource source;
    private PIDOutput output;

    private double setpoint;

    public MockPIDController(PIDSource source, PIDOutput output)
    {
        this.source = source;
        this.output = output;
    }

    public void setPID(double p, double i, double d, double f)
    {
        this.kP = p;
        this.kI = i;
        this.kD = d;
        this.kF = f;
    }

    public double[] getPIDGains()
    {
        return new double[] {kP, kI, kD, kF};
    }

    public double getSetpoint()
    {
        return setpoint;
    }

    public void setSetpoint(double s)
    {
        this.setpoint = s;
    }

    public double getInput()
    {
        return source.pidGet();
    }
}
