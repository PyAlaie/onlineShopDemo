package com.example.onlineshopdemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUp implements Initializable {

    @FXML
    private TextField usernameTextField, passwordTextField, emailTextField;

    @FXML
    private RadioButton costumerRadioButton, sellerRadioButton;
    @FXML
    private Label warningLabel;
    @FXML
                    private AnchorPane mainContainer;

    public void signUp(ActionEvent e){
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String email = emailTextField.getText();
        if(OnlineShop.isBlank(username) || OnlineShop.isBlank(password) || OnlineShop.isBlank(email)){
            return;
        }
        if(sellerRadioButton.isSelected()){
            if(!OnlineShop.doesSellerExist(username)){
                OnlineShop.addSeller(new Seller(username, password, email));
                closeWindow(e);
            }
            else {
                warningLabel.setText("Username already exists!");
            }
        }
        else if(costumerRadioButton.isSelected()){
            if(!OnlineShop.doesCostumerExist(username)){
                OnlineShop.addCostumer(new Costumer(username,password,email));
                closeWindow(e);
            }
            else {
                warningLabel.setText("Username already exists!");
            }
        }
    }

    public void closeWindow(ActionEvent actionEvent){
        Node  source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainContainer.getStyleClass().add("AnchorPane");
    }
}
