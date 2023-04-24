//Jared Bratton

package menucontrols;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import menupanels.MainMenuPanel;
import dataclasses.MainMenuLoginData;
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
		JLabel labelStatusResponse = mainMenu.getLabelStatusResponse();

		// Buttons
		if (command == "Log In")
		{
			JTextField fieldUsername = mainMenu.getFieldUsername();
			JTextField fieldPassword = mainMenu.getFieldPassword();

			MainMenuLoginData credentials = new MainMenuLoginData(fieldUsername.getText(), fieldPassword.getText());

			try 
			{
				battleshipClient.sendToServer(credentials);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}

			labelStatusResponse.setForeground(Color.black);
			labelStatusResponse.setText("Sent credentials to server.");

		} 
		else if (command == "Create User") 
		{
			labelStatusResponse.setText("Changing to Create User menu.");
			try 
			{
				CardLayout cardLayout = (CardLayout)container.getLayout();
				cardLayout.show(container, "3");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

		} 
		else if (command == "Delete User") 
		{
			labelStatusResponse.setText("Changing to Delete User menu.");
			try 
			{
				CardLayout cardLayout = (CardLayout)container.getLayout();
				cardLayout.show(container, "2");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

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
			//Set them to enabled and editable as appropriate 
			buttonGoToCreateUser.setEnabled(true);
			buttonGoToDeleteUser.setEnabled(true);
			fieldUsername.setEditable(true);
			fieldPassword.setEditable(true);
			labelStatusResponse.setForeground(Color.black);
			labelStatusResponse.setText("Connected to Battleship Server.");
			labelStatusResponse.setForeground(Color.GREEN);

			JButton loginButton = mainMenu.getButtonLogIn();
			loginButton.setEnabled(true);

			if (fieldIPAddress.getText().equals("")) {

				try {
					battleshipClient.openConnection();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				

			} else {

				// Because there's something in the IP address field, open the connection with that.
				battleshipClient.setHost(fieldIPAddress.getText());

				try {
					battleshipClient.openConnection();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

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
