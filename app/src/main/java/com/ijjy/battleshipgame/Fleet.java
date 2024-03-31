package com.ijjy.battleshipgame;

public class Fleet{
    public Ship[] shipsArray; //making it public for easier access
    public final String[] shipNames = {"Aircraft Carrier","Cruiser","Submarine","Corvette","Destroyer"};
    public final int[] shipDimens = {4,2,3,1,3,1,2,1,5,1};
    public Boolean fleetStatus;

    public Fleet()
    {
        int j = 0;
        for(int i = 0; i < 5; i++) //for the 5 ships in the fleet
        {
            shipsArray[i] = new Ship(shipDimens[i],shipDimens[i++],shipNames[j]);
            i+=2;
            j++;
        }
        this.fleetStatus = true; //initially active
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
