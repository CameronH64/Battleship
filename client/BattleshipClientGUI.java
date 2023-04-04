package client;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gameplaypanel.GameplayPanel;
import gameplaycontrol.GameplayControl;
import gameplaypanel.TestingIPAddressPanel;
import gameplaycontrol.TestingIPAddressControl;

public class BattleshipClientGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BattleshipClientGUI(){
		
		// Instantiate the client.
		BattleshipClient battleshipClient = new BattleshipClient();

		// Initialize JFrame settings.
		this.setTitle("Battleship GUI");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Make the CardLayout.
		CardLayout cardLayout = new CardLayout();
		JPanel buffer = new JPanel(cardLayout);
		
		
		
		// This will be extended to include all of the JPanels.
		TestingIPAddressControl testingIPAddressControl = new TestingIPAddressControl(buffer, battleshipClient);
		TestingIPAddressPanel testClientLoginPanel = new TestingIPAddressPanel(testingIPAddressControl);
		GameplayControl gameplayControl = new GameplayControl(buffer, battleshipClient);
		GameplayPanel gameplayPanel = new GameplayPanel(gameplayControl);
		
		// Add the panels to CardLayout
		buffer.add(gameplayPanel, "7");
		buffer.add(testClientLoginPanel, "1");
		
		
		
		// Add the CardLayout container to the JFrame.
		this.add(buffer, BorderLayout.CENTER);
		
		// Show the JFrame
		this.setSize(500, 500);
		this.setVisible(true);
		this.setResizable(true);
		
		// Kick off the GUI with the first JPanel.
		cardLayout.show(buffer, "1");
		
	}
	
	public static void main(String[] args) {
		
		// Basically combine all the other testing classes into a single class 
		// such that it makes a cardLayout that has all the JPanels.
		
		// Also create a client object for each JPanel to use.
		
		// When adding each panel, add the client object to the control classes that need them.
		// Then, add the panels to the control panels.
		
		// Once that is started, kick off the CardLayout with the MainMenuPanel.
		// That's all this class does.
		
		new BattleshipClientGUI();
		
	}

}
