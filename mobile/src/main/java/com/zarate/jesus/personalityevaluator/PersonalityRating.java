package com.zarate.jesus.personalityevaluator;

import android.graphics.Color;

import java.sql.Array;

/**
 * Created by Jeanette on 6/8/16.
 */
public class PersonalityRating
{
    double psAngle, maAngle, agAngle, naAngle, neAngle, exAngle, coAngle, opAngle;
    double smallestVal = 0;
    double sumOfVals = 0;
    SliceOfPie[] slices = null;

    public PersonalityRating(double ps, double ma, double ag, double na, double ne, double ex, double co, double op)
    {
        double[] values = {ps, ma, ag, na, ne, ex, co, op};
        smallestVal = getSmallestVal(values);

        double psVal = calculateValue(ps);
        double maVal = calculateValue(ma);
        double agVal = calculateValue(ag);
        double naVal = calculateValue(na);
        double neVal = calculateValue(ne);
        double exVal = calculateValue(ex);
        double coVal = calculateValue(co);
        double opVal = calculateValue(op);

        sumOfVals = psVal + maVal + agVal + naVal + neVal + exVal + coVal + opVal;

        //The percentages of each trait
        double psPercentage = calculatePercentage(psVal);
        double maPercentage = calculatePercentage(maVal);
        double agPercentage = calculatePercentage(agVal);
        double naPercentage = calculatePercentage(naVal);
        double nePercentage = calculatePercentage(neVal);
        double exPercentage = calculatePercentage(exVal);
        double coPercentage = calculatePercentage(coVal);
        double opPercentage = calculatePercentage(opVal);


        //return (percentage/100) * 2 * Math.PI;
        this.psAngle = calculateAngle(psPercentage);
        this.maAngle = calculateAngle(maPercentage);
        this.agAngle = calculateAngle(agPercentage);
        this.naAngle = calculateAngle(naPercentage);
        this.neAngle = calculateAngle(nePercentage);
        this.exAngle = calculateAngle(exPercentage);
        this.coAngle = calculateAngle(coPercentage);
        this.opAngle = calculateAngle(opPercentage);

        slices = new SliceOfPie[]{new SliceOfPie("Psychopathic", psAngle, Color.RED),
                new SliceOfPie("Machiallevalant", maAngle, Color.BLUE),
                new SliceOfPie("Agreeable", agAngle, Color.GREEN),
                new SliceOfPie("Narcissistic", naAngle, Color.GRAY),
                new SliceOfPie("Neuroticist", neAngle, Color.MAGENTA),
                new SliceOfPie("Extrovert", exAngle, Color.BLACK),
                new SliceOfPie("conscientiousness", coAngle, Color.CYAN),
                new SliceOfPie("Openness", opAngle, Color.YELLOW)};
    }



    /*
     * Returns the array of all slices (1 slice for each personality trait)
     */
    public SliceOfPie[] getSlices()
    {
        return slices;
    }

    public double calculateValue(double val)
    {
        return val + 2*Math.abs(smallestVal);
    }

    public double calculatePercentage(double val)
    {
        return val * 100 / sumOfVals;
    }

    public double calculateAngle(double percentage)
    {
        return (percentage / 100) * 360;
    }

    public double getSmallestVal(double[] values)
    {
        for(int i = 0; i < values.length; i++)
        {
            if(smallestVal > values[i])
            {
                smallestVal = values[i];
            }
        }

        return smallestVal;
    }
}
