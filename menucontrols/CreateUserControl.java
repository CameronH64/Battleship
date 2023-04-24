//Jared Bratton

package menucontrols;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import menupanels.CreateUserPanel;
import dataclasses.CreateUserData;
import client.BattleshipClient;

public class CreateUserControl implements ActionListener
{
	// Private data fields.
	private JPanel container;
	private CreateUserPanel createUserMenu;
	private JLabel mainMenuStatus;
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
			JTextField fieldConfirmPassword = createUserMenu.getFieldConfirmPassword();
			JLabel labelStatusResponse = createUserMenu.getLabelStatusResponse();
			CreateUserData credentials = new CreateUserData(fieldUsername.getText(), fieldPassword.getText());
			if (fieldConfirmPassword.getText().equals(fieldPassword.getText()))
			{
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
				labelStatusResponse.setText("Sent account creation request. Waiting on response...");
				System.out.println("Client attempting to create new account");
			}
			else 
			{
				labelStatusResponse.setForeground(Color.RED);
				labelStatusResponse.setText("Make sure passwords match!");
			}

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

	public JLabel getMainMenuStatus() {
		return mainMenuStatus;
	}

	public void setMainMenuStatus(JLabel mainMenuStatus) {
		this.mainMenuStatus = mainMenuStatus;
	}

}
