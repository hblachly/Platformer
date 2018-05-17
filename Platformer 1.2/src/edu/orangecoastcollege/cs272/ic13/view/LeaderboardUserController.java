package edu.orangecoastcollege.cs272.ic13.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class LeaderboardUserController 
{
	

	    @FXML
	    private Label coinOne;

	    @FXML
	    private Label coinTwo;

	    @FXML
	    private Label coinThree;

	    @FXML
	    private Label coinFour;

	    @FXML
	    private Label coinFive;

	    @FXML
	    private Label coinSix;

	    @FXML
	    private Label coinSeven;

	    @FXML
	    private Label coinEight;

	    @FXML
	    private Label coinNine;

	    @FXML
	    private Label coinTen;

	    @FXML
	    private Label cnOne;

	    @FXML
	    private Label cnTwo;

	    @FXML
	    private Label cnThree;

	    @FXML
	    private Label cnFour;

	    @FXML
	    private Label cnFive;

	    @FXML
	    private Label cnSix;

	    @FXML
	    private Label cnSeven;

	    @FXML
	    private Label cnEight;

	    @FXML
	    private Label cnNine;

	    @FXML
	    private Label cnTen;

	    @FXML
	    void loadClearBoard(MouseEvent event) 
	    {
	    	ViewNavigator.loadScene("Leaderboard", ViewNavigator.LEADERBOARD_USER_CLEAR_SCENE);
	    }

	    @FXML
	    void loadCoinBoard(MouseEvent event) 
	    {	
	    	ViewNavigator.loadScene("Leaderboard", ViewNavigator.LEADERBOARD_USER_SCENE);
	    }

	    @FXML
	    void loadEnemyBoard(MouseEvent event) 
	    {
	    	ViewNavigator.loadScene("Leaderboard", ViewNavigator.LEADERBOARD_USER_ENEMY_SCENE);
	    }

	    @FXML
	    void loadStartScreen(ActionEvent event) 
	    {
	    	ViewNavigator.loadScene("Menu", ViewNavigator.MENU_USER_SCENE);
	    }


	}


