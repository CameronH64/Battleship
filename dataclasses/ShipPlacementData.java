package dataclasses;

import java.io.Serializable;
import java.util.ArrayList;

public class ShipPlacementData implements Serializable {
	
	private ArrayList<String> shipConfiguration;
	
	public ShipPlacementData(){
				
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
