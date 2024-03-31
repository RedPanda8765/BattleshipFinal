package com.ijjy.battleshipgame;

public class Ship {
    private int height;
    private int width;
    private ShipPoint[] location;
    private Boolean shipStatus; //true = active, false = sunk
    private String shipName;
    private int hitCapacity;
    private int currentHitCount;

    public Ship(int height, int width, String shipName)
    {
        this.height = height;
        this.width = width;
        for (ShipPoint point : this.location)
            point = new ShipPoint();
        this.shipStatus = true;
        this.shipName = shipName;
        this.hitCapacity = height * width;
        this.currentHitCount = 0;
    }

    public Boolean getStatus()
    {
        if(this.currentHitCount == this.hitCapacity) this.shipStatus = false; //sunk
        else this.shipStatus = true; //still active

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

    public int setCurrentHitCount()
    {
        return this.currentHitCount;
    }

    public double getPrcntDmge()
    {
        double percentDamage = (double)this.currentHitCount/this.hitCapacity;
        return percentDamage;
    }

    @Override
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

/*
    private int dimX;
	private int dimY;
	private ShipPoint[] location;
	private Boolean shipStatus;
	private String shipName;
	private int hitCapacity;
	private int currentHitCount;

	public Ship()
	{
		this.dimX = 0;
		this.dimY = 0;
		for (ShipPoint point : location)
			point = new ShipPoint();
		this.shipStatus = true;
		this.shipName = null;
		this.hitCapacity = 0;
		this.currentHitCount = 0;
	}

	public Ship(int dimX, int dimY, String shipName)
	{
		this.dimX = dimX;
		this.dimY = dimY;
		for (ShipPoint point : location)
			point = new ShipPoint();
		this.shipStatus = true;
		this.shipName = shipName;
		this.hitCapacity = dimX * dimY;
		this.currentHitCount = 0;
	}

	public void setHeight(int x)
	{
		this.dimX = x;
	}

	public void setWidth(int y)
	{
		this.dimY = y;
	}

	public int getDimX()
	{
		return this.dimX;
	}

	public int getDimY()
	{
		return this.dimY;
	}

	public void setLocation(ShipPoint[] points)
	{
 		int i = 0;
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

	public Boolean getStatus()
	{
		return this.shipStatus;
	}

	public void setHitCapacity(int max)
	{
		this.hitCapacity = max;
	}

	public int getCurrentHitCount()
	{
		return this.currentHitCount;
	}

	public double getPrcntDmge()
	{
		double percentDamage = (double)this.currentHitCount/this.hitCapacity;
		return percentDamage;
	}

	public void toString()
	{
		String result = this.shipName + "\'s Info:-------\nDimensions: " + Integer.toString(this.dimX) + "X" + Integer.toString(this.dimY) + "\nLocation:";

		for(int i = 0; i < this.dimX*this.dimY; i++)
		{
			result += " (" + Integer.toString(this.location[i].x) + ", " + Integer.toString(this.location[i].y) + ")";
		}

		if(shipStatus) result += " Ship Status: Active\n"
		else result += " Ship Status: Sunk\n"

		result += "Damage Capacity: " + this.hitCapacity + "\nCurrent Hits: " + this.currentHitCount;

		return result;
	}

} //end of Ship class

 */