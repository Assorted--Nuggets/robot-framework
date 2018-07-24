package org.usfirst.frc.team1764.robot.custom.motion;

public class Segment {
    private double startX;
    private double endX;
    private double startY;
    private double endY;

    public static Segment segmentFromSlope(double startX, double endX, double startY, double slope)
    {
        return new Segment(startX, endX, startY, startY + slope*(endX - startX));
    }

    public Segment(double startX, double endX, double startY, double endY)
    {
        this.setStartX(startX);
        this.setEndX(endX);
        this.setStartY(startY);
        this.setEndY(endY);
    }

    public double integrate()
    {
        double dx = this.getEndX() - this.getStartX();
        return (0.5*(this.getEndY() - this.getStartY()) + this.getStartY())*dx;
    }

    public double differentiate()
    {
        double dx = this.getEndX() - this.getStartX();
        double dy = this.getEndY() - this.getStartY();

        return dy/dx;
    }

    public boolean inDomain(double x)
    {
        return (x >= getStartX()) && (x < getEndX());
    }

    public double getOutput(double x)
    {
        if(inDomain(x)) {
            double dx = x - this.getStartX();
            double dy = differentiate() * dx;
            return this.getStartY() + dy;
        }
        System.err.println("Time " + x + " not in domain (" + this.getStartX() +", " + this.getEndX() + ")");
        return 0;
    }


    public double getStartX() {
        return startX;
    }

    public void setStartX(double startX) {
        this.startX = startX;
    }

    public double getEndX() {
        return endX;
    }

    public void setEndX(double endX) {
        this.endX = endX;
    }

    public double getStartY() {
        return startY;
    }

    public void setStartY(double startY) {
        this.startY = startY;
    }

    public double getEndY() {
        return endY;
    }

    public void setEndY(double endY) {
        this.endY = endY;
    }
}
