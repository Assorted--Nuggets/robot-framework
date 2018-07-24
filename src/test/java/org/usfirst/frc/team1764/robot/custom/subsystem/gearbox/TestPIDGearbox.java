package org.usfirst.frc.team1764.robot.custom.subsystem.gearbox;

import org.junit.BeforeClass;
import org.junit.Test;
import org.usfirst.frc.team1764.robot.custom.control.gearbox.GearboxPercentController;
import org.usfirst.frc.team1764.robot.custom.control.gearbox.pid.GearboxPositionController;
import org.usfirst.frc.team1764.robot.custom.control.gearbox.pid.GearboxSpeedController;
import org.usfirst.frc.team1764.robot.custom.control.signal.GearboxControlSignal;
import org.usfirst.frc.team1764.robot.custom.control.gearbox.GearboxPercentController;
import org.usfirst.frc.team1764.robot.custom.control.gearbox.pid.GearboxPositionController;
import org.usfirst.frc.team1764.robot.custom.control.gearbox.pid.GearboxSpeedController;
import org.usfirst.frc.team1764.robot.custom.control.signal.GearboxControlSignal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TestPIDGearbox
{
    private static MockControllableGearbox gb;

    @BeforeClass
    public static void createPIDGearbox()
    {
        gb = new MockControllableGearbox();
        gb.getControllerSelector().addAll(GearboxPercentController.getPrototype(gb), GearboxPositionController
                                                  .getPrototype(gb),
                                          GearboxSpeedController.getLeftPrototype(gb));
    }

    @Test
    public void testSelect()
    {
        gb.loadController("percent");
        assertTrue(gb.getControllerSelector().getSelected().getName().equals("percent"));
        gb.loadController("position");
        assertTrue(gb.getControllerSelector().getSelected().getName().equals("position"));
        gb.loadController("speed");
        assertTrue(gb.getControllerSelector().getSelected().getName().equals("speed"));
    }

    @Test
    public void testSetPercent()
    {
        gb.loadController("percent");
        gb.set(GearboxControlSignal.generateLeftSignal(0.4));
        assertEquals(0.4, gb.getMotorOutput(), 0);
    }

    @Test
    public void testSet()
    {
        gb.loadController("speed");
        gb.set(GearboxControlSignal.generateLeftSignal(10));
        assertEquals(10, gb.getSetpoint(), 0);

        gb.loadController("position");
        gb.set(GearboxControlSignal.generateLeftSignal(5));
        assertEquals(5, gb.getSetpoint(), 0);
    }
}
