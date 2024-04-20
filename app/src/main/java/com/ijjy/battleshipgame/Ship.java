package com.ijjy.battleshipgame;

import androidx.annotation.NonNull;

public class Ship {
    private int height;
    private int width;
    public ShipPoint[] location;
    private Boolean shipStatus; //true = active, false = inactive or sunk
    private String shipName;
    private int hitCapacity;
    private int currentHitCount;

    public Ship(int height, int width, String shipName)
    {
        this.height = height;
        this.width = width;
        this.location = new ShipPoint[height*width]; //****CHANGE: added this line (TRANSLATED)
        for(int i = 0; i < height*width; i++)
        {
            this.location[i] = new ShipPoint();
        }
        this.shipStatus = false; //initially inactive (because ship isn't placed)
        this.shipName = shipName;
        this.hitCapacity = height * width;
        this.currentHitCount = 0;
    }

    public Boolean getStatus()
    {
        return this.shipStatus;
    }

    public void setStatus(Boolean status)
    {
        this.shipStatus = status;
    }

    public void setHeight(int x)
    {
        this.height = x;
    }

    public void setWidth(int y)
    {
        this.width = y;
    }

    public int getHeight()
    {
        return this.height;
    }

    public int getWidth()
    {
        return this.width;
    }

    public void setLocation(ShipPoint[] points)
    {
        int i = 0;
        /* APP CRASHES IF CODE BELOW REPLACES LINES 80 - 84..why?
        for(ShipPoint point : points)
        {
            this.location[i].x = point.x;
            this.location[i].y = point.y;
            i++;
        }
         */

        for(ShipPoint point : points)
        {
            this.location[i] = point;
            i++;
        }
    }

    public ShipPoint[] getLocation()
    {
        return this.location;
    }

    public int getCurrentHitCount()
    {
        return this.currentHitCount;
    }

    //increments ship's current hit count and updates ship's status
    public void shipDealtHit()
    {
        this.currentHitCount += 1;
        if(this.currentHitCount == this.hitCapacity) this.shipStatus = false; //sunk
    }

    public double getPrcntDmge()
    {
        return (double)this.currentHitCount/this.hitCapacity; //****CHANGE: removed double declaration (TRANSLATED)
    }

    @NonNull
    public String toString()
    {
        String result = this.shipName + "\'s Info:-------\nDimensions: " + Integer.toString(this.height) + "X" + Integer.toString(this.width) + "\nLocation:";

        for(int i = 0; i < this.height*this.width; i++)
        {
            result += " (" + Integer.toString(this.location[i].x) + ", " + Integer.toString(this.location[i].y) + ")";
        }

        if(shipStatus) result += " Ship Status: Active\n";
        else result += " Ship Status: Sunk\n";

        result += "Damage Capacity: " + this.hitCapacity + "\nCurrent Hits: " + this.currentHitCount;

        return result;
    }


}