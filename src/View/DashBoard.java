package View;

import Controller.Controller;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class DashBoard extends BorderPane {
	private Button exitButton;
	private Button playStopButton;
	private Slider slider;
	private Label timerLabel;
	private Controller controller;
	private HBox collection;

	public DashBoard(Controller controller) {
		collection = new HBox();
		this.controller = controller;
		createSlider();
		createTimerLabel();
		createButtons();
		this.setBackground(new Background(new BackgroundFill(Color.DARKGREY, null, null)));
		this.setPrefSize(760, 100);
		collection.setSpacing(40);

		collection.getChildren().addAll(playStopButton, exitButton, slider, timerLabel);
		this.setCenter(collection);
		collection.setAlignment(Pos.CENTER);
	}

	public void createButtons() {
		exitButton = new Button("Exit");
		exitButton.setOnAction(e -> System.exit(0));
		exitButton.setPrefSize(100, 40);
		playStopButton = new Button("Start");
		playStopButton.setOnAction(e -> setPlayStopButtonText(controller.getTimeline(),controller.getTimeLine1(),controller.getTimeLineStopWatch()));
		playStopButton.setPrefSize(100, 40);
	}

	public void setPlayStopButtonText(Timeline timeLine,Timeline timeLine1,Timeline stopWatch) {

		if (playStopButton.getText().equals("Start")) {
			timeLine.play();
			timeLine1.play();
			stopWatch.play();
			
			
			playStopButton.setText("pause");

		} else {
			playStopButton.setText("Start");
			timeLine.pause();
			timeLine1.pause();
			stopWatch.pause();
			
			
		}
	}

	public void createTimerLabel() {
		timerLabel = new Label("00:00.000");
		timerLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, null, null)));
		timerLabel.setPrefSize(100, 40);
		timerLabel.textProperty().bind(controller.getGame().getTime());
	}


	public void createSlider() {
		slider = new Slider();
		slider.setPrefWidth(220);
		slider.setPrefHeight(70);
		slider.setMin(1);
		slider.setMax(12);
		slider.valueProperty().bind(controller.getGame().getGameSpeed());
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(1);
		slider.setMinorTickCount(1);
		slider.setBlockIncrement(1);
		slider.mouseTransparentProperty().set(true);
		slider.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, null, null)));
		slider.setDisable(true);
		slider.setOpacity(255);
		
	}
}
