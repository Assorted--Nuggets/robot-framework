package org.usfirst.frc.team1764.robot.custom.util;

public class PIDGearboxConfig {
    public String name;
    public int motorPort, encoderPortA, encoderPortB;
    public boolean inverted;

    public PIDGearboxConfig(String name, int motorPort, int encoderPortA, int encoderPortB, boolean inverted)
    {
        this.name = name;
        this.motorPort = motorPort;
        this.encoderPortA = encoderPortA;
        this.encoderPortB = encoderPortB;
        this.inverted = inverted;
    }
}
