package gameplaycontrol;

import javax.swing.*;

import client.BattleshipClient;
import dataclasses.CellLabel;
import dataclasses.LoginData;
import dataclasses.ShotFiredData;
import gameplaypanel.GameplayPanel;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class TestingPanelSwitchControl implements ActionListener
{
	// Private data fields.
	private JPanel container;
	private BattleshipClient battleshipClient;
	
	// This constructor connects the outside components so that the control panel can affect things.
	public TestingPanelSwitchControl(JPanel container, BattleshipClient battleshipClient)
	{
		this.container = container;
		this.battleshipClient = battleshipClient;
	}	
	
	
	public void actionPerformed(ActionEvent ae)
	{
		
		
		// Get the name of the button clicked.
		String command = ae.getActionCommand();
		
		
		
		if (command == "Connect") {
			
//			ClientNumberRequest request = new ClientNumberRequest();
//			
//			try {
//				battleshipClient.sendToServer(request);
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			
			
			
			battleshipClient.setPort(8300);
			
			try {
				battleshipClient.openConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
						
		} else if (command == "Switch") {
			
			System.out.println("Switching panels...");
			
			CardLayout cardLayout = (CardLayout)container.getLayout();
			cardLayout.layoutContainer(container);
			
			cardLayout.show(container, "6");
			
			
		}
		
		// Testing code. Use this code to switch to another JPanel. (Make a new JPanel for yourself for testing.
		
//		CardLayout cardLayout = (CardLayout)container.getLayout();
//		cardLayout.show(container, "1");

		
	}


}
