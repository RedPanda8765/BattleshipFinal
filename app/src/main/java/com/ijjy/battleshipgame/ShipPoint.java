package com.ijjy.battleshipgame;

public class ShipPoint {
    public int x;
    public int y;

    public ShipPoint()
    {
        this.x = 0;
        this.y = 0;
    }

    public ShipPoint(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void setShipPointLocation(int xpoint, int ypoint)
    {
        this.x = xpoint;
        this.y = ypoint;
    }
}