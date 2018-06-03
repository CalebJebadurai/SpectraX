package com.shuats.calebjebadurai;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Controller implements Initializable {
	private static final int GRAPH_CELL = 100;

	@FXML
	public GridPane rootGridPane;

	@FXML
	public TabPane tabPane;

	@FXML
	public AnchorPane inputPane, fftPane, filteredPane, ifftPane;

	@FXML
	public Tab inputTab, fftTab, filteredTab, ifftTab;

	@FXML
	public Slider lpfSlider, hpfSlider;

	@FXML
	public Button renderButton;

//	public void createGraphBackground() {
//
//		Shape rectangle =  new Rectangle(16 * GRAPH_CELL, 9 * GRAPH_CELL);
//		rootGridPane.add(rectangle,1,1);
//
//		renderButton.setOnAction(event -> {
//
//		});
//	}

	public void resetSpectra() {

		// Remove all remove all Graph from TabPane

//		createGraphBackground(); // Prepare a fresh Background
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}