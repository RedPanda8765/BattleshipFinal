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
        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j < 10; j++)
                this.board[i][j] = 0;
        }
        //create fleet:
        this.battleFleet = new Fleet();
    }

    public void placeShip(int selectedShip, int xPoint, int yPoint) {
        int k = 0;
        int width = this.battleFleet.shipsArray[selectedShip - 1].getWidth();
        int height = this.battleFleet.shipsArray[selectedShip - 1].getHeight();
        ShipPoint[] points = new ShipPoint[height*width];
        int maxX = xPoint + height - 1;
        int maxY = yPoint + width - 1;

        if (maxX > 10) {
            int sub = maxX - 9;
            xPoint -= sub;
        } else if (maxY > 10) {
            int sub = maxY - 9;
            yPoint -= sub;
        }
        for (int i = 0; i < this.battleFleet.shipsArray[selectedShip - 1].getHeight(); i++) {
            for (int j = 0; j < this.battleFleet.shipsArray[selectedShip - 1].getWidth(); j++) {
                this.board[xPoint + i][yPoint + j] = selectedShip;
                points[k] = new ShipPoint();
                points[k].setShipPointLocation(xPoint + i, yPoint + j);
                //this.battleFleet.shipsArray[selectedShip-1].getLocation()[k].setShipPointLocation(xPoint+i,yPoint+j);
                k++;
            }
        }
        this.battleFleet.shipsArray[selectedShip - 1].setLocation(points);
    }

}
/*
public class StrategyBoard{
	private int[][] board;
	private Fleet battleFleet;

	public StrategyBoard()
	{
		initBoard();
		createFleet();
	}

	public void initBoard()
	{
		board = new int[10][10]; //creating 10x10 grid

		//java automatically fills int arrays with 0s upon creation,
		//but just in case, doing it here again.
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
				board[i][j] = 0;
		}
	}

	public void creatFleet()
	{
		this.battleFleet = new Fleet();
	}

	public Fleet getFleet()
	{
		return this.battleFleet;
	}

	public Boolean spotTaken(int x, int y) //for using before entering Rounds-stage
	{
		Boolean result = true;
		if (this.board[x][y] != 0) result = false;
		return result;
	}

	public Boolean withinGrid(int x, int y) //for using before entering Rounds-stage
	{
		Boolean result = false;
		if((x > 0) && (x < 10) && (y > 0) && (y < 10)) result = true;
		return result;
	}

	public Boolean checkFleetPositionValidity() //for using before entering Rounds-stage
	{
		Boolean result = true; //set to valid as default
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++) //for each point on the board in the fleet
			{
				if(this.board[i][j] == -1) //if there is a ship intersecting, the value of -1 is placed at that coordinate
				{
					result = false;
					break;
				}
			}
			if(!result) break; //breaking from outer for loop to stop further checking if a value of -1 is found at least once.
		}

		return result;
	}

} //end of StrategyBoard class

 */
