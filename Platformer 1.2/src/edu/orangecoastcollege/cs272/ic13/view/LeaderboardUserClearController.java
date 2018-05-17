package edu.orangecoastcollege.cs272.ic13.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class LeaderboardUserClearController {


	    @FXML
	    private Label clear1;

	    @FXML
	    private Label clear2;

	    @FXML
	    private Label clear3;

	    @FXML
	    private Label clear4;

	    @FXML
	    private Label clear5;

	    @FXML
	    private Label clear6;

	    @FXML
	    private Label clear7;

	    @FXML
	    private Label clear8;

	    @FXML
	    private Label clear9;

	    @FXML
	    private Label clear10;

	    @FXML
	    private Label clearN1;

	    @FXML
	    private Label clearN2;

	    @FXML
	    private Label clearN3;

	    @FXML
	    private Label clearN4;

	    @FXML
	    private Label clearN5;

	    @FXML
	    private Label clearN6;

	    @FXML
	    private Label clearN7;

	    @FXML
	    private Label clearN8;

	    @FXML
	    private Label clearN9;

	    @FXML
	    private Label clearN10;

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


