package edu.orangecoastcollege.cs272.ic13.model;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class Enemy
{
	private List<Node> visual;
	private Rectangle body;
	private List<Polygon> spikes;
	private double x;
	private double y;
	private Color color;
	private int velocity;
	
	public Enemy(double x, double y, Color color)
	{
		this.x = x;
		this.y = y;
		this.color = color;
		velocity = -1;
		visual = new ArrayList<>();
	}
	
	public void createEnemy()
	{
		body = new Rectangle(25, 45, color);
		//spikes = new ArrayList<>();
		body.setX(x + 10);
		body.setY(y);
		visual.add(body);
		Polygon spike;
		double distance = 0;
		for (int i = 0; i < 6; i++)
		{
			if (i > 2)
			{
				distance = (i - 3) * 16.5;
				spike = new Polygon(x + 35.0, y + distance + 1, x + 35.0, y + 10.0 + distance + 1,
						x + 45.0, y + 5.0 + distance + 1);
				spike.setFill(Color.GRAY);
				spike.setStroke(Color.BLACK);
				visual.add(spike);
			}
			else
			{
				distance = i * 16.5;
				spike = new Polygon(x + 10.0, y + distance + 1, x + 10.0, y + 10 + distance + 1,
						x + 0.0, y + 5 + distance + 1);
				spike.setFill(Color.GRAY);
				spike.setStroke(Color.BLACK);
				visual.add(spike);
			}
		}
	}
	
	public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    public boolean onTop(Bounds b)
    {
        return b.intersects(x + 15, y - 45, 15, 5);
    }
    
    public void setX()
    {
    	x += velocity;
    }
	
	public List<Node> getVisual()
	{
		return visual;
	}
	
	public void moveVisual()
	{
		visual.forEach(node -> node.setTranslateX(node.getTranslateX() + velocity));
	}
	
	public Rectangle getBody()
	{
		return body;
	}
	
	public List<Polygon> getSpikes()
	{
		return spikes;
	}
	
	public int getVelocity()
	{
		return velocity;
	}
	
	public void reverseVelocity()
	{
		velocity = -velocity;
	}
}