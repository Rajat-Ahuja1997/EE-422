/* CRITTERS Critter.java
 * EE422C Project 5 submission by
 * <Rajat Ahuja>
 * <RA29697>
 * <15465>
 * <Shane Zhao>
 * <SSZ255>
 * <15465>
 * Slip days used: <0>
 * Spring 2018
 */

package assignment5;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.lang.reflect.Modifier;
import java.net.URISyntaxException;
import java.util.*;
import java.io.*;


import assignment5.Critter.CritterShape;


/**
 * 
 * Main file to run our app on JavaFX
 *
 */
public class Main extends Application{

	private static String myPackage;
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	static GridPane scene = new GridPane();
	static GridPane modelWorld = new GridPane();
	static int size = 10;
	static boolean run = false;
	Stage window;
	
	/**
	 * Start method to start our JavaFX app
	 * @param primaryStage that is the stage we use
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		window = primaryStage;
		window.setTitle("Critter 2");
		scene.setPadding(new Insets(10,10,10,10));
		scene.setVgap(8);
		scene.setHgap(10);
		
		//Labels which represent plain text in the JavaFX
		Label makeLabel = new Label("Make Critters");
		GridPane.setConstraints(makeLabel, 2, 0);
		Label seedLabel = new Label("Seed Number:");
		GridPane.setConstraints(seedLabel, 2, 1);
		Label timeStepLabel = new Label("Time Step:");
		GridPane.setConstraints(timeStepLabel, 2, 2);
		Label runStatsLabel = new Label("Run Stats:");
		GridPane.setConstraints(runStatsLabel, 2, 3);
		
		//All of our input Textfields 
		TextField makeInput = new TextField();
		GridPane.setConstraints(makeInput, 3, 0);
		TextField makeAmount = new TextField();
		GridPane.setConstraints(makeAmount, 4, 0);
		String text = makeInput.getText();
		String number = makeAmount.getText();
		
		TextField seedInput = new TextField();
		GridPane.setConstraints(seedInput, 3, 1);
		
		TextField timeStepInput = new TextField();
		GridPane.setConstraints(timeStepInput, 3, 2);
		
		TextField runStatsInput = new TextField();
		GridPane.setConstraints(runStatsInput, 3, 3);
		
		//makes the critter
		Button makeButton = new Button("Create");
		GridPane.setConstraints(makeButton, 6, 0);
		makeButton.setOnAction(value -> {
			try {
				if(makeAmount.getText().isEmpty()) {
					Critter.makeCritter(makeInput.getText());
				}
				else {
					for(int i = 0; i < Integer.parseInt(makeAmount.getText()); i++) {
						Critter.makeCritter(makeInput.getText()); 
					}
				}
			} 
			catch (InvalidCritterException e) {
				window.close();
			}
		});
		
		Button seedButton = new Button("Seed");
		GridPane.setConstraints(seedButton, 4, 1);
		seedButton.setOnAction(value -> {
			Critter.setSeed(Integer.parseInt(seedInput.getText()));
		});
		
		//Will be the button that does all of the time steps
		Button timeButton = new Button("Do Time Step");
		GridPane.setConstraints(timeButton, 4, 2);
		timeButton.setOnAction(value -> {
			if(timeStepInput.getText().isEmpty()) {
				Critter.worldTimeStep();
			}
			else {
				for(int i = 0; i < Integer.parseInt(timeStepInput.getText()); i++) {
					Critter.worldTimeStep();
				}
			}
		});
		
		Button runButton = new Button("Run Stats");
		GridPane.setConstraints(runButton, 4, 3);
		
		runButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event)
			{
				try{
					String result = new String();
					result = "";
					List<Critter> runStatsList = null;
					runStatsList = Critter.getInstances(makeInput.getText());
					result = Critter.runStats(runStatsList);
					runStatsInput.setText(result);
				} catch (InvalidCritterException e) {
				}
			}
		});

		
		//Closes out of JavaFX 
		Button quit = new Button("Quit");
		GridPane.setConstraints(quit, 5, 5);
		quit.setOnAction(value -> {
			window.close();
		});
		
		Button show = new Button("Show");
		GridPane.setConstraints(show, 5, 6);
		show.setOnAction(value -> {
			Critter.displayWorld();
		});
		
		Button simulate = new Button("Simulate");
		GridPane.setConstraints(simulate, 5, 7);
		simulate.setOnAction(value -> {
			Timeline timeline = new Timeline(new KeyFrame(
			        Duration.millis(2500),
			        ae -> Critter.worldTimeStep()));
			timeline.setCycleCount(Animation.INDEFINITE);
			timeline.play();

		});
		
		Slider timeSlider = new Slider();
		timeSlider.setMin(0);
		timeSlider.setMax(25);
		timeSlider.setValue(0);
		timeSlider.setShowTickLabels(true);
		timeSlider.setShowTickMarks(true);
		timeSlider.setMajorTickUnit(5);
		timeSlider.setMajorTickUnit(10);
		timeSlider.setMajorTickUnit(15);
		timeSlider.setMajorTickUnit(20);
		timeSlider.setBlockIncrement(1);
		
		Button testing = new Button("Animation");
		GridPane.setConstraints(testing, 7, 6);
		Timeline timeline = new Timeline();
		testing.setOnAction(value -> {
			
			run = !run;
			if(run) {
			
				timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), 
					new EventHandler<ActionEvent>() {
				
					@Override
					public void handle(ActionEvent event){
						for(int i = 0; i < timeSlider.getValue(); i++){
							Critter.worldTimeStep();
						}
						Critter.displayWorld();
						runButton.fire();
					}
				
				}));    
				timeline.setCycleCount(Timeline.INDEFINITE);
				timeline.play();
			
			}
		});
		scene.getChildren().add(testing);
		
		modelWorld.add(timeSlider, Params.world_width+1, Params.world_width+1);
		
		scene.getChildren().addAll(simulate, timeSlider, modelWorld, show, makeAmount, makeInput, makeLabel, makeButton, seedButton, timeButton, runButton, quit, seedLabel, seedInput,timeStepLabel, runStatsLabel, timeStepInput, runStatsInput);
		
		Scene test = new Scene(scene,1250,750);
		window.setScene(test);
		window.show();
	}
	
	/**
	 * Runs main
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Paints our world on JavaFX
	 */
	public static void paintGrid() {
		for (int i = 0; i < Params.world_width; i++) {
			for (int j = 0; j < Params.world_height; j++) {
				Shape s = new Rectangle(size,size);
				s.setFill(Color.WHITE);
				s.setStroke(Color.BLACK); 
				modelWorld.add(s, i, j);
			}
		}
		
		List<Critter> pop = Critter.getAllCritters();
		for(int i = 0; i<pop.size(); i++) {
			Critter c = pop.get(i);
			Shape crit = getShape(c.viewShape());
			crit.setFill(c.viewFillColor());
			crit.setStroke(c.viewOutlineColor());
			modelWorld.add(crit, c.getX(), c.getY());
		}
		
	}

