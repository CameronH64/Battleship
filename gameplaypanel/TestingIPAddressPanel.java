package gameplaypanel;

import javax.swing.*;

import client.BattleshipClient;
import gameplaycontrol.GameplayControl;
import gameplaycontrol.TestingIPAddressControl;

import java.awt.*;

public class TestingIPAddressPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Private Data Fields and JComponents.
	// Generally, it's better to have these explicitly declared here so that it's easier to debug the code.
	
	JLabel battleshipLogoImage;
	JLabel enterIPAddressLabel;
	JTextField ipAddressTextField;
	JLabel invalidAddressLabel;
	JButton quitButton;
	JButton connectButton;

	public String getHostname() { return ipAddressTextField.getText(); }

	// Constructor
	public TestingIPAddressPanel(TestingIPAddressControl testingIPAddressControl){
		
		// ----------------------- Create JComponents -----------------------
		
		// Battleship logo
		ImageIcon image = new ImageIcon("./assets/battleshipLogo40.png");
		battleshipLogoImage = new JLabel();
		battleshipLogoImage.setIcon(image);
		
		// IP Address Panel
		JPanel ipAddressPanel = new JPanel(new GridLayout(1, 2, 5, 5));
		// IP Address instruction
		enterIPAddressLabel = new JLabel("Enter an IP Address:", JLabel.RIGHT);
		
		// IP Address TextField
		ipAddressTextField = new JTextField(10);
		
		ipAddressPanel.add(enterIPAddressLabel);
		ipAddressPanel.add(ipAddressTextField);
		
		// IP Address Error message
		invalidAddressLabel = new JLabel("error goes here", JLabel.CENTER);
		invalidAddressLabel.setForeground(Color.RED);
		JPanel invalidAddressLabelPanel = new JPanel();
		invalidAddressLabelPanel.add(invalidAddressLabel);
		
		// Buttons
		JPanel buttonPanel = new JPanel();
		quitButton = new JButton("Quit");
		quitButton.addActionListener(testingIPAddressControl);
		connectButton = new JButton("Connect");
		connectButton.addActionListener(testingIPAddressControl);
		buttonPanel.add(quitButton);
		buttonPanel.add(connectButton);
		
		
		// ----------------------- Add JComponents to JPanel -----------------------
		JPanel grid = new JPanel(new GridLayout(4, 1, 0, 10));
		grid.add(battleshipLogoImage);
		grid.add(ipAddressPanel);
		grid.add(buttonPanel);
		grid.add(invalidAddressLabelPanel);
		add(grid);
		
		
		// ----------------------- Show JPanel -----------------------
//		setSize(500, 500);
		setVisible(true);
		
	}
	
}
