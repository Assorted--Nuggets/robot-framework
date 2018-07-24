package org.usfirst.frc.team1764.robot.custom.subsystem.base;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import org.usfirst.frc.team1764.robot.custom.control.PIDControlInterface;
import org.usfirst.frc.team1764.robot.custom.control.signal.ControlSignal;

public abstract class PIDControllable<T extends ControlSignal> extends ControllableBase<T> implements PID
{
    protected PIDSource source;
    protected PIDOutput output;

    public PIDControllable()
    {
        source = new PIDSource()
        {
            @Override
            public void setPIDSourceType(PIDSourceType pidSource)
            {

            }

            @Override
            public PIDSourceType getPIDSourceType()
            {
                return PIDSourceType.kDisplacement;
            }

            @Override
            public double pidGet()
            {
                return returnPIDInput();
            }
        };

        output = this::usePIDOutput;
    }

    public double returnPIDInput()
    {
        if(getControllerSelector().hasSelected() && getControllerSelector().getSelected() instanceof PIDControlInterface)
        {
            return ((PIDControlInterface)getControllerSelector().getSelected()).getPIDInput();
        }
        return 0;
    }
    public void usePIDOutput(double output)
    {
        if(getControllerSelector().hasSelected() && getControllerSelector().getSelected() instanceof PIDControlInterface)
        {
            ((PIDControlInterface)getControllerSelector().getSelected()).usePIDOutput(output);
        }
    }
}
