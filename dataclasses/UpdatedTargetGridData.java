package dataclasses;

import java.io.Serializable;
import java.util.ArrayList;

public class UpdatedTargetGridData implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<TargetLabel> targetLabels;
	
	public UpdatedTargetGridData() {
		
		targetLabels = new ArrayList<TargetLabel>();
		
	}

	public UpdatedTargetGridData(ArrayList<TargetLabel> targetLabels) {
		
		this.targetLabels = targetLabels;
		
	}
	
	public ArrayList<TargetLabel> getTargetLabels() {
		return targetLabels;
	}

	public void setTargetLabels(ArrayList<TargetLabel> targetLabels) {
		this.targetLabels = targetLabels;
	}
	
}
