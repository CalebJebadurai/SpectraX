package com.shuats.calebjebadurai;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    private Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("spectraUI.fxml"));
        GridPane rootGridPane = loader.load();

        controller = loader.getController();


        MenuBar menuBar = createMenu();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

        Pane menuPane = (Pane) rootGridPane.getChildren().get(0);
        menuPane.getChildren().addAll(menuBar);

        loadNewData(new Stage());
        controller.displayGraph();

//        TabPane tabPane = controller.tabPane;
//        rootGridPane.add(tabPane,0,1);

        Scene scene = new Scene(rootGridPane);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Spectra X");
        primaryStage.setResizable(true);
        primaryStage.show();
    }

//    public TabPane createTabPane(){
//
//        //Line Charts
//        LineChart inputLineChart = controller.inputLineChart;
//        LineChart fftLineChart = controller.fftLineChart;
//        LineChart filteredLineChart = controller.filteredLineChart;
//        LineChart ifftLineChart = controller.ifftLineChart;
//
//        //Anchor Panes
//        AnchorPane inputPane = new AnchorPane();
//        inputPane.getChildren().add(inputLineChart);
//
//        AnchorPane fftPane = new AnchorPane();
//        fftPane.getChildren().add(fftLineChart);
//
//        AnchorPane filteredPane = new AnchorPane();
//        filteredPane.getChildren().add(filteredLineChart);
//
//        AnchorPane ifftPane = new AnchorPane();
//        ifftPane.getChildren().add(ifftLineChart);
//
//        //Tabs
//        Tab inputTab = new Tab();
//        inputTab.setContent(inputPane);
//
//        Tab fftTab = new Tab();
//        fftTab.setContent(fftPane);
//
//        Tab filteredTab = new Tab();
//        filteredTab.setContent(filteredPane);
//
//        Tab ifftTab = new Tab();
//        ifftTab.setContent(ifftPane);
//
//        //Tab Pane
//        TabPane tabPane = new TabPane();
//        tabPane.getTabs().add(inputTab);
//        tabPane.getTabs().add(fftTab);
//        tabPane.getTabs().add(filteredTab);
//        tabPane.getTabs().add(ifftTab);
//
//        return tabPane;
//
//    }

    public MenuBar createMenu(){

        //File Menu Items
        MenuItem newSpectra = new MenuItem("Load New Data");
        newSpectra.setOnAction(event -> loadNewData(new Stage()));

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

    private void loadNewData(Stage primaryStage) {

        primaryStage.setTitle("Import Data from Text file");

        String filePath = new String();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new java.io.File("/"));
        fileChooser.setTitle("Select Only a (*.txt) file.");

        // Set extension filter
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show open file dialog
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            filePath = file.getPath();

        controller.setRowCol(filePath);
        }
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