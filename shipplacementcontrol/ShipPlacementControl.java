package shipplacementcontrol;

import javax.swing.*;

import client.BattleshipClient;
import dataclasses.PlacementLabel;
import shipplacementpanel.ShipPlacementPanel;

import java.awt.CardLayout;
import java.awt.event.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class ShipPlacementControl implements ActionListener
{
	// Private data fields.
	private JPanel container;
	private BattleshipClient battleshipClient;
	
	private ShipPlacementPanel shipPlacementPanel;
	private ArrayList<PlacementLabel> placementLabels;
	
	// This constructor connects the outside components so that the control panel can affect things.
	public ShipPlacementControl(JPanel container, BattleshipClient battleshipClient)
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
		if (command == "Confirm Placement")
		{
			
			// Use ship validation algorithm here; return a boolean.
			
			// If valid, switch.
			
			// If not, change the test on the panel.
			
			
			
			CardLayout cardLayout = (CardLayout)container.getLayout();
			cardLayout.show(container, "6");
		}

		// Have more if-else statements here for every action that happens (buttons, etc).
		
		
		
	}

	public ShipPlacementPanel getShipPlacementPanel() {
		return shipPlacementPanel;
	}

	public void setShipPlacementPanel(ShipPlacementPanel shipPlacementPanel) {
		this.shipPlacementPanel = shipPlacementPanel;
	}

	public ArrayList<PlacementLabel> getPlacementLabels() {
		return placementLabels;
	}

	public void setPlacementLabels(ArrayList<PlacementLabel> placementLabels) {
		this.placementLabels = placementLabels;
	}
	
	

}
