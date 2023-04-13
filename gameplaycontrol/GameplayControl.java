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
		if (command == "New Game")
		{
			System.out.println("Client-side: New Game pressed.");
			
			
			
		} else if (command == "Quit") {
			
			System.out.println("Client-side: Quit pressed.");
			
			GameplayPanel panel = (GameplayPanel)container.getComponent(0);
			JButton temp = panel.getNewGameButton();
			
			temp.setVisible(true);
			
			
		} else if (command == "Connect") {
			
			try {
				battleshipClient.openConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		// Testing code. Use this code to switch to another JPanel. (Make a new JPanel for yourself for testing.

		// Can set the New Game button to be visible!
		
//		GameplayPanel panel = (GameplayPanel)container.getComponent(1);
//		JButton temp = panel.getNewGameButton();
//		
//		temp.setVisible(true);

		
//		CardLayout cardLayout = (CardLayout)container.getLayout();
//		cardLayout.show(container, "1");

		
	}


}
