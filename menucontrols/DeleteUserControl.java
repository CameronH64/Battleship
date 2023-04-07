//Jared Bratton

package menucontrols;

import javax.swing.*;
import java.awt.CardLayout;
import java.awt.event.*;
import java.io.*;
import menupanels.DeleteUserPanel;

public class DeleteUserControl implements ActionListener
{
	// Private data fields.
	private JPanel container;
	private DeleteUserPanel deleteUserMenu;

	// This constructor connects the outside components so that the control panel can affect things.
	public DeleteUserControl(JPanel container)
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

		} else if (command == "Main Menu") 
		{
			CardLayout cardLayout = (CardLayout)container.getLayout();
			cardLayout.show(container, "1");

		} else if (command == "Delete User") 
		{
			System.out.println("User deleted!");

		} 
		else if (command == "Quit")
		{
			System.exit(0);

		}


		// Have more if-else statements here for every action that happens (buttons, etc).



	}

	public DeleteUserPanel getDeleteUserMenu() {
		return deleteUserMenu;
	}

	public void setDeleteUserMenu(DeleteUserPanel deleteUserMenu) {
		this.deleteUserMenu = deleteUserMenu;
	}

}
