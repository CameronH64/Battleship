package shipplacementcontrol;

import javax.swing.*;

import client.BattleshipClient;
import dataclasses.PlacementLabel;
import dataclasses.MainMenuLoginData;
import dataclasses.ShotFiredData;
import gameplaypanel.GameplayPanel;

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
	
	
	
	// This constructor connects the outside components so that the control panel can affect things.
	public PlacementLabelControl(PlacementLabel placementLabel)
	{
		this.placementLabel = placementLabel;
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		placementLabel.setBackground(Color.RED);
		System.out.println("Position: " + placementLabel.getPosition());
		
		// Remove the mouse listener so that the user can't press the same cell twice.
		placementLabel.removeMouseListener(this);
		
		placementLabel.getShipCharacter();
		
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
