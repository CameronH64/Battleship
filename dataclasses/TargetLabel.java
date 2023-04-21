package dataclasses;

import java.io.Serializable;

import javax.swing.JLabel;

public class TargetLabel extends JLabel implements Serializable {
	
	private int position;			// 0-99 for the GridLayout
	private String hitCharacter;		// "0" for nothing, "1" for hit (red), and "2" for miss (white).
	
	public TargetLabel(int position, String hitCharacter) {
		this.position = position;
		this.hitCharacter = hitCharacter;
	}
	
	public TargetLabel(int position) {
		this.position = position;
		hitCharacter = "0";
	}
	
	public int getPosition() 							{ return position; }
	public void setPosition(int position) 				{ this.position = position; }
	public String getHitCharacter() 					{ return hitCharacter; }
	public void setHitCharacter(String hitCharacter) 	{ this.hitCharacter = hitCharacter; }
	
}
