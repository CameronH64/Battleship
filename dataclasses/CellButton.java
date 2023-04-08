package dataclasses;

import javax.swing.JButton;

public class CellButton extends JButton {
	
	private int position;
	
	public CellButton(int position) {
		
		this.position = position;
		
	}

	public int getPosition() 				{ return position; }
	public void setPosition(int position) 	{ this.position = position; }
	
}
