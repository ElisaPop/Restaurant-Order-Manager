package main;

import gui.admin.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        AdminController mainCtrl = new AdminController();

        primaryStage.setScene(mainCtrl.getScene());
        primaryStage.setTitle("Order Management");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}