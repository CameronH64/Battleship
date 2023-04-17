//Jared Bratton

package menupanels;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import menucontrols.DeleteUserControl;

public class DeleteUserPanel extends JPanel {
	
	/**
	 * push+pull test
	 */
	private static final long serialVersionUID = 1L;
	
	// Private Data Fields and JComponents.
	// Generally, it's better to have these explicitly declared here so that it's easier to debug the code.
	
	JLabel listenLabel;
	JButton mainMenuButton;
	JButton deleteUserButton;
	JButton quitButton;
	
	// Constructor
	public DeleteUserPanel(DeleteUserControl deleteUserControl){		
		
		listenLabel = new JLabel("Hey, listen!");
		mainMenuButton = new JButton("Main Menu");
		mainMenuButton.addActionListener(deleteUserControl);
		deleteUserButton = new JButton("Delete User");
		deleteUserButton.addActionListener(deleteUserControl);
		quitButton = new JButton("Quit");
		quitButton.addActionListener(deleteUserControl);
		
		add(listenLabel);
		add(mainMenuButton);
		add(deleteUserButton);
		add(quitButton);
		
		// Show the JPanel.
		setSize(500, 500);
		setVisible(true);
		
	}
	
}
