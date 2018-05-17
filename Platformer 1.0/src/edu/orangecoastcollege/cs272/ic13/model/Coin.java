package edu.orangecoastcollege.cs272.ic13.model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class Coin {
	private List<Node> coin = new ArrayList<>();
	private int x;
	private int y;
	
	public Coin(int x, int y)
	{
		this.x = x;
		this.y = y;
		createCoin(x, y);
	}
	
	private void createCoin(int x, int y)
	{
		Circle body = new Circle(x - 25, y - 25, 12.5);
		body.setFill(Color.YELLOW);
		body.setStroke(Color.ORANGE);
		coin.add(body);
		Label label = new Label("$");
		label.setTextFill(Color.BLACK);
		label.setFont(Font.font(15));
		label.setLayoutX(x - 29);
		label.setLayoutY(y - 35);
		coin.add(label);
	}
	
	public List<Node> getVisual()
	{
		return coin;
	}
}
