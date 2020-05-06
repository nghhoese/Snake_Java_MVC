package View;


import Controller.Controller;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class GameScene extends Scene{
	private BorderPane game;
	private DashBoard dashBoard;
	private DrawPane drawPane;
	private Controller controller;

	public GameScene(Controller controller) {
		super(new Pane());
		this.controller = controller;
		drawPane = new DrawPane(this.controller.getGame());
		dashBoard = new DashBoard(this.controller);
		game = new BorderPane();
		game.setCenter(drawPane);
		game.setBottom(dashBoard);
		this.setRoot(game);
		

		}

	public DrawPane getDrawPane() {
		return drawPane;
	}
	




}
