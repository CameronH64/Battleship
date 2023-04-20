package gameplaycontrol;

import javax.swing.*;

import client.BattleshipClient;
import dataclasses.MainMenuLoginData;
import dataclasses.ShotFiredData;
import dataclasses.TargetLabel;
import gameplaypanel.GameplayPanel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class TargetLabelControl implements MouseListener
{
	// Private data fields.
	private JPanel container;
	private BattleshipClient battleshipClient;
	private TargetLabel targetLabel;
	private GameplayPanel setGameplayPanel;
	
	
	// This constructor connects the outside components so that the control panel can affect things.
	public TargetLabelControl(TargetLabel targetLabel, BattleshipClient battleshipClient)
	{
		this.targetLabel = targetLabel;
		this.battleshipClient = battleshipClient;		
	}
	
	public TargetLabelControl(TargetLabel targetLabel)
	{
		this.targetLabel = targetLabel;	
	}
	
	
	public BattleshipClient getBattleshipClient() {
		return battleshipClient;
	}



	public void setBattleshipClient(BattleshipClient battleshipClient) {
		this.battleshipClient = battleshipClient;
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {		
		
		// When the user presses a TargetLabel, the 
		
		System.out.println("[CLIENT] POSITION: " + targetLabel.getPosition());		
		
		ShotFiredData shotFiredData = new ShotFiredData(targetLabel.getPosition());
		
		try {
			battleshipClient.sendToServer(shotFiredData);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Remove the mouse listener so that the user can't press the same cell twice.
		targetLabel.removeMouseListener(this);
		
//		setGameplayPanel.repaint();
		
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

	public void setGameplayPanel(GameplayPanel gameplayPanel) {
		
		this.setGameplayPanel = gameplayPanel;
		
	}

}