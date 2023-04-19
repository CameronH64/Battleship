package dataclasses;

import java.util.ArrayList;

import ocsf.server.ConnectionToClient;

public class PlayerData {
	
	private int playerNumber;
	private String playerName;
	private ConnectionToClient playerConnectionToClient;
	
	private ArrayList<String> playerTargetingGrid;
	private ShipPlacementData playerOceanGrid;
	
	public PlayerData() {
		
		// Set up the targeting grid; it'll be blank.
		playerTargetingGrid = new ArrayList<String>();
		
		for (int i = 0; i < 100; i++) {
			
			playerTargetingGrid.add("0");
			
		}
		
	}
	
	public void setTestingOceanGrid() {
		
		// This method is for testing, since I can't get the ship configuration from the panel yet.
		
		ArrayList<String> flatFleet = new ArrayList<String>();
		
		String playerFleetArray[][] = {{"C","C","C","C","C","0","0","0","0","0"},
									   {"B","B","B","B","0","0","0","0","0","0"},
									   {"D","D","D","0","0","0","0","0","0","0"},
									   {"S","S","S","0","0","0","0","0","0","0"},
									   {"P","P","0","0","0","0","0","0","0","0"},
									   {"0","0","0","0","0","0","0","0","0","0"},
									   {"0","0","0","0","0","0","0","0","0","0"},
									   {"0","0","0","0","0","0","0","0","0","0"},
									   {"0","0","0","0","0","0","0","0","0","0"},
									   {"0","0","0","0","0","0","0","0","0","0"}};
		
		
		
		// First, flatten the array into a 1D array.
		for(int i = 0; i < 10; i++) {				// Check each row.
			for(int j = 0; j < 10; j++) {			// Check each column.
				flatFleet.add(playerFleetArray[i][j]);
			}
		}
		
		playerOceanGrid.setShipConfiguration(flatFleet);
		
	}
	
	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	public ShipPlacementData getPlayerOceanGrid() {
		return playerOceanGrid;
	}

	public void setPlayerOceanGrid(ShipPlacementData playerOceanGrid) {
		this.playerOceanGrid = playerOceanGrid;
	}

	public ArrayList<String> getPlayerTargetingGrid() {
		return playerTargetingGrid;
	}

	public void setPlayerTargetingGrid(ArrayList<String> playerTargetingGrid) {
		this.playerTargetingGrid = playerTargetingGrid;
	}

	public ConnectionToClient getPlayerConnectionToClient() {
		return playerConnectionToClient;
	}

	public void setPlayerConnectionToClient(ConnectionToClient playerConnectionToClient) {
		this.playerConnectionToClient = playerConnectionToClient;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	
	
}
