package edu.orangecoastcollege.cs272.ic13.model;

import java.awt.Point;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Player {
	private Circle visualCircle;
	private int x;
	private int y;
	private Point velocity;
	private boolean jumpable = true;
	private boolean goingRight = true;
	private boolean movingDown = false;
	private boolean canGoRight = true;
	private boolean canGoLeft = true;
	
	Player(int x, int y, Color color)
	{
		visualCircle = new Circle(x, y, 22.5, color);
		visualCircle.setStroke(Color.BLACK);
		velocity = new Point(0, 0);
		this.x = x;
		this.y = y;
	}
	
	public Circle getVisualCircle()
	{
		return visualCircle;
	}
	
	public boolean movingDown()
	{
		return movingDown;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Point getVelocity() {
		return velocity;
	}

	public void setVelocity(Point velocity) {
		this.velocity = velocity;
	}

	public boolean isJumpable() {
		return jumpable;
	}

	public void setJumpable(boolean jumpable) {
		this.jumpable = jumpable;
	}

	public boolean isGoingRight() {
		return goingRight;
	}

	public void setGoingRight(boolean goingRight) {
		this.goingRight = goingRight;
	}

	public boolean isMovingDown() {
		return movingDown;
	}

	public void setMovingDown(boolean movingDown) {
		this.movingDown = movingDown;
	}

	public boolean canGoRight() {
		return canGoRight;
	}

	public void setCanGoRight(boolean canGoRight) {
		this.canGoRight = canGoRight;
	}

	public boolean canGoLeft() {
		return canGoLeft;
	}

	public void setCanGoLeft(boolean canGoLeft) {
		this.canGoLeft = canGoLeft;
	}

	public void setVisualCircle(Circle visualCircle) {
		this.visualCircle = visualCircle;
	}
}
