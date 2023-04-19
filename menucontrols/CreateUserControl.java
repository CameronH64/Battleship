//Jared Bratton

package menucontrols;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import menupanels.CreateUserPanel;
import dataclasses.MainMenuLoginData;
import client.BattleshipClient;

public class CreateUserControl implements ActionListener
{
	// Private data fields.
	private JPanel container;
	private CreateUserPanel createUserMenu;
	private BattleshipClient battleshipClient;

	// This constructor connects the outside components so that the control panel can affect things.
	public CreateUserControl(JPanel container, BattleshipClient battleshipClient)
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
		if (command == "Create User")
		{
			JTextField fieldUsername = createUserMenu.getFieldUsername();
			JTextField fieldPassword = createUserMenu.getFieldPassword();
			JLabel labelStatusResponse = createUserMenu.getLabelStatusResponse();
			MainMenuLoginData credentials = new MainMenuLoginData(fieldUsername.getText(), fieldPassword.getText());
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
		return battleshipClient;
	}

	public void setClient(BattleshipClient client) {
		this.battleshipClient = client;
	}

}
