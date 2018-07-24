package org.usfirst.frc.team1764.robot.custom.subsystem.watchdog;

import org.usfirst.frc.team1764.robot.custom.subsystem.base.Movable;
import org.usfirst.frc.team1764.robot.custom.subsystem.base.Orientable;

public interface SafeGuard<T extends Orientable & Movable>
{
    boolean shouldKill(T target);
}
