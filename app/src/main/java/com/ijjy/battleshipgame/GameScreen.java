package com.ijjy.battleshipgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class GameScreen extends AppCompatActivity {

    static int chosenOpp; //static so it can be set from the StartScreen class

    Button forfietButton;
    Boolean gameOver = false;
    Boolean AITurnOngoing = false;

    int forfeit = 0;

    MediaPlayer clockMedia, p1missileMedia, p2missileMedia, splashMedia, sunkMedia, sonarMedia;

    TextView attText, text, bottomtext;
    ImageView[][] coordinates2 = new ImageView[10][10]; //for grid

    int xPoint = -1;
    int yPoint = -1;

    ImageView ship1, ship2, ship3, ship4, ship5, confirmBut, soundBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game_screen);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        ship1 = findViewById(R.id.ship1_id);
        ship2 = findViewById(R.id.ship2_id);
        ship3 = findViewById(R.id.ship3_id);
        ship4 = findViewById(R.id.ship4_id);
        ship5 = findViewById(R.id.ship5_id);
        confirmBut = findViewById(R.id.confirm_id);
        soundBut = findViewById(R.id.button_sound_id);
        forfietButton = findViewById(R.id.button_forfeit);

        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j < 10; j++)
            {
                int res = getResources().getIdentifier("gridspot_"+i+"."+j, "id", getPackageName()); //This line is necessary to "convert" a string (e.g. "i1", "i2" etc.) to a resource ID
                coordinates2[i][j] = (ImageView) findViewById(res);
            }
        }
        attText = findViewById(R.id.attack_board_id);
        text = findViewById(R.id.message);
        bottomtext = findViewById(R.id.bottom_text_id);

        //loading sound files for clock ticking, missile explosion, splash, sunking ship, and sonar;
        clockMedia = MediaPlayer.create(this, R.raw.clockticking);
        p1missileMedia = MediaPlayer.create(this, R.raw.missileexplosion);
        p2missileMedia = MediaPlayer.create(this, R.raw.missileexplosion);
        splashMedia = MediaPlayer.create(this, R.raw.watersplash);
        sunkMedia = MediaPlayer.create(this, R.raw.bigboom);
        sonarMedia = MediaPlayer.create(this, R.raw.sonarping);

        soundBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //turn off all media if a MediaPlayer is not null
                if(sonarMedia != null)
                {
                    clockMedia.release();
                    p1missileMedia.release();
                    p2missileMedia.release();
                    splashMedia.release();
                    sunkMedia.release();
                    sonarMedia.release();

                    clockMedia = null;
                    p1missileMedia = null;
                    p2missileMedia = null;
                    splashMedia = null;
                    sunkMedia = null;
                    sonarMedia = null;

                    soundBut.setImageResource(R.drawable.speakeroff);

                }
                else { //turning sound back on
                    clockMedia = MediaPlayer.create(GameScreen.this, R.raw.clockticking);
                    p1missileMedia = MediaPlayer.create(GameScreen.this, R.raw.missileexplosion);
                    p2missileMedia = MediaPlayer.create(GameScreen.this, R.raw.missileexplosion);
                    splashMedia = MediaPlayer.create(GameScreen.this, R.raw.watersplash);
                    sunkMedia = MediaPlayer.create(GameScreen.this, R.raw.bigboom);
                    sonarMedia = MediaPlayer.create(GameScreen.this, R.raw.sonarping);

                    soundBut.setImageResource(R.drawable.speaker);
                }
            }
        });


    }
