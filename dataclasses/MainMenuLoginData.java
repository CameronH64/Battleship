package dataclasses;

import java.io.Serializable;

public class MainMenuLoginData implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	
	// Constructor
	public MainMenuLoginData(String username, String password){
		
		this.username = username;
		this.password = password;
		
	}
	
	// Getters and Setters
	public String getPassword() { return password; }
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
	public void setPassword(String password) { this.password = password; }
	
}
