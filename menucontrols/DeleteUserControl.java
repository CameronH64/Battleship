//Jared Bratton

package menucontrols;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import menupanels.DeleteUserPanel;
import dataclasses.DeleteUserData;
import client.BattleshipClient;

public class DeleteUserControl implements ActionListener
{
	// Private data fields.
	private JPanel container;
	private DeleteUserPanel deleteUserMenu;
	private JLabel mainMenuStatus;
	private BattleshipClient battleshipClient;

	// This constructor connects the outside components so that the control panel can affect things.
	public DeleteUserControl(JPanel container, BattleshipClient battleshipClient)
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
		if (command == "Delete User")
		{
			JTextField fieldUsername = deleteUserMenu.getFieldUsername();
			JTextField fieldPassword = deleteUserMenu.getFieldPassword();
			JLabel labelStatusResponse = deleteUserMenu.getLabelStatusResponse();
			DeleteUserData credentials = new DeleteUserData(fieldUsername.getText(), fieldPassword.getText());
			
			//Send the info to the server!
			try 
			{
				battleshipClient.sendToServer(credentials);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}

			labelStatusResponse.setForeground(Color.black);
			labelStatusResponse.setText("Sent deletion request to server. Waiting on response...");
			System.out.println("Client attempting to delete account");

		} 
		else if (command == "Main Menu") 
		{
			CardLayout cardLayout = (CardLayout)container.getLayout();
			cardLayout.show(container, "1");
			mainMenuStatus.setForeground(Color.BLACK);
			mainMenuStatus.setText("Eneter credentials to log in.");

		}
		else if (command == "Quit")
		{
			System.exit(0);

		}

	}


	/*
	 * Setters and Getters beyond this point
	 */
	public DeleteUserPanel getDeleteUserMenu() {
		return deleteUserMenu;
	}

	public void setDeleteUserMenu(DeleteUserPanel deleteUserMenu) {
		this.deleteUserMenu = deleteUserMenu;
	}

	public BattleshipClient getClient() {
		return battleshipClient;
	}

	public void setClient(BattleshipClient client) {
		this.battleshipClient = client;
	}

	public JLabel getMainMenuStatus() {
		return mainMenuStatus;
	}

	public void setMainMenuStatus(JLabel mainMenuStatus) {
		this.mainMenuStatus = mainMenuStatus;
	}

}
