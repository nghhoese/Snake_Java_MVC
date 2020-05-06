package Model;

public class BodyPart {

	private int x;
	private int y;
	private int oldX;
	private int oldY;;

	public BodyPart(int x, int y) {
		this.x = x;
		this.y = y;
		oldX = 0;
		oldY = 0;

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

	public void setX(int x) {
		this.x = x;

	}

	public void setY(int y) {
		this.y = y;

	}

//
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
