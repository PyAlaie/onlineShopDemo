package com.example.onlineshopdemo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;
import java.net.URL;

public class CostumerPanel implements Initializable  {
    @FXML
    private Label statusLabel;

    @FXML
    private AnchorPane mainContainer;
    @FXML
    private Button notifBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        statusLabel.setText("Hello " + OnlineShop.getLoggedInUser().getUsername() +
                ", Your wallet balance is " + OnlineShop.getLoggedInCostumer().getWallet().getBalance());
        notifBtn.setText("Notifications("+OnlineShop.getLoggedInCostumer().getNotifications().size()+")");
    }

    public void showFundRequests(ActionEvent e){
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

        for(FundRequest fundRequest : OnlineShop.getFundRequests()){
            if(fundRequest.getCostumer().equals(OnlineShop.getLoggedInCostumer())){
                Button l = new Button();
                l.setFont(new Font(18));
                l.setPrefHeight(35);
                l.getStyleClass().add("shadow");
                l.setText("Fund request for " + fundRequest.getAmount() + " money, Confirmed: " + fundRequest.isConfirmed());

                if(fundRequest.isConfirmed()){
                    l.getStyleClass().add("green");
                }
                else {
                    if(fundRequest.isChecked()){
                        l.getStyleClass().add("red");
                    }
                }

                vbox.getChildren().add(l);
            }
        }

        Button newFundRequest = new Button("New fund request");
        newFundRequest.setFont(new Font(18));
        newFundRequest.setPrefHeight(35);
        newFundRequest.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TextInputDialog td = new TextInputDialog();
                td.setHeaderText("Enter amount");
                td.setTitle("New Fund Request");
                Optional<String> result = td.showAndWait();

                result.ifPresent(name -> {
                    int amount = Integer.parseInt(name);
                    FundRequest fundRequest = new FundRequest(amount, OnlineShop.getLoggedInCostumer());
                    OnlineShop.addFundRequest(fundRequest);
                    showFundRequests(e);
                });
            }
        });

        vbox.getChildren().add(newFundRequest);

        mainContainer.getChildren().add(sp);
    }

    public void logoutBtnHit(ActionEvent event) throws IOException {
        OnlineShop.logoutCostumer();
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

    public void home(ActionEvent e){
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

        for(Product product : OnlineShop.getProducts()){
            Button button = new Button();
            button.setFont(new Font(18));
            button.setPrefHeight(35);
            button.getStyleClass().add("shadow");
            String title = product.getName() + ", Price: " + product.getPrice()
                    + ", Count: " + product.getCount();
            button.setText(title);

            button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    viewProduct(e,product);
                }
            });
            vbox.getChildren().add(button);
        }
        mainContainer.getChildren().add(sp);
    }

    public void carts(ActionEvent e){
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

        for(Item item : OnlineShop.getLoggedInCostumer().getCart().getItems()){
            Button button = new Button();
            button.setFont(new Font(18));
            button.setPrefHeight(35);
            button.setText("Product: " + item.getProduct().getName() + ", Count: " + item.getCount());
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    viewProduct(e,item.getProduct());
                }
            });
            vbox.getChildren().add(button);
        }

        Button order = new Button();
        order.setFont(new Font(18));
        order.setPrefHeight(35);
        order.setText("Order! (Total price: "+ OnlineShop.getLoggedInCostumer().getCart().calculteTotalPrice()+")");
        if(OnlineShop.getLoggedInCostumer().getCart().getItems().size() == 0){
            order.setDisable(true);
        }
        order.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                orderCart(e);
                carts(e);
            }
        });

        vbox.getChildren().add(order);

        mainContainer.getChildren().add(sp);
    }

    public void profile(ActionEvent e){
        mainContainer.getChildren().clear();

        Costumer costumer = OnlineShop.getLoggedInCostumer();

        TextField username = new TextField(costumer.getUsername());
        username.setPromptText("Username");
        TextField password = new TextField(costumer.getPassword());
        password.setPromptText("password");
        TextField email = new TextField(costumer.getEmail());
        email.setPromptText("Email");
        TextField phoneNumber = new TextField(costumer.getPhoneNumber());
        phoneNumber.setPromptText("Phone number");
        TextField address = new TextField(costumer.getAddress());
        address.setPromptText("Address");

        VBox vbox = new VBox();
        AnchorPane.setTopAnchor(vbox,0.0);
        AnchorPane.setBottomAnchor(vbox,0.0);
        AnchorPane.setLeftAnchor(vbox,0.0);
        AnchorPane.setRightAnchor(vbox,0.0);

        vbox.setSpacing(20.0);
        vbox.setPadding(new Insets(30.0));

        Button save = new Button("Apply changes");
        save.setFont(new Font(18));
        save.setPrefHeight(35);
        save.setPrefWidth(Double.POSITIVE_INFINITY);
        save.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                OnlineShop.getLoggedInCostumer().setUsername(username.getText());
                OnlineShop.getLoggedInCostumer().setEmail(email.getText());
                OnlineShop.getLoggedInCostumer().setPassword(password.getText());
                OnlineShop.getLoggedInCostumer().setAddress(address.getText());
                OnlineShop.getLoggedInCostumer().setPhoneNumber(phoneNumber.getText());

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved!");
                alert.show();
            }
        });

        vbox.getChildren().add(username);
        vbox.getChildren().add(password);
        vbox.getChildren().add(email);
        vbox.getChildren().add(address);
        vbox.getChildren().add(phoneNumber);
        vbox.getChildren().add(save);

        mainContainer.getChildren().add(vbox);
    }

    public void orderCart(ActionEvent e){
        OnlineShop.orderCart(OnlineShop.getLoggedInCostumer().getCart());
    }

    public void orders(ActionEvent e){
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

        ArrayList<Order> orders = OnlineShop.getCostumerOrders(OnlineShop.getLoggedInCostumer());
        Collections.reverse(orders);
        for(Order order : orders){
            Button button = new Button();
            button.setFont(new Font(18));
            button.setPrefHeight(35);
            button.setText("Total price: " + order.getTotalPrice() + ", Date: " + order.getDate() + ", Confirmed: " + order.isConfirmed());

            if(!order.isChecked()){
                button.getStyleClass().add("yellow");
            }
            else{
                if(order.isConfirmed()){
                    button.getStyleClass().add("green");
                }
                else {
                    button.getStyleClass().add("red");
                }
            }

            vbox.getChildren().add(button);
        }

        mainContainer.getChildren().add(sp);
    }

    public void perchasedProducts(ActionEvent e){
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

        for(Item item : OnlineShop.getLoggedInCostumer().getPerchasedProducts()){
            Button button = new Button();
            button.setFont(new Font(18));
            button.setPrefHeight(35);
            button.setText(item.getProduct().getName()+", "+item.getCount());
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    viewProduct(e,item.getProduct());
                }
            });

            vbox.getChildren().add(button);
        }

        mainContainer.getChildren().add(sp);
    }

    public void viewProduct(ActionEvent e, Product product){
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

        Label name = new Label("Name: " + product.getName());
        Label price = new Label("Price: " + product.getPrice());
        Label count = new Label("Count: " + product.getCount());
        Label description = new Label("Description: " + product.getDesciption());
        Label seller = new Label("Seller: " + product.getSeller().getUsername());
        Label category = new Label("Category: " + product.getCategory().getCategory());
        Label subCategory = new Label("SubCategory: " + product.getCategory().getSubTitle());
        Label rating = new Label("Rating: " + product.calculateRating());

        name.getStyleClass().add("name_label");
        price.getStyleClass().add("property_label");
        count.getStyleClass().add("property_label");
        seller.getStyleClass().add("property_label");
        category.getStyleClass().add("property_label");
        subCategory.getStyleClass().add("property_label");
        rating.getStyleClass().add("property_label");
        description.getStyleClass().add("description_label");

        category.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                viewProductsByCategory(e, product.getCategory().getCategory());
            }
        });

        subCategory.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                viewProductsBySubCategory(e, product.getCategory());
            }
        });

        Button buy = new Button("Add to cart");
        Button rate = new Button("Rate");
        Group btns = new Group(buy,rate);
        rate.getStyleClass().add("rate_button");
        buy.getStyleClass().add("buy_button");
        buy.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                    TextInputDialog td = new TextInputDialog();
                    td.setHeaderText("How many?");
                    td.showAndWait().ifPresent(rs -> {
                        int amount = Integer.parseInt(rs);
                        if(amount > product.getCount() || amount < 1){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setHeaderText("Number is bigger than the product count");
                            alert.show();
                        }
                        else {
                            Item item = new Item(product, amount);
                            OnlineShop.getLoggedInCostumer().getCart().addItem(item);
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setHeaderText("Added to cart!");
                            alert.show();
                        }
                    });
            }
        });

        rate.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TextInputDialog td = new TextInputDialog();
                td.setHeaderText("Enter number [1-5]:");

                if(product.getRating(OnlineShop.getLoggedInCostumer()) != -1){
                    td.setContentText(Integer.toString(product.getRating(OnlineShop.getLoggedInCostumer())));
                }

                td.showAndWait().ifPresent(rs -> {
                    int amount = Integer.parseInt(rs);
                    if(amount >= 1 && amount <= 5){
                        product.addOrEditRating(OnlineShop.getLoggedInCostumer(), amount);
                        viewProduct(e,product);
                    }
                });
            }
        });

        Label comments = new Label("Comments");
        comments.getStyleClass().add("comments_title");

        vbox.getChildren().add(name);
        vbox.getChildren().add(price);
        vbox.getChildren().add(count);
        vbox.getChildren().add(seller);
        vbox.getChildren().add(category);
        vbox.getChildren().add(subCategory);
        vbox.getChildren().add(rating);
        vbox.getChildren().add(description);
        vbox.getChildren().add(btns);
        vbox.getChildren().add(comments);

        for(Comment comment : product.getComments()){
            Label commentText = new Label(comment.getCostumer().getUsername()+": "+comment.getText());
            commentText.getStyleClass().add("comments_text");
            vbox.getChildren().add(commentText);
        }

        TextField newComment = new TextField();
        Button newCommentBtn = new Button("Comment");
        newCommentBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Comment comment = new Comment(newComment.getText(), product, OnlineShop.getLoggedInCostumer());
                product.addComment(comment);
                newComment.setText("");
                viewProduct(e,product);
            }
        });

        vbox.getChildren().add(newComment);
        vbox.getChildren().add(newCommentBtn);

        mainContainer.getChildren().add(sp);
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

        for(Notification notification : OnlineShop.getLoggedInCostumer().getNotifications()){
            Button button = new Button();
//            button.getStyleClass().add("notification_btn");
            button.setText(notification.getTitle());

            button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(notification.getTitle());
                    alert.setContentText(notification.getText());
                    alert.showAndWait();
                    OnlineShop.getLoggedInCostumer().removeNotification(notification);
                    notifBtn.setText("Notifications("+OnlineShop.getLoggedInCostumer().getNotifications().size()+")");
                    notifications(e);
                }
            });

            vbox.getChildren().add(button);
        }

        Button clear = new Button("Clear");
        clear.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                OnlineShop.getLoggedInCostumer().clearNotifications();
            }
        });
        vbox.getChildren().add(clear);

        mainContainer.getChildren().add(sp);
    }

    public void viewProductsByCategory(ActionEvent e, Category category){
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

        for(Product product : OnlineShop.getProductsByCategory(category)){
            Button button = new Button();
            button.setFont(new Font(18));
            button.setPrefHeight(35);
//            button.setPrefWidth(Double.POSITIVE_INFINITY);
            button.setText(product.getName());

            button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    viewProduct(e,product);
                }
            });
            vbox.getChildren().add(button);
        }

        mainContainer.getChildren().add(sp);
    }

    public void viewProductsBySubCategory(ActionEvent e, SubCategory subCategory){
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

        for(Product product : OnlineShop.getProductsBySubCategory(subCategory)){
            Button button = new Button();
            button.setFont(new Font(18));
            button.setPrefHeight(35);
//            button.setPrefWidth(Double.POSITIVE_INFINITY);
            button.setText(product.getName());

            button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    viewProduct(e,product);
                }
            });
            vbox.getChildren().add(button);
        }

        mainContainer.getChildren().add(sp);
    }

    public void searchPage(ActionEvent e){
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

        TextField searchField = new TextField();
        searchField.setPromptText("Search...");

        vbox.getChildren().add(searchField);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            vbox.getChildren().clear();
            vbox.getChildren().add(searchField);
            for(Product product : OnlineShop.search(searchField.getText())){
                Button button = new Button();
                button.setFont(new Font(18));
                button.setPrefHeight(35);
                button.setText(product.getName());

                button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        viewProduct(e,product);
                    }
                });
                vbox.getChildren().add(button);
            }
        });

        mainContainer.getChildren().add(sp);
    }
}
