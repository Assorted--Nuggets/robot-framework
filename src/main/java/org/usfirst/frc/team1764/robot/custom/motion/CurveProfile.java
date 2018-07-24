package org.usfirst.frc.team1764.robot.custom.motion;

import org.usfirst.frc.team1764.robot.custom.math.Complex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is used to store a list of distances mapped to target angles
 * Also takes continuous inputs and maps them to discrete outputs using 1D nearest neighbor interpolation
 */
public class CurveProfile
{
    /* Storing list of trajectory points, real part describes arclength, imaginary part describes angle */
    private ArrayList<Complex> curve;

    private int startIndexSearch = 0; /* Stores where we want to start our search when mapping an arc length to angle */

    private CurveProfile()
    {
        this.curve = new ArrayList<>();
    }

    /**
     * add a point to the curve
     * @param c Complex number with real part describing arclength and imaginary part describing angle
     */
    private void addPoint(Complex c)
    {
        this.curve.add(c);
    }

    /**
     * Takes a continuous valued arclength input and maps it to an average between two points
     * @param arclength continous input of the arclength we wish to map from
     * @return Complex number with real part describing arclength and imaginary part describing angle
     */
    public Complex getPoint(double arclength)
    {
        /*
        Start our search at the current startIndexSearch and iterate through curve, keep i bound curve size minus 1 because
        we are taking each point and averaging it between the point after it
         */
        for(int i = startIndexSearch; i < curve.size() - 1; i++)
        {
            /* Check if the input between the arclength defined by this point and the next point */
            if(arclength >= curve.get(i).real && arclength <= curve.get(i+1).real)
            {
                /*
                We can make some pretty helpful assumptions here
                Since we know that our arclength is strictly increasing, we can determine that our start index will also be strictly increasing
                therefore, each time we process an input and find its corresponding index that is left sided, we can avoid
                searching through all the ones that we have know for certain do not contain this arclength input
                this reduces an O(n^2) algorithm to a O(1) algorithm, which is a huge performance difference
                 */
                startIndexSearch = i;

                /* Return the average between the left bound and right bound */
                return curve.get(i).add(curve.get(i+1)).divide(Complex.pureReal(2));
            }
        }

        /* Didn't find a pair of points that this input was in between, so it's not in the domain of the trajectory profile */
        System.err.println("Arclength " + arclength + " not in domain of trajectory profile");
        return Complex.pureReal(0);
    }

    /**
     * @return the total arclength of the curve
     */
    public double getLength()
    {
        return curve.get(curve.size()-1).real;
    }

    /**
     * parses a file and loads it into a CurveProfile
     * @param path path that points to the file containing the curve profile
     * @return the curve profile represented by the file
     * @throws IOException If there was no file found
     */
    public static CurveProfile loadFromFile(String path) throws IOException
    {
        File f = new File(path);
        CurveProfile result = new CurveProfile();
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(f));

        String line;
        while((line = reader.readLine()) != null)
        {
            String[] lineSplit = line.split(" ");

            /* subtract 90 degrees from the parsed angle because the curve generator is from a zero angle on the real axis,
            * but our gyro reads 0 as its initial heading */
            result.addPoint(new Complex(Double.parseDouble(lineSplit[0]), Double.parseDouble(lineSplit[1])-90));
        }

        return result;
    }
}
