//Jared Bratton

package menucontrols;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import menupanels.DeleteUserPanel;
import dataclasses.LoginData;
import client.BattleshipClient;

public class DeleteUserControl implements ActionListener
{
	// Private data fields.
	private JPanel container;
	private DeleteUserPanel deleteUserMenu;
	private BattleshipClient client;

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

		// Buttons
		if (command == "Delete User")
		{
			JTextField fieldUsername = deleteUserMenu.getFieldUsername();
			JTextField fieldPassword = deleteUserMenu.getFieldPassword();
			JLabel labelStatusResponse = deleteUserMenu.getLabelStatusResponse();
			LoginData credentials = new LoginData(fieldUsername.getText(), fieldPassword.getText());
			//client.sendToServer(credentials);

			labelStatusResponse.setForeground(Color.black);
			labelStatusResponse.setText("Sent deletion request to server. Waiting on response...");
			System.out.println("Client attempting to delete account");

		} 
		else if (command == "Main Menu") 
		{
			CardLayout cardLayout = (CardLayout)container.getLayout();
			cardLayout.show(container, "1");

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
		return client;
	}

	public void setClient(BattleshipClient client) {
		this.client = client;
	}

}
