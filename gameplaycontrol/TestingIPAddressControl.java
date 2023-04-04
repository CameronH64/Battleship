package gameplaycontrol;

import javax.swing.*;

import client.BattleshipClient;
import dataclasses.LoginData;
import dataclasses.ShotFiredData;
import gameplaypanel.TestingIPAddressPanel;

import java.awt.CardLayout;
import java.awt.event.*;
import java.io.IOException;
import java.io.Serializable;

public class TestingIPAddressControl implements ActionListener
{
	// Private data fields.
	private JPanel container;
	private BattleshipClient battleshipClient;
	
	// This constructor connects the outside components so that the control panel can affect things.
	public TestingIPAddressControl(JPanel container, BattleshipClient battleshipClient)
	{
		this.container = container;
		this.battleshipClient = battleshipClient;
	}

	// Handle button clicks.
	public void actionPerformed(ActionEvent ae)
	{
		
		
		// Get the name of the button clicked.
		String command = ae.getActionCommand();
		
		
		
		// The Cancel button takes the user back to the initial panel.
		if (command == "Quit")
		{	
			System.exit(0);
			
		} else if (command == "Connect") {
			
			// Set the port
			battleshipClient.setPort(8300);
			battleshipClient.setHost("localhost");
			
			// Set the hostname
//			TestingIPAddressPanel testingIPAddressPanel = (TestingIPAddressPanel)container.getComponent(1);
//			String hostname = testingIPAddressPanel.getHostname();
//			battleshipClient.setHost(hostname);
			
			
			try {
				battleshipClient.openConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Invalid hostname.");
			}
			
			if (battleshipClient.isConnected()){
				CardLayout cardLayout = (CardLayout)container.getLayout();
				cardLayout.show(container, "7");
			}
			
			
		}		// Testing code. Use this code to switch to another JPanel. (Make a new JPanel for yourself for testing.
		
//		CardLayout cardLayout = (CardLayout)container.getLayout();
//		cardLayout.show(container, "1");

		
	}

}
