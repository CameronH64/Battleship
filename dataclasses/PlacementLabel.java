package dataclasses;

import java.io.Serializable;

import javax.swing.JLabel;

public class PlacementLabel extends JLabel implements Serializable {
	
	private int position;			// 0-99 for the GridLayout
	private String shipCharacter;	// The character that is there.
	
	public PlacementLabel(int position) {
		
		this.position = position;
		shipCharacter = "";
		
	}

	public int getPosition() 							{ return position; }
	public void setPosition(int position) 				{ this.position = position; }
	public String getShipCharacter() 					{ return shipCharacter; }
	public void setShipCharacter(String shipCharacter) 	{ this.shipCharacter = shipCharacter; }
	
}
