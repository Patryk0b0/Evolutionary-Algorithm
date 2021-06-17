package pl.com.main;

import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.function.Function;
import pl.com.thread.FillWithRandom;

public class Main extends Application {
	
	public static final int MAX_POPULATION = 200;
	public static final int WIDTH = 8;	
	public static final int HEIGHT = WIDTH;
	public static final int HETMANS = WIDTH;	
	public static final int SIZE = WIDTH * HEIGHT;
	public static final int ITERATIONS = 50;
	public static final int TICK = 2;
	public static final int MUTATIONS = 20;
	
	public static Random random = new Random();

	static XYChart.Series<Number, Number> min;
	static XYChart.Series<Number, Number> max;
	static XYChart.Series<Number, Number> avg;

	@Override
	public void start(Stage primaryStage) throws Exception {
        NumberAxis xAxis = new NumberAxis(0, ITERATIONS, TICK);
        NumberAxis yAxis = new NumberAxis(0, HETMANS, 0.5);
        yAxis.setLabel("Minimum");
        

        LineChart baseChart = new LineChart(xAxis, yAxis);
        
        min = new XYChart.Series<>();
        min.setName("Minimum");
        baseChart.getData().add(min);
        
        max = new XYChart.Series<>();
        max.setName("Maximum");
        
        avg = new XYChart.Series<>();
        avg.setName("Average");
        
//        baseChart.getData().add(prepareSeries("Series 1", (x) -> (double)x));

        MultipleAxesLineChart chart = new MultipleAxesLineChart(baseChart, Color.RED);

        chart.addSeries(max, Color.BLUE);
        chart.addSeries(avg, Color.GREEN);
        
//        chart.addSeries(prepareSeries("Series 2", (x) -> (double)x*x),Color.BLUE);
//        chart.addSeries(prepareSeries("Series 3", (x) -> (double)-x*x),Color.GREEN);
//        chart.addSeries(prepareSeries("Series 4", (x) -> ((double) (x-250))*x),Color.DARKCYAN);
//        chart.addSeries(prepareSeries("Series 5", (x) -> ((double)(x+100)*(x-200))),Color.BROWN);

        primaryStage.setTitle("MultipleAxesLineChart");

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(chart);
        borderPane.setBottom(chart.getLegend());

        Scene scene = new Scene(borderPane, 1024, 600);
        scene.getStylesheets().add("style.css");

        primaryStage.setScene(scene);
        primaryStage.show();

		new Thread(Population.create(MAX_POPULATION)).start();
	}
	
	public static void min(int i, float min2, Function<Integer, Double> function) {
		Platform.runLater(() -> min.getData().add(new XYChart.Data<>(function.apply(i), min2))) ;
	}
	
	public static void max(int i, float min2, Function<Integer, Double> function) {
		Platform.runLater(() -> max.getData().add(new XYChart.Data<>(function.apply(i), min2))) ;
	}
	
	public static void avg(int i, float min2, Function<Integer, Double> function) {
		Platform.runLater(() -> avg.getData().add(new XYChart.Data<>(function.apply(i), min2))) ;
	}
	
//    private XYChart.Series<Number, Number> prepareSeries(String name, Function<Integer, Double> function) {
//        XYChart.Series<Number, Number> series = new XYChart.Series<>();
//        series.setName(name);
//        for (int i = 0; i < X_DATA_COUNT; i++) {
//            series.getData().add(new XYChart.Data<>(function.apply(i), i));
//        }
//        return series;
//    }
	
	public static void main(String[] args) {
		launch(args);
	}

}
