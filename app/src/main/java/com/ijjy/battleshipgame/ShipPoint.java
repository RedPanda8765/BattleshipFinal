package com.ijjy.battleshipgame;

public class ShipPoint {
    public int x;
    public int y;
    public Boolean hitStatus; //true means hit, false mean not hit

    public ShipPoint()
    {
        this.x = 0;
        this.y = 0;
        this.hitStatus = false;
    }

    public ShipPoint(int x, int y, Boolean status)
    {
        this.x = x;
        this.y = y;
        this.hitStatus = status;
    }
    public void setShipPointStatus(Boolean status)
    {
        this.hitStatus=status;
    }

    public void setShipPointLocation(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}
