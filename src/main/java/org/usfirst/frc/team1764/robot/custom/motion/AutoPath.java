package org.usfirst.frc.team1764.robot.custom.motion;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team1764.robot.custom.math.Complex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AutoPath
{
    private ArrayList<Complex> points;
    private ArrayList<Double> angles;

    private ArrayList<Double> distances;

    private AutoPath(ArrayList<Complex> points)
    {
        this.points = new ArrayList<>();
        this.angles = new ArrayList<>();
        this.distances = new ArrayList<>();

        this.points.addAll(points);

        for(int i = 0; i < points.size() - 1; i++)
        {
            Complex current = this.points.get(i);
            Complex next = this.points.get(i + 1);

            this.angles.add(Math.toDegrees(next.subtract(current).getPhaseInverse()));
            this.distances.add(next.subtract(current).getAbs());

        }
    }

    public ArrayList<Double> getAngles()
    {
        return angles;
    }

    public ArrayList<Double> getDistances()
    {
        return distances;
    }

    public Complex getStartNode()
    {
        return points.get(0);
    }

    public static AutoPath loadFromFile(String filename) throws IOException
    {
        return loadFromFile(new File(filename));
    }

    public static AutoPath loadFromFile(File f) throws IOException
    {
        ArrayList<Complex> result = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(f));

        String line;
        while((line = reader.readLine()) != null)
        {
            String[] lineSplit = line.split(" ");
            //System.out.println(line);
            result.add(new Complex(Double.parseDouble(lineSplit[0]), Double.parseDouble(lineSplit[1])));
        }

        return new AutoPath(result);
    }
}
