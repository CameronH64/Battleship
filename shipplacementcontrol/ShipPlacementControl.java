package shipplacementcontrol;

import javax.swing.*;

import java.awt.CardLayout;
import java.awt.event.*;
import java.io.IOException;
import java.io.Serializable;

public class ShipPlacementControl implements ActionListener
{
	// Private data fields.
	private JPanel container;
	
	// This constructor connects the outside components so that the control panel can affect things.
	public ShipPlacementControl(JPanel container)
	{
		this.container = container;
	}

	// Handle button clicks.
	public void actionPerformed(ActionEvent ae)
	{
		
		
		// Get the name of the button clicked.
		String command = ae.getActionCommand();
		
		
		
		// The Cancel button takes the user back to the initial panel.
		if (command == "Confirm Placement")
		{
			System.out.println("Confirm placement pressed.");
			
		}		

		// Have more if-else statements here for every action that happens (buttons, etc).
		
		
		
	}

}
