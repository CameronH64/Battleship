//Jared Bratton

package menucontrols;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import menupanels.MainMenuPanel;

public class MainMenuControl implements ActionListener
{
	// Private data fields.
	private JPanel container;
	private MainMenuPanel mainMenu;

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
			/*
			 * The buttons and text fields should only be enabled 
			 * once the user is connected to a server. For now, 
			 * just setting them to enabled when connect is pressed 
			 * will allow debugging display on other panels
			 */
			//Get stuff to modify enabled-ness
			JButton buttonGoToCreateUser = mainMenu.getButtonGoToCreateUser();
			JButton buttonGoToDeleteUser = mainMenu.getButtonGoToDeleteUser();
			JTextField fieldUsername = mainMenu.getFieldUsername();
			JTextField fieldPassword = mainMenu.getFieldPassword();
			JLabel labelStatusResponse = mainMenu.getLabelStatusResponse();
			//Set them to enabled and editable as appropriate 
			buttonGoToCreateUser.setEnabled(true);
			buttonGoToDeleteUser.setEnabled(true);
			fieldUsername.setEditable(true);
			fieldPassword.setEditable(true);
			labelStatusResponse.setText("Connected!");
			labelStatusResponse.setForeground(Color.GREEN);
		}


		// Have more if-else statements here for every action that happens (buttons, etc).



	}

	public MainMenuPanel getMainMenu() {
		return mainMenu;
	}

	public void setMainMenu(MainMenuPanel mainMenu) {
		this.mainMenu = mainMenu;
	}

}
