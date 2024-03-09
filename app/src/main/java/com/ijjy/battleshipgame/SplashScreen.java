package com.ijjy.battleshipgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {
    private int i = 0;
    private Timer timer;
    private ProgressBar loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        loadingBar = (ProgressBar) findViewById(R.id.loadingbar);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(i<100){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                    loadingBar.setProgress(i);
                    i++;
                } else {
                    timer.cancel();
                    startActivity(new Intent(getApplicationContext(),LoginScreen.class));
                    finish();
                    //after splash screen, the signup screen appears next.
                }
            }
        }, 0, 10);

    }
}