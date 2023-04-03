package com.example.onlineshopdemo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminPanel implements Initializable {
    @FXML
    private VBox mainVbox;
    @FXML
    private Label statusLabel;
    public void showSellers(ActionEvent e){
        mainVbox.getChildren().clear();
        for(Seller s : OnlineShop.getSellers()){
            Button l = new Button();
            l.setFont(new Font(18));
            l.setPrefHeight(35);
            l.setPrefWidth(Double.POSITIVE_INFINITY);
            l.setText(s.getUsername()+ ", Authorized: " + s.isAuth());

            l.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    ButtonType yes = new ButtonType("Authorize", ButtonBar.ButtonData.YES);
                    ButtonType no = new ButtonType("Unauthorize", ButtonBar.ButtonData.NO);

                    Alert alert = new Alert(Alert.AlertType.WARNING,s.toString() ,yes,no);

                    alert.setTitle("Seller Details");

                    alert.setHeaderText("Do you want to authorize this seller?");

                    alert.getButtonTypes().add(ButtonType.CLOSE);
                    alert.showAndWait().ifPresent(rs -> {
                        if (rs == yes) {
                            s.authorize();
                            showSellers(e);
                        }
                        else if(rs == no){
                            s.unauthorize();
                            showSellers(e);
                        }
                    });
                }
            });

            if(!s.isChecked()){
                l.getStyleClass().add("yellow");
            }
            else {
                if(s.isAuth()){
                    l.getStyleClass().add("green");
                }
                else {
                    l.getStyleClass().add("red");
                }
            }
            mainVbox.getChildren().add(l);
        }
    }
    public void showFundRequests(ActionEvent e){
        mainVbox.getChildren().clear();
        for(FundRequest fundRequest : OnlineShop.getFundRequests()){
            Button l = new Button();
            l.setFont(new Font(18));
            l.setPrefHeight(35);
            l.setPrefWidth(Double.POSITIVE_INFINITY);
            l.setText("Request from " + fundRequest.getCostumer() + " for " + fundRequest.getAmount() + " amount of money");

            l.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    ButtonType yes = new ButtonType("Confirm", ButtonBar.ButtonData.YES);
                    ButtonType no = new ButtonType("Deny", ButtonBar.ButtonData.NO);

                    Alert alert = new Alert(Alert.AlertType.WARNING,fundRequest.toString() ,no,yes);

                    alert.setTitle("Request Details");

                    alert.setHeaderText("Do you confirm this fund request?");

                    alert.getButtonTypes().add(ButtonType.CLOSE);
                    alert.showAndWait().ifPresent(rs -> {
                        if (rs == yes) {
                            fundRequest.confirm();
                            showFundRequests(e);
                        }
                        else if(rs == no){
                            fundRequest.deny();
                            showFundRequests(e);
                        }
                    });
                }
            });

            if(!fundRequest.isChecked()){
                l.getStyleClass().add("yellow");
            }
            else {
                l.setDisable(true);
                if(fundRequest.isConfirmed()){
                    l.getStyleClass().add("green");
                }
                else {
                    l.getStyleClass().add("red");
                }
            }
            mainVbox.getChildren().add(l);
        }
    }
    public void showOrders(ActionEvent e){
        mainVbox.getChildren().clear();
        for(Order order : OnlineShop.getOrders()){
            Button l = new Button();
            l.setFont(new Font(18));
            l.setPrefHeight(35);
            l.setPrefWidth(Double.POSITIVE_INFINITY);
            l.setText("Order from " + order.getCostumer() + " for total price of " + order.getTotalPrice());

            l.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    ButtonType yes = new ButtonType("Confirm", ButtonBar.ButtonData.YES);
                    ButtonType no = new ButtonType("Deny", ButtonBar.ButtonData.NO);

                    Alert alert = new Alert(Alert.AlertType.WARNING,order.toString() ,no,yes);

                    alert.setTitle("Order Details");

                    alert.setHeaderText("Do you confirm this order?");

                    alert.getButtonTypes().add(ButtonType.CLOSE);
                    alert.showAndWait().ifPresent(rs -> {
                        if (rs == yes) {
                            order.confirm();
                            showOrders(e);
                        }
                        else if(rs == no){
                            order.deny();
                            showOrders(e);
                        }
                    });
                }
            });

            if(!order.isChecked()){
                l.getStyleClass().add("yellow");
            }
            else {
                l.setDisable(true);
                if(order.isConfirmed()){
                    l.getStyleClass().add("green");
                }
                else {
                    l.getStyleClass().add("red");
                }
            }
            mainVbox.getChildren().add(l);
        }
    }

    public void showProducts(ActionEvent e){
        mainVbox.getChildren().clear();
        for(Product product : OnlineShop.getProducts()){
            Button l = new Button();
            l.setFont(new Font(18));
            l.setPrefHeight(35);
            l.setPrefWidth(Double.POSITIVE_INFINITY);

            l.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    Alert alert = new Alert(Alert.AlertType.WARNING,product.toString());

                    alert.setTitle("Product Details");

                    alert.setHeaderText("Product Details");
                    alert.show();
                }
            });
            l.setText(product.toString());
            mainVbox.getChildren().add(l);
        }
    }

    public void addAdmin(){
        mainVbox.getChildren().clear();
        TextField username = new TextField();
        PasswordField password = new PasswordField();
        TextField email = new TextField();

        Button add = new Button("Add");

        username.setPromptText("Username");
        password.setPromptText("Password");
        email.setPromptText("Email");

        username.setPrefHeight(55);
        username.setPrefWidth(Double.POSITIVE_INFINITY);

        password.setPrefHeight(55);
        password.setPadding(new Insets(10));
        password.setPrefWidth(Double.POSITIVE_INFINITY);

        email.setPrefHeight(55);
        email.setPadding(new Insets(10));
        email.setPrefWidth(Double.POSITIVE_INFINITY);

        add.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(!OnlineShop.doesAdminExist(username.getText()) && !username.getText().equals("")){
                    Admin admin = new Admin(username.getText(),password.getText(),email.getText());
                    OnlineShop.addAdmin(admin);
                    username.setText("");
                    password.setText("");
                    email.setText("");
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Admin Added");
                    a.show();
                }
            }
        });

        mainVbox.getChildren().add(username);
        mainVbox.getChildren().add(password);
        mainVbox.getChildren().add(email);
        mainVbox.getChildren().add(add);
    }

    public void logoutBtnHit(ActionEvent event) throws IOException {
        OnlineShop.logoutAdmin();
        closeWindow(event);
        openStage("loginRegister.fxml");
    }

    public void closeWindow(ActionEvent actionEvent){
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void openStage(String stageName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(stageName));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());
        stage.setTitle(OnlineShop.getName());
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.statusLabel.setText("Hello " + OnlineShop.getLoggedInUser().getUsername());
    }

    public void profileView(ActionEvent e){
        mainVbox.getChildren().clear();
        TextField username = new TextField(OnlineShop.getLoggedInUser().getUsername());
        PasswordField password = new PasswordField();
        TextField email = new TextField(OnlineShop.getLoggedInUser().getEmail());

        Button add = new Button("Apply changes");

        username.setPromptText("Username");
        password.setPromptText("Password");
        email.setPromptText("Email");

        username.setPrefHeight(55);
        username.setPrefWidth(Double.POSITIVE_INFINITY);

        password.setPrefHeight(55);
        password.setPadding(new Insets(10));
        password.setPrefWidth(Double.POSITIVE_INFINITY);

        email.setPrefHeight(55);
        email.setPadding(new Insets(10));
        email.setPrefWidth(Double.POSITIVE_INFINITY);

        password.setText(OnlineShop.getLoggedInUser().getPassword());

        add.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                OnlineShop.getLoggedInUser().setUsername(username.getText());
                OnlineShop.getLoggedInUser().setPassword(password.getText());
                OnlineShop.getLoggedInUser().setEmail(email.getText());

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Profile Updated");
                alert.show();
            }
        });

        mainVbox.getChildren().add(username);
        mainVbox.getChildren().add(password);
        mainVbox.getChildren().add(email);
        mainVbox.getChildren().add(add);
    }
}

