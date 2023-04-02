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
	
	JLabel listenLabel;
	JButton sendMessageButton;
	JButton connectButton;
	
	// Constructor
	public GameplayPanel(GameplayControl gameplayControl){
		
		// Make the GUI using JComponents.
		listenLabel = new JLabel("Hey, listen! Press the button!");

		connectButton = new JButton("Connect");
		connectButton.addActionListener(gameplayControl);
		
		sendMessageButton = new JButton("Send Message");
		sendMessageButton.addActionListener(gameplayControl);
		
		// Add components to JPanel.
		add(listenLabel, BorderLayout.CENTER);
		add(sendMessageButton, BorderLayout.CENTER);
		add(connectButton, BorderLayout.CENTER);
		
		// Show the JPanel.
		setSize(500, 500);
		setVisible(true);
		
	}
	
}
