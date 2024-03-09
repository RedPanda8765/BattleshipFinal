package com.ijjy.battleshipgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class ChooseOpponentScreen extends AppCompatActivity {

    ImageView humanChosen, compChosen;
    Boolean ifChosen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_choose_opponent_screen);

        humanChosen = findViewById(R.id.picture_human);
        compChosen = findViewById(R.id.picture_AI);

        humanChosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compChosen.setImageResource(R.drawable.artificialintelligencefaded);
                humanChosen.setImageResource(R.drawable.checkmark);
                ifChosen = true;
            }
        });

        compChosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                humanChosen.setImageResource(R.drawable.personfaded);
                compChosen.setImageResource(R.drawable.checkmark);
                ifChosen = true;
            }
        });
    }

    public void homebuttonclick(View view) {
        Intent intent = new Intent(getApplicationContext(),StartScreen.class);
        startActivity(intent);
        finish();
    }

    public void gobuttonclick(View view) {
        if(ifChosen) {
            Intent intent = new Intent(getApplicationContext(),StrategyBoardScreen.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Select an Opponent",Toast.LENGTH_SHORT).show();
        }
    }
}