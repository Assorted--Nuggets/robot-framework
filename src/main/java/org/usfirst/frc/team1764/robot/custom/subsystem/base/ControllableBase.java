package org.usfirst.frc.team1764.robot.custom.subsystem.base;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import org.usfirst.frc.team1764.robot.custom.control.Controller;
import org.usfirst.frc.team1764.robot.custom.control.IdleController;
import org.usfirst.frc.team1764.robot.custom.control.signal.ControlSignal;
import org.usfirst.frc.team1764.robot.custom.util.Selector;

import java.util.HashMap;
import java.util.function.DoubleSupplier;

public abstract class ControllableBase<T extends ControlSignal> implements Controllable<T>
{
    private Selector<Controller<T>> controllerSelector;
    private Subsystem target;
    private HashMap<String, DoubleSupplier> attributes;
    public boolean isLocked = false;

    ControllableBase()
    {
        controllerSelector = new Selector<>();
        attributes = new HashMap<>();
        controllerSelector.addAll(new IdleController<>(this));
    }

    protected void addAttribute(String key, DoubleSupplier supplier)
    {
        attributes.put(key, supplier);
    }

    public void createSubsystem(Command defaultCommand)
    {
        this.target = new Subsystem()
        {
            @Override
            protected void initDefaultCommand()
            {
                this.setDefaultCommand(defaultCommand);
            }
        };
    }

    public void createSubsystem()
    {
        this.target = new Subsystem()
        {
            @Override
            public void initSendable(SendableBuilder builder)
            {
                super.initSendable(builder);

                for(String s : attributes.keySet())
                {
                    builder.addDoubleProperty(s, attributes.get(s), null);
                }
            }

            @Override
            protected void initDefaultCommand()
            {
            }
        };
    }

    public void logStatus()
    {
        System.out.println("############### "
                                   + this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".") + 1)
                                   + " ###############");
        for(String s : attributes.keySet())
        {
            System.out.println("    " + s + ": " + attributes.get(s).getAsDouble());
        }
    }

    public void bindToSubsystem(Subsystem s)
    {
        this.target = s;
    }

    public Subsystem getTarget()
    {
        if(this.target == null)
        {
            System.err.println("Error: This Controllable does not target a Subsystem");
        }
        return target;
    }

    public void emergencyStop()
    {
        if(!isLocked())
        {
            reset();
            loadController("idle");
            isLocked = true;
        }
    }

    @Override
    public boolean isLocked()
    {
        return isLocked;
    }

    @Override
    public Selector<Controller<T>> getControllerSelector()
    {
        return controllerSelector;
    }
}
