package dataclasses;

import java.util.ArrayList;

public class ShipPlacementData {
	
	private ArrayList<String> shipConfiguration;
	
	ShipPlacementData(){
				
		shipConfiguration = new ArrayList<String>();
		
	}
	
	// Getters and Setters
	public ArrayList<String> getShipConfiguration() {
		return shipConfiguration;
	}

	public void setShipConfiguration(ArrayList<String> shipConfiguration) {
		this.shipConfiguration = shipConfiguration;
	}
	
}
