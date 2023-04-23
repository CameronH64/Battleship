package gameplaycontrol;

import javax.swing.*;

import client.BattleshipClient;
import dataclasses.PlacementLabel;
import dataclasses.MainMenuLoginData;
import dataclasses.OceanLabel;
import dataclasses.ShotFiredData;
import dataclasses.TargetLabel;
import gameplaypanel.GameplayPanel;
import shipplacementpanel.ShipPlacementPanel;

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
	private GameplayPanel gameplayPanel;
	private ArrayList<TargetLabel> targetLabels;
	private ArrayList<OceanLabel> oceanLabels;	
	
	
	// This constructor connects the outside components so that the control panel can affect things.
	public GameplayControl(JPanel container, BattleshipClient battleshipClient)
	{
		this.container = container;
		this.battleshipClient = battleshipClient;
		
	}
	
	public void setGameplayPanel(GameplayPanel gameplayPanel) {
		
		this.gameplayPanel = gameplayPanel;
		
	}
	
	public ArrayList<TargetLabel> getTargetLabels() {
		return targetLabels;
	}

	public void setTargetLabels(ArrayList<TargetLabel> targetLabels) {
		this.targetLabels = targetLabels;
	}

	public ArrayList<OceanLabel> getOceanLabels() {
		return oceanLabels;
	}

	public void setOceanLabels(ArrayList<OceanLabel> oceanLabels) {
		this.oceanLabels = oceanLabels;
	}

	public GameplayPanel getGameplayPanel() {
		return gameplayPanel;
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
			System.out.println("[CLIENT] NEW GAME PRESSED");
			
			
			
		} else if (command == "Quit") {
			
			System.out.println("[CLIENT] QUIT PRESSED");
			
			// Be sure to exit cleanly.
			
			try {
				battleshipClient.closeConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.exit(0);
				
		}
		
		
		
		// Code for enabling the New Game button
//		JButton newGameButton = gameplayPanel.getNewGameButton();
//		newGameButton.setEnabled(true);
		
				
//		CardLayout cardLayout = (CardLayout)container.getLayout();
//		cardLayout.show(container, "1");

		
		
	}


}
