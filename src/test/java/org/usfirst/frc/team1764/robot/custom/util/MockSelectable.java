package org.usfirst.frc.team1764.robot.custom.util;

import org.usfirst.frc.team1764.robot.custom.util.Selectable;

public class MockSelectable implements Selectable
{
    @Override
    public String getName()
    {
        return "mock";
    }

    @Override
    public void onSelect()
    {
        System.out.println(getName() + " selected");
    }

    @Override
    public void onDeselect()
    {
        System.out.println(getName() + " deselected");
    }
}
