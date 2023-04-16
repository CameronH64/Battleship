package dataclasses;

import java.io.Serializable;

import javax.swing.JLabel;

public class CellLabel extends JLabel implements Serializable {
	
	private int position;
	private String shipCharacter;
	
	public CellLabel(int position) {
		
		this.position = position;
		shipCharacter = "";
		
	}

	public int getPosition() 							{ return position; }
	public void setPosition(int position) 				{ this.position = position; }
	public String getShipCharacter() 					{ return shipCharacter; }
	public void setShipCharacter(String shipCharacter) 	{ this.shipCharacter = shipCharacter; }
	
}
