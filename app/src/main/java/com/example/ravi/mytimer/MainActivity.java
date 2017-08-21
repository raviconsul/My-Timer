package com.example.ravi.mytimer;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    // List of time intervals
    Spinner spinner;

    // Dispplays time
    TextView textView;

    // Value of seconds is set default to 10 seconds
    private int seconds = 10;
    Button start , stop;
    CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Custom toolbar
        Toolbar toolbar = (Toolbar)findViewById(R.id.tb1);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("My Timer");

        spinner = (Spinner)findViewById(R.id.spinner);
        textView = (TextView)findViewById(R.id.textView);
        start  = (Button)findViewById(R.id.start);
        stop = (Button)findViewById(R.id.stop);

        // Stop button is initially disabled to prevent crash
        stop.setEnabled(false);

        // Setting the list to the spinner
        String[] time = {"10 seconds", "20 seconds", "30 seconds", "40 seconds", "50 seconds", "1 minute", "5 minute", "10 minute", "15 minute", "30 minute", "45 minute", "1 hour"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,time);
        spinner.setAdapter(arrayAdapter);
    }

    // When user starts timer
    public void startTimer(View view) {

        // Button is diabled so that user cannot start two timers simentaneously
        start.setEnabled(false);

        // Stp button is set enabled
        stop.setEnabled(true);
        final String text = spinner.getSelectedItem().toString();
        Toast.makeText(getApplicationContext(),"You selected: " + text,Toast.LENGTH_SHORT).show();

        // Setting the selected seconds
        switch(text)
        {
            case "10 seconds":
                seconds = 10;
                break;
            case "20 seconds":
                seconds = 20;
                break;
            case "30 seconds":
                seconds = 30;
                break;
            case "40 seconds":
                seconds = 40;
                break;
            case "50 seconds":
                seconds = 50;
                break;
            case "1 minute":
                seconds = 60;
                break;
            case "5 minute":
                seconds = 300;
                break;
            case "10 minute":
                seconds = 600;
                break;
            case "15 minute":
                seconds = 900;
                break;
            case "30 minute":
                seconds = 1800;
                break;
            case "45 minute":
                seconds = 2700;
                break;
            case "1 hour":
                seconds = 3600;
                break;

        }


        // Countdown timer starts
        countDownTimer = new CountDownTimer(seconds*1000,1000)
        {
            @Override
            public void onTick(long millisUntilFinished) {

                // Converting time to standard units to be displayed to the user
                long minutes = millisUntilFinished/60000;
                long hour = 0;
                if (minutes >= 60) {
                    hour = minutes / 60;
                    minutes %= 60;
                    millisUntilFinished %= 60;
                }

                millisUntilFinished = millisUntilFinished/1000;

                if (millisUntilFinished >= 60)
                {
                    millisUntilFinished %= 60;
                }

                textView.setText(String.valueOf(hour).toString() + " : " + String.valueOf(minutes).toString() + " : " + String.valueOf(millisUntilFinished).toString());

            }

            // On finishing the time
            public void onFinish()
            {
                textView.setText("0 : 00 : 00");
                Intent intent  = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);

                // Restoring to default settings
                start.setEnabled(true);
                stop.setEnabled(false);
            }
        }.start();


    }


    // When the stop button is pressed
    public void stopTimer(View view)
    {
        countDownTimer.cancel();
        textView.setText("0 : 00 : 00");
        start.setEnabled(true);
    }
}
