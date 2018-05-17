package edu.orangecoastcollege.cs272.ic13.view;

import edu.orangecoastcollege.cs272.ic13.controller.Controller;
import edu.orangecoastcollege.cs272.ic13.model.Level;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainView extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Set stage only needs to be called once for the view navigator
        ViewNavigator.setStage(primaryStage);
        ViewNavigator.loadScene("Menu", ViewNavigator.MENU_SCENE);
    }

    public static void main(String[] args) {
        launch(args);
    }
}