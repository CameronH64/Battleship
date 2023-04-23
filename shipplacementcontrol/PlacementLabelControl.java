package shipplacementcontrol;

import javax.swing.*;

import client.BattleshipClient;
import dataclasses.PlacementLabel;
import dataclasses.MainMenuLoginData;
import dataclasses.ShotFiredData;
import gameplaypanel.GameplayPanel;
import shipplacementpanel.ShipPlacementPanel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class PlacementLabelControl implements MouseListener
{
	// Private data fields.
	private JPanel container;
	private BattleshipClient battleshipClient;
	private PlacementLabel placementLabel;
	private ShipPlacementPanel shipPlacementPanel;
	
	
	
	// This constructor connects the outside components so that the control panel can affect things.
	public PlacementLabelControl(PlacementLabel placementLabel)
	{
		this.placementLabel = placementLabel;
	}
	
	
	public void setPlacementPanel(ShipPlacementPanel shipPlacementPanel) {
		
		this.shipPlacementPanel = shipPlacementPanel;
		
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

		if (shipPlacementPanel.getCarrierRadioButton().isSelected()) {
			
			if (placementLabel.getText() == "C") {
				
				placementLabel.setBackground(Color.BLUE);
				placementLabel.setText("");
				placementLabel.setShipCharacter("0");
//				System.out.println("Unset Carrier: " + placementLabel.getPosition());
				
			} else {
				
				placementLabel.setBackground(Color.GRAY);
				placementLabel.setText("C");
				placementLabel.setShipCharacter("C");
//				System.out.println("Set Carrier: " + placementLabel.getPosition());
				
			}
			
			
			
		} else if (shipPlacementPanel.getBattleshipRadioButton().isSelected()) {
			
			if (placementLabel.getText() == "B") {
				
				placementLabel.setBackground(Color.BLUE);
				placementLabel.setText("");
				placementLabel.setShipCharacter("0");
//				System.out.println("Unset Battleship: " + placementLabel.getPosition());
				
			} else {
				
				placementLabel.setBackground(Color.GRAY);
				placementLabel.setText("B");
				placementLabel.setShipCharacter("B");
//				System.out.println("Set Battleship: " + placementLabel.getPosition());
				
			}
			
			
		} else if (shipPlacementPanel.getDestroyerRadioButton().isSelected()) {
			
			if (placementLabel.getText() == "D") {
				
				placementLabel.setBackground(Color.BLUE);
				placementLabel.setText("");
				placementLabel.setShipCharacter("0");
//				System.out.println("Unset Destroyer: " + placementLabel.getPosition());
				
			} else {
				
				placementLabel.setBackground(Color.GRAY);
				placementLabel.setText("D");
				placementLabel.setShipCharacter("D");
//				System.out.println("Set Destroyer: " + placementLabel.getPosition());
				
			}
			
			
		} else if (shipPlacementPanel.getSubmarineRadioButton().isSelected()) {
			
			if (placementLabel.getText() == "S") {
				
				placementLabel.setBackground(Color.BLUE);
				placementLabel.setText("");
				placementLabel.setShipCharacter("0");
//				System.out.println("Unset Submarine: " + placementLabel.getPosition());
				
			} else {
				
				placementLabel.setBackground(Color.GRAY);
				placementLabel.setText("S");
				placementLabel.setShipCharacter("S");
//				System.out.println("Set Submarine: " + placementLabel.getPosition());
				
			}
			
			
		} else if (shipPlacementPanel.getPatrolRadioButton().isSelected()) {
			
			if (placementLabel.getText() == "P") {
				
				placementLabel.setBackground(Color.BLUE);
				placementLabel.setText("");
				placementLabel.setShipCharacter("0");
//				System.out.println("Unset Patrol: " + placementLabel.getPosition());
				
			} else {
				
				placementLabel.setBackground(Color.GRAY);
				placementLabel.setText("P");
				placementLabel.setShipCharacter("P");
//				System.out.println("Set Patrol: " + placementLabel.getPosition());
				
			}
			
		}
		
		
		
		// Remove the mouse listener so that the user can't press the same cell twice.
//		placementLabel.removeMouseListener(this);
		
//		placementLabel.getShipCharacter();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
