// Cameron Holbrook
// Lab 3 Out

package client;

import java.awt.CardLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JPanel;
import ocsf.client.AbstractClient;
import dataclasses.ConfirmationData;
import dataclasses.MainMenuLoginData;
import dataclasses.OceanLabel;
import dataclasses.ShotFiredData;
import dataclasses.TargetLabel;
import dataclasses.UpdatedOceanGridData;
import dataclasses.UpdatedTargetGridData;

public class BattleshipClient extends AbstractClient{
	
	private JPanel container;
	
	private ArrayList<TargetLabel> displayTargetLabels;
	private ArrayList<OceanLabel> displayOceanLabels;

	
	public BattleshipClient() {
		super("localhost", 8300);
		
		
		// Has authoritative copy on the server, but need this to map server -> client for GUI stuff.
		displayTargetLabels = new ArrayList<TargetLabel>();
		
		for (int i = 0; i < 100; i++) {
			TargetLabel targetLabel = new TargetLabel(i, "0");
			targetLabel.setBackground(Color.BLUE);
			displayTargetLabels.add(targetLabel);
			
		}

		// Has authoritative copy on the server, but need this to map server -> client for GUI stuff.
		displayOceanLabels = new ArrayList<OceanLabel>();
		
		for (int i = 0; i < 100; i++) {
			
			OceanLabel oceanLabel = new OceanLabel(i, "0");
			oceanLabel.setBackground(Color.BLUE);
			displayOceanLabels.add(oceanLabel);
			
		}
		
	}
	
//	public void setContainer(JPanel container) { this.container = container; }
	
	public void connectionEstablished() {
		System.out.println("[CLIENT] CLIENT CONNECTED");
	}
		
	public void handleMessageFromServer(Object arg0) {
		
		// Reminder to myself: This method is a big if-else statement that takes in server information, and does stuff with it.
		
//		serverMessageArea.append("Server: " + (String)arg0 + "\n");
//		clientIDTextField.setText((String)arg0);
		
//		boolean loginStatus = (boolean)arg0;
		
		if (arg0 instanceof MainMenuLoginData) {
			
			System.out.println("[CLIENT] RECEIVED LOGINDATA");
			
		} else if (arg0 instanceof ShotFiredData) {
			
			System.out.println("[CLIENT] RECEIVED SHOTFIREDDATA");
			
		} else if (arg0 instanceof ConfirmationData) {
			
			// If yes, switch
			// If no, stay.
			
			ConfirmationData confirmation = (ConfirmationData)arg0;
			
			if (confirmation.getValue()) {
				
				CardLayout cardLayout = (CardLayout)container.getLayout();
				cardLayout.show(container, "4");				
				
			} else {
				
				System.out.println("[CLIENT] INVALID CREDENTIALS");
				
			}
			

			
		} else if (arg0 instanceof String) {
			
			System.out.println("[CLIENT] RECEIVED STRING");
			
			
			
		} else if (arg0 instanceof UpdatedTargetGridData) {
			
			UpdatedTargetGridData updatedTargetGridData = (UpdatedTargetGridData)arg0;
			
			ArrayList<TargetLabel> serverTargetLabels = updatedTargetGridData.getTargetLabels();
			
			int i = 0;
			for (TargetLabel targetLabel : displayTargetLabels) {
				
				String serverShipCharacter = serverTargetLabels.get(i).getShipCharacter();
				targetLabel.setShipCharacter(serverShipCharacter);
				
				i++;
			}
			
			updateTargetGrid();			// Cycles through the client-side Arraylist. If "1" instead of "0", setBackground to red.
			
			
		} else if (arg0 instanceof UpdatedOceanGridData) {

			UpdatedOceanGridData updatedOceanGridData = (UpdatedOceanGridData)arg0;
			
			ArrayList<OceanLabel> serverOceanLabels = updatedOceanGridData.getOceanLabels();

//			displayOceanLabels = updatedOceanGridData.getOceanLabels();
			
			int i = 0;
			for (OceanLabel oceanLabel : displayOceanLabels) {
				
				String serverShipCharacter = serverOceanLabels.get(i).getShipCharacter();
				oceanLabel.setShipCharacter(serverShipCharacter);
				
				i++;
			}

			setupOceanGrid();
			
		}		
	}
	
	public void connectionClosed() {
//		statusLabel.setText("Not Connected");
//		statusLabel.setForeground(Color.RED);
		
		System.out.println("[CLIENT] CONNECTION CLOSED");
		
	}
	
	public void setContainer(JPanel container) {
		
		this.container = container;
		
	}
	
	public void updateTargetGrid() {
		
		for (TargetLabel targetLabel: displayTargetLabels) {
			
			if (targetLabel.getShipCharacter().equals("1")){
				targetLabel.setBackground(Color.RED);
			}
			
			if (targetLabel.getShipCharacter().equals("2")){
				targetLabel.setBackground(Color.WHITE);
			}
			
		}
		
	}
	
	public void updateOceanGrid() {
		
		
		
	}
	
	public void setupOceanGrid() {
		
		for (OceanLabel oceanLabel: displayOceanLabels) {
			
			if (oceanLabel.getShipCharacter().equals("C")){
				oceanLabel.setBackground(Color.GRAY);
				oceanLabel.setText("C");
			}

			if (oceanLabel.getShipCharacter().equals("B")){
				oceanLabel.setBackground(Color.GRAY);
				oceanLabel.setText("B");
			}

			if (oceanLabel.getShipCharacter().equals("D")){
				oceanLabel.setBackground(Color.GRAY);
				oceanLabel.setText("D");
			}

			if (oceanLabel.getShipCharacter().equals("S")){
				oceanLabel.setBackground(Color.GRAY);
				oceanLabel.setText("S");
			}

			if (oceanLabel.getShipCharacter().equals("P")){
				oceanLabel.setBackground(Color.GRAY);
				oceanLabel.setText("P");
			}
		}
		
	}
	
	public ArrayList<TargetLabel> getTargetLabels() {
		return displayTargetLabels;
	}

	public void setTargetLabels(ArrayList<TargetLabel> targetLabels) {
		this.displayTargetLabels = targetLabels;
	}

	public ArrayList<OceanLabel> getOceanLabels() {
		return displayOceanLabels;
	}

	public void setOceanLabels(ArrayList<OceanLabel> oceanLabels) {
		this.displayOceanLabels = oceanLabels;
	}
	
	
	
	// Already has a sendToServer() method. No implementation needed.
	
}
