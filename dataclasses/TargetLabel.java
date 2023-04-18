package dataclasses;

import java.io.Serializable;

import javax.swing.JLabel;

public class TargetLabel extends JLabel implements Serializable {
	
	private int position;			// 0-99 for the GridLayout
	private String shipCharacter;	// The character that is there.
	
	public TargetLabel(int position) {
		
		this.position = position;
		
	}

	public int getPosition() 							{ return position; }
	public void setPosition(int position) 				{ this.position = position; }
	public String getShipCharacter() 					{ return shipCharacter; }
	public void setShipCharacter(String shipCharacter) 	{ this.shipCharacter = shipCharacter; }
	
}