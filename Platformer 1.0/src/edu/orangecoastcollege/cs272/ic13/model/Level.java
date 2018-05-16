package edu.orangecoastcollege.cs272.ic13.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Level
{
    private int id;
    private String name, creator;
    private String[] map;
    private Point start = new Point();
    private Player playerObject;
    private Circle player;
    private List<Platform> platforms;
    private List<Enemy> enemies;
    private double rightBound = 600;
	private double leftBound = 0;
	private double upperBound = 0;
	private double lowerBound = 600;

    public Level(int id, String name, Point start, String[] map, String creator)
    {
        this.id = id;
        this.name = name;
        this.start = start;
        
        this.map = map;
        this.creator = creator;
        
    }

    public void loadLevel(Pane pane)
    {
    	pane.getChildren().clear();
    	playerObject = new Player(start.x, start.y, Color.WHITE);
    	player = playerObject.getVisualCircle();
        platforms = new ArrayList<>();
        enemies = new ArrayList<>();
    	
        Platform plat;
        Enemy enemy;
        for(int y = 0; y < map.length; y++)
        {
            for(int x = 0; x < map[y].length(); x++)
            {
                if (map[y].charAt(x) == 'p')
                {
                    plat = new Platform(x * 45, y * 45, 45, 45, Color.BLACK);
                    platforms.add(plat);
                    pane.getChildren().add(plat.getVisualRect());
                }
                else if (map[y].charAt(x) == 'e')
                {
                	enemy = new Enemy(x * 45, y * 45, Color.RED);
                	enemy.createEnemy();
                	enemies.add(enemy);
                	enemy.getVisual().forEach(node -> pane.getChildren().add(node));
                }
            }
        }
        pane.getChildren().add(player);
    }
    
    public void scroll(Pane map)
    {
    	
        double x = player.getCenterX() + player.getTranslateX();
        double y = player.getCenterY() + player.getTranslateY();
        if (rightBound < 42 * 45 && x > rightBound - 300 )
        {
            map.setLayoutX(map.getLayoutX() - 8);
            rightBound += 8;
            leftBound += 8;
        }
        if (leftBound > 0 && x < rightBound - 300)
        {
                map.setLayoutX(map.getLayoutX() + 8);
                rightBound -= 8;
                leftBound -=8;
        }
        if (upperBound > -200 && y < upperBound + 300)
        {
        	map.setLayoutY(map.getLayoutY() + 8);
        	upperBound -= 8;
        	lowerBound -= 8;
        }
        if (lowerBound < 600 && y > lowerBound - 300)
        {
        	map.setLayoutY(map.getLayoutY() - 8);
        	upperBound += 8;
        	lowerBound += 8;
        }
    }

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCreator() {
		return creator;
	}

	public String[] getMap() {
		return map;
	}

	public Point getStart() {
		return start;
	}

	public Circle getPlayer() {
		return player;
	}

	public List<Platform> getPlatforms() {
		return platforms;
	}

	public List<Enemy> getEnemies() {
		return enemies;
	}
	
	public void updateEnemies()
	{
		double x;
		int y;
		for (Enemy enemy : enemies)
		{
			x = enemy.getX() / 45 + 0.5;
			y = (int) (enemy.getY() / 45);
			if (map[y + 1].charAt((int) (x)) != 'p' || 
				map[y].charAt((int) (x - 0.5)) == 'p' || map[y].charAt((int)(x + 0.5)) == 'p')
					enemy.reverseVelocity();
			enemy.setX();
			enemy.moveVisual();
		}
	}
	
	public void updatePlayer(Pane pane, Scene scene)
	{
	    scroll(pane);
		playerObject.setMovingDown(!(playerObject.getVelocity().y < 0));
		intersectsX();
		scene.setOnKeyPressed(e -> move(e));
		scene.setOnKeyReleased(e -> stop(e));
		if (!intersectsX())
			translateX();
		if (!intersectsY())
			translateY();
		gravity();
	}
	
	private boolean intersectsX()
	{
		for (Platform plat : platforms)
		{
			if (plat.intersectsLeftBound(player.getBoundsInParent()))
			{
				player.setTranslateX(player.getTranslateX() - 1);
				playerObject.setX((int) player.getCenterX());
				playerObject.setY((int) player.getCenterY());
				playerObject.getVelocity().x = 0;
				playerObject.setCanGoRight(false);
				return true;
			}
			else if (plat.intersectsRightBound(player.getBoundsInParent()))
			{
				player.setTranslateX(player.getTranslateX() + 1);
				playerObject.setX((int) player.getCenterX());
				playerObject.setY((int) player.getCenterY());
				playerObject.getVelocity().x = 0;
				playerObject.setCanGoLeft(false);
				return true;
			}
		}
		return false;
	}

	private boolean intersectsY()
	{
		for (Platform plat : platforms)
		{
			if (plat.intersectsTopBound(player.getBoundsInParent()) && playerObject.isMovingDown())
			{
				player.setTranslateY(player.getTranslateY() - 1);
				playerObject.setX((int) player.getCenterX());
				playerObject.setY((int) player.getCenterY());
				playerObject.getVelocity().y = 0;
				playerObject.setJumpable(true);
				return true;
			}
			else if (plat.intersectsBottomBound(player.getBoundsInParent()) && !playerObject.movingDown())
			{
					player.setTranslateY(player.getTranslateY() + 1);
					playerObject.setX((int) player.getCenterX());
					playerObject.setY((int) player.getCenterY());
					playerObject.getVelocity().y = 0;
					playerObject.setJumpable(false);
					return true;
			}
		}
		return false;
	}
	
	private void translateY() {
		player.setTranslateY(player.getTranslateY() + playerObject.getVelocity().y);
	}

	private void translateX() {
		player.setTranslateX(player.getTranslateX() + playerObject.getVelocity().x);
	}

	private void gravity()
	{
		int v = playerObject.getVelocity().y;
		if (!intersectsY())
		{
		    if (!intersectsX() && v != 0)
		    {
		        playerObject.setCanGoRight(true);
		        playerObject.setCanGoLeft(true);
		    }
			if (v < 10) playerObject.getVelocity().y++;
			player.setTranslateY(player.getTranslateY() + v);
			playerObject.setX((int) player.getCenterX());
			playerObject.setY((int) player.getCenterY());
		}
	}
	
	private void stop(KeyEvent e)
	{
		if (e.getCode() == KeyCode.SPACE)
		{
			playerObject.getVelocity().y = 0;
		}
		else if (e.getCode() == KeyCode.D && playerObject.getVelocity().x > 0)
			playerObject.getVelocity().x = 0;
		else if (e.getCode() == KeyCode.A && playerObject.getVelocity().x < 0)
			playerObject.getVelocity().x  = 0;
	}

	private void move(KeyEvent e) {
		if (playerObject.isGoingRight())
			playerObject.setCanGoLeft(true);
		else playerObject.setCanGoRight(true);
		if (e.getCode() == KeyCode.SPACE && playerObject.isJumpable())
		{
			playerObject.setCanGoRight(true);
			playerObject.setCanGoLeft(true);
			playerObject.getVelocity().y = -11;
			playerObject.setJumpable(false);
			playerObject.setMovingDown(false);
		}
		if (e.getCode() == KeyCode.D && playerObject.canGoRight())
		{
			playerObject.setGoingRight(true);
			playerObject.getVelocity().x = 8;
		}
		if (e.getCode() == KeyCode.A && playerObject.canGoLeft())
		{
			playerObject.setGoingRight(false);
			playerObject.getVelocity().x = -8;
		}
	}
}
