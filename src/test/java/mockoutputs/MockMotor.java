package mockoutputs;

public class MockMotor
{
    private double output;
    public void set(double s)
    {
        this.output = s;
        //System.out.println("MOTOR SPEED SET: " + s);
    }

    public double get()
    {
        return output;
    }
}
