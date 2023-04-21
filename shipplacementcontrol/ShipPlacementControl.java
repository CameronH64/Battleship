package shipplacementcontrol;

import javax.swing.*;

import client.BattleshipClient;
import dataclasses.PlacementLabel;
import dataclasses.ShipPlacementData;
import shipplacementpanel.ShipPlacementPanel;

import java.awt.CardLayout;
import java.awt.event.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class ShipPlacementControl implements ActionListener
{
	// Private data fields.
	private JPanel container;
	private BattleshipClient battleshipClient;
	
	private ShipPlacementPanel shipPlacementPanel;
	
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
			
			
			// Send an ArrayList<String> to the server. This is the necessary information needed for the OceanGrid.
			
			ArrayList<PlacementLabel> placementLabels = shipPlacementPanel.getPlacementLabels();			// Returns an ArrayList.
			ShipPlacementData shipPlacementData = new ShipPlacementData();
			
			shipPlacementData.setShipConfigurationFromPlacement(placementLabels);
			
			try {
				battleshipClient.sendToServer(shipPlacementData);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
	
}
