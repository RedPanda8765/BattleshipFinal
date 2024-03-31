package com.ijjy.battleshipgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
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

    private long pressedTime;
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    Button button;
    TextView textView;
    static String userNameSS; //static so it can be set from the signup/login classes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start_screen);

        button = (Button)findViewById(R.id.button_logout);
        textView = (TextView)findViewById(R.id.user_id_main_screen);

        //displaying username in TextView at top left
        textView.setText(userNameSS);
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
        Intent intent = new Intent(getApplicationContext(),ChooseOpponentScreen.class);
        startActivity(intent);
    }

    public void statsclick(View view) {
        Intent intent = new Intent(getApplicationContext(),StatsScreen.class);
        startActivity(intent);
    }
}