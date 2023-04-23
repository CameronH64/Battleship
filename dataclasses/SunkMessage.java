package dataclasses;

import java.io.Serializable;

public class SunkMessage implements Serializable {
	
	private String sunkMessage;
	
	public SunkMessage() {
		
		
		
	}
	
	public SunkMessage(String sunkMessage) {
		
		this.sunkMessage = sunkMessage;
		
	}

	public String getSunkMessage() {
		return sunkMessage;
	}

	public void setSunkMessage(String sunkMessage) {
		this.sunkMessage = sunkMessage;
	}
	
}
