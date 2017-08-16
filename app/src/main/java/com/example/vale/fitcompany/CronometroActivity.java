package com.example.vale.fitcompany;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;


public class CronometroActivity extends AppCompatActivity {
    Chronometer mChronometer;
    long timeWhenStopped = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cronometro);

        mChronometer = (Chronometer) findViewById(R.id.chronometer);
    }


    public void StartCronometro(View v )
    {
        mChronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
        mChronometer.start();
    }

    public void ResetCronometro(View v )
    {
        mChronometer.setBase(SystemClock.elapsedRealtime());
        timeWhenStopped = 0;
    }

    public void PauseCronometro(View v )
    {
        timeWhenStopped = mChronometer.getBase() - SystemClock.elapsedRealtime();
        mChronometer.stop();
    }
}