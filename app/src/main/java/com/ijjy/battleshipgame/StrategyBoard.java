package com.ijjy.battleshipgame;

public class StrategyBoard {

    public int[][] board; //to make access easier
    public Fleet battleFleet; //to make access easier

    public StrategyBoard()
    {
        //initBoard:
        this.board = new int[10][10]; //creating 10x10 grid

        //java automatically fills int arrays with 0s upon creation,
        //but just in case, doing it here again.
        clearGrid();

        //create fleet:
        this.battleFleet = new Fleet();
    }

    public void clearGrid()
    {
        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j < 10; j++)
            {
                this.board[i][j]=0;
            }
        }
    }

    //places all the ships on a clean board except a select one.
    public void placeAllShipsExcept(int selected)
    {
        clearGrid();
        for(int i = 0; i < 5; i++) //for every ship
        {
            if(i != (selected-1) && this.battleFleet.shipsArray[i].getStatus())
            {
                ShipPoint[] points = this.battleFleet.shipsArray[i].getLocation();
                for(ShipPoint pt : points)
                    this.board[pt.x][pt.y] = i+1;
            }
        }
    }

    public void placeShip(int selectedShip, int xPoint, int yPoint) {
        int k = 0;
        int wid = this.battleFleet.shipsArray[selectedShip - 1].getWidth();
        int hgt = this.battleFleet.shipsArray[selectedShip - 1].getHeight();
        int len = hgt*wid;

        ShipPoint[] points = new ShipPoint[len];

        int maxX = xPoint + hgt;
        int maxY = yPoint + wid;

        if (maxX > 10) {
            int sub = maxX - 10;
            xPoint -= sub;
            maxX = maxX - sub;
        }
        if (maxY > 10)
        {
            int sub = maxY - 10;
            yPoint -= sub;
            maxY = maxY - sub;
        }
        for (int i = xPoint; i < maxX; i++) {
            for (int j = yPoint; j < maxY; j++) {
                this.board[i][j] = selectedShip;
                points[k] = new ShipPoint(i,j);
                k++;
            }
        }

        this.battleFleet.shipsArray[selectedShip - 1].setLocation(points); //<- problem was in here! FIXED
        this.battleFleet.shipsArray[selectedShip-1].setStatus(true); //active/placed on board
    }

    public Boolean noOverlapping()
    {
        int count = 0;
        for(int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                if(this.board[i][j] != 0) count++;
            }
        }

        if (count == 21) return true; //no overlapping, test passed
        else return false; //overlapping ships, test failed
    }

    public Boolean allPlaced()
    {
        for(int i = 0; i < 5; i++)
        {
            if(!this.battleFleet.shipsArray[i].getStatus()) return false; //at least one ship isn't placed
        }
        return true; //all ships are placed
    }
} //end of StrategyBoard class