package dataclasses;

public class ShotFiredData {
	
	private int x;
	private int y;
	
	ShotFiredData(int x, int y){
		
		this.x = x;
		this.y = y;
		
	}
	
	// Getters and Setters
	public int getX() { return x; }
	public int getY() { return y; }
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	
	
}
