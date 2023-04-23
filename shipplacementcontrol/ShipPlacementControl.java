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

			if (checkValidFleetConfiguration(shipPlacementData.getShipConfiguration())) {
				
				
				try {
					battleshipClient.sendToServer(shipPlacementData);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
//				shipPlacementPanel.revalidate();
				
				CardLayout cardLayout = (CardLayout)container.getLayout();
				cardLayout.show(container, "6");
				
				
			} else {
				
				shipPlacementPanel.getErrorLabel().setText("Invalid ship configuration; try again.");
				
			}
			
			
		}

		// Have more if-else statements here for every action that happens (buttons, etc).
		
		
		
	}

	public ShipPlacementPanel getShipPlacementPanel() {
		return shipPlacementPanel;
	}

	public void setShipPlacementPanel(ShipPlacementPanel shipPlacementPanel) {
		this.shipPlacementPanel = shipPlacementPanel;
	}
	
public static boolean checkValidFleetConfiguration(ArrayList<String> flatFleet) {
		
		/*
		 * EXPLANATION:
		 * 
		 * There's no need to use such a complicated algorithm that I made up!
		 * We should instead use this much simpler algorithm (that I also made up)!
		 * 
		 * Take any 2D fleet configuration, and then flatten it to 1D.
		 * Battleships can only have two orientations: vertical or horizontal.
		 * 
		 * In this new 1D format:
		 * Horizontal ships's letters will be bunched together with no spaces in between them.
		 * Vertical ships's letters will be spaced EXACTLY THE WIDTH OF THE GAME BOARD (in this case, 10 spaces).
		 * 
		 * These principles hold for any number of ships, however long they are, and any size game board!
		 * 
		 * Example:
		 * 00000000000C0PP000000C000000000C00BBBB000C000B00000C000000000000D0S0000000D0S0000000D0S0000000000000
		 * This is a valid fleet configuration. Why?
		 * 
		 * Either:
		 * Each letter has exactly 10 spaces in between them OR
		 * Each letter is grouped together.
		 * 
		 * Pseudocode:
		 * Flatten 2D array to 1D array.
		 * 
		 * Check there are 17 non-zeros. If not, return false. (A brief, but necessary pre-validation)
		 * Cycle through each element.
		 * 
		 * When a letter is hit, check the next element to see if it matches.
		 * If it matches, it's horizontal. If not, it's vertical.
		 * 
		 * If horizontal, check that there are that many sequential letters.
		 * If vertical, check if there are that many sequential letters with 10 space in between them.
		 * 
		 * This actually inherently takes care of placing boats in an L-shape.
		 * The reason is because when a boat is discovered (its letter is found),
		 * The algorithm is expecting to find 5, 4, 3, or 2 spaces.
		 * And because of the 17 count check before the algorithm starts,
		 * the ships MUST be in this configuration.
		 * 
		 * */
		
		int totalShipSize = 0;
		
		int runningCarrierCount = 0;
		int runningBattleshipCount = 0;
		int runningDestroyerCount = 0;
		int runningSubmarineCount = 0;
		int runningPatrolCount = 0;
		
		
//		// Debugging; print out the whole 1D array.
//		for (String string : flatFleet) {
//			System.out.print(string);
//		}
		
		
		
		// A quick validation to ensure correct size of ships.
		for(int i = 0; i < 100; i++) {
			if(!flatFleet.get(i).equals("0")) { totalShipSize++; }
		}
		
		if (totalShipSize != 17) {
			return false;
		}
		
		
		
		// Do ship checking.
		// The comments for the carrier are the same for the other four ships.
		
		// Carrier
		for(int i = 0; i < 100; i++) {				// Cycle through 1D array.
			if(flatFleet.get(i) == "C") {			// ... Until it hits a carrier ship (represented by a C).
				if(flatFleet.get(i+1) == "C") {		// Check if orientation is vertical.
				
					for(int j = 0; j < 5; j++) {					// If vertical, check the C's in the downward direction.
						if(flatFleet.get(i+j).equals("C")) {
							runningCarrierCount++;
						}
					}
					break;
				} else {
					
					for(int j = 0; j < 5; j++) {	// Check if orientation is horizontal.	
						if(flatFleet.get(i+(j*10)).equals("C")) {		// If vertical, check the C's in the downward direction.
							runningCarrierCount++;
						}
					}
					break;
				}
			}
		}


		
		// Battleship
		for(int i = 0; i < 100; i++) {
			if(flatFleet.get(i) == "B") {
				if(flatFleet.get(i+1) == "B") {	// Check orientation of ship.
				
					for(int j = 0; j < 4; j++) {
						if(flatFleet.get(i+j).equals("B")) {
							runningBattleshipCount++;
						}
					}
					break;
				} else {
					
					for(int j = 0; j < 4; j++) {
						if(flatFleet.get(i+(j*10)).equals("B")) {
							runningBattleshipCount++;
						}
					}
					break;
				}
			}
		}
		

		// Destroyer
		for(int i = 0; i < 100; i++) {
			if(flatFleet.get(i) == "D") {
				if(flatFleet.get(i+1) == "D") {	// Check orientation of ship.
				
					for(int j = 0; j < 3; j++) {
						if(flatFleet.get(i+j).equals("D")) {
							runningDestroyerCount++;
						}
					}
					break;
				} else {
					
					for(int j = 0; j < 3; j++) {
						if(flatFleet.get(i+(j*10)).equals("D")) {
							runningDestroyerCount++;
						}
					}
					break;
				}
			}
		}

		// Submarine
		for(int i = 0; i < 100; i++) {
			if(flatFleet.get(i) == "S") {
				if(flatFleet.get(i+1) == "S") {	// Check orientation of ship.
				
					for(int j = 0; j < 3; j++) {
						if(flatFleet.get(i+j).equals("S")) {
							runningSubmarineCount++;
						}
					}
					break;
				} else {
					
					for(int j = 0; j < 3; j++) {
						if(flatFleet.get(i+(j*10)).equals("S")) {
							runningSubmarineCount++;
						}
					}
					break;
				}
			}
		}
		
		
		// Patrol
		for(int i = 0; i < 100; i++) {
			if(flatFleet.get(i) == "P") {
				if(flatFleet.get(i+1) == "P") {	// Check orientation of ship.
				
					for(int j = 0; j < 3; j++) {
						if(flatFleet.get(i+j).equals("P")) {
							runningPatrolCount++;
						}
					}
					break;
				} else {
					
					for(int j = 0; j < 3; j++) {
						if(flatFleet.get(i+(j*10)).equals("P")) {
							runningPatrolCount++;
						}
					}
					break;
				}
			}
		}

		
		// If every single battleship is the length that they should be, then it's a valid fleet configuration.
		// This is why the check for 17 spaces is not sufficient.
		// For example, a carrier could be 6 spaces and a battleship could be 3.
		// That method alone would fail this test.
		if(runningCarrierCount == 5 && runningBattleshipCount == 4 && runningDestroyerCount == 3 && runningSubmarineCount == 3 && runningPatrolCount == 2) {
			return true;
		}
		
		return false;
	
	}
	
}
