package platformer;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Demo extends Application {
	private Circle player = new Circle(67.5, 382.5, 22.5);
	private Rectangle background = new Rectangle(0, 0, 42 * 45, 450);
	private boolean jumpable;
	private boolean goingRight;
	private boolean movingDown;
	private boolean canGoRight = true;
	private boolean canGoLeft = true;
	private double rightBound = 600;
	private double leftBound = 0;
	private double upperBound = 0;
	private double lowerBound = 600;
	Point velocity = new Point();
	private Pane map = new Pane();
	List<Platform> platforms = new ArrayList<>();

	private Button startButton = new Button("Start");
	private Pane startPane = new Pane();
	private Label title = new Label(" My First \nPlatformer");
	Scene scene = new Scene(startPane, 600, 450);

    public static void main(String[] args) {

        launch(args);
    }
	@Override
	public void start(Stage primaryStage) throws Exception {
		map.getChildren().add(background);
		startPane.getChildren().add(title);
	    startPane.getChildren().add(startButton);
	    title.setFont(Font.font(30));
	    title.setEffect(new DropShadow(4.0, 2.0f, 2.0f, Color.BLACK));
	    title.setTranslateX(240);
	    title.setTranslateY(50);
	    
	    startButton.setPrefWidth(100);
	    startButton.setPrefHeight(50);
	    startButton.setTranslateX(250);
	    startButton.setTranslateY(300);
		jumpable = true;
		map.getChildren().add(player);
		player.setFill(Color.WHITE);
		player.setStroke(Color.BLACK);
		background.setFill(Color.LIGHTBLUE);
		background.setY(-200);
		background.setHeight(background.getHeight() + 200);
		loadPlatforms();
		addPlatforms();

		primaryStage.setTitle("Platformer");
		

        AnimationTimer timer = new AnimationTimer() {
        	public void handle(long now)
        	{
        		
        	    scroll();
        		movingDown = !(velocity.y < 0);
        		intersectsX();
        		scene.setOnKeyPressed(e -> move(e));
        		if (!intersectsX())
        			translateX();
        		if (!intersectsY())
        			translateY();
        		gravity();
        	}
        };
        startButton.setOnAction(e -> {scene.setRoot(map); timer.start();});
        scene.setOnKeyReleased(e -> stop(e));
        primaryStage.setScene(scene);
        primaryStage.show();
	}

	private void stop(KeyEvent e)
	{
		if (e.getCode() == KeyCode.SPACE)
		{
			velocity.y = 0;
		}
		else if (e.getCode() == KeyCode.D && velocity.x > 0)
			velocity.x = 0;
		else if (e.getCode() == KeyCode.A && velocity.x < 0)
			velocity.x  = 0;
	}

	private void move(KeyEvent e) {
		if (goingRight)
			canGoLeft = true;
		else canGoRight = true;
		if (e.getCode() == KeyCode.SPACE && jumpable)
		{
			canGoRight = true;
			canGoLeft = true;
			velocity.y = -11;
			jumpable = false;
			movingDown = false;
		}
		if (e.getCode() == KeyCode.D && canGoRight)
		{
			goingRight = true;
		    velocity.x = 8;
		}
		if (e.getCode() == KeyCode.A && canGoLeft)
		{
			goingRight = false;
		    velocity.x = -8;
		}
	}

	private void translateY() {
		player.setTranslateY(player.getTranslateY() + velocity.y);
	}

	private void translateX() {
		player.setTranslateX(player.getTranslateX() + velocity.x);
	}

	private void gravity()
	{
		int v = velocity.y;
		if (!intersectsY())
		{
		    if (!intersectsX() && v != 0)
		    {
		        canGoRight = true;
		        canGoLeft = true;
		    }
			if (v < 10) velocity.y++;
			player.setTranslateY(player.getTranslateY() + v);
		}
	}

	private void loadPlatforms()
	{
        try (Scanner in = new Scanner(new File("map.txt")))
        {
	    int y = 0;
	    while (in.hasNextLine())
	    {
	        String s = in.nextLine();
	        for (int i = 0; i < s.length(); i++)
	        {
	            if (s.charAt(i) == '1')
	            {
	                platforms.add(new Platform(i * 45, y * 45, 45, 45, Color.BLACK));
	            }
	        }
	        y++;
	    }
	    }
	    catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

	private void addPlatforms()
	{
	    for (Platform plat : platforms)
	    {
	        map.getChildren().add(plat.getVisualRect());
	    }
	}

	private boolean intersectsX()
	{
		for (Platform plat : platforms)
		{
			if (plat.intersectsLeftBound(player.getBoundsInParent()))
			{
				player.setTranslateX(player.getTranslateX() - 1);
				velocity.x = 0;
				canGoRight = false;
				return true;
			}
			else if (plat.intersectsRightBound(player.getBoundsInParent()))
			{
				player.setTranslateX(player.getTranslateX() + 1);
				velocity.x = 0;
				canGoLeft = false;
				return true;
			}
		}
		return false;
	}

	private boolean intersectsY()
	{
		for (Platform plat : platforms)
		{
			if (plat.intersectsTopBound(player.getBoundsInParent()) && movingDown)
			{
				player.setTranslateY(player.getTranslateY() - 1);
				velocity.y = 0;
				jumpable = true;
				return true;
			}
			else if (plat.intersectsBottomBound(player.getBoundsInParent()) && !movingDown)
			{
					player.setTranslateY(player.getTranslateY() + 1);
					velocity.y = 0;
					jumpable = false;
					return true;
			}
		}
		return false;
	}

	private void scroll()
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
}