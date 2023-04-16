package client;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gameplaypanel.GameplayPanel;
import gameplaypanel.TestingPanelSwitchPanel;
import instructioncontrol.InstructionControl;
import instructionpanel.InstructionPanel;
import menucontrols.CreateUserControl;
import menucontrols.DeleteUserControl;
import menucontrols.MainMenuControl;
import menupanels.CreateUserPanel;
import menupanels.DeleteUserPanel;
import menupanels.MainMenuPanel;
import shipplacementcontrol.ShipPlacementControl;
import shipplacementpanel.ShipPlacementPanel;
import gameplaycontrol.GameplayControl;
import gameplaycontrol.TestingPanelSwitchControl;

public class BattleshipClientGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BattleshipClientGUI(){
		
		
		
		// Initialize JFrame settings.
		this.setTitle("Battleship Client");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Make the CardLayout.
		CardLayout cardLayout = new CardLayout();
		JPanel buffer = new JPanel(cardLayout);
		
		
		
		// ---------------------- INSTANTIATE THE CLIENT ----------------------
		
		BattleshipClient battleshipClient = new BattleshipClient();
		battleshipClient.setPort(8300);
		battleshipClient.setContainer(buffer);
		
		// --------------------------------------------------------------------
		
		
		
		// 1. Instantiate the Panel/Control pairs (six total).
		
		MainMenuControl mainMenuControl = new MainMenuControl(buffer, battleshipClient);		// Will probably need to take in the database object soon.
		MainMenuPanel mainMenuPanel = new MainMenuPanel(mainMenuControl);
		mainMenuControl.setMainMenu(mainMenuPanel);
		
		DeleteUserControl deleteUserControl = new DeleteUserControl(buffer, battleshipClient);
		DeleteUserPanel deleteUserPanel = new DeleteUserPanel(deleteUserControl);
		
		CreateUserControl createUserControl = new CreateUserControl(buffer, battleshipClient);
		CreateUserPanel createUserPanel = new CreateUserPanel(createUserControl);
		
		InstructionControl instructionControl = new InstructionControl(buffer);
		InstructionPanel instructionPanel = new InstructionPanel(instructionControl);
		
		ShipPlacementControl shipPlacementControl = new ShipPlacementControl(buffer, battleshipClient);
		ShipPlacementPanel shipPlacementPanel = new ShipPlacementPanel(shipPlacementControl);
		
		GameplayControl gameplayControl = new GameplayControl(buffer, battleshipClient);
		GameplayPanel gameplayPanel = new GameplayPanel(gameplayControl);
		
		
		
		// 2. Add the panels to cardLayout (six total).
//		buffer.add(testingPanelSwitchPanel, "1");
		buffer.add(mainMenuPanel, "1");
		buffer.add(deleteUserPanel, "2");
		buffer.add(createUserPanel, "3");
		buffer.add(instructionPanel, "4");
		buffer.add(shipPlacementPanel, "5");
		buffer.add(gameplayPanel, "6");
		
		
		
		// Add the CardLayout container to the JFrame.
		add(buffer, BorderLayout.CENTER);
		
		// Show the JFrame
		setSize(500, 500);
		setVisible(true);
		pack();			// Testing. May be useful for resizing the client window. May be okay to just extend the window to size manually.
		setResizable(true);
		
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
