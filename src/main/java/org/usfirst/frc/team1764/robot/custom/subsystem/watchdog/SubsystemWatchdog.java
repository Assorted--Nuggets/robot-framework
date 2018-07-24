package org.usfirst.frc.team1764.robot.custom.subsystem.watchdog;

import org.usfirst.frc.team1764.robot.custom.subsystem.base.ControllableBase;
import org.usfirst.frc.team1764.robot.custom.subsystem.base.Movable;
import org.usfirst.frc.team1764.robot.custom.subsystem.base.Orientable;

import java.util.ArrayList;

public class SubsystemWatchdog<T extends ControllableBase & Orientable & Movable>
{
    private ArrayList<MessagedSafeGuard<T>> safeguards;

    T target;

    public SubsystemWatchdog(T target)
    {
        this.target = target;
        safeguards = new ArrayList<>();
    }

    public void addSafeGuard(MessagedSafeGuard<T> safeGuard)
    {
        safeguards.add(safeGuard);
    }

    public void checkAll()
    {
        for(MessagedSafeGuard<T> s : safeguards)
        {
            if(s.shouldKill(target))
            {
                System.out.println("EMERGENCY STOPPED FOR: " + s.getMessage());
                target.emergencyStop();
            }
        }
    }
}
