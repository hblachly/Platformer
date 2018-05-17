package edu.orangecoastcollege.cs272.ic13.view;

import edu.orangecoastcollege.cs272.ic13.controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;


public class Menu {

	@FXML
	void loadEditor(ActionEvent event) 
	{

	}
	
    @FXML
    void loadGame(ActionEvent event) 
    {
    	 Controller controller = Controller.getInstance();
	    	controller.setScene(ViewNavigator.loadScene("Level 1", ViewNavigator.LEVEL_SCENE));
    }

    @FXML
    void loadLeaderboard(ActionEvent event) 
    {
    	ViewNavigator.loadScene("Leaderboard", ViewNavigator.LEADERBOARD_SCENE);
    }

    @FXML
    void loadProfile(ActionEvent event) {

    }

    @FXML
    void loadSignIn(MouseEvent event) 
    {
    	ViewNavigator.loadScene("Sign In", ViewNavigator.SIGN_IN_SCENE);
    }

    @FXML
    void loadSignUp(MouseEvent event) 
    {
    	ViewNavigator.loadScene("Sign Up", ViewNavigator.SIGN_UP_SCENE);
    }

}

