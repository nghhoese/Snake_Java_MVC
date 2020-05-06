package View;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameOverScene extends Scene{
 private BorderPane gameOverPane;
 private Label gameOver;
 private Label endTime;
 private VBox textPane;

	public GameOverScene() {
		super(new Pane());
		
		gameOverPane = new BorderPane();
		gameOverPane.setPrefSize(760, 600);
		gameOverPane.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
		createLabels();
		gameOverPane.setCenter(textPane);
		this.setRoot(gameOverPane);
		
	}
	public void createLabels() {
		
		gameOver = new Label("Game Over");
		endTime = new Label("00:00:00");
		gameOver.setFont(new Font("ARIAL BLACK",30));
		endTime.setFont(new Font("ARIAL BLACK",30));
		endTime.setTextFill(Color.WHITE);
		textPane = new VBox();
		textPane.setAlignment(Pos.CENTER);
		textPane.getChildren().addAll(gameOver,endTime);
	}
	public void updateEndTime(String endTime) {
		this.endTime.setText(endTime);
		
	}
}
