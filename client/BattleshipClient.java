// Cameron Holbrook
// Lab 3 Out

package client;

import java.awt.CardLayout;
import java.util.ArrayList;

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
	
	private ArrayList<TargetLabel> localTargetLabels;
	private ArrayList<OceanLabel> localOceanLabels;

	
	public BattleshipClient() {
		super("localhost", 8300);
		
		localTargetLabels = new ArrayList<TargetLabel>();
		
		for (int i = 0; i < 100; i++) {
			
			localTargetLabels.add(new TargetLabel(5));
			
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
			
			ArrayList<TargetLabel> targetLabels = updatedTargetGridData.getTargetLabels();
			
			setTargetLabels(targetLabels);
			
			
		} else if (arg0 instanceof UpdatedOceanGridData) {

			UpdatedOceanGridData updatedOceanGridData = (UpdatedOceanGridData)arg0;
			
			ArrayList<OceanLabel> oceanLabels = updatedOceanGridData.getOceanLabels();
			
			setOceanLabels(oceanLabels);
			
			
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

	public ArrayList<TargetLabel> getTargetLabels() {
		return localTargetLabels;
	}

	public void setTargetLabels(ArrayList<TargetLabel> targetLabels) {
		this.localTargetLabels = targetLabels;
	}

	public ArrayList<OceanLabel> getOceanLabels() {
		return localOceanLabels;
	}

	public void setOceanLabels(ArrayList<OceanLabel> oceanLabels) {
		this.localOceanLabels = oceanLabels;
	}
	
	
	
	// Already has a sendToServer() method. No implementation needed.
	
}
