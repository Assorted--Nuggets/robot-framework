package org.usfirst.frc.team1764.robot.custom.motion;

import java.util.ArrayList;

public class MotionProfile {
    private ArrayList<Segment> segments;

    public static MotionProfile genTrapezoidal(double distance, double rampDistance, double maxPower)
    {
        Segment rampUp = new Segment(0, rampDistance, 0.10, maxPower);
        Segment hold = new Segment(rampDistance, distance - rampDistance, maxPower, maxPower);
        Segment rampDown = new Segment(distance - rampDistance, distance, maxPower, 0);
        MotionProfile res = new MotionProfile();
        res.addSegment(rampUp);
        res.addSegment(hold);
        res.addSegment(rampDown);

        return res;
    }

    public MotionProfile()
    {
        this.segments = new ArrayList<>();
    }

    public boolean isContinuous()
    {
        for(int i = 0; i < segments.size()-1; i++)
        {
            if(!(segments.get(i).getEndX() == segments.get(i+1).getStartX() &&
                    segments.get(i).getEndY() == segments.get(i+1).getStartY()))
            {
                return false;
            }
        }

        return true;
    }

    public void addSegment(Segment s)
    {
        segments.add(s);
    }

    public ArrayList<Segment> getSegments()
    {
        return segments;
    }

    public double integrate()
    {
        double integral = 0;

        for(Segment s : segments)
        {
            integral += s.integrate();
        }
        return integral;
    }

    /*
    * this could definitely be optimized quite a bit since we know that t will always be increasing
    * knowing this, we can simply store the last index used to access the array and use that as our starting point for our search algorithm
    * this reduces an O(n) search to O(1) which is ideal (obviously dependent on how quickly our indices are changing)
    * Additionally, it's not super useful in this particular use case, since we only have 3 segments maximum, but when we use velocity
    * profiles it will be extremely useful */
    public double getOutput(double t)
    {
        /* We want to just clamp down negative values because sometimes the robot will start off in a bad spot if it is turning on point */
        if(segments.get(0).getStartX() > t)
        {
            return segments.get(0).getStartY();
        }

        for(Segment s : segments)
        {
            if(s.inDomain(t))
            {
                //System.out.println(s.getOutput(t));
                return s.getOutput(t);
            }
        }

        System.err.println("Time " + t + " not in domain");
        return 0;
    }

    public double getEndX()
    {
        return segments.get(segments.size()-1).getEndX();
    }
}