package Model;

import java.util.ArrayList;
import java.util.Random;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Game {
private Snake snake;
private ArrayList<Spot> spots;
private Marker marker;
private Random rand;
private int gameTime;
private String gameTimeFormatted;
private SimpleStringProperty time;
private SimpleDoubleProperty gameSpeed;
	public Game() {
		time = new SimpleStringProperty("00:00.000");
		gameTime = 0;
		gameTimeFormatted = "";
		rand  = new Random();
		gameSpeed = new SimpleDoubleProperty(1);
		
		
		snake = new Snake(6,2,Direction.RIGHT);
		spots = new ArrayList<>();
	}
	public SimpleStringProperty getTime() {
		return time;
	}
	public SimpleDoubleProperty getGameSpeed() {
		return gameSpeed;
	}
	public String getGameTimeFormatted() {
		return gameTimeFormatted;
	}
	public void setGameTimeFormatted(String gameTimeFormatted) {
		this.gameTimeFormatted = gameTimeFormatted;
	}
	public int getGameTime() {
		return gameTime;
	}
	public void setGameTime(int gameTime) {
		this.gameTime = gameTime;
	}
	public Snake getSnake() {
		return snake;
	}
	public Boolean handleSpotsInGame() {
		//handle spots
		//return true if snake still alive
		for(Spot spot: this.getSpots()) {
			//if snake hits a spot
			if((this.getSnake().getX() == spot.getX()) && (this.getSnake().getY() == spot.getY())) {
				switch (spot.getMarker()) {
				case BEAR:
					
						//Calculate total lenght
					int totallenght = this.getSnake().getSnakeBodyParts().size() +1;
					//calculate parts to cut per requirement
					int partsToCut = (totallenght / 2) - 1;
					//remove parts per requirement
					this.getSnake().removeBodyPart(partsToCut);
					//remove spot from the game
					this.getSpots().remove(spot);
					//check if after bear ate part of snake size is not smaller than 5
					//if snake is smaller than 5 return false meaning its dead
					if((this.getSnake().getSnakeBodyParts().size() +1) < 5 ) {
						return false;
					}
					return true;
				case MOUSE:
					//add 5 bodyparts
					this.getSnake().addBodyPart(5);
					//remove spot from the game
					this.getSpots().remove(spot);
					//return true (alive)
					return true;
				case FIRE:
					//remove spot from the game
					this.getSpots().remove(spot);
					//return dead
					return false;
			
			

			}
			}
		}
		return true;
	}
	//creates random spots:
	//int amount is the amount of random spots
	public void createRandomSpots(int amount) {
		int index = 0;
		
		ArrayList<Marker> markers = new ArrayList<>();
		
		markers.add(marker.MOUSE);
		
		markers.add(marker.BEAR);
		
		markers.add(marker.FIRE);
		//creates random number 0-2 to grab random marker
	
		
		while(index < amount) {
			spots.add(new Spot(rand.nextInt(18),rand.nextInt(14),markers.get(rand.nextInt(3))));
			index++; 
		}
		
	}
	public ArrayList<Spot> getSpots() {
		return spots;
	}
	public void moveSnake() {
		this.getSnake().moveHead();
		int index = 0;
		while (index < this.getSnake().getSnakeBodyParts().size()) {
			if (index == 0) {
				//if first body part grab old positions from the head
				this.getSnake().getSnakeBodyParts().get(0)
						.setOldX(this.getSnake().getSnakeBodyParts().get(0).getX());
				this.getSnake().getSnakeBodyParts().get(0)
						.setOldY(this.getSnake().getSnakeBodyParts().get(0).getY());
				this.getSnake().getSnakeBodyParts().get(0).setX(this.getSnake().getOldX());
				this.getSnake().getSnakeBodyParts().get(0).setY(this.getSnake().getOldY());
			} else {
				//else grab old position of previous bodypart
				this.getSnake().getSnakeBodyParts().get(index)
						.setOldX(this.getSnake().getSnakeBodyParts().get(index).getX());
				this.getSnake().getSnakeBodyParts().get(index)
						.setOldY(this.getSnake().getSnakeBodyParts().get(index).getY());
				this.getSnake().getSnakeBodyParts().get(index)
						.setX(this.getSnake().getSnakeBodyParts().get(index - 1).getOldX());
				this.getSnake().getSnakeBodyParts().get(index)
						.setY(this.getSnake().getSnakeBodyParts().get(index - 1).getOldY());
			}
			index++;

		}
	}
	
	
	
}
