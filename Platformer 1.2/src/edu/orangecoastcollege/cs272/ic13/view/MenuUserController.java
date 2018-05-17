package edu.orangecoastcollege.cs272.ic13.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.orangecoastcollege.cs272.ic13.controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class MenuUserController implements Initializable  {
	
	    @FXML
	    private Label userName;
	    
	    
	   
	    public void setLabelText(String text)
	    {
	    	userName.setText(text);
	    }
	    
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
	    	ViewNavigator.loadScene("Leaderboard", ViewNavigator.LEADERBOARD_USER_SCENE);
	    }


	    @FXML
	    void loadProfile(ActionEvent event) {

	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			 Controller controller = Controller.getInstance();
			userName.setText("Welcome " + controller.getCurrentUser().getName() +"!");
		}

	}


