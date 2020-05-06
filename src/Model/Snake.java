package Model;

import java.util.ArrayList;

public class Snake {
	private ArrayList<BodyPart> snakeBodyParts;
	private int x;
	private int y;
	private int oldX;
	private int oldY;
	private Direction direction;

	public Snake(int x, int y, Direction direction) {
		snakeBodyParts = new ArrayList<>();
		createSnakeBodyParts();
		this.x = x;
		this.y = y;
		oldX = 0;
		oldY = 0;
		this.direction = direction;

	}

	public int getOldX() {
		return oldX;
	}

	public void setOldX(int oldX) {
		this.oldX = oldX;
	}

	public int getOldY() {
		return oldY;
	}

	public void setOldY(int oldY) {
		this.oldY = oldY;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void createSnakeBodyParts() {

		getSnakeBodyParts().add(new BodyPart(5, 2));
		getSnakeBodyParts().add(new BodyPart(4, 2));
		getSnakeBodyParts().add(new BodyPart(3, 2));
		getSnakeBodyParts().add(new BodyPart(2, 2));
	}

	public ArrayList<BodyPart> getSnakeBodyParts() {
		return snakeBodyParts;
	}

	public void removeBodyPart(int amount) {
		int index = 0;

		while (index < amount) {
			this.getSnakeBodyParts().remove(this.getSnakeBodyParts().size() - 1);
			index++;
		}
	}

	public void addBodyPart(int amount) {
		int lastindex = this.getSnakeBodyParts().size() - 1;
		int index = 0;
		while (index < amount) {
			getSnakeBodyParts().add(new BodyPart(this.getSnakeBodyParts().get(lastindex).getX(),
					this.getSnakeBodyParts().get(lastindex).getY()));
			index++;
		}
	}

	public boolean CheckForCollision() {
		for (BodyPart bp : getSnakeBodyParts()) {
			if ((bp.getX() == this.getX() && bp.getY() == this.getY()) || (this.getX() * 40) > 760 || this.getX() < 0
					|| (this.getY() * 40) > 600 || this.getY() < 0) {
				return true;
			}
		}
		return false;

	}

	public void moveHead() {
		this.setOldX(this.getX());
		this.setOldY(this.getY());
		switch (this.getDirection()) {
		case UP:

			this.setY(this.getY() - 1);

			break;
		case DOWN:
			this.setY(this.getY() + 1);

			break;
		case LEFT:
			this.setX(this.getX() - 1);

			break;
		case RIGHT:
			this.setX(this.getX() + 1);

			break;

		}
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

}
