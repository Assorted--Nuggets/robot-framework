package org.usfirst.frc.team1764.robot.custom.subsystem.watchdog;

import org.usfirst.frc.team1764.robot.custom.subsystem.base.Movable;
import org.usfirst.frc.team1764.robot.custom.subsystem.base.Orientable;

public class MessagedSafeGuard<T extends Movable & Orientable>
{
    String message;
    SafeGuard<T> safeGuard;

    public MessagedSafeGuard(String message, SafeGuard<T> safeGuard)
    {
        this.message = message;
        this.safeGuard = safeGuard;
    }

    public boolean shouldKill(T target)
    {
        return safeGuard.shouldKill(target);
    }

    public SafeGuard<T> getSafeGuard()
    {
        return safeGuard;
    }

    public String getMessage()
    {
        return message;
    }
}
