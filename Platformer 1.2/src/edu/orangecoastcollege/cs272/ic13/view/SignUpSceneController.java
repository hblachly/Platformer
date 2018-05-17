package edu.orangecoastcollege.cs272.ic13.view;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import edu.orangecoastcollege.cs272.ic13.controller.Controller;
import edu.orangecoastcollege.cs272.ic13.model.Level;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public class SignUpSceneController {
    private static Controller controller = Controller.getInstance();
    @FXML
    private TextField nameTF;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField passwordTF;

    @FXML
    private Label nameErrorLabel;

    @FXML
    private Label emailErrorLabel;

    @FXML
    private Label passwordErrorLabel;

    @FXML
    private Label signUpErrorLabel;


    
    @FXML
    void loadMenu(ActionEvent event) 
    {
    	ViewNavigator.loadScene("Menu", ViewNavigator.MENU_SCENE);
    }
    
    // Event Listener on Label.onMouseClicked
    @FXML
    public void signIn(MouseEvent event) {
        ViewNavigator.loadScene("Sign In", ViewNavigator.SIGN_IN_SCENE);
    }
    // Event Listener on Button.onAction
    @FXML
    public boolean signUp(ActionEvent event) {
        String name = nameTF.getText();
        String email = emailTF.getText();
        String password = passwordTF.getText();

        nameErrorLabel.setVisible(name.isEmpty());
        emailErrorLabel.setVisible(email.isEmpty());
        passwordErrorLabel.setVisible(password.isEmpty());

        if (nameErrorLabel.isVisible() || emailErrorLabel.isVisible() || passwordErrorLabel.isVisible())
            return false;
        String result = controller.signUpUser(name, email, password);
        if (!result.equals("SUCCESS"))
        {
            signUpErrorLabel.setText(result);
            signUpErrorLabel.setVisible(true);
            return false;
        }
        ViewNavigator.loadScene("Sign In", ViewNavigator.SIGN_IN_SCENE);
        return true;
    }
}