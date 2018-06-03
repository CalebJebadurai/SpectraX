package com.shuats.calebjebadurai;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Controller implements Initializable {

	private static final int GRAPH_CELL = 100;
	private static double RAW_DATA[][];
	private static int row, col;
	private static List<Double> Data;
	private static XYChart.Series SeriesData;
	private static int upperBound;
	protected static Region region;

	@FXML
	public GridPane rootGridPane;

	@FXML
	public TabPane tabPane;

	@FXML
	public AnchorPane inputPane, fftPane, filteredPane, ifftPane;

	@FXML
	public LineChart inputLineChart, fftLineChart, filteredLineChart, ifftLineChart;

	@FXML
	public Tab inputTab, fftTab, filteredTab, ifftTab;

	@FXML
	public Slider lpfSlider, hpfSlider, lpfTreshold, hpfTreshold;

	@FXML
	public Button renderButton;









	public void displayGraph() {

		//Defining the x axis
		NumberAxis xAxis = new NumberAxis(0, upperBound, 10);
		xAxis.setLabel("Time");

		//Defining the y axis
		NumberAxis yAxis = new NumberAxis(-1.2, 1.2, 0.02);
		yAxis.setLabel("Amplitude");

		//Creating the line chart
		inputLineChart = new LineChart(xAxis,yAxis);

		//Prepare XYChart.Series objects by setting data
		SeriesData.setName("No of schools in an year");

		//Setting the data to Line chart
		inputLineChart.getData().add(SeriesData);
		inputLineChart.setVisible(true);
	}










	public void setRowCol(String filePath)//sets row, coloumn and instantiates rawData
	{
		String stringLine;
		StringTokenizer tokens;
		try
		{

			FileReader fileReader = new FileReader(filePath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while((stringLine = bufferedReader.readLine()) != null)
			{
				if(row==0)
				{
					tokens=new StringTokenizer(stringLine);
					col=tokens.countTokens();
				}
				row++;
			}
			bufferedReader.close();
			fileReader.close();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		RAW_DATA=new double[row][col];
		System.out.println("check 1\t"+row+"\t"+col);

		setData(filePath);
	}

	public void setData(String filePath) //throws IOException
	{
		StringTokenizer st;
		int R=0;
		int C=0;
		try
		{
			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);
			String s;

			while((s=br.readLine())!= null && R<row)
			{
				st=new StringTokenizer(s);
				double sum=0;
				for(C=0;C<col;C++)
				{
					try
					{
						String temp=st.nextToken();
						System.out.print("    :"+temp+":");
						RAW_DATA[R][C]=Double.parseDouble(temp);
					}
					catch(Exception e)
					{
						System.out.print("Null Pointer"+e);
					}
					sum+=RAW_DATA[R][C];
				}
				System.out.println("");
				RAW_DATA[R][0]=sum;
				R++;
			}
			br.close();
			fr.close();
		}
		catch(Exception e)
		{
			System.out.println("IOE"+e);
		}
		trimmer(RAW_DATA);
	}

	public void trimmer(double[][] x)
	{
//		Data=new ArrayList();
//		serialList = new ArrayList<>();

		SeriesData = new XYChart.Series();

		row=2;
		int len=x.length;
		while(row<len)
		{
			row*=2;
		}

		//row/=2;
		//Data=new double[row];

		for(int i=0;i<row;i++)
		{

			upperBound = i;
			//scores.add((double) random.nextDouble() * maxScore);
			if(i>=len) {
//				Data.add(0.0);
				SeriesData.getData().add(new XYChart.Data<Integer, Double>(i, 0.0));
			}
			else {
//				Data.add((double)x[i][0]);//[i]=x[i][1];
				SeriesData.getData().add(new XYChart.Data<Integer,Double>(i,x[i][0]));
			}
		}
//		System.out.print("\t\t"+Data);
//		return Data;
	}

	public void resetSpectra() {

		// Remove all remove all Graph from TabPane

//		createGraphBackground(); // Prepare a fresh Background
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}