/*
    public void AITurn() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //int[] returnedArray = StartScreen.Manager.P2.AIshoot(); //P2 AI shooting at P1
                StartScreen.ai.makeMove(); //P2 AI shooting at P1
                int x = StartScreen.ai.getLastTarget().x;
                int y = StartScreen.ai.getLastTarget().y;

                String str1 = "AI Missile Launched...\n";
                if (StartScreen.ai.getHitStatus()) //AI hit Player1
                {
                    //resetting media
                    clockMedia.pause();
                    clockMedia.seekTo(0);
                    p1missileMedia.pause();
                    p1missileMedia.seekTo(0);

                    //starting missile media
                    p1missileMedia.start();
                    int ship = StartScreen.Manager.P1.playerboard.board[x][y]-10;
                    //ship has been hit and status is updated accordingly
                    StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[ship-1].shipDealtHit();
                    StartScreen.Manager.P2.hits++;
                    str1 += StartScreen.Manager.P1.playerboard.battleFleet.shipNames[ship-1] + " Hit!";
                    displayShipsStatus();
                }
                else { //AI missed
                    splashMedia.start();
                    str1 += "AI Missed Your Fleet!";
                    StartScreen.Manager.P2.misses++;
                }
                text.setText(str1);
                displayStrategyBoard();

            }
        }, 2000);

        //checking if all of P1's ships are down
        if(StartScreen.Manager.checkGameOver()) //if the game is over
        {
            //moving to Game Over Screen
            Intent intent = new Intent(getApplicationContext(),GameOverScreen.class);
            startActivity(intent);
            finish();

        }
    }
 */


    public void AITurn() {
        AITurnOngoing = true;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //int[] returnedArray = StartScreen.Manager.P2.AIshoot(); //P2 AI shooting at P1
                StartScreen.ai.makeMove(); //P2 AI shooting at P1
                int x = StartScreen.ai.getLastTarget().x;
                int y = StartScreen.ai.getLastTarget().y;

                String str1 = "AI Missile Launched...\n";
                if (StartScreen.Manager.P1.playerboard.board[x][y]>10) //AI hit Player1
                {
                    StartScreen.Manager.P2.hits++;

                    //starting missile media
                    if(sonarMedia != null) p2missileMedia.start();
                    int ship = StartScreen.Manager.P1.playerboard.board[x][y]-10;
                    str1 += StartScreen.Manager.P1.playerboard.battleFleet.shipNames[ship-1] + " Hit!";
                    displayShipsStatus(); //maybe needed here instead?*************
                }
                else { //AI missed
                    if(sonarMedia != null) splashMedia.start();
                    str1 += "AI Missed Your Fleet!";
                    StartScreen.Manager.P2.misses++;
                }
                text.setText(str1);
                displayStrategyBoard();

            }
        }, 2000);

        //checking if all of P1 ships are down
        if(!gameOver && StartScreen.Manager.checkGameOver()) //if the game is over
        {
            gameOver = true;
            //moving to Game Over Screen
            Intent intent = new Intent(getApplicationContext(),GameOverScreen.class);
            startActivity(intent);
            finish();

        }

    }


    public void gridPress(View view) {

        if(!AITurnOngoing && (forfeit%2==0))
        {
            String id = getResources().getResourceEntryName(view.getId());
            xPoint = id.charAt(9)-48; //extracting the x coordinate of the selected grid spot
            yPoint = id.charAt(11)-48; //extracting the y coordinate of the selected grid spot

            if(StartScreen.Manager.P2.playerboard.board[xPoint][yPoint] < 10) //if a point on the grid is selected
            {
                String str1 = "     CONFIRM    ";
                bottomtext.setText(str1);

                resetSelection(); //resetting selection
                ViewGroup.LayoutParams params = confirmBut.getLayoutParams();
                params.height= 100;
                params.width= 100;
                confirmBut.setLayoutParams(params);
                confirmBut.setImageResource(R.drawable.checkmark);

                int res = getResources().getIdentifier("gridspot_"+xPoint+"."+yPoint, "id", getPackageName()); //This line is necessary to "convert" a string (e.g. "i1", "i2" etc.) to a resource ID
                ImageView selectedSquare = (ImageView)findViewById(res);

                selectedSquare.setImageResource(R.drawable.squareselected);

                String str2 = "Tap Checkmark to \nConfirm Your Choice";
                text.setText(str2);

                //animating CHECKMARK
                RotateAnimation anim = new RotateAnimation(-5f, 5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                anim.setInterpolator(new LinearInterpolator());
                anim.setRepeatCount(2);
                anim.setRepeatMode(Animation.REVERSE);
                anim.setDuration(100);

                // Start animating the image
                confirmBut.startAnimation(anim);

            }
            else //already has been chosen before
            {
                Toast.makeText(getBaseContext(), "You've Already Attacked There - Choose New Spot", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void confirmbuttonclick(View view) {

        if(forfeit%2 == 0)
        {
            AITurnOngoing = true;
            //if there is a point on the grid selected and that hasn't already been selected
            if(xPoint != -1 && yPoint != -1 && (StartScreen.Manager.P2.playerboard.board[xPoint][yPoint] < 10)) //if a point on the grid is selected
            {
                //starting audio for User's missile launch
                if(sonarMedia != null)
                {
                    sonarMedia.start();
                    clockMedia.start();
                }

                //add 10 to the attacked spot to keep track where P1 has hit/missed the enemy
                StartScreen.Manager.P2.playerboard.board[xPoint][yPoint] += 10;

                String str2;
                String str1 = "";
                bottomtext.setText(str1);

                ViewGroup.LayoutParams params = confirmBut.getLayoutParams();
                params.height= 300;
                params.width= 300;
                confirmBut.setLayoutParams(params);

                new CountDownTimer(3000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        millisUntilFinished += 1000;
                        String str1 = "Launching Missile in\n" + (millisUntilFinished/1000);
                        text.setText(str1);
                        confirmBut.setImageResource(R.drawable.submarinemissile);
                    }
                    public void onFinish() {
                        String str1, str2;
                        if(sonarMedia != null)
                        {
                            clockMedia.pause();
                        }
                        if(StartScreen.Manager.P2.playerboard.board[xPoint][yPoint] > 10) //if opponent has been hit
                        {
                            if(sonarMedia != null)
                            {
                                p1missileMedia.start();
                            }
                            StartScreen.Manager.P1.hits++;
                            params.height= 300;
                            params.width= 300;
                            confirmBut.setLayoutParams(params);
                            confirmBut.setImageResource(R.drawable.cornerexplosion);
                            str2 = "You Landed a Hit!\n";
                            text.setText(str2);
                            int res = getResources().getIdentifier("gridspot_"+xPoint+"."+yPoint, "id", getPackageName()); //This line is necessary to "convert" a string (e.g. "i1", "i2" etc.) to a resource ID
                            ImageView hitSquare = (ImageView)findViewById(res);
                            hitSquare.setImageResource(R.drawable.hit);


                            int hitShip = StartScreen.Manager.P2.playerboard.board[xPoint][yPoint] - 10;

                            //next, adding +1 to currentHitCount & setting ship status
                            StartScreen.Manager.P2.playerboard.battleFleet.shipsArray[hitShip-1].shipDealtHit();

                            if(!StartScreen.Manager.P2.playerboard.battleFleet.shipsArray[hitShip-1].getStatus()) //ship has been sunk
                            {
                                if(sonarMedia != null)
                                {
                                    sunkMedia.start();
                                }
                                displaySunkShipsOnAttackBoard();
                                String str3 = StartScreen.Manager.P2.playerboard.battleFleet.shipNames[hitShip-1] + "\nSunk!";
                                text.setText(str3);
                                confirmBut.setImageResource(R.drawable.brightexplosion);
                                str1 = "";
                                bottomtext.setText(str1);
                            }

                            //checking if all of P2's ships are down
                            if(!gameOver && StartScreen.Manager.checkGameOver()) //if the game is over
                            {
                                gameOver = true;
                                //moving to Game Over Screen
                                Intent intent = new Intent(getApplicationContext(),GameOverScreen.class);
                                startActivity(intent);
                                finish();

                            }

                        }
                        else //if opponent has not been hit
                        {
                            if(sonarMedia != null)
                            {
                                splashMedia.start();
                            }
                            StartScreen.Manager.P1.misses++;
                            params.height= 300;
                            params.width= 300;
                            confirmBut.setLayoutParams(params);
                            confirmBut.setImageResource(R.drawable.sunkmissile);
                            str2 = "You Missed!\n";
                            text.setText(str2);
                            int res = getResources().getIdentifier("gridspot_"+xPoint+"."+yPoint, "id", getPackageName()); //This line is necessary to "convert" a string (e.g. "i1", "i2" etc.) to a resource ID
                            ImageView missedSquare = (ImageView)findViewById(res);
                            missedSquare.setImageResource(R.drawable.miss);
                        }
                    }
                }.start();

                //3-sec timer
                new CountDownTimer(7000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        String str1 = " ";
                        bottomtext.setText(str1);
                        millisUntilFinished += 1000;
                        if(millisUntilFinished < 4000)
                        {
                            attText.setText("Strategy Board");
                            displayStrategyBoard();
                            String str = "AI Launching Attack in \n     " + (millisUntilFinished/1000) + " s";
                            text.setText(str);
                            confirmBut.setImageResource(R.drawable.artificialintelligence);
                        }
                    }
                    public void onFinish() {
                        //displayStrategyBoard();
                        String str = "AI Missile Launched...\n";
                        text.setText(str);
                        AITurn();
                    }
                }.start();

                new CountDownTimer(12000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        String str1 = " ";
                        bottomtext.setText(str1);
                        millisUntilFinished += 1000;
                        if(millisUntilFinished < 2000)
                        {
                            attText.setText("Attack Board");
                            AITurnOngoing = false;
                            displayAttackBoard();
                            displaySunkShipsOnAttackBoard();
                            String str = "Your Turn \n";
                            text.setText(str);
                            str1 = "Confirm Choice";
                            bottomtext.setText(str1);
                            params.height= 100;
                            params.width= 100;
                            confirmBut.setLayoutParams(params);
                            confirmBut.setImageResource(R.drawable.noselection);
                        }
                    }
                    public void onFinish() {
                    }
                }.start();

            }
            else {
                Toast.makeText(getBaseContext(), "Choose a Grid Point to Attack", Toast.LENGTH_SHORT).show();
            }
        }//forfeit % 2 == 0

    }

    public void forfeitbuttonclick(View view) {
        forfeit++;

        Button endButton = findViewById(R.id.end_match_id);
        endButton.setVisibility(View.VISIBLE); //making button visible

        if(forfeit%2 == 1)
        {
            String str = "Forfeit the Battle?";
            text.setText(str);
            forfietButton.setText("CONTINUE MATCH");
        }
        else //player chose to stay
        {
            //restore the button and screen
            forfietButton.setText("Forfeit");
            forfeit = 0;
            resetSelection();
            String str = "Tap the Grid to \nLaunch Missile At Enemy";
            text.setText(str);
            str = "Confirm Choice";
            bottomtext.setText(str);

            endButton.setVisibility(View.GONE); //making button go away
        }
    }

    public void endbuttonclick(View view) {
        //stop all audio
        clockMedia.stop();
        p1missileMedia.stop();
        p2missileMedia.stop();
        splashMedia.stop();
        sunkMedia.stop();
        sonarMedia.stop();
        Intent intent = new Intent(getApplicationContext(),StartScreen.class);
        startActivity(intent);
        finish();
    }

    public void displayStrategyBoard()
    {
        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j < 10; j++)
            {

                int val = StartScreen.Manager.P1.playerboard.board[i][j];
                switch(val)
                {
                    case 0:
                        coordinates2[i][j].setImageResource(R.drawable.square);
                        break;
                    case 1:
                        coordinates2[i][j].setImageResource(R.drawable.ship_one_placed);
                        break;
                    case 2:
                        coordinates2[i][j].setImageResource(R.drawable.ship_two_placed);
                        break;
                    case 3:
                        coordinates2[i][j].setImageResource(R.drawable.ship_three_placed);
                        break;
                    case 4:
                        coordinates2[i][j].setImageResource(R.drawable.ship_four_placed);
                        break;
                    case 5:
                        coordinates2[i][j].setImageResource(R.drawable.ship_five_placed);
                        break;
                    case 10:
                        coordinates2[i][j].setImageResource(R.drawable.squarefaded);
                        break;
                    default:
                        coordinates2[i][j].setImageResource(R.drawable.hit);
                        break;
                }
            }
        }
    }

    public void displayAttackBoard()
    {
        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j < 10; j++)
            {

                int val = StartScreen.Manager.P2.playerboard.board[i][j];

                if(val < 10) coordinates2[i][j].setImageResource(R.drawable.square);
                else if(val > 10)  coordinates2[i][j].setImageResource(R.drawable.hit);
                else coordinates2[i][j].setImageResource(R.drawable.miss);
            }
        }
    }

    public void resetSelection()
    {
        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j < 10; j++)
            {
                int res = getResources().getIdentifier("gridspot_"+i+"."+j, "id", getPackageName()); //This line is necessary to "convert" a string (e.g. "i1", "i2" etc.) to a resource ID

                //if the spot hasn't been attacked yet, then reset

                if(StartScreen.Manager.P2.playerboard.board[i][j] < 10)
                {
                    ImageView image = (ImageView)findViewById(res);
                    image.setImageResource(R.drawable.square);
                }
            }
        }
    }

    public void displaySunkShipsOnAttackBoard()
    {
        for(int i = 0; i < 5; i++)
        {
            if(!StartScreen.Manager.P2.playerboard.battleFleet.shipsArray[i].getStatus()) //if ship is sunk
            {
                ShipPoint[] points = StartScreen.Manager.P2.playerboard.battleFleet.shipsArray[i].getLocation();
                for(ShipPoint pt : points)
                {
                    int res = getResources().getIdentifier("gridspot_"+pt.x+"."+pt.y, "id", getPackageName());
                    ImageView image = (ImageView)findViewById(res);
                    image.setImageResource(R.drawable.hitred);

                }
            }
        }
    }

    public void displayShipsStatus()
    {
        for(int i = 0; i < 5; i++)
        {
            if(!StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[i].getStatus()) //if ship is sunk
            {
                switch(i)
                {
                    case 0:
                        ship1.setImageResource(R.drawable.ship_one_v_sunk);
                        break;
                    case 1:
                        ship2.setImageResource(R.drawable.ship_two_v_sunk);
                        break;
                    case 2:
                        ship3.setImageResource(R.drawable.ship_three_v_sunk);
                        break;
                    case 3:
                        ship4.setImageResource(R.drawable.ship_four_v_sunk);
                        break;
                    case 4:
                        ship5.setImageResource(R.drawable.ship_five_v_sunk);
                        break;
                }
            }
        }
    }

}