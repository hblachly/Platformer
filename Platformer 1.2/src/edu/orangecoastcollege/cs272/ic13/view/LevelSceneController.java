package edu.orangecoastcollege.cs272.ic13.view;

import edu.orangecoastcollege.cs272.ic13.controller.Controller;
import edu.orangecoastcollege.cs272.ic13.model.Level;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class LevelSceneController {
	private static Controller controller = Controller.getInstance();
	private Level level = controller.getAllLevels().get(0);
	private Pane currentPane;
	
	AnimationTimer timer = new AnimationTimer() {
    	public void handle(long now)
    	{
    		update();
    	}
	};
    
    public void start()
    {
    	controller.setPane();
    	currentPane = controller.getCurrentPane();
    	level.loadLevel(currentPane);
    	timer.start();
    }
    
    private void update()
    {
    	level.updateEnemies();
    	level.updatePlayer(currentPane, controller.getCurrentScene());
    }
}
