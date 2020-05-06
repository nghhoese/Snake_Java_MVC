package Model;

public class Spot {
private int y;
private int x;
private Marker marker;
public Spot(int x,int y,Marker marker) {
	this.x = x;
	this.y = y;
	this.marker = marker;
}
public int getY() {
	return y;
}
public int getX() {
	return x;
}
public Marker getMarker() {
	return marker;
}

}
