package com.example.smartaquarium;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class splashscreen extends Activity {

    private Timer _timer = new Timer();
    private TimerTask t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        t = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(splashscreen.this,dashboard.class);
                        splashscreen.this.startActivity(intent);
                        splashscreen.this.finish();
                    }
                });
            }
        };
        _timer.schedule(t, (int)(2000));
    }
}