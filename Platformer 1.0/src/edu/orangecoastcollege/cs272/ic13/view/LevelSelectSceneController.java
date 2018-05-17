package edu.orangecoastcollege.cs272.ic13.view;

import edu.orangecoastcollege.cs272.ic13.controller.Controller;

public class LevelSelectSceneController {

	public void level1()
	{
		Controller.getInstance().setScene(ViewNavigator.loadScene("Level 1", ViewNavigator.LEVEL_SCENE));
	}
}
