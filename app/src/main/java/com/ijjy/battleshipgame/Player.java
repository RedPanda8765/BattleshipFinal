package com.ijjy.battleshipgame;

import java.util.Random; //for random number generator
import java.util.HashMap; // import the HashMap class

public class Player {

    public StrategyBoard playerboard;
    //public BattleshipAI ai;
    public Boolean isHuman;
    public int hits;
    public int misses;

    public Player(Boolean IsHuman)
    {

        this.playerboard = new StrategyBoard();
        this.isHuman = IsHuman;
        /*
        if(IsHuman) {
            //some logic for human
            this.playerboard = new StrategyBoard();
        }
        else
        {
            ai = new BattleshipAI(); //initializes AI strategy board and
                                     //activates fleet (randomly places ships)
            this.playerboard = ai.stratboard;



            //initialize AI strategy board.
            //this.playerboard = new StrategyBoard();

            //random placement of ships on AI's board
            //initAIStrategyBoard();
        }

         */
        this.hits = 0;
        this.misses = 0;
    }

    /*
        AIshoot deals a random shot at it's opponent and returns
        an int array of size 3, where
                int[0] = (0-9) - the x-coordinate shot at
                int[1] = (0-9) - the y-coordinate shot at
                int[2] = (1-5) - ship hit, and 0 for miss
   */

    /*
    public int[] AIshoot()
    {
        ai.makeMove(); //ai launches attack on Player1
        ShipPoint target = ai.getLastTarget();
        int[] returnArray = new int[3];
        returnArray[0] = target.x;
        returnArray[1] = target.y;
        returnArray[2] = ai.opponentBoard[target.x][target.y] - 10; //storing 0-5 at last index,
                                                                    //representing ships (1-5) or no ship (0)
        return returnArray;
        //////////////////////////////////////


        int[] returnArray = new int[3];
        //randomly shooting
        Random rand = new Random();
        int randx, randy;
        Boolean repeat;

        do {
            repeat = true;
            randx = rand.nextInt(10); //generates a random integer from 0-9
            randy = rand.nextInt(10); //generates a random integer from 0-9

            if (this.playerboard.board[randx][randy] < 10) //a valid spot has been selected
            {
                returnArray[0] = randx;
                returnArray[1] = randy;
                repeat = false;
            }
        }while(repeat);

        returnArray[2] = this.playerboard.board[randx][randy]; //storing ship that is hit
        this.playerboard.board[randx][randy] += 10; //adding 10 to the selected spot

        if(this.playerboard.board[randx][randy] > 10) //AI player landed a hit
        {
            //ship has been hit and status is updated accordingly
            this.playerboard.battleFleet.shipsArray[returnArray[2]-1].shipDealtHit();
            this.hits++;
        }
        else this.misses++;

        return returnArray;
    }
     */

    public double getHitMissRatio()
    {
        if(this.misses == 0) return (double) this.hits;
        return (double) this.hits /this.misses;
    }

    /*
    public void initAIStrategyBoard()
    {
        //placing ships randomly
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
                int hgt = this.playerboard.battleFleet.shipsArray[i].getHeight();
                this.playerboard.battleFleet.shipsArray[i].setHeight(this.playerboard.battleFleet.shipsArray[i].getWidth());
                this.playerboard.battleFleet.shipsArray[i].setWidth(hgt);
            }
            do{
                crossCheckFailed = false;
                do{
                    randx = rand.nextInt(10); //generates a random integer from 0-9
                    randy = rand.nextInt(10); //generates a random integer from 0-9
                    key = Integer.valueOf(String.valueOf(randx)+String.valueOf(randy));
                } while(hashT.get(key) != 0);

                //placing ship on board
                this.playerboard.placeShip(i+1, randx, randy);

                //placing ship in HashMap
                ShipPoint[] points = this.playerboard.battleFleet.shipsArray[i].getLocation();
                for(j = 0; j < points.length && !crossCheckFailed; j++)
                {
                    key = Integer.valueOf(String.valueOf(points[j].x)+String.valueOf(points[j].y));
                    if(hashT.get(key) == 0) hashT.put(key, i+1);
                    else crossCheckFailed = true;

                }//end of for-loop for cross-checking each ship

                if(crossCheckFailed)
                {
                    this.playerboard.placeAllShipsExcept(i+1); //remove ship from board
                    for(k = 0; k < j; k++) //remove ship from HashMap
                    {
                        key = Integer.valueOf(String.valueOf(points[k].x)+String.valueOf(points[k].y));
                        hashT.put(key, 0);
                    }
                }

            } while(crossCheckFailed); //end of outer do-while loop
        }//end of for-loop for placing each of the 5 ships

        playerboard.battleFleet.fleetStatus = true; //activating fleet
    }//end of initAIStrategyBoard() method

     */
}
