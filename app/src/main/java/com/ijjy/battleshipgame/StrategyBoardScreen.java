package com.ijjy.battleshipgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class StrategyBoardScreen extends AppCompatActivity {

    TextView text;
    ImageView[][] coordinates2 = new ImageView[10][10]; //for grid

    ImageView ship1, ship2, ship3, ship4, ship5, selectedShip, rotateBut;
    int selected = -1;
    int[] clicks = new int[5]; //keeps track of how many rotation-clicks for each ship


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_strategy_board_screen);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        //refreshing P1's StrategyBoard
        StartScreen.Manager.resetP1();

        ship1 = findViewById(R.id.ship1_id);
        ship2 = findViewById(R.id.ship2_id);
        ship3 = findViewById(R.id.ship3_id);
        ship4 = findViewById(R.id.ship4_id);
        ship5 = findViewById(R.id.ship5_id);
        selectedShip = findViewById(R.id.selectedship_id);
        rotateBut = findViewById(R.id.rotate_id);

        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j < 10; j++)
            {
                int res = getResources().getIdentifier("gridspot_"+i+"."+j, "id", getPackageName()); //This line is necessary to "convert" a string (e.g. "i1", "i2" etc.) to a resource ID
                coordinates2[i][j] = (ImageView) findViewById(res);
            }
        }
        text = findViewById(R.id.message);

        ship1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deselectAll();
                clicks[0] = 0;
                ViewGroup.LayoutParams params = selectedShip.getLayoutParams();
                params.height=325;
                params.width=325;
                selectedShip.setLayoutParams(params);
                selectedShip.setImageResource(R.drawable.ship_one_v);
                ship1.setImageResource(R.drawable.ship_one_v_selected);
                selected = 1;
                // String str = "You've selected Ship 1";
                //text.setText(str);
                //
                StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].setHeight(StartScreen.Manager.P1.playerboard.battleFleet.shipDimens[2*selected-2]);
                StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].setWidth(StartScreen.Manager.P1.playerboard.battleFleet.shipDimens[2*selected-1]);
                /*
                String str = selected + "\'s height: " + StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].getHeight();
                str += " & " + selected + "\'s width: " + StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].getWidth();
                text.setText(str);
                 */

                String str = StartScreen.Manager.P1.playerboard.battleFleet.shipNames[selected-1] + " Selected\n Tap to Place Ship on Grid";
                text.setText(str);
                
                //animating ship image
                RotateAnimation anim = new RotateAnimation(-5f, 5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                anim.setInterpolator(new LinearInterpolator());
                anim.setRepeatCount(2);
                anim.setRepeatMode(Animation.REVERSE);
                anim.setDuration(100);

                // Start animating the image
                ship1.startAnimation(anim);
                
            }
        });

        ship2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deselectAll();
                clicks[1] = 0;
                ViewGroup.LayoutParams params = selectedShip.getLayoutParams();
                params.height=225;
                params.width=225;
                selectedShip.setLayoutParams(params);
                selectedShip.setImageResource(R.drawable.ship_two_v);
                ship2.setImageResource(R.drawable.ship_two_v_selected);
                selected = 2;
                //String str = "You've selected Ship 2";
                // text.setText(str);
                //
                StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].setHeight(StartScreen.Manager.P1.playerboard.battleFleet.shipDimens[2*selected-2]);
                StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].setWidth(StartScreen.Manager.P1.playerboard.battleFleet.shipDimens[2*selected-1]);
                /*
                String str = selected + "\'s height: " + StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].getHeight();
                str += " & " + selected + "\'s width: " + StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].getWidth();
                text.setText(str);
                 */

                String str = StartScreen.Manager.P1.playerboard.battleFleet.shipNames[selected-1] + " Selected\n Tap to Place Ship on Grid";
                text.setText(str);

                //animating ship image
                RotateAnimation anim = new RotateAnimation(-5f, 5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                anim.setInterpolator(new LinearInterpolator());
                anim.setRepeatCount(2);
                anim.setRepeatMode(Animation.REVERSE);
                anim.setDuration(100);

                // Start animating the image
                ship2.startAnimation(anim);
            }
        });

        ship3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deselectAll();
                clicks[2] = 0;
                ViewGroup.LayoutParams params = selectedShip.getLayoutParams();
                params.height=225;
                params.width=225;
                selectedShip.setLayoutParams(params);
                selectedShip.setImageResource(R.drawable.ship_three_v);
                ship3.setImageResource(R.drawable.ship_three_v_selected);
                selected = 3;
                // String str = "You've selected Ship 3";
                // text.setText(str);
                //
                StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].setHeight(StartScreen.Manager.P1.playerboard.battleFleet.shipDimens[2*selected-2]);
                StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].setWidth(StartScreen.Manager.P1.playerboard.battleFleet.shipDimens[2*selected-1]);
                /*
                String str = selected + "\'s height: " + StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].getHeight();
                str += " & " + selected + "\'s width: " + StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].getWidth();
                text.setText(str);
                 */

                String str = StartScreen.Manager.P1.playerboard.battleFleet.shipNames[selected-1] + " Selected\n Tap to Place Ship on Grid";
                text.setText(str);

                //animating ship image
                RotateAnimation anim = new RotateAnimation(-5f, 5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                anim.setInterpolator(new LinearInterpolator());
                anim.setRepeatCount(2);
                anim.setRepeatMode(Animation.REVERSE);
                anim.setDuration(100);

                // Start animating the image
                ship3.startAnimation(anim);
            }
        });

        ship4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deselectAll();
                clicks[3] = 0;
                ViewGroup.LayoutParams params = selectedShip.getLayoutParams();
                params.height=200;
                params.width=200;
                selectedShip.setLayoutParams(params);
                selectedShip.setImageResource(R.drawable.ship_four_v);
                ship4.setImageResource(R.drawable.ship_four_v_selected);
                selected = 4;
                //String str = "You've selected Ship 4";
                //text.setText(str);
                //
                StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].setHeight(StartScreen.Manager.P1.playerboard.battleFleet.shipDimens[2*selected-2]);
                StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].setWidth(StartScreen.Manager.P1.playerboard.battleFleet.shipDimens[2*selected-1]);
                /*
                String str = selected + "\'s height: " + StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].getHeight();
                str += " & " + selected + "\'s width: " + StartScreen  .Manager.P1.playerboard.battleFleet.shipsArray[selected-1].getWidth();
                text.setText(str);
                 */

                String str = StartScreen.Manager.P1.playerboard.battleFleet.shipNames[selected-1] + " Selected\n Tap to Place Ship on Grid";
                text.setText(str);

                //animating ship image
                RotateAnimation anim = new RotateAnimation(-5f, 5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                anim.setInterpolator(new LinearInterpolator());
                anim.setRepeatCount(2);
                anim.setRepeatMode(Animation.REVERSE);
                anim.setDuration(100);

                // Start animating the image
                ship4.startAnimation(anim);
            }
        });

        ship5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deselectAll();
                clicks[4] = 0;
                ViewGroup.LayoutParams params = selectedShip.getLayoutParams();
                params.height=350;
                params.width=300;
                selectedShip.setLayoutParams(params);
                selectedShip.setImageResource(R.drawable.ship_five_v);
                ship5.setImageResource(R.drawable.ship_five_v_selected);
                selected = 5;
                //
                StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].setHeight(StartScreen.Manager.P1.playerboard.battleFleet.shipDimens[2*selected-2]);
                StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].setWidth(StartScreen.Manager.P1.playerboard.battleFleet.shipDimens[2*selected-1]);
                /*
                String str = selected + "\'s height: " + StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].getHeight();
                str += " & " + selected + "\'s width: " + StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].getWidth();
                text.setText(str);
                 */

                String str = StartScreen.Manager.P1.playerboard.battleFleet.shipNames[selected-1] + " Selected\n Tap to Place Ship on Grid";
                text.setText(str);

                //animating ship image
                RotateAnimation anim = new RotateAnimation(-5f, 5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                anim.setInterpolator(new LinearInterpolator());
                anim.setRepeatCount(2);
                anim.setRepeatMode(Animation.REVERSE);
                anim.setDuration(100);

                // Start animating the image
                ship5.startAnimation(anim);
            }
        });

        rotateBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str;

                switch(selected)
                {
                    case 1:
                        clicks[0]++; //odd # of clicks = horizontal, even = vertical
                        if(clicks[0]%2==1) {
                            selectedShip.setImageResource(R.drawable.ship_one_h);
                            ViewGroup.LayoutParams params = selectedShip.getLayoutParams();
                            params.height=325;
                            params.width=325;
                            selectedShip.setLayoutParams(params);
                            StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].setHeight(StartScreen.Manager.P1.playerboard.battleFleet.shipDimens[2*selected-1]);
                            StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].setWidth(StartScreen.Manager.P1.playerboard.battleFleet.shipDimens[2*selected-2]);
                            //String str = selected + "\'s height: " + StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].getHeight();
                            //str += " & " + selected + "\'s width: " + StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].getWidth();
                            //text.setText(str);

                        }
                        else {
                            selectedShip.setImageResource(R.drawable.ship_one_v);
                            ViewGroup.LayoutParams params = selectedShip.getLayoutParams();
                            params.height=325;
                            params.width=325;
                            selectedShip.setLayoutParams(params);
                            StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].setHeight(StartScreen.Manager.P1.playerboard.battleFleet.shipDimens[2*selected-2]);
                            StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].setWidth(StartScreen.Manager.P1.playerboard.battleFleet.shipDimens[2*selected-1]);
                            //String str = selected + "\'s height: " + StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].getHeight();
                            //str += " & " + selected + "\'s width: " + StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].getWidth();
                            //text.setText(str);
                        }

                        str = StartScreen.Manager.P1.playerboard.battleFleet.shipNames[selected-1] + " Rotated\n Tap to Place Ship on Grid";
                        text.setText(str);
                        break;
                    case 2:
                        clicks[1]++; //odd # of clicks = horizontal, even = vertical
                        if(clicks[1]%2==1) {
                            selectedShip.setImageResource(R.drawable.ship_two_h);
                            ViewGroup.LayoutParams params = selectedShip.getLayoutParams();
                            params.height=225;
                            params.width=225;
                            selectedShip.setLayoutParams(params);
                            StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].setHeight(StartScreen.Manager.P1.playerboard.battleFleet.shipDimens[2*selected-1]);
                            StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].setWidth(StartScreen.Manager.P1.playerboard.battleFleet.shipDimens[2*selected-2]);
                            //String str = selected + "\'s height: " + StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].getHeight();
                            //str += " & " + selected + "\'s width: " + StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].getWidth();
                            //text.setText(str);
                        }
                        else {
                            selectedShip.setImageResource(R.drawable.ship_two_v);
                            ViewGroup.LayoutParams params = selectedShip.getLayoutParams();
                            params.height=225;
                            params.width=225;
                            selectedShip.setLayoutParams(params);
                            StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].setHeight(StartScreen.Manager.P1.playerboard.battleFleet.shipDimens[2*selected-2]);
                            StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].setWidth(StartScreen.Manager.P1.playerboard.battleFleet.shipDimens[2*selected-1]);
                            //String str = selected + "\'s height: " + StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].getHeight();
                            //str += " & " + selected + "\'s width: " + StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].getWidth();
                            //text.setText(str);
                        }
                        str = StartScreen.Manager.P1.playerboard.battleFleet.shipNames[selected-1] + " Rotated\n Tap to Place Ship on Grid";
                        text.setText(str);
                        break;
                    case 3:
                        clicks[2]++; //odd # of clicks = horizontal, even = vertical
                        if(clicks[2]%2==1) {
                            selectedShip.setImageResource(R.drawable.ship_three_h);
                            ViewGroup.LayoutParams params = selectedShip.getLayoutParams();
                            params.height=225;
                            params.width=225;
                            selectedShip.setLayoutParams(params);
                            StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].setHeight(StartScreen.Manager.P1.playerboard.battleFleet.shipDimens[2*selected-1]);
                            StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].setWidth(StartScreen.Manager.P1.playerboard.battleFleet.shipDimens[2*selected-2]);
                            //String str = selected + "\'s height: " + StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].getHeight();
                            //str += " & " + selected + "\'s width: " + StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].getWidth();
                            //text.setText(str);
                        }
                        else {
                            selectedShip.setImageResource(R.drawable.ship_three_v);
                            ViewGroup.LayoutParams params = selectedShip.getLayoutParams();
                            params.height=225;
                            params.width=225;
                            selectedShip.setLayoutParams(params);
                            StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].setHeight(StartScreen.Manager.P1.playerboard.battleFleet.shipDimens[2*selected-2]);
                            StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].setWidth(StartScreen.Manager.P1.playerboard.battleFleet.shipDimens[2*selected-1]);
                            //String str = selected + "\'s height: " + StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].getHeight();
                            //str += " & " + selected + "\'s width: " + StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].getWidth();
                            //text.setText(str);
                        }
                        str = StartScreen.Manager.P1.playerboard.battleFleet.shipNames[selected-1] + " Rotated\n Tap to Place Ship on Grid";
                        text.setText(str);
                        break;
                    case 4:
                        clicks[3]++; //odd # of clicks = horizontal, even = vertical
                        if(clicks[3]%2==1) {
                            selectedShip.setImageResource(R.drawable.ship_four_h);
                            ViewGroup.LayoutParams params = selectedShip.getLayoutParams();
                            params.height=200;
                            params.width=200;
                            selectedShip.setLayoutParams(params);
                            StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].setHeight(StartScreen.Manager.P1.playerboard.battleFleet.shipDimens[2*selected-1]);
                            StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].setWidth(StartScreen.Manager.P1.playerboard.battleFleet.shipDimens[2*selected-2]);
                            //String str = selected + "\'s height: " + StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].getHeight();
                            //str += " & " + selected + "\'s width: " + StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].getWidth();
                            //text.setText(str);
                        }
                        else {
                            selectedShip.setImageResource(R.drawable.ship_four_v);
                            ViewGroup.LayoutParams params = selectedShip.getLayoutParams();
                            params.height=200;
                            params.width=200;
                            selectedShip.setLayoutParams(params);
                            StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].setHeight(StartScreen.Manager.P1.playerboard.battleFleet.shipDimens[2*selected-2]);
                            StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].setWidth(StartScreen.Manager.P1.playerboard.battleFleet.shipDimens[2*selected-1]);
                            //String str = selected + "\'s height: " + StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].getHeight();
                            //str += " & " + selected + "\'s width: " + StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].getWidth();
                            //text.setText(str);
                        }
                        str = StartScreen.Manager.P1.playerboard.battleFleet.shipNames[selected-1] + " Rotated\n Tap to Place Ship on Grid";
                        text.setText(str);
                        break;
                    case 5:
                        clicks[4]++; //odd # of clicks = horizontal, even = vertical
                        if(clicks[4]%2==1) {
                            selectedShip.setImageResource(R.drawable.ship_five_h);
                            ViewGroup.LayoutParams params = selectedShip.getLayoutParams();
                            params.height=300;
                            params.width=350;
                            selectedShip.setLayoutParams(params);
                            StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].setHeight(StartScreen.Manager.P1.playerboard.battleFleet.shipDimens[2*selected-1]);
                            StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].setWidth(StartScreen.Manager.P1.playerboard.battleFleet.shipDimens[2*selected-2]);
                            //String str = selected + "\'s height: " + StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].getHeight();
                            //str += " & " + selected + "\'s width: " + StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].getWidth();
                            //text.setText(str);
                        }
                        else {
                            selectedShip.setImageResource(R.drawable.ship_five_v);
                            ViewGroup.LayoutParams params = selectedShip.getLayoutParams();
                            params.height=350;
                            params.width=300;
                            selectedShip.setLayoutParams(params);
                            StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].setHeight(StartScreen.Manager.P1.playerboard.battleFleet.shipDimens[2*selected-2]);
                            StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].setWidth(StartScreen.Manager.P1.playerboard.battleFleet.shipDimens[2*selected-1]);
                            //String str = selected + "\'s height: " + StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].getHeight();
                            //str += " & " + selected + "\'s width: " + StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].getWidth();
                            //text.setText(str);
                        }
                        str = StartScreen.Manager.P1.playerboard.battleFleet.shipNames[selected-1] + " Rotated\n Tap to Place Ship on Grid";
                        text.setText(str);
                        break;
                } //end of switch case

            }
        });
    }

    public void gridPress(View view) {  //needs fixing, app crashes if grid is pressed!

        String id = getResources().getResourceEntryName(view.getId());
        int xPoint = id.charAt(9)-48; //extracting the x coordinate of the selected grid spot
        int yPoint = id.charAt(11)-48; //extracting the y coordinate of the selected grid spot
        //LINES 310-311 - DELETE AFTER
        //String str = "You selected (" + xPoint + ", " + yPoint+ ")";
        //text.setText(str);

        if(selected != -1)
        {

            if(StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].getStatus())
                StartScreen.Manager.P1.playerboard.placeAllShipsExcept(selected);

            StartScreen.Manager.P1.playerboard.placeShip(selected,xPoint,yPoint);

            String str = StartScreen.Manager.P1.playerboard.battleFleet.shipNames[selected-1] + "\nStationed On Grid";
            text.setText(str);

            ShipPoint[] points = StartScreen.Manager.P1.playerboard.battleFleet.shipsArray[selected-1].getLocation();

            //LINES 324-329 - DELETE AFTER
            /*
            String str2 = "";
            for(ShipPoint pt : points)
            {
                str2 += "(" + pt.x + ", " + pt.y+ ") - ";
            }
            text.setText(str2);
             */

            for(int i = 0; i < 10; i++)
            {
                for(int j = 0; j < 10; j++)
                {

                    int res = getResources().getIdentifier("gridspot_"+i+"."+j, "id", getPackageName()); //This line is necessary to "convert" a string (e.g. "i1", "i2" etc.) to a resource ID
                    ImageView image = (ImageView)findViewById(res);

                    int val = StartScreen.Manager.P1.playerboard.board[i][j];
                    switch(val)
                    {
                        case 1:
                            image.setImageResource(R.drawable.ship_one_placed);
                            break;
                        case 2:
                            image.setImageResource(R.drawable.ship_two_placed);
                            break;
                        case 3:
                            image.setImageResource(R.drawable.ship_three_placed);
                            break;
                        case 4:
                            image.setImageResource(R.drawable.ship_four_placed);
                            break;
                        case 5:
                            image.setImageResource(R.drawable.ship_five_placed);
                            break;
                        default:
                            image.setImageResource(R.drawable.square);
                            break;
                    }
                }
            }

        } //end of if
        else
        {
            Toast.makeText(getBaseContext(), "Select a Ship", Toast.LENGTH_SHORT).show();
        }

    }//end of gridPress method

    public void homebuttonclick(View view) {
        Intent intent = new Intent(getApplicationContext(),StartScreen.class);
        startActivity(intent);
        finish();
    }

    public void startmatchbuttonclick(View view) {
        if(StartScreen.Manager.P1.playerboard.allPlaced() && StartScreen.Manager.P1.playerboard.noOverlapping())
        {
            //activate fleet
            StartScreen.Manager.P1.playerboard.battleFleet.fleetStatus = true;
            Intent intent = new Intent(getApplicationContext(),GameScreen.class);
            startActivity(intent);
            finish();
        }
        else if (!StartScreen.Manager.P1.playerboard.allPlaced())
            Toast.makeText(getBaseContext(), "Place Entire Fleet\n", Toast.LENGTH_SHORT).show();
        else if (!StartScreen.Manager.P1.playerboard.noOverlapping())
            Toast.makeText(getBaseContext(), "Overlapping Ships! Reposition Fleet\n", Toast.LENGTH_SHORT).show();

        deselectAll();
        selected = -1;
        ViewGroup.LayoutParams params = selectedShip.getLayoutParams();
        params.height=75;
        params.width=75;
        selectedShip.setLayoutParams(params);
        selectedShip.setImageResource(R.drawable.noselection);
    }

    public void deselectAll(){
        ship1.setImageResource(R.drawable.ship_one_v);
        ship2.setImageResource(R.drawable.ship_two_v);
        ship3.setImageResource(R.drawable.ship_three_v);
        ship4.setImageResource(R.drawable.ship_four_v);
        ship5.setImageResource(R.drawable.ship_five_v);
    }

}