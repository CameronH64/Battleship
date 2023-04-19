package dataclasses;

import java.util.ArrayList;

import ocsf.server.ConnectionToClient;

public class PlayerData {
	
	private int playerNumber;
	private String playerName;
	private ConnectionToClient playerConnectionToClient;
	
	private ArrayList<String> playerTargetingGrid;
	private ArrayList<String> playerOceanGrid;
	
	private int carrierHitCount;
	private int battleshipHitCount;
	private int destroyerHitCount;
	private int submarineHitCount;
	private int patrolHitCount;
	
	private boolean carrierSunk;
	private boolean battleshipSunk;
	private boolean destroyerSunk;
	private boolean submarineSunk;
	private boolean patrolSunk;
	
	public PlayerData() {
		
		carrierHitCount = 0;
		battleshipHitCount = 0;
		destroyerHitCount = 0;
		submarineHitCount = 0;
		patrolHitCount = 0;
		
		carrierSunk = false;
		battleshipSunk = false;
		destroyerSunk = false;
		submarineSunk = false;
		patrolSunk = false;
		
		// Always set up the targeting grid; it'll be blank.
		playerTargetingGrid = new ArrayList<String>();
		
		for (int i = 0; i < 100; i++) {	
			playerTargetingGrid.add("0");
		}
		
		setTestingOceanGrid();
		
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
		
		setPlayerOceanGrid(flatFleet);
		
	}
	

	public boolean isCarrierSunk() {
		return carrierSunk;
	}

	public void setCarrierSunk(boolean carrierSunk) {
		this.carrierSunk = carrierSunk;
	}

	public boolean isBattleshipSunk() {
		return battleshipSunk;
	}

	public void setBattleshipSunk(boolean battleshipSunk) {
		this.battleshipSunk = battleshipSunk;
	}

	public boolean isDestroyerSunk() {
		return destroyerSunk;
	}

	public void setDestroyerSunk(boolean destroyerSunk) {
		this.destroyerSunk = destroyerSunk;
	}

	public boolean isSubmarineSunk() {
		return submarineSunk;
	}

	public void setSubmarineSunk(boolean submarineSunk) {
		this.submarineSunk = submarineSunk;
	}

	public boolean isPatrolSunk() {
		return patrolSunk;
	}

	public void setPatrolSunk(boolean patrolSunk) {
		this.patrolSunk = patrolSunk;
	}

	public int getCarrierHitCount() {
		return carrierHitCount;
	}

	public void setCarrierHitCount(int carrierHitCount) {
		this.carrierHitCount = carrierHitCount;
	}

	public int getBattleshipHitCount() {
		return battleshipHitCount;
	}

	public void setBattleshipHitCount(int battleshipHitCount) {
		this.battleshipHitCount = battleshipHitCount;
	}

	public int getDestroyerHitCount() {
		return destroyerHitCount;
	}

	public void setDestroyerHitCount(int destroyerHitCount) {
		this.destroyerHitCount = destroyerHitCount;
	}

	public int getSubmarineHitCount() {
		return submarineHitCount;
	}

	public void setSubmarineHitCount(int submarineHitCount) {
		this.submarineHitCount = submarineHitCount;
	}

	public int getPatrolHitCount() {
		return patrolHitCount;
	}

	public void setPatrolHitCount(int patrolHitCount) {
		this.patrolHitCount = patrolHitCount;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	public ArrayList<String> getPlayerOceanGrid() {
		return playerOceanGrid;
	}

	public void setPlayerOceanGrid(ArrayList<String> playerOceanGrid) {
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
