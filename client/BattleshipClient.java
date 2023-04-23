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
import dataclasses.InvalidShotMessage;
import dataclasses.MainMenuLoginData;
import dataclasses.OceanLabel;
import dataclasses.ShotFiredData;
import dataclasses.SunkMessage;
import dataclasses.TargetLabel;
import dataclasses.UpdatedOceanGridData;
import dataclasses.UpdatedTargetGridData;
import dataclasses.WinLoseMessage;
import gameplaypanel.GameplayPanel;

public class BattleshipClient extends AbstractClient{
	
	private JPanel container;
	private GameplayPanel gameplayPanel;
	
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
			
		} else if (arg0 instanceof UpdatedTargetGridData) {
			
			UpdatedTargetGridData updatedTargetGridData = (UpdatedTargetGridData)arg0;
			
			ArrayList<TargetLabel> serverTargetLabels = updatedTargetGridData.getTargetLabels();
			
			int i = 0;
			for (TargetLabel targetLabel : displayTargetLabels) {
				
				String serverHitCharacter = serverTargetLabels.get(i).getHitCharacter();
				targetLabel.setHitCharacter(serverHitCharacter);
				
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
				
				boolean serverShipHitStatus = serverOceanLabels.get(i).getShipHitStatus();
				oceanLabel.setShipHitStatus(serverShipHitStatus);
				
				i++;
			}

			setupOceanGrid();
			
		} else if (arg0 instanceof SunkMessage) {
			
			SunkMessage sunkMessage = (SunkMessage)arg0;
			
			String message = sunkMessage.getSunkMessage();
			
			switch(message) {
			
			case "carrier":
//				System.out.println("[CLIENT-SIDE] SUNK CARRIER");
				String asdf = gameplayPanel.getSunkShipsLabel().getText();
				gameplayPanel.getSunkShipsLabel().setText(asdf + "carrier ");
				break;
			case "battleship":
//				System.out.println("[CLIENT-SIDE] SUNK BATTLESHIP");
				String sdfg = gameplayPanel.getSunkShipsLabel().getText();
				gameplayPanel.getSunkShipsLabel().setText(sdfg + "battleship ");
				break;
			case "destroyer":
//				System.out.println("[CLIENT-SIDE] SUNK DESTROYER");
				String dfgh = gameplayPanel.getSunkShipsLabel().getText();
				gameplayPanel.getSunkShipsLabel().setText(dfgh + "destroyer ");
				break;
			case "submarine":
//				System.out.println("[CLIENT-SIDE] SUNK SUBMARINE");
				String fghj = gameplayPanel.getSunkShipsLabel().getText();
				gameplayPanel.getSunkShipsLabel().setText(fghj + "submarine ");
				break;
			case "patrol":
//				System.out.println("[CLIENT-SIDE] SUNK PATROL");
				String ghjk = gameplayPanel.getSunkShipsLabel().getText();
				gameplayPanel.getSunkShipsLabel().setText(ghjk + "patrol ");
				break;
				
			}
			
		} else if (arg0 instanceof WinLoseMessage) {
			
			WinLoseMessage winLoseMessage = (WinLoseMessage)arg0;
			
			String message = winLoseMessage.getMessage();
			
			if (message.equals("win")) {
				
				gameplayPanel.getWinLoseLabel().setText("You win!");
				
			} else if (message.equals("lose")) {
				
				gameplayPanel.getWinLoseLabel().setText("You lose!");				
				
			}
			
		} else if (arg0 instanceof InvalidShotMessage) {
			
			InvalidShotMessage invalidShotMessage = (InvalidShotMessage)arg0;
			
			String message = invalidShotMessage.getMessage();
			
			if (message.equals("single")) {
				
				gameplayPanel.getWinLoseLabel().setText("Wait for another player!");
				
			} else if (message.equals("repeat")) {
				
				gameplayPanel.getWinLoseLabel().setText("Repeat shot; try again.");
				
			} else if (message.equals("blank")) {
				
				gameplayPanel.getWinLoseLabel().setText("");
				
			} else if (message.equals("inorder")) {
				
				gameplayPanel.getWinLoseLabel().setText("Wait your turn!");

			} else if (message.equals("yesturn")) {
				
				gameplayPanel.getTargetGridLabel().setText("Targeting Grid (Your Turn)");
				
			} else if (message.equals("noturn")) {
				
				gameplayPanel.getTargetGridLabel().setText("Targeting Grid");
				
			}
			
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
			
			if (targetLabel.getHitCharacter().equals("1")){
				targetLabel.setBackground(Color.RED);
			}
						
			if (targetLabel.getHitCharacter().equals("2")){
				targetLabel.setBackground(Color.WHITE);
			}

		}
		
	}
	
	public void updateOceanGrid() {
		
		
		
	}
	
	public void setupOceanGrid() {
		
		for (OceanLabel oceanLabel: displayOceanLabels) {
			
			if (oceanLabel.getShipCharacter().equals("C")){
				
				oceanLabel.setText("C");

				if (oceanLabel.getShipHitStatus() == false) {
					oceanLabel.setBackground(Color.GRAY);
					
				} else {
					oceanLabel.setBackground(Color.RED);
				}
				
			}

			if (oceanLabel.getShipCharacter().equals("B")){
				oceanLabel.setText("B");

				if (oceanLabel.getShipHitStatus() == false) {
					oceanLabel.setBackground(Color.GRAY);
					
				} else {
					oceanLabel.setBackground(Color.RED);
				}
			}

			if (oceanLabel.getShipCharacter().equals("D")){
				oceanLabel.setText("D");

				if (oceanLabel.getShipHitStatus() == false) {
					oceanLabel.setBackground(Color.GRAY);
					
				} else {
					oceanLabel.setBackground(Color.RED);
				}
			}

			if (oceanLabel.getShipCharacter().equals("S")){
				oceanLabel.setText("S");

				if (oceanLabel.getShipHitStatus() == false) {
					oceanLabel.setBackground(Color.GRAY);
					
				} else {
					oceanLabel.setBackground(Color.RED);
				}
			}

			if (oceanLabel.getShipCharacter().equals("P")){
				oceanLabel.setText("P");

				if (oceanLabel.getShipHitStatus() == false) {
					oceanLabel.setBackground(Color.GRAY);
					
				} else {
					oceanLabel.setBackground(Color.RED);
				}
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

	public GameplayPanel getGameplayPanel() {
		return gameplayPanel;
	}

	public void setGameplayPanel(GameplayPanel gameplayPanel) {
		this.gameplayPanel = gameplayPanel;
	}
	
	
	
	// Already has a sendToServer() method. No implementation needed.
	
}
