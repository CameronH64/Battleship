package menupanels;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import menucontrols.MainMenuControl;

public class MainMenuPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Private Data Fields and JComponents.
	// Generally, it's better to have these explicitly declared here so that it's easier to debug the code.
	
	JLabel listenLabel;
	JButton pressingButton;
	JButton goToCreateUser;
	JButton goToDeleteUser;
	
	// Constructor
	public MainMenuPanel(MainMenuControl mainMenuControl){
		
		// Make the GUI using JComponents.
		listenLabel = new JLabel("Hey, listen! Press the button!");
		pressingButton = new JButton("Press Me");
		pressingButton.addActionListener(mainMenuControl);
		goToCreateUser = new JButton("Create User");
		goToCreateUser.addActionListener(mainMenuControl);
		goToDeleteUser = new JButton("Delete User");
		goToDeleteUser.addActionListener(mainMenuControl);
		
		// Add components to JPanel.
		add(listenLabel, BorderLayout.CENTER);
		add(pressingButton, BorderLayout.CENTER);
		add(goToCreateUser, BorderLayout.CENTER);
		add(goToDeleteUser, BorderLayout.CENTER);
		
		// Show the JPanel.
		setSize(500, 500);
		setVisible(true);
		
	}
	
}
