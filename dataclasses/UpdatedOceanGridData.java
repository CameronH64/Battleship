package dataclasses;

import java.io.Serializable;
import java.util.ArrayList;

public class UpdatedOceanGridData implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<OceanLabel> oceanLabels;
	
	public UpdatedOceanGridData() {
		
		oceanLabels = new ArrayList<OceanLabel>();
		
	}

	public UpdatedOceanGridData(ArrayList<OceanLabel> oceanLabels) {
		
		this.oceanLabels = oceanLabels;
		
	}
	
	public ArrayList<OceanLabel> getOceanLabels() {
		return oceanLabels;
	}

	public void setOceanLabels(ArrayList<OceanLabel> oceanLabels) {
		this.oceanLabels = oceanLabels;
	}
	
}
