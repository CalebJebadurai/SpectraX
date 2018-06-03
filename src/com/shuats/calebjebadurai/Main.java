package com.shuats.calebjebadurai;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    private Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("spectraUI.fxml"));
        GridPane rootGridPane = loader.load();

        controller = loader.getController();
//        controller.createGraphBackground();

        MenuBar menuBar = createMenu();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

        Pane menuPane = (Pane) rootGridPane.getChildren().get(0);
        menuPane.getChildren().addAll(menuBar);

        Scene scene = new Scene(rootGridPane);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Spectra X");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public MenuBar createMenu(){

        //File Menu Items
        MenuItem newSpectra = new MenuItem("Load New Data");
        newSpectra.setOnAction(event -> controller.resetSpectra());

        MenuItem restartSpectra = new MenuItem("Restart Spectra");
        restartSpectra.setOnAction(event -> controller.resetSpectra());

        SeparatorMenuItem separatorMenuItem1 = new SeparatorMenuItem();
        MenuItem exitSpectra = new MenuItem("Exit");
        exitSpectra.setOnAction(event -> exitSpectra());

        //Help Menu Items
        MenuItem aboutSpectra = new MenuItem("About Spectra");
        aboutSpectra.setOnAction(event -> aboutSpectra());

        SeparatorMenuItem separatorMenuItem2 = new SeparatorMenuItem();
        MenuItem aboutMe = new MenuItem("About Me");
        aboutMe.setOnAction(event -> aboutMe());

        //File Menu
        Menu fileMenu = new Menu("File");
        fileMenu.getItems().addAll(newSpectra, restartSpectra, separatorMenuItem1, exitSpectra);

        //Help Menu
        Menu helpMenu = new Menu("Help");
        helpMenu.getItems().addAll(aboutSpectra,separatorMenuItem2, aboutMe);

        //Menu Bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, helpMenu);

        return menuBar;
    }

    private void aboutMe() {
        Alert aboutMe = new Alert(Alert.AlertType.INFORMATION);
        aboutMe.setTitle("About The Developer");
        aboutMe.setHeaderText("Caleb Jebadurai");
        aboutMe.setContentText("I am learning to build many more awesome Java applications. Thank you for playing!");
        aboutMe.show();
    }

    private void aboutSpectra() {
        Alert aboutGame = new Alert(Alert.AlertType.INFORMATION);
        aboutGame.setTitle("Connect 4");
        aboutGame.setHeaderText("How to play?");
        aboutGame.setContentText("Drop the discs turn by turn and try to get 4 discs" +
                " in continuous line diagonally, vertically or horizontally." +
                "The player who gets 4 discs in line wins the GAME!");
        aboutGame.show();
    }

    private void exitSpectra() {
        Platform.exit();
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}