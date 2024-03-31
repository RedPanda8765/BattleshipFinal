package com.ijjy.battleshipgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class StrategyBoardScreen extends AppCompatActivity {

    //StrategyBoard stratBoard = new StrategyBoard(); <-- needs fixing, app crashes if line is uncommented
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
        ship1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deselectAll();
                ViewGroup.LayoutParams params = selectedShip.getLayoutParams();
                params.height=300;
                params.width=300;
                selectedShip.setLayoutParams(params);
                selectedShip.setImageResource(R.drawable.ship_one_v);
                ship1.setImageResource(R.drawable.ship_one_v_selected);
                selected = 1;
            }
        });

        ship2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deselectAll();
                ViewGroup.LayoutParams params = selectedShip.getLayoutParams();
                params.height=300;
                params.width=300;
                selectedShip.setLayoutParams(params);
                selectedShip.setImageResource(R.drawable.ship_two_v);
                ship2.setImageResource(R.drawable.ship_two_v_selected);
                selected = 2;
            }
        });

        ship3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deselectAll();
                ViewGroup.LayoutParams params = selectedShip.getLayoutParams();
                params.height=300;
                params.width=300;
                selectedShip.setLayoutParams(params);
                selectedShip.setImageResource(R.drawable.ship_three_v);
                ship3.setImageResource(R.drawable.ship_three_v_selected);
                selected = 3;
            }
        });

        ship4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deselectAll();
                ViewGroup.LayoutParams params = selectedShip.getLayoutParams();
                params.height=300;
                params.width=300;
                selectedShip.setLayoutParams(params);
                selectedShip.setImageResource(R.drawable.ship_four_v);
                ship4.setImageResource(R.drawable.ship_four_v_selected);
                selected = 4;
            }
        });

        ship5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deselectAll();
                ViewGroup.LayoutParams params = selectedShip.getLayoutParams();
                params.height=350;
                params.width=300;
                selectedShip.setLayoutParams(params);
                selectedShip.setImageResource(R.drawable.ship_five_v);
                ship5.setImageResource(R.drawable.ship_five_v_selected);
                selected = 5;
            }
        });

        rotateBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch(selected)
                {
                    case 1:
                        clicks[0]++; //odd # of clicks = horizontal, even = vertical
                        if(clicks[0]%2==1) {
                            selectedShip.setImageResource(R.drawable.ship_one_h);
                            ViewGroup.LayoutParams params = selectedShip.getLayoutParams();
                            params.height=300;
                            params.width=300;
                            selectedShip.setLayoutParams(params);
                            //stratBoard.battleFleet.shipsArray[selected-1].setHeight(stratBoard.battleFleet.shipDimens[2*selected-1]);
                            //stratBoard.battleFleet.shipsArray[selected-1].setWidth(stratBoard.battleFleet.shipDimens[2*selected-2]);

                        }
                        else {
                            selectedShip.setImageResource(R.drawable.ship_one_v);
                            ViewGroup.LayoutParams params = selectedShip.getLayoutParams();
                            params.height=300;
                            params.width=300;
                            selectedShip.setLayoutParams(params);
                            //stratBoard.battleFleet.shipsArray[selected-1].setHeight(stratBoard.battleFleet.shipDimens[2*selected-2]);
                            //stratBoard.battleFleet.shipsArray[selected-1].setWidth(stratBoard.battleFleet.shipDimens[2*selected-1]);
                        }
                        break;
                    case 2:
                        clicks[1]++; //odd # of clicks = horizontal, even = vertical
                        if(clicks[1]%2==1) {
                            selectedShip.setImageResource(R.drawable.ship_two_h);
                            ViewGroup.LayoutParams params = selectedShip.getLayoutParams();
                            params.height=300;
                            params.width=300;
                            selectedShip.setLayoutParams(params);
                            //stratBoard.battleFleet.shipsArray[selected-1].setHeight(stratBoard.battleFleet.shipDimens[2*selected-1]);
                            //stratBoard.battleFleet.shipsArray[selected-1].setWidth(stratBoard.battleFleet.shipDimens[2*selected-2]);
                        }
                        else {
                            selectedShip.setImageResource(R.drawable.ship_two_v);
                            ViewGroup.LayoutParams params = selectedShip.getLayoutParams();
                            params.height=300;
                            params.width=300;
                            selectedShip.setLayoutParams(params);
                            //stratBoard.battleFleet.shipsArray[selected-1].setHeight(stratBoard.battleFleet.shipDimens[2*selected-2]);
                            //stratBoard.battleFleet.shipsArray[selected-1].setWidth(stratBoard.battleFleet.shipDimens[2*selected-1]);
                        }
                        break;
                    case 3:
                        clicks[2]++; //odd # of clicks = horizontal, even = vertical
                        if(clicks[2]%2==1) {
                            selectedShip.setImageResource(R.drawable.ship_three_h);
                            ViewGroup.LayoutParams params = selectedShip.getLayoutParams();
                            params.height=300;
                            params.width=300;
                            selectedShip.setLayoutParams(params);
                            //stratBoard.battleFleet.shipsArray[selected-1].setHeight(stratBoard.battleFleet.shipDimens[2*selected-1]);
                            //stratBoard.battleFleet.shipsArray[selected-1].setWidth(stratBoard.battleFleet.shipDimens[2*selected-2]);
                        }
                        else {
                            selectedShip.setImageResource(R.drawable.ship_three_v);
                            ViewGroup.LayoutParams params = selectedShip.getLayoutParams();
                            params.height=300;
                            params.width=300;
                            selectedShip.setLayoutParams(params);
                            //stratBoard.battleFleet.shipsArray[selected-1].setHeight(stratBoard.battleFleet.shipDimens[2*selected-2]);
                            //stratBoard.battleFleet.shipsArray[selected-1].setWidth(stratBoard.battleFleet.shipDimens[2*selected-1]);
                        }
                        break;
                    case 4:
                        clicks[3]++; //odd # of clicks = horizontal, even = vertical
                        if(clicks[3]%2==1) {
                            selectedShip.setImageResource(R.drawable.ship_four_h);
                            ViewGroup.LayoutParams params = selectedShip.getLayoutParams();
                            params.height=300;
                            params.width=300;
                            selectedShip.setLayoutParams(params);
                            //stratBoard.battleFleet.shipsArray[selected-1].setHeight(stratBoard.battleFleet.shipDimens[2*selected-1]);
                            //stratBoard.battleFleet.shipsArray[selected-1].setWidth(stratBoard.battleFleet.shipDimens[2*selected-2]);
                        }
                        else {
                            selectedShip.setImageResource(R.drawable.ship_four_v);
                            ViewGroup.LayoutParams params = selectedShip.getLayoutParams();
                            params.height=300;
                            params.width=300;
                            selectedShip.setLayoutParams(params);
                            //stratBoard.battleFleet.shipsArray[selected-1].setHeight(stratBoard.battleFleet.shipDimens[2*selected-2]);
                            //stratBoard.battleFleet.shipsArray[selected-1].setWidth(stratBoard.battleFleet.shipDimens[2*selected-1]);
                        }
                        break;
                    case 5:
                        clicks[4]++; //odd # of clicks = horizontal, even = vertical
                        if(clicks[4]%2==1) {
                            selectedShip.setImageResource(R.drawable.ship_five_h);
                            ViewGroup.LayoutParams params = selectedShip.getLayoutParams();
                            params.height=300;
                            params.width=350;
                            selectedShip.setLayoutParams(params);
                            //stratBoard.battleFleet.shipsArray[selected-1].setHeight(stratBoard.battleFleet.shipDimens[2*selected-1]);
                            //stratBoard.battleFleet.shipsArray[selected-1].setWidth(stratBoard.battleFleet.shipDimens[2*selected-2]);
                        }
                        else {
                            selectedShip.setImageResource(R.drawable.ship_five_v);
                            ViewGroup.LayoutParams params = selectedShip.getLayoutParams();
                            params.height=350;
                            params.width=300;
                            selectedShip.setLayoutParams(params);
                            //stratBoard.battleFleet.shipsArray[selected-1].setHeight(stratBoard.battleFleet.shipDimens[2*selected-2]);
                            //stratBoard.battleFleet.shipsArray[selected-1].setWidth(stratBoard.battleFleet.shipDimens[2*selected-1]);
                        }
                        break;
                } //end of switch case

            }
        });
    }

    public void gridPress(View view) {
        /*
        String id = getResources().getResourceEntryName(view.getId());
        int xPoint = id.charAt(9)-48; //extracting the x coordinate of the selected grid spot
        int yPoint = id.charAt(11)-48; ////extracting the y coordinate of the selected grid spot

        if(selected != -1)
        {
            stratBoard.placeShip(selected,xPoint,yPoint);
            ShipPoint[] points = stratBoard.battleFleet.shipsArray[selected-1].getLocation();
            for(ShipPoint point : points)
            {
                int res = getResources().getIdentifier("gridspot_"+point.x+"."+point.y, "id", getPackageName()); //This line is necessary to "convert" a string (e.g. "i1", "i2" etc.) to a resource ID
                ImageView image = (ImageView)findViewById(res);

                switch(selected)
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
                }
            }
        }
        else
        {
            Toast.makeText(getBaseContext(), "Select a ship, rotate (if desired), then place on grid", Toast.LENGTH_SHORT).show();
        }
        */
    }

    public void homebuttonclick(View view) {
        Intent intent = new Intent(getApplicationContext(),StartScreen.class);
        startActivity(intent);
        finish();
    }

    public void deselectAll(){
        ship1.setImageResource(R.drawable.ship_one_v);
        ship2.setImageResource(R.drawable.ship_two_v);
        ship3.setImageResource(R.drawable.ship_three_v);
        ship4.setImageResource(R.drawable.ship_four_v);
        ship5.setImageResource(R.drawable.ship_five_v);
    }
}