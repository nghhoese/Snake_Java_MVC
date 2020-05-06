package View;

import java.io.File;


import Model.BodyPart;
import Model.Game;
import Model.Spot;
import javafx.animation.Animation;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class DrawPane extends BorderPane {

	private Game game;
	private GridPane gridPane;
	
	private int gridSpotSize;

	public DrawPane(Game game) {
		gridSpotSize = 40;
		this.game = game;
		gridPane = new GridPane();

		this.setPrefSize(760, 600);

		this.drawGrid();
		this.getChildren().addAll(gridPane);

		

	}



	public void drawGrid() {
		//draws background
		int counter = 0;
		int counter1 = 0;
		int counter2 = 0;
		for (int x = 0; x < 19; x++) {
			for (int y = 0; y < 15; y++) {
				Rectangle rt = new Rectangle(gridSpotSize, gridSpotSize);
				//if counter2 = 0 it means it is in a odd column
				//else its in an even column
				if (counter2 == 0) {
					//if counter = 0 it means its in a odd row else its in an even row
					//same goes for counter1
					if (counter == 0) {
						rt.setFill(Color.BLACK);
						counter = 1;

					} else {
						rt.setFill(Color.web("#1e1e1e"));
						counter = 0;

					}

				} else {

					if (counter1 == 0) {
						rt.setFill(Color.web("#1e1e1e"));
						counter1 = 1;
					} else {
						rt.setFill(Color.web("#323232"));
						counter1 = 0;
					}

				}

				gridPane.add(rt, x, y);

			}
			if (counter2 == 0) {
				counter2 = 1;
			} else {
				counter2 = 0;
			}
			counter = 0;
			counter1 = 0;

		}
	}

	public void drawSpot(GraphicsContext gc) {

		for (Spot spot : game.getSpots()) {
			switch (spot.getMarker()) {
			case BEAR:
				gc.drawImage(new Image(new File("resources/images/bear.png").toURI().toString()),
						spot.getX() * gridSpotSize, spot.getY() * gridSpotSize, gridSpotSize, gridSpotSize);
				break;
			case MOUSE:
				gc.drawImage(new Image(new File("resources/images/mouse.png").toURI().toString()),
						spot.getX() * gridSpotSize, spot.getY() * gridSpotSize, gridSpotSize, gridSpotSize);
				break;
			case FIRE:
				gc.drawImage(new Image(new File("resources/images/fire.png").toURI().toString()),
						spot.getX() * gridSpotSize, spot.getY() * gridSpotSize, gridSpotSize, gridSpotSize);

				break;
			}

		}

	}

	public void drawcanvas() {
		Canvas c = new Canvas(760, 600);
		this.getChildren().add(c);
		GraphicsContext gc = c.getGraphicsContext2D();
		drawSnake(gc);
		drawSpot(gc);
		this.getChildren().clear();
		this.getChildren().addAll(gridPane, c);

	}

	public void drawSnake(GraphicsContext gc) {
		gc.setFill(Color.RED);
		gc.fillOval((game.getSnake().getX()) * gridSpotSize, (game.getSnake().getY()) * gridSpotSize, gridSpotSize,
				gridSpotSize);
		for (BodyPart bp : game.getSnake().getSnakeBodyParts()) {

			gc.setFill(Color.ORANGE);
			gc.fillOval((bp.getX()) * gridSpotSize, (bp.getY()) * gridSpotSize, gridSpotSize, gridSpotSize);

		}
	}

}
