package edu.orangecoastcollege.cs272.ic13.model;

import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Platform {
    private Rectangle visualRect;
    private double x;
    private double y;
    private double width;
    private double height;
    private Color color;

    public Platform(double x, double y, double width, double height, Color color)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        setVisualRect();
    }

    public Rectangle getVisualRect() {
        return visualRect;
    }

    private void setVisualRect() {
        visualRect = new Rectangle(x, y, width, height);
        visualRect.setFill(color);
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

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean intersectsLeftBound(Bounds b)
    {
        return b.intersects(x, y + height / 5, width / 9, height * 0.7);
    }
    public boolean intersectsRightBound(Bounds b)
    {
        return b.intersects(x + 8 * width / 9, y + height / 5, width / 9, height * 0.7);
    }

    public boolean intersectsTopBound(Bounds b)
    {
        return b.intersects(x + width / 10, y - 1, width * 0.7, height / 9);
    }

    public boolean intersectsBottomBound(Bounds b)
    {
        return b.intersects(x + width / 10, y + height + 1, width * 0.7, height / 9);
    }
}
