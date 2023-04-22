package dataclasses;

import java.io.Serializable;

public class WinLoseMessage implements Serializable {
	
	private String message;
	
	public WinLoseMessage() {
		
		message = "";
		
	}

	public WinLoseMessage(String message) {
		
		this.message = message;
		
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
