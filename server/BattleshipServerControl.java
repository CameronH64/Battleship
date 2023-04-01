package server;

import javax.swing.*;

import java.awt.CardLayout;
import java.awt.event.*;
import java.io.IOException;
import java.io.Serializable;

public class BattleshipServerControl implements ActionListener
{
	// Private data fields.
	private JPanel container;
	private BattleshipServer server;
	
	// This constructor connects the outside components so that the control panel can affect things.
	public BattleshipServerControl(JPanel container, BattleshipServer server)
	{
		this.container = container;
		this.server = server;
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
			
		} else if (command == "Main Menu") {
			
			CardLayout cardLayout = (CardLayout)container.getLayout();
			cardLayout.show(container, "1");
			
			
		} else if (command == "Create User") {
			
			System.out.println("User created!");
			
			
		} else if (command == "Quit") {
		
			System.exit(0);
		
		
		}
		

		// Have more if-else statements here for every action that happens (buttons, etc).
		
		
		
	}

}