//Jared Bratton

package menucontrols;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import menupanels.MainMenuPanel;
import dataclasses.LoginData;
import client.BattleshipClient;

public class MainMenuControl implements ActionListener
{
	// Private data fields.
	private JPanel container; 
	private MainMenuPanel mainMenu; 
	private BattleshipClient battleshipClient; 

	// This constructor connects the outside components so that the control panel can affect things.
	public MainMenuControl(JPanel container, BattleshipClient battleshipClient)
	{
		this.container = container;
		this.battleshipClient = battleshipClient;
	}

	// Handle button clicks.
	public void actionPerformed(ActionEvent ae)
	{

		// Get the name of the button clicked.
		String command = ae.getActionCommand();

		// Buttons
		if (command == "Log In")
		{
			JTextField fieldUsername = mainMenu.getFieldUsername();
			JTextField fieldPassword = mainMenu.getFieldPassword();
			JLabel labelStatusResponse = mainMenu.getLabelStatusResponse();
			LoginData credentials = new LoginData(fieldUsername.getText(), fieldPassword.getText());
			//client.sendToServer(credentials);

			labelStatusResponse.setForeground(Color.black);
			labelStatusResponse.setText("Sent credentials to server. Waiting on response...");
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
			JTextField fieldIPAddress = mainMenu.getFieldIPAddress();
			JLabel labelStatusResponse = mainMenu.getLabelStatusResponse();
			//Set them to enabled and editable as appropriate 
			buttonGoToCreateUser.setEnabled(true);
			buttonGoToDeleteUser.setEnabled(true);
			fieldUsername.setEditable(true);
			fieldPassword.setEditable(true);
			labelStatusResponse.setForeground(Color.black);
			labelStatusResponse.setText("Connected to Battleship Server.");

			battleshipClient.setHost(fieldIPAddress.getText());
			try {
				battleshipClient.openConnection();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Error happened!");
			}
			
			
			
//			System.out.println("Client attempting to connect to server");
		}

	}


	/*
	 * Setters and Getters beyond this point
	 */
	public MainMenuPanel getMainMenu() {
		return mainMenu;
	}

	public void setMainMenu(MainMenuPanel mainMenu) {
		this.mainMenu = mainMenu;
	}

	public BattleshipClient getClient() {
		return battleshipClient;
	}

	public void setClient(BattleshipClient client) {
		this.battleshipClient = client;
	}

}
