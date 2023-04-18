package dataclasses;

import java.io.Serializable;

public class ShotReceivedData implements Serializable {
	
	private int position;
	
	public ShotReceivedData(int position){
		
		this.position = position;
		
	}
	
	// Getters and Setters
	public int getPosition() { return position; }
	public void setPosition(int position) { this.position = position; }
	
	
}
