package gameplaycontrol;

import javax.swing.*;

import client.BattleshipClient;
import dataclasses.CellLabel;
import dataclasses.LoginData;
import dataclasses.ShotFiredData;
import gameplaypanel.GameplayPanel;

import java.awt.CardLayout;
import java.awt.event.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class GameplayControl implements ActionListener
{
	// Private data fields.
	private JPanel container;
	private BattleshipClient battleshipClient;
	private ArrayList<CellLabel> gameplayCellLabels;
	
	// This constructor connects the outside components so that the control panel can affect things.
	public GameplayControl(JPanel container, BattleshipClient battleshipClient)
	{
		this.container = container;
		this.battleshipClient = battleshipClient;
	}
	
	public void setCellLabels(ArrayList<CellLabel> gameplayCellLabels) {
		
		this.gameplayCellLabels = gameplayCellLabels;
		
	}
	
	
	
	public JPanel getContainer() {
		return container;
	}

	public void setContainer(JPanel container) {
		this.container = container;
	}

	public BattleshipClient getBattleshipClient() {
		return battleshipClient;
	}

	public void setBattleshipClient(BattleshipClient battleshipClient) {
		this.battleshipClient = battleshipClient;
	}

	public void actionPerformed(ActionEvent ae)
	{
		
		
		// Get the name of the button clicked.
		String command = ae.getActionCommand();
		
		
		
		// The Cancel button takes the user back to the initial panel.
		if (command == "Connect")
		{
			System.out.println("Client-side: Connect pressed.");
			
			battleshipClient.setPort(8300);
			
			try {
				battleshipClient.openConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		// Testing code. Use this code to switch to another JPanel. (Make a new JPanel for yourself for testing.
		
//		CardLayout cardLayout = (CardLayout)container.getLayout();
//		cardLayout.show(container, "1");

		
	}


}
