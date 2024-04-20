package com.ijjy.battleshipgame;
public class Fleet{
    public Ship[] shipsArray = new Ship[5]; //making it public for easier access
    public String[] shipNames = {"Aircraft Carrier","Cruiser","Submarine","Corvette","Destroyer"};
    public int[] shipDimens = {4,2,3,1,3,1,2,1,5,1};
    public Boolean fleetStatus; //true = active, false = inactive or all ships sunk

    public Fleet()
    {
        int i = 0;
        for(int j = 0; j < 5; j++) //for the 5 ships in the fleet
        {
            shipsArray[j] = new Ship(shipDimens[i],shipDimens[++i],shipNames[j]);
            i++;
        }
        this.fleetStatus = false; //initially inactive
    }

    public void updateStatus()
    {
        int count = 0; //counts number of sunk ships
        for(int i = 0; i < 5; i++) //for all 5 ships in the fleet
        {
            if(!this.shipsArray[i].getStatus()) count++;
        }

        //if all 5 ships are sunk (the entire fleet is down), set to false:
        if(count == 5) this.fleetStatus = false;
    }

}