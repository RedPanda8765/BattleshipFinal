package com.ijjy.battleshipgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class StatsScreen extends AppCompatActivity {

    TextView statTextfield;
    static int scoreSS; //static so it can be set from the signup/login classes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_stats_screen);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        statTextfield = findViewById(R.id.stat_field);
        statTextfield.setText(Integer.toString(scoreSS)); //setting score information in stat field
    }

    public void homebuttonclick(View view) {
        Intent intent = new Intent(getApplicationContext(),StartScreen.class);
        startActivity(intent);
        finish();
    }
}