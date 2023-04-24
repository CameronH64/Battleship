package client;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gameplaypanel.GameplayPanel;
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

public class BattleshipClientGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BattleshipClient battleshipClient;
	private MainMenuControl mainMenuControl;
	private MainMenuPanel mainMenuPanel;
	private DeleteUserControl deleteUserControl;
	private DeleteUserPanel deleteUserPanel;
	private CreateUserControl createUserControl;
	private CreateUserPanel createUserPanel;
	private InstructionControl instructionControl;
	private InstructionPanel instructionPanel;
	private ShipPlacementControl shipPlacementControl;
	private ShipPlacementPanel shipPlacementPanel;
	private GameplayControl gameplayControl;
	private GameplayPanel gameplayPanel;

	public BattleshipClientGUI(){



		// Initialize JFrame settings.
		this.setTitle("Battleship Client");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Make the CardLayout.
		CardLayout cardLayout = new CardLayout();
		JPanel buffer = new JPanel(cardLayout);



		// ---------------------- INSTANTIATE THE CLIENT ----------------------

		battleshipClient = new BattleshipClient();
		battleshipClient.setPort(8300);
		battleshipClient.setContainer(buffer);

		// --------------------------------------------------------------------



		// 1. Instantiate the Panel/Control pairs (six total).

		mainMenuControl = new MainMenuControl(buffer, battleshipClient);		// Will probably need to take in the database object soon.
		mainMenuPanel = new MainMenuPanel(mainMenuControl);
		mainMenuControl.setMainMenu(mainMenuPanel);

		deleteUserControl = new DeleteUserControl(buffer, battleshipClient);
		deleteUserPanel = new DeleteUserPanel(deleteUserControl);
		deleteUserControl.setMainMenuStatus(mainMenuPanel.getLabelStatusResponse());
		deleteUserControl.setDeleteUserMenu(deleteUserPanel);

		createUserControl = new CreateUserControl(buffer, battleshipClient);
		createUserPanel = new CreateUserPanel(createUserControl);
		createUserControl.setMainMenuStatus(mainMenuPanel.getLabelStatusResponse());
		createUserControl.setCreateUserMenu(createUserPanel);

		instructionControl = new InstructionControl(buffer);
		instructionPanel = new InstructionPanel(instructionControl);

		shipPlacementControl = new ShipPlacementControl(buffer, battleshipClient);
		shipPlacementPanel = new ShipPlacementPanel(shipPlacementControl);
		shipPlacementControl.setShipPlacementPanel(shipPlacementPanel);

		gameplayControl = new GameplayControl(buffer, battleshipClient);
		gameplayPanel = new GameplayPanel(gameplayControl, battleshipClient);
		gameplayControl.setGameplayPanel(gameplayPanel);
		
		/*
		 * Link the GUI and status labels to the client
		 */
		battleshipClient.setGui(this);
		battleshipClient.setLabelMainMenuStatus(mainMenuPanel.getLabelStatusResponse());
		battleshipClient.setLabelCreateUserStatus(createUserPanel.getLabelStatusResponse());
		battleshipClient.setLabelDeleteUserStatus(deleteUserPanel.getLabelStatusResponse());


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

	/*
	 * Getters and setters beyond this point
	 */
	public BattleshipClient getBattleshipClient() {
		return battleshipClient;
	}
	public void setBattleshipClient(BattleshipClient battleshipClient) {
		this.battleshipClient = battleshipClient;
	}
	public MainMenuControl getMainMenuControl() {
		return mainMenuControl;
	}
	public void setMainMenuControl(MainMenuControl mainMenuControl) {
		this.mainMenuControl = mainMenuControl;
	}
	public MainMenuPanel getMainMenuPanel() {
		return mainMenuPanel;
	}
	public void setMainMenuPanel(MainMenuPanel mainMenuPanel) {
		this.mainMenuPanel = mainMenuPanel;
	}
	public DeleteUserControl getDeleteUserControl() {
		return deleteUserControl;
	}
	public void setDeleteUserControl(DeleteUserControl deleteUserControl) {
		this.deleteUserControl = deleteUserControl;
	}
	public DeleteUserPanel getDeleteUserPanel() {
		return deleteUserPanel;
	}
	public void setDeleteUserPanel(DeleteUserPanel deleteUserPanel) {
		this.deleteUserPanel = deleteUserPanel;
	}
	public CreateUserControl getCreateUserControl() {
		return createUserControl;
	}
	public void setCreateUserControl(CreateUserControl createUserControl) {
		this.createUserControl = createUserControl;
	}
	public CreateUserPanel getCreateUserPanel() {
		return createUserPanel;
	}
	public void setCreateUserPanel(CreateUserPanel createUserPanel) {
		this.createUserPanel = createUserPanel;
	}
	public InstructionControl getInstructionControl() {
		return instructionControl;
	}
	public void setInstructionControl(InstructionControl instructionControl) {
		this.instructionControl = instructionControl;
	}
	public InstructionPanel getInstructionPanel() {
		return instructionPanel;
	}
	public void setInstructionPanel(InstructionPanel instructionPanel) {
		this.instructionPanel = instructionPanel;
	}
	public ShipPlacementControl getShipPlacementControl() {
		return shipPlacementControl;
	}
	public void setShipPlacementControl(ShipPlacementControl shipPlacementControl) {
		this.shipPlacementControl = shipPlacementControl;
	}
	public ShipPlacementPanel getShipPlacementPanel() {
		return shipPlacementPanel;
	}
	public void setShipPlacementPanel(ShipPlacementPanel shipPlacementPanel) {
		this.shipPlacementPanel = shipPlacementPanel;
	}
	public GameplayControl getGameplayControl() {
		return gameplayControl;
	}
	public void setGameplayControl(GameplayControl gameplayControl) {
		this.gameplayControl = gameplayControl;
	}
	public GameplayPanel getGameplayPanel() {
		return gameplayPanel;
	}
	public void setGameplayPanel(GameplayPanel gameplayPanel) {
		this.gameplayPanel = gameplayPanel;
	}
}
