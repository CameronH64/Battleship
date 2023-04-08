//Jared Bratton

package menucontrols;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import menupanels.CreateUserPanel;
import dataclasses.LoginData;
import client.BattleshipClient;

public class CreateUserControl implements ActionListener
{
	// Private data fields.
	private JPanel container;
	private CreateUserPanel createUserMenu;
	private BattleshipClient client;

	// This constructor connects the outside components so that the control panel can affect things.
	public CreateUserControl(JPanel container)
	{
		this.container = container;
	}

	// Handle button clicks.
	public void actionPerformed(ActionEvent ae)
	{

		// Get the name of the button clicked.
		String command = ae.getActionCommand();

		// Buttons
		if (command == "Create User")
		{
			JTextField fieldUsername = createUserMenu.getFieldUsername();
			JTextField fieldPassword = createUserMenu.getFieldPassword();
			JLabel labelStatusResponse = createUserMenu.getLabelStatusResponse();
			LoginData credentials = new LoginData(fieldUsername.getText(), fieldPassword.getText());
			//client.sendToServer(credentials);

			labelStatusResponse.setForeground(Color.black);
			labelStatusResponse.setText("Sent account creation request. Waiting on response...");
			System.out.println("Client attempting to create new account");

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
	public CreateUserPanel getCreateUserMenu() {
		return createUserMenu;
	}

	public void setCreateUserMenu(CreateUserPanel createUserMenu) {
		this.createUserMenu = createUserMenu;
	}

	public BattleshipClient getClient() {
		return client;
	}

	public void setClient(BattleshipClient client) {
		this.client = client;
	}

}
