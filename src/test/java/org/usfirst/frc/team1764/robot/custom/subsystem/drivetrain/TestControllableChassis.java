package org.usfirst.frc.team1764.robot.custom.subsystem.drivetrain;

import org.junit.BeforeClass;
import org.junit.Test;
import org.usfirst.frc.team1764.robot.custom.control.chassis.ChassisPercentController;
import org.usfirst.frc.team1764.robot.custom.control.chassis.ChassisSpeedController;
import org.usfirst.frc.team1764.robot.custom.control.chassis.pid.ChassisAngleController;
import org.usfirst.frc.team1764.robot.custom.control.signal.ChassisControlSignal;
import org.usfirst.frc.team1764.robot.custom.control.chassis.ChassisPercentController;
import org.usfirst.frc.team1764.robot.custom.control.chassis.ChassisSpeedController;
import org.usfirst.frc.team1764.robot.custom.control.chassis.pid.ChassisAngleController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestControllableChassis
{
    private static MockControllableChassis gb;

    @BeforeClass
    public static void createPIDGearbox()
    {
        gb = new MockControllableChassis();
        gb.getControllerSelector().addAll(ChassisPercentController.getPrototype(gb), ChassisSpeedController
                                                  .getPrototype(gb),
                                          ChassisAngleController.getPrototype(gb));
    }

    @Test
    public void testSelect()
    {
        checkIfLoaded("percent");
        checkIfLoaded("speed");
        checkIfLoaded("angle");
    }

    @Test
    public void testSet()
    {
        //System.out.println("CHECKING ANGLE SET...");
        gb.loadController("angle");
        checkSet(10);
        checkSet(30);
        checkSet(-10);
    }


    @Test
    public void testSetPercent()
    {
        //System.out.println("CHECKING PERCENT SET...");
        gb.loadController("percent");
        checkSetPercent(0.4, 0.4);
        //gb.logStatus();
        checkSetPercent(0.1, 0.1);
        //gb.logStatus();
        checkSetPercent(-0.4, 0.1);
        //gb.logStatus();
    }

    @Test
    public void testIdle()
    {
        //System.out.println("CHECKING IDLE");
        gb.loadController("idle");
        gb.set(ChassisControlSignal.generateSignal(0.33, 0.66));
        assertEquals(0, gb.getRightOutput(), 0);
        assertEquals(0, gb.getLeftOutput(), 0);
    }


    private void checkIfLoaded(String name)
    {
        //System.out.println("CHECKING LOAD: " + name);
        gb.loadController(name);
        assertTrue(gb.getControllerSelector().getSelected().getName().equals(name));
    }

    private void checkSetPercent(double left, double right)
    {
        gb.set(ChassisControlSignal.generateSignal(left, right));
        assertEquals(left, gb.getLeftOutput(), 0);
        assertEquals(right, gb.getRightOutput(), 0);
    }

    private void checkSet(double angle)
    {
        gb.setSetpoint(angle);
        assertEquals(angle, gb.getSetpoint(), 0);
    }


}
