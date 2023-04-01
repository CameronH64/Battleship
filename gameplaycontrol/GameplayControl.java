package gameplaycontrol;

import javax.swing.*;

import client.BattleshipClient;

import java.awt.CardLayout;
import java.awt.event.*;
import java.io.IOException;
import java.io.Serializable;

public class GameplayControl implements ActionListener
{
	// Private data fields.
	private JPanel container;
	private BattleshipClient battleshipClient;
	
	// This constructor connects the outside components so that the control panel can affect things.
	public GameplayControl(JPanel container, BattleshipClient battleshipClient)
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
		if (command == "Press Me")
		{
			System.out.println("Button pressed.");
			
		}
		
		// Testing code. Use this code to switch to another JPanel. (Make a new JPanel for yourself for testing.
		
//		CardLayout cardLayout = (CardLayout)container.getLayout();
//		cardLayout.show(container, "1");

		
	}

}
