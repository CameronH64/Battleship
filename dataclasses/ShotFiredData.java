package dataclasses;

import java.io.Serializable;

public class ShotFiredData implements Serializable {
	
	private int position;
	
	public ShotFiredData(int position){
		
		this.position = position;
		
	}
	
	// Getters and Setters
	public int getPosition() { return position; }
	public void setPosition(int position) { this.position = position; }
	
	
}
