package gameplaypanel;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.Serializable;

public class CreateUserControl implements ActionListener
{
	// Private data fields.
	private JPanel container;
	
	// This constructor connects the outside components so that the control panel can affect things.
	CreateUserControl(JPanel container)
	{
		this.container = container;
	}

	// Handle button clicks.
	public void actionPerformed(ActionEvent ae)
	{
		
		
		// Get the name of the button clicked.
		String command = ae.getActionCommand();
		
		
		
		// The Cancel button takes the user back to the initial panel.
		if (command == "Press Me")
		{
			System.out.println("Test");
			
		}

		// Have more if-else statements here for every action that happens (buttons, etc).
		
		
		
	}

}
