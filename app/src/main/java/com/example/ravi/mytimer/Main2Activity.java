package com.example.ravi.mytimer;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

        // Vibrate for 500 milliseconds
        v.vibrate(3000);


        // Alarm tone starts
        mediaPlayer  = MediaPlayer.create(getApplicationContext(),R.raw.audio);
        mediaPlayer.start();

        Toolbar toolbar = (Toolbar)findViewById(R.id.tb);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Time UP!!!");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //toolbar.isHovered();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            //Home button pressed
            case android.R.id.home:
                mediaPlayer.stop();
                Intent homeIntent = new Intent(this, MainActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
        }

        return super.onOptionsItemSelected(item);
    }


    // If back button presssed, music should be stopped
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }
}
