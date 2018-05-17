package edu.orangecoastcollege.cs272.ic13.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ViewNavigator {
	public static final String LEADERBOARD_USER_SCENE = "LeaderboardUser.fxml";
	public static final String LEADERBOARD_USER_ENEMY_SCENE = "LeaderboardUserEnemies.fxml";
	public static final String LEADERBOARD_USER_CLEAR_SCENE = "LeaderboardUserClear.fxml";
	public static final String LEADERBOARD_SCENE = "Leaderboard.fxml";
	public static final String LEADERBOARD_ENEMY_SCENE = "LeaderboardEnemies.fxml";
	public static final String LEADERBOARD_CLEAR_SCENE = "LeaderboardClear.fxml";
	public static final String MENU_SCENE = "Menu.fxml";
	public static final String MENU_USER_SCENE = "MenuUser.fxml";
	public static final String SIGN_IN_SCENE = "SignIn.fxml";
	public static final String SIGN_UP_SCENE = "SignUp.fxml";
    public static final String LEVEL_SCENE = "LevelScene.fxml";


    public static Stage mainStage;

    public static void setStage(Stage stage) {
        mainStage = stage;
    }

    public static Scene loadScene(String title, String sceneFXML) {

        try {
            mainStage.setTitle(title);
            Scene scene = new Scene(FXMLLoader.load(ViewNavigator.class.getResource(sceneFXML)));
            mainStage.setScene(scene);
            mainStage.show();
            return mainStage.getScene();
        } catch (IOException e) {
            System.err.println("Error loading: " + sceneFXML + "\n" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
