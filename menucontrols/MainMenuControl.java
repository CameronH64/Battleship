//Jared Bratton

package menucontrols;

import javax.swing.*;

import java.awt.CardLayout;
import java.awt.event.*;
import java.io.IOException;
import java.io.Serializable;

public class MainMenuControl implements ActionListener
{
	// Private data fields.
	private JPanel container;

	// This constructor connects the outside components so that the control panel can affect things.
	public MainMenuControl(JPanel container)
	{
		this.container = container;
	}

	// Handle button clicks.
	public void actionPerformed(ActionEvent ae)
	{


		// Get the name of the button clicked.
		String command = ae.getActionCommand();



		// The Cancel button takes the user back to the initial panel.
		if (command == "Log In")
		{
			System.out.println("Client attempting to log in");

		} 
		else if (command == "Create User") 
		{
			CardLayout cardLayout = (CardLayout)container.getLayout();
			cardLayout.show(container, "2");

		} 
		else if (command == "Delete User") 
		{
			CardLayout cardLayout = (CardLayout)container.getLayout();
			cardLayout.show(container, "3");

		}
		else if (command == "Quit")
		{
			System.exit(0);
		}
		else if (command == "Connect")
		{
			System.out.println("Client attempting to connect to server");
		}


		// Have more if-else statements here for every action that happens (buttons, etc).



	}

}
