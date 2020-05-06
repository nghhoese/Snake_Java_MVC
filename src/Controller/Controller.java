package Controller;


import java.util.Random;

import Model.Direction;
import Model.Game;

import View.DrawPane;
import View.GameOverScene;
import View.GameScene;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller {
	
	private Stage stage;
	private GameScene gameScene;
	private GameOverScene gameOverScene;
	private Game game;
	private DrawPane drawPane;
	
	private Timeline timeline;
	private Timeline timeLine1;
	private Timeline stopWatch;
	private double interval;
	private double rate ;
	private Random rand;

	
	

	public Controller(Stage stage) {
		
		rand = new Random();
		rate = 1;
		interval = 700;
		this.stage = stage;
		game = new Game();
		stopWatch = new Timeline(new KeyFrame(Duration.millis(1), event -> stopWatch()));
		stopWatch.setCycleCount(Animation.INDEFINITE);
		timeline = new Timeline(new KeyFrame(Duration.millis(interval), event -> startGame()));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeLine1 = new Timeline(new KeyFrame(Duration.millis(5000), event -> this.growAndSpeedUpSnake()));
		timeLine1.setCycleCount(Animation.INDEFINITE);
		gameScene = new GameScene(this);
		gameScene.addEventFilter(KeyEvent.KEY_PRESSED, KeyEvent -> handleKeyPres(KeyEvent));
		gameOverScene = new GameOverScene();
		drawPane = gameScene.getDrawPane();
		
		stage.setTitle("PROG4 ASS Snake - <Nick van Hoesel>");

		stage.setScene(gameScene);
		stage.centerOnScreen();
		stage.setResizable(false);
		stage.show();
		
		drawPane.drawcanvas();
		
	}

	public Game getGame() {
		return game;
	}

	public void setSnakeDir(Direction dir) {
		getGame().getSnake().setDirection(dir);
	}

	public void gameOverHandler() {
		//checks if snake has collision or is killed by a spot
		if (getGame().getSnake().CheckForCollision() || !getGame().handleSpotsInGame()) {
			//stops all timelines and music and sets end time to gameOverScene
			timeline.stop();
			timeLine1.stop();
			stopWatch.stop();
			
			gameOverScene.updateEndTime(getGame().getGameTimeFormatted());
			stage.setScene(gameOverScene);
		}
	}

	public void startGame() {

		
		//moves snake and bodyparts
		this.getGame().moveSnake();
		//checks if snake is dead and handles spots
		gameOverHandler();
		//draws everything
		drawPane.drawcanvas();

	

	}

	public Timeline getTimeline() {
		return timeline;
	}
	public Timeline getTimeLine1() {
		return timeLine1;
	}
	public Timeline getTimeLineStopWatch() {
		return stopWatch;
	}

	public void handleKeyPres(KeyEvent e) {
		switch (e.getCode()) {
		case LEFT:
			setSnakeDir(Direction.LEFT);

			break;
		case UP:
			setSnakeDir(Direction.UP);

			break;
		case RIGHT:
			setSnakeDir(Direction.RIGHT);

			break;
		case DOWN:
			setSnakeDir(Direction.DOWN);

			break;
		default:
			break;
		}
	}
	public void growAndSpeedUpSnake() {
		this.placeRandomSpots();
	//grow with 3 bodyparts
		getGame().getSnake().addBodyPart(3);
		//Changes speed of game by * 0.75
		//until 12 steps are done so until gameSpeed is 12
		if(getGame().getGameSpeed().doubleValue() < 12) {
		rate = rate + 0.75;
		timeline.setRate(rate);
		getGame().getGameSpeed().set(getGame().getGameSpeed().doubleValue() + 1);

		}
		
		
	}
	public void placeRandomSpots() {
		//create random true or false to check if spots will be placed
		Boolean placeSpots = rand.nextBoolean();
		//if placeSpots is true spots will be placed
		if(placeSpots) {
			//creates a random amount of spots between 0-6 spots
		getGame().createRandomSpots(rand.nextInt(7));
	}
	}
public void stopWatch() {
	//gameTime + 1 ms
	this.getGame().setGameTime(this.getGame().getGameTime() + 1);
	//converts millisecond to second and minutes
	long s = (this.getGame().getGameTime() /1000) % 60;
    long m = ((this.getGame().getGameTime() /1000) / 60) % 60;
    long milli = this.getGame().getGameTime() % 1000;
    //formats string to mm:ss.mmm
    getGame().setGameTimeFormatted(String.format(" %02d:%02d.%03d", m,s,milli));
    getGame().getTime().set(getGame().getGameTimeFormatted());
    
  
}


}
