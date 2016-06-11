package com.zarate.jesus.personalityevaluator;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class PersonalityRatingActivity extends ActionBarActivity
{

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //Change this so that is gets the values from algorithm
        PersonalityRating person = new PersonalityRating(-0.031, -0.039, 0.055, 0.043, 0.024, 0.0, 0.028, 0.093);
        SliceOfPie[] slices = person.getSlices();

        //*** Pie chart Layout ***\\
        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        PieChart pieChart = new PieChart(this, slices);

       // LinearLayout headerLayout = new LinearLayout(this);
       // headerLayout.setOrientation(LinearLayout.VERTICAL);
        PieHeader header = new PieHeader(this, slices);

        rootLayout.addView(pieChart, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                0, 60));
        rootLayout.addView(header, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                0, 40));

        setContentView(rootLayout);
    }


}
