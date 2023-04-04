// Cameron Holbrook
// Lab 3 Out

package client;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import ocsf.client.AbstractClient;

import dataclasses.LoginData;
import dataclasses.ShotFiredData;

public class BattleshipClient extends AbstractClient{
	
//	private JPanel container;

	public BattleshipClient() {
		super("localhost", 8300);
	}
	
//	public void setContainer(JPanel container) { this.container = container; }
	
	public void connectionEstablished() {
		System.out.println("Client-side: Client connected. ");
	}
		
	public void handleMessageFromServer(Object arg0) {
		
		// Reminder to myself: This method is a big if-else statement that takes in server information, and does stuff with it.
		
//		serverMessageArea.append("Server: " + (String)arg0 + "\n");
//		clientIDTextField.setText((String)arg0);
		
//		boolean loginStatus = (boolean)arg0;
		
		if (arg0 instanceof LoginData) {
			
			System.out.println("Client-side: Received LoginData.");
			
		} else if (arg0 instanceof ShotFiredData) {
			
			System.out.println("Client-side: Received ShotFiredData.");
			
		}
		
	}
	
	public void connectionClosed() {
//		statusLabel.setText("Not Connected");
//		statusLabel.setForeground(Color.RED);
		
		
		
	}
	
	// Already has a sendToServer() method. No implementation needed.
	
}
