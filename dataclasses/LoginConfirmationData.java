package dataclasses;

import java.io.Serializable;

public class LoginConfirmationData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean value;
	
	public LoginConfirmationData() {
		
		value = false;
		
	}
	
	public LoginConfirmationData(boolean value) {
		
		this.value = value;
		
	}
	
	public void setValue(boolean value) { this.value = value; }
	public boolean getValue() { return value; }
	
}
