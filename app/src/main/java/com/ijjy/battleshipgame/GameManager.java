package com.ijjy.battleshipgame;

import java.util.HashMap;
import java.util.Random;

public class GameManager {

    public Player P1;
    public Player P2;
    private int winner;

    //game ai is an Inner class of the gamemanager *********************************************
    class BattleshipAI2{
        private Boolean found;
        private Boolean hit;
        private int lastHitRow;
        private int lastHitCol;
        private int i; // 0: down, 1: up, 2: right, 3: left
        private int k; //index of huntArray
        private ShipPoint[] huntArray;
        private ShipPoint lastTarget;

        public BattleshipAI2() {
            this.found = false;
            this.hit = false;
            this.lastHitRow = -1;
            this.lastHitCol = -1;
            this.i = -1;
            this.k = -1;
            this.huntArray = new ShipPoint[8];
            for(int j = 0; j < 8; j++)
            {
                this.huntArray[j] = new ShipPoint();
            }

            this.lastTarget = new ShipPoint();

            //setting up AI's board
            initializeBoard();
        }

        private void initializeBoard() {

            //Initializing AI's Strategy Board - placing ships at random locations with random orientation
            Random rand = new Random();
            HashMap<Integer, Integer> hashT = new HashMap<Integer, Integer>(); //using for constant-time lookup for cross-examining grid points
            int randx, randy, randhorizontal, key, i, j, k;
            Boolean crossCheckFailed;

            //filling all 100 spots in HashMap with 0s
            for(i = 0; i < 100; i++)
                hashT.put(i,0);

            for(i = 0; i < 5; i++)
            {
                randhorizontal = rand.nextInt(2); //generates a random integer from 0-1
                if(randhorizontal == 1) //turn ship horizontal
                {
                    int hgt = P2.playerboard.battleFleet.shipsArray[i].getHeight();
                    P2.playerboard.battleFleet.shipsArray[i].setHeight(P2.playerboard.battleFleet.shipsArray[i].getWidth());
                    P2.playerboard.battleFleet.shipsArray[i].setWidth(hgt);
                }
                do{
                    crossCheckFailed = false;
                    do{
                        randx = rand.nextInt(10); //generates a random integer from 0-9
                        randy = rand.nextInt(10); //generates a random integer from 0-9
                        key = Integer.valueOf(String.valueOf(randx)+String.valueOf(randy));
                    } while(hashT.get(key) != 0);

                    //placing ship on board
                    P2.playerboard.placeShip(i+1, randx, randy);

                    //placing ship in HashMap
                    ShipPoint[] points = P2.playerboard.battleFleet.shipsArray[i].getLocation();
                    for(j = 0; j < points.length && !crossCheckFailed; j++)
                    {
                        key = Integer.valueOf(String.valueOf(points[j].x)+String.valueOf(points[j].y));
                        if(hashT.get(key) == 0) hashT.put(key, i+1);
                        else crossCheckFailed = true;

                    }//end of for-loop for cross-checking each ship

                    if(crossCheckFailed)
                    {
                        P2.playerboard.placeAllShipsExcept(i+1); //remove ship from board
                        for(k = 0; k < j; k++) //remove ship from HashMap
                        {
                            key = Integer.valueOf(String.valueOf(points[k].x)+String.valueOf(points[k].y));
                            hashT.put(key, 0);
                        }
                    }

                } while(crossCheckFailed); //end of outer do-while loop
            }//end of for-loop for placing each of the 5 ships

            P2.playerboard.battleFleet.fleetStatus = true; //activating fleet
        }

        public void makeMove() {
            Random rand = new Random();
            int row, col;
            Boolean pass = false;

            // If the enemy's ship had been found
            if (found) {
                // Make a move around the last hit
                if(hit)
                {
                    this.k++;
                    this.huntArray[k].setShipPointLocation(this.lastHitRow, this.lastHitCol);
                }
                do {
                    this.i++;
                    row = this.lastHitRow;
                    col = this.lastHitCol;
                    switch (this.i) {
                        case 0:
                            row++;
                            break;
                        case 1:
                            row--;
                            break;
                        case 2:
                            col++;
                            break;
                        case 3:
                            col--;
                            break;
                        default: //if i > 3
                            this.i = -1; //resetting i
                            this.lastHitRow = huntArray[0].x; //moving back to first hit point on ship
                            this.lastHitCol = huntArray[0].y;
                            pass = true;
                    }
                } while (!pass && !isValidMove(row, col));
            }
            else
            {
                // Make a random move
                do {
                    row = rand.nextInt(10);
                    col = rand.nextInt(10);
                } while (!isValidMove(row, col));
            }

            // Mark the move on the AI opponent's board
            if(!pass) P1.playerboard.board[row][col] += 10;

            // If the move hits a ship
            if (!pass && (P1.playerboard.board[row][col] > 10)) {
                int hitShip = P1.playerboard.board[row][col] - 10;
                P1.playerboard.battleFleet.shipsArray[hitShip-1].shipDealtHit();
                this.found = true;
                this.hit = true;
                this.i = -1;
                this.lastHitRow = row;
                this.lastHitCol = col;
                if(!P1.playerboard.battleFleet.shipsArray[hitShip-1].getStatus()) //if ship sinks
                {
                    this.found = false;
                    this.hit = false;
                    this.k = -1; //resetting huntArray
                }
            }
            else
            { //AI misses opponent's fleet
                if(this.i != -1)
                {
                    this.hit = false;
                    if(this.i == 3)
                    {
                        this.i = -1;
                        //int j = this.k - 1;
                        //this.lastHitRow = huntArray[j].x;
                        //this.lastHitCol = huntArray[j].y;
                        this.lastHitRow = huntArray[0].x;
                        this.lastHitCol = huntArray[0].y;
                    }
                }
            }//end of else statement (for misses)

            this.lastTarget.setShipPointLocation(row, col);
        }

        private boolean isValidMove(int row, int col) {
            return row >= 0 && row < 10 && col >= 0 && col < 10 && (P1.playerboard.board[row][col] < 10);
        }

        // returns AI's last targeted gridpoint
        public ShipPoint getLastTarget() {
            return this.lastTarget;
        }

    } //end of inner BattleshipAI class

    //********************************************************************
    public GameManager(){

        this.P1 = new Player(true);
        this.winner = 0; //no winner
    }

    public Boolean checkGameOver()
    {
        //updating both player's fleet statuses
        P1.playerboard.battleFleet.updateStatus();
        P2.playerboard.battleFleet.updateStatus();

        if(!P1.playerboard.battleFleet.fleetStatus)
        {
            this.winner = 2;
            return true;
        }
        else if (!P2.playerboard.battleFleet.fleetStatus)
        {
            this.winner = 1;
            return true;
        }
        else
            return false; //game isn't over yet, both player's fleets are active
    }

    //returns winner (1 = P1, 2 = P2, and 0 = no winner)
    public int getWinner()
    {
        return this.winner;
    }

    public void resetP1()
    {
        this.P1 = null;
        this.P1 = new Player(true);
        this.winner = 0; //no winner
    }

    /*
    public void P2Turn()
    {
        if(this.humanOpp)
        {
            //human P2 launches attack at P1
        }
        else if(!this.humanOpp)
        {
            //AI P2 launches attack at P1
            int[] returnedArray = this.P2.AIshoot();
        }
    }

     */


    //if multiplayer, use p2, else
    //create a new, random strategy board, and then act using that.


    //enemy AI
    //shoot at random coordinates
    //if hit, use a angorithm

    //turn logic{
    //status checks at beginning and end of turn.
    //loop game until the status checks fail (semaphor?)

    //}
}
