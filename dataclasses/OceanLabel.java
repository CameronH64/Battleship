package dataclasses;

import java.io.Serializable;

import javax.swing.JLabel;

public class OceanLabel extends JLabel implements Serializable {
	
	private int position;			// 0-99 for the GridLayout
	private String shipCharacter;	// The character that is there.
	
	public OceanLabel(int position) {
		
		this.position = position;
		shipCharacter = "";
	}
	
	public OceanLabel(int position, String shipCharacter) {
		
		this.position = position;
		this.shipCharacter = shipCharacter;
		
	}

	public int getPosition() 							{ return position; }
	public void setPosition(int position) 				{ this.position = position; }
	public String getShipCharacter() 					{ return shipCharacter; }
	public void setShipCharacter(String shipCharacter) 	{ this.shipCharacter = shipCharacter; }
	
}
