package dataclasses;

import java.io.Serializable;

public class InvalidShotMessage implements Serializable {
	
	private String message;
	
	public InvalidShotMessage() {
		
		message = "";
		
	}

	public InvalidShotMessage(String message) {
		
		this.message = message;
		
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
