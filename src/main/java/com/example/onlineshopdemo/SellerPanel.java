package com.example.onlineshopdemo;

import javafx.collections.FXCollections;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.ResourceBundle;

public class SellerPanel implements Initializable {
    @FXML
    private Label statusLabel;

    @FXML
    private AnchorPane mainContainer;
    @FXML
    private Button notifBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        statusLabel.setText("Hello " + OnlineShop.getLoggedInUser().getUsername() +
                ", Your wallet balance is " + OnlineShop.getLoggedInSeller().getWallet().getBalance());
        notifBtn.setText("Notifications("+OnlineShop.getLoggedInSeller().getNotifications().size()+")");
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

    public void showProducts(ActionEvent e){
        mainContainer.getChildren().clear();

        VBox vbox = new VBox();
        AnchorPane.setTopAnchor(vbox,0.0);
        AnchorPane.setBottomAnchor(vbox,0.0);
        AnchorPane.setLeftAnchor(vbox,0.0);
        AnchorPane.setRightAnchor(vbox,0.0);

        for(Product product : OnlineShop.getLoggedInSeller().getProducts()) {
            Button l = new Button();
            l.setFont(new Font(18));
            l.setPrefHeight(35);
            l.setPrefWidth(Double.POSITIVE_INFINITY);

            l.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mainContainer.getChildren().clear();

                    VBox vbox = new VBox();
                    AnchorPane.setTopAnchor(vbox,0.0);
                    AnchorPane.setBottomAnchor(vbox,0.0);
                    AnchorPane.setLeftAnchor(vbox,0.0);
                    AnchorPane.setRightAnchor(vbox,0.0);

                    vbox.setSpacing(20.0);
                    vbox.setPadding(new Insets(30.0));

                    TextField name = new TextField(product.getName());
                    name.setPromptText("Name");

                    TextField price = new TextField(Integer.toString(product.getPrice()));
                    price.setPromptText("Price");

                    TextField count = new TextField(Integer.toString(product.getCount()));
                    count.setPromptText("Count");

                    TextArea description = new TextArea(product.desciption);
                    description.setPromptText("Description");

                    ComboBox category = new ComboBox(FXCollections.observableArrayList(OnlineShop.getCategories()));
                    category.setValue(product.getCategory().getCategory());
                    ComboBox subCategory = new ComboBox();
                    subCategory.setValue(product.getCategory());

                    category.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Category c = OnlineShop.getCategoryByTitle(category.getValue().toString());
                            subCategory.setItems(FXCollections.observableArrayList(OnlineShop.getSubCategories(c)));
                        }
                    });

                    vbox.getChildren().add(name);
                    vbox.getChildren().add(price);
                    vbox.getChildren().add(count);
                    vbox.getChildren().add(description);
                    vbox.getChildren().add(category);
                    vbox.getChildren().add(subCategory);

                    Button update = new Button("Update");
                    update.setFont(new Font(18));
                    update.setPrefHeight(35);
                    update.setPrefWidth(Double.POSITIVE_INFINITY);
                    update.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            product.setName(name.getText());
                            product.setCount(Integer.parseInt(count.getText()));
                            product.setPrice(Integer.parseInt(price.getText()));
                            product.setDesciption(description.getText());
                            product.setCategory((SubCategory) subCategory.getValue());
                            showProducts(e);
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Product Details");
                            alert.setHeaderText("Product updated successfully :)");
                            alert.show();
                        }
                    });
                    vbox.getChildren().add(update);

                    mainContainer.getChildren().add(vbox);
                }
            });
            l.setText(product.toString());
            vbox.getChildren().add(l);
        }

        Button newProduct = new Button("New product");
        newProduct.setFont(new Font(18));
        newProduct.setPrefHeight(35);
        newProduct.setPrefWidth(Double.POSITIVE_INFINITY);
        newProduct.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                addNewProduct(e);
            }
        });
        vbox.getChildren().add(newProduct);

        mainContainer.getChildren().add(vbox);
    }

    public void addNewProduct(ActionEvent event){
        mainContainer.getChildren().clear();

        VBox vbox = new VBox();
        AnchorPane.setTopAnchor(vbox,0.0);
        AnchorPane.setBottomAnchor(vbox,0.0);
        AnchorPane.setLeftAnchor(vbox,0.0);
        AnchorPane.setRightAnchor(vbox,0.0);

        vbox.setSpacing(20.0);
        vbox.setPadding(new Insets(30.0));

        TextField name = new TextField();
        name.setPromptText("Name");

        TextField price = new TextField();
        price.setPromptText("Price");

        TextField count = new TextField();
        count.setPromptText("Count");

        TextArea description = new TextArea();
        description.setPromptText("Description");

        ComboBox category = new ComboBox(FXCollections.observableArrayList(OnlineShop.getCategories()));
        ComboBox subCategory = new ComboBox();

        category.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Category c = OnlineShop.getCategoryByTitle(category.getValue().toString());
                subCategory.setItems(FXCollections.observableArrayList(OnlineShop.getSubCategories(c)));
            }
        });

        vbox.getChildren().add(name);
        vbox.getChildren().add(price);
        vbox.getChildren().add(count);
        vbox.getChildren().add(description);
        vbox.getChildren().add(category);
        vbox.getChildren().add(subCategory);

        Button addProduct = new Button("Add");
        addProduct.setFont(new Font(18));
        addProduct.setPrefHeight(35);
        addProduct.setPrefWidth(Double.POSITIVE_INFINITY);
        addProduct.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Product Details");
                alert.setHeaderText("Product added successfully :)");
                alert.show();
                Product product = new Product(name.getText(), Integer.parseInt(price.getText()), Integer.parseInt(count.getText()));
                product.setSeller(OnlineShop.getLoggedInSeller());
                product.setCategory((SubCategory) subCategory.getValue());
                OnlineShop.addProduct(product);
                showProducts(event);
            }
        });
        vbox.getChildren().add(addProduct);

        mainContainer.getChildren().add(vbox);
    }

    public void showProfile(ActionEvent e){
        mainContainer.getChildren().clear();

        VBox vbox = new VBox();
        AnchorPane.setTopAnchor(vbox,0.0);
        AnchorPane.setBottomAnchor(vbox,0.0);
        AnchorPane.setLeftAnchor(vbox,0.0);
        AnchorPane.setRightAnchor(vbox,0.0);

        vbox.setSpacing(20.0);
        vbox.setPadding(new Insets(30.0));

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

        vbox.getChildren().add(username);
        vbox.getChildren().add(email);
        vbox.getChildren().add(password);
        vbox.getChildren().add(add);
        mainContainer.getChildren().add(vbox);
    }

    public void showOrders(ActionEvent e){
        mainContainer.getChildren().clear();

        VBox vbox = new VBox();

        AnchorPane.setTopAnchor(vbox,0.0);
        AnchorPane.setBottomAnchor(vbox,0.0);
        AnchorPane.setLeftAnchor(vbox,0.0);
        AnchorPane.setRightAnchor(vbox,0.0);

        vbox.setSpacing(20.0);
        vbox.setPadding(new Insets(30.0));

        for(Order order : OnlineShop.getSellerOrders(OnlineShop.getLoggedInSeller())){
            Button orderBtn = new Button();
            orderBtn.setFont(new Font(18));
            orderBtn.setPrefHeight(35);
            orderBtn.setPrefWidth(Double.POSITIVE_INFINITY);
            orderBtn.setText("order from " + order.getCostumer());

            vbox.getChildren().add(orderBtn);
        }

        mainContainer.getChildren().add(vbox);
    }

    public void transactions(ActionEvent e){
        mainContainer.getChildren().clear();

        VBox vbox = new VBox();

        AnchorPane.setTopAnchor(vbox,0.0);
        AnchorPane.setBottomAnchor(vbox,0.0);
        AnchorPane.setLeftAnchor(vbox,0.0);
        AnchorPane.setRightAnchor(vbox,0.0);

        vbox.setSpacing(20.0);
        vbox.setPadding(new Insets(30.0));

        for(Transaction transaction : OnlineShop.getLoggedInSeller().getTransactions()){
            Button btn = new Button();
            btn.setFont(new Font(18));
            btn.setPrefHeight(35);
            btn.setPrefWidth(Double.POSITIVE_INFINITY);
            btn.setText(transaction.getAmount() + " amount of money was deposited to your wallet from " + transaction.getFrom().getUsername());

            vbox.getChildren().add(btn);
        }

        mainContainer.getChildren().add(vbox);
    }

    public void notifications(ActionEvent e){
        mainContainer.getChildren().clear();

        VBox vbox = new VBox();
        ScrollPane sp = new ScrollPane(vbox);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        AnchorPane.setTopAnchor(sp,0.0);
        AnchorPane.setBottomAnchor(sp,0.0);
        AnchorPane.setLeftAnchor(sp,0.0);
        AnchorPane.setRightAnchor(sp,0.0);

        vbox.setSpacing(30.0);
        vbox.setPadding(new Insets(30.0));

        ArrayList<Notification> notifications = OnlineShop.getLoggedInSeller().getNotifications();
        Collections.reverse(notifications);
        for(Notification notification : notifications){
            Button button = new Button();
            button.setText(notification.getTitle());

            button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(notification.getTitle());
                    alert.setContentText(notification.getText());
                    alert.showAndWait();
                    OnlineShop.getLoggedInSeller().removeNotification(notification);
                    notifBtn.setText("Notifications("+OnlineShop.getLoggedInSeller().getNotifications().size()+")");
                    notifications(e);
                }
            });

            vbox.getChildren().add(button);
        }

        Button clear = new Button("Clear");
        clear.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                OnlineShop.getLoggedInSeller().clearNotifications();
                notifBtn.setText("Notifications("+OnlineShop.getLoggedInSeller().getNotifications().size()+")");
                notifications(e);
            }
        });
        vbox.getChildren().add(clear);

        mainContainer.getChildren().add(sp);
    }
}
