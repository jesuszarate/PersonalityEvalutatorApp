package com.zarate.jesus.personalityevaluator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText usernameInput = (EditText)findViewById(R.id.usernameInput);
        Button evaluateButton = (Button)findViewById(R.id.evaluateButton);

        evaluateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String userName = "@" + usernameInput.getText().toString();
                Toast.makeText(MainActivity.this, userName, Toast.LENGTH_SHORT).show();

                Intent personalityIntent = new Intent(MainActivity.this, PersonalityRating.class);
                startActivity(personalityIntent);
            }
        });
    }

}
