package gameplaypanel;

import javax.swing.*;

import client.BattleshipClient;
import gameplaycontrol.GameplayControl;

import java.awt.*;

public class GameplayPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Private Data Fields and JComponents.
	// Generally, it's better to have these explicitly declared here so that it's easier to debug the code.
	
	JButton sendMessageButton;
	JButton sendShotFiredButton;
	JButton connectButton;
	
	// Constructor
	public GameplayPanel(GameplayControl gameplayControl){
		
		
		
		// Make the GUI using JComponents.
		sendMessageButton = new JButton("Send LoginData");
		sendMessageButton.addActionListener(gameplayControl);
		
		sendShotFiredButton = new JButton("Send ShotFiredData");
		sendShotFiredButton.addActionListener(gameplayControl);
		
		connectButton = new JButton("Connect");
		connectButton.addActionListener(gameplayControl);
		
		
		
		// Add components to JPanel.
		add(sendMessageButton, BorderLayout.CENTER);
		add(sendShotFiredButton, BorderLayout.CENTER);
		add(connectButton, BorderLayout.CENTER);
		
		
		
		// Show the JPanel.
		setSize(500, 500);
		setVisible(true);
		
	}
	
}
