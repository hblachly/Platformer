package edu.orangecoastcollege.cs272.ic13.view;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class LeaderboardEnemiesController {

    @FXML
    private Label enemy1;

    @FXML
    private Label enemy2;

    @FXML
    private Label enemy3;

    @FXML
    private Label enemy4;

    @FXML
    private Label enemy5;

    @FXML
    private Label enemy6;

    @FXML
    private Label enemy7;

    @FXML
    private Label enemy8;

    @FXML
    private Label enemy9;

    @FXML
    private Label enemy10;

    @FXML
    private Label en1;

    @FXML
    private Label en2;

    @FXML
    private Label en3;

    @FXML
    private Label en4;

    @FXML
    private Label en5;

    @FXML
    private Label en6;

    @FXML
    private Label en7;

    @FXML
    private Label en8;

    @FXML
    private Label en9;

    @FXML
    private Label en10;

    @FXML
    void loadClearBoard(MouseEvent event)
    {
    	ViewNavigator.loadScene("Leaderboard", ViewNavigator.LEADERBOARD_CLEAR_SCENE);
    }

    @FXML
    void loadCoinBoard(MouseEvent event)
    {
    	ViewNavigator.loadScene("Leaderboard", ViewNavigator.LEADERBOARD_SCENE);
    }

    @FXML
    void loadEnemyBoard(MouseEvent event) 
    {
    	ViewNavigator.loadScene("Leaderboard", ViewNavigator.LEADERBOARD_ENEMY_SCENE);
    }

    @FXML
    void loadStartScreen(ActionEvent event) 
    {
    	ViewNavigator.loadScene("Leaderboard", ViewNavigator.MENU_SCENE);
    }

}
