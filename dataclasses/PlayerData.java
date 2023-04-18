package dataclasses;

import java.util.ArrayList;

import ocsf.server.ConnectionToClient;

public class PlayerData {
	
	private int playerNumber;
	private String playerName;
	private ShipPlacementData playerOceanGrid;
	private ArrayList<Integer> playerTargetingGrid;
	private ConnectionToClient playerConnectionToClient;
	
	public PlayerData() {

		playerTargetingGrid = new ArrayList<Integer>();
		
		for (int i = 0; i < 100; i++) {
			
			playerTargetingGrid.add(0);
			
		}
		
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

	public ArrayList<Integer> getPlayerTargetingGrid() {
		return playerTargetingGrid;
	}

	public void setPlayerTargetingGrid(ArrayList<Integer> playerTargetingGrid) {
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
