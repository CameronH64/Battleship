package dataclasses;

import java.io.Serializable;

public class ConfirmationData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean value;
	private boolean creation;
	private boolean deletion;

	public ConfirmationData() {

		value = false;
		creation = false;
		deletion = false;
	}

	public ConfirmationData(boolean value) {

		this.value = value;

	}

	public void setValue(boolean value) { this.value = value; } 
	public void setCreation(boolean creation) { this.creation = creation; }
	public void setDeletion(boolean deletion) { this.deletion = deletion; }
	public boolean getValue() { return value; }
	public boolean getCreation() { return creation; }
	public boolean getDeletion() { return deletion; }

}
