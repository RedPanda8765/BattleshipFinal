package com.ijjy.battleshipgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StartScreen extends AppCompatActivity {

    static GameManager Manager = new GameManager();
    static GameManager.BattleshipAI2 ai;
    private long pressedTime;
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    Button button;
    TextView textView, titleTxtView;
    ImageView warshipImgView;
    static String userNameSS; //static so it can be set from the signup/login classes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start_screen);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        button = (Button)findViewById(R.id.button_logout);
        textView = (TextView)findViewById(R.id.user_id_main_screen);
        titleTxtView = (TextView)findViewById(R.id.title_id);
        warshipImgView = findViewById(R.id.warship_id);

        //displaying username in TextView at top left
        textView.setText(userNameSS);

        //Animating the warship
        Animation mAnimation = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_PARENT, -1.0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 1.0f,
                TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.ABSOLUTE, 0f);
        mAnimation.setDuration(10000);
        mAnimation.setRepeatCount(-1);
        mAnimation.setRepeatMode(Animation.RESTART);
        mAnimation.setInterpolator(new LinearInterpolator());
        warshipImgView.setAnimation(mAnimation);

        //animating the Title
        mAnimation = new TranslateAnimation(
                TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 0.01f);
        mAnimation.setDuration(1000);
        mAnimation.setRepeatCount(-1);
        mAnimation.setRepeatMode(Animation.REVERSE);
        mAnimation.setInterpolator(new LinearInterpolator());
        titleTxtView.setAnimation(mAnimation);



    }

    @Override
    public void onBackPressed() {

        if (pressedTime + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
            finishAffinity();
            System.exit(0);
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }

    public void logoutbuttonclick(View view) {
        Intent intent = new Intent(getApplicationContext(),LoginScreen.class);
        startActivity(intent);
        finish();
    }

    public void howbuttonclick(View view) {
        Intent intent = new Intent(getApplicationContext(),HowToPlayScreen.class);
        startActivity(intent);
    }

    public void chooseopponentclick(View view) {
        Manager.P2 = new Player(false);
        ai = Manager.new BattleshipAI2();
        Intent intent = new Intent(getApplicationContext(),StrategyBoardScreen.class);
        startActivity(intent);

    }

    public void statsclick(View view) {
        Intent intent = new Intent(getApplicationContext(),StatsScreen.class);
        startActivity(intent);
    }
}