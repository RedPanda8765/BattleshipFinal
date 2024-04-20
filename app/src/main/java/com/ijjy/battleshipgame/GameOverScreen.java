package com.ijjy.battleshipgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;

public class GameOverScreen extends AppCompatActivity {

    TextView winnerTxtView, messageTxtView, pointsTxtView;
    ImageView winnerImgView;
    DecimalFormat df = new DecimalFormat("#.###");
    MediaPlayer media;
    double hitmissratio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game_over_screen);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        winnerTxtView = findViewById(R.id.winner_id);
        messageTxtView = findViewById(R.id.message);
        pointsTxtView = findViewById(R.id.points_id);
        winnerImgView = findViewById(R.id.winners_image_id);

        hitmissratio = StartScreen.Manager.P1.getHitMissRatio();

        if(StartScreen.Manager.getWinner() == 1)
        {
            media = MediaPlayer.create(this, R.raw.youwon);
            media.start();
            winnerTxtView.setText("Victory");
            //update score of user
            int pts = (int) (5000 * hitmissratio); //getting new game's points, based on hit:miss ratio
            messageTxtView.setText("Hits:Misses: " + df.format(hitmissratio) + "\nBattle Points: +" + pts);
            StatsScreen.scoreSS += pts;
            pointsTxtView.setText("Total Score: " + StatsScreen.scoreSS);

            //store new updated score into database for user
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference reference = firebaseDatabase.getReference("datauser");
            reference.child(StartScreen.userNameSS).child("score").setValue(StatsScreen.scoreSS);


        }
        else
        {
            media = MediaPlayer.create(this, R.raw.youlost);
            media.start();
            winnerTxtView.setText("You Lost");
            //No updating score of user, since they lost
            //update score of user
            messageTxtView.setText("Hits:Misses: " + df.format(hitmissratio) + "\nBattle Points: +0");
            pointsTxtView.setText("Total Score: " + StatsScreen.scoreSS);
            winnerImgView.setImageResource(R.drawable.deathskull);
        }
    }

    public void homebuttonclick(View view) {
        Intent intent = new Intent(getApplicationContext(),StartScreen.class);
        startActivity(intent);
        finish();
    }
}