package com.example.onlineshopdemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginRegister implements Initializable {

    @FXML
    private Label title;
    @FXML
    private TextField passwordTextField, usernameTextField;

    @FXML
    private AnchorPane mainContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.setText(OnlineShop.getName());
        mainContainer.getStyleClass().add("AnchorPane");
    }

    public void hitLoginButton(ActionEvent e) throws IOException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        if(OnlineShop.doesAdminExist(username) || OnlineShop.doesSellerExist(username) || OnlineShop.doesCostumerExist(username)){
            if(OnlineShop.authenticateAdmin(username,password)){
                OnlineShop.loginAdmin(username);
                closeWindow(e);
                goToStage("adminPanel.fxml");
            }
            else if(OnlineShop.authenticateCostumer(username,password)) {
                OnlineShop.loginCostumer(username);
                closeWindow(e);
                goToStage("costumerPanel.fxml");
            }
            else if(OnlineShop.authenticateSeller(username,password)){
                OnlineShop.loginSeller(username);
                if(OnlineShop.getLoggedInSeller().isAuth()){
                    closeWindow(e);
                    goToStage("sellerPanel.fxml");
                }
                else if(OnlineShop.getLoggedInSeller().isChecked()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("You are not authorized!");
                    alert.show();
                    OnlineShop.logoutSeller();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Your profile is not checked by the admins yet!");
                    alert.show();
                    OnlineShop.logoutSeller();
                }
            }
            else {
                title.setText("wrong password!");
            }
        }
        else{
            title.setText("Username does not exist!");
        }
    }

    public void goToSignUpPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("signUp.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());
        stage.setTitle(OnlineShop.getName());
        stage.setScene(scene);
        stage.show();
    }

    public void goToStage(String stageName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(stageName));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());
        stage.setTitle(OnlineShop.getName());
        stage.setScene(scene);
        stage.show();
    }

    public void closeWindow(ActionEvent actionEvent){
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