	/**
	 * Gets the type of shape we have
	 * @param shape
	 * @return Shape one of the five shapes we have
	 */
	private static Shape getShape(Critter.CritterShape shape) {
		Shape s = null;
		switch(shape) {
			case CIRCLE:   s = new Circle((size-1)/2);
						   break;
			case TRIANGLE: s = drawTriangle();
						   break;
			case SQUARE:   s = new Rectangle(size, size);
						   break;
			case DIAMOND:  s = drawDiamond();
		}
		return s;
	}
	
	/**
	 * Method that draws our triangles
	 * @return Shape
	 */
	private static Shape drawTriangle() {
		Polygon triangle = new Polygon();
		triangle.getPoints().addAll(new Double[]{
			    (double)size/2.0, 1.0,
			    (double)size - 1.0, (double)size - 1.0,
			    1.0, (double)size-1.0
		});
		return triangle;
	}
	
	/**
	 * Method that draws our diamonds
	 * @return Shape diamond
	 */
	private static Shape drawDiamond() {
		Polygon diamond = new Polygon();
		diamond.getPoints().addAll(new Double[]{
				(double)size/2.0, 1.0,
				(double)size - 1.0, (double)size/2.0,
				(double)size/2.0, (double)size - 1.0,
				1.0, (double)size/2.0
		});
		return diamond;
	}

}
