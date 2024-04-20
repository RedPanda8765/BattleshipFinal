package com.ijjy.battleshipgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class HowToPlayScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_how_to_play_screen);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);
    }

    public void homebuttonclick(View view) {
        Intent intent = new Intent(getApplicationContext(),StartScreen.class);
        startActivity(intent);
        finish();
    }
}