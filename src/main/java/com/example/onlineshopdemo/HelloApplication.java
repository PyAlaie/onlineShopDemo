package com.example.onlineshopdemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/onlineshopdemo/loginRegister.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        OnlineShop.setName("KOKOKALA");
        OnlineShop.setWebAddress("kokokala.com");
        OnlineShop.setSupportPhoneNumber("123456789");
        Test.importTestCases();

        launch();
    }
}