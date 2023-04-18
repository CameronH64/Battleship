package dataclasses;

import java.io.Serializable;
import java.util.ArrayList;

public class ShipPlacementData implements Serializable {
	
	private ArrayList<PlacementLabel> shipConfiguration;
	
	public ShipPlacementData(){
				
		shipConfiguration = new ArrayList<PlacementLabel>();
		
	}
	
	// Getters and Setters
	public ArrayList<PlacementLabel> getShipConfiguration() {
		return shipConfiguration;
	}

	public void setShipConfiguration(ArrayList<PlacementLabel> shipConfiguration) {
		this.shipConfiguration = shipConfiguration;
	}
	
}
