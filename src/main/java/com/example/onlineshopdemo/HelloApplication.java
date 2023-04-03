package com.example.onlineshopdemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginRegister.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        if(!OnlineShop.tryToLoad()){
            OnlineShop.setName("KOKOKALA");
            OnlineShop.setWebAddress("kokokala.com");
            OnlineShop.setSupportPhoneNumber("123456789");
            Test.importTestCases();
            System.out.println("we are doin it all over again");
        }
        launch();
    }
    @Override
    public void stop(){
        OnlineShop.save();
    }
}