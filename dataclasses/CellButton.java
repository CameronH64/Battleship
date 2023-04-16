package dataclasses;

import java.io.Serializable;

import javax.swing.JButton;

public class CellButton extends JButton implements Serializable {
	
	private int position;
	
	public CellButton(int position) {
		
		this.position = position;
		
	}

	public int getPosition() 				{ return position; }
	public void setPosition(int position) 	{ this.position = position; }
	
}
