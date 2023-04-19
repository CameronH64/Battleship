package dataclasses;

import java.io.Serializable;

public class ConfirmationData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean value;
	
	public ConfirmationData() {
		
		value = false;
		
	}
	
	public ConfirmationData(boolean value) {
		
		this.value = value;
		
	}
	
	public void setValue(boolean value) { this.value = value; }
	public boolean getValue() { return value; }
	
}
