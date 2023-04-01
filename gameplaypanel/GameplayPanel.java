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
	JButton pressingButton;
	int clickCount;
	
	// Constructor
	public GameplayPanel(GameplayControl gameplayControl){
		
		BattleshipClient battleshipClient = new BattleshipClient();
		
		// Make the GUI using JComponents.
		listenLabel = new JLabel("Hey, listen! Press the button!");
		pressingButton = new JButton("Press Me");
		pressingButton.addActionListener(gameplayControl);
		clickCount = 0;
		
		// Add components to JPanel.
		add(listenLabel, BorderLayout.CENTER);
		add(pressingButton, BorderLayout.CENTER);
		
		// Show the JPanel.
		setSize(500, 500);
		setVisible(true);
		
	}
	
}
