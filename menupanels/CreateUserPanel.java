//Jared Bratton

package menupanels;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import menucontrols.CreateUserControl;

public class CreateUserPanel extends JPanel {
	
	/**
	 * Test GitHub push+pull
	 */
	private static final long serialVersionUID = 1L;
	
	// Private Data Fields and JComponents.
	// Generally, it's better to have these explicitly declared here so that it's easier to debug the code.
	
	JLabel listenLabel;
	JButton mainMenuButton;
	JButton createUserButton;
	JButton quitButton;
	
	// Constructor
	public CreateUserPanel(CreateUserControl createUserControl){
		
		listenLabel = new JLabel("Hey, listen!");
		mainMenuButton = new JButton("Main Menu");
		mainMenuButton.addActionListener(createUserControl);
		createUserButton = new JButton("Create User");
		createUserButton.addActionListener(createUserControl);
		quitButton = new JButton("Quit");
		quitButton.addActionListener(createUserControl);
		
		add(listenLabel);
		add(mainMenuButton);
		add(createUserButton);
		add(quitButton);
		
		// Show the JPanel.
		setSize(500, 500);
		setVisible(true);
		
	}
	
}
