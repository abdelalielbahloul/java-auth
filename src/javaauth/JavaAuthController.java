/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaauth;

import Utlis.ConnectionDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 *
 * @author Abdelali-PC
 */
public class JavaAuthController implements Initializable {
    
    @FXML
    private Button btnLogin;
    @FXML
    private Label lblErrors;
    @FXML
    private TextField txtLogin;
    @FXML
    private PasswordField txtPassword;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnLogin) {
            // call to login method here
            Login();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    Connection cnx = ConnectionDB.getCnx();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    private void Login(){
        String login = txtLogin.getText().toString();
        String password = txtPassword.getText().toString();
        String query = "SELECT * FROM users where email = ? and mdp = ?";
        
        try {
            preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            
            if (!resultSet.next()) {
                lblErrors.setTextFill(Color.RED);
                lblErrors.setText("Email or password invalid");
            } else {
                lblErrors.setTextFill(Color.GREEN);
                lblErrors.setText("Login success");

            }

        } catch (Exception ex) {
            Logger.getLogger(JavaAuthController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
