package dataclasses;

import javax.swing.JLabel;

public class CellLabel extends JLabel {
	
	private int position;
	
	public CellLabel(int position) {
		
		this.position = position;
		
	}

	public int getPosition() 				{ return position; }
	public void setPosition(int position) 	{ this.position = position; }
	
}
