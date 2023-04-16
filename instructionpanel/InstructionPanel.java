package instructionpanel;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

// Import the instruction control.
import instructioncontrol.InstructionControl;

public class InstructionPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Private Data Fields and JComponents.
	// Generally, it's better to have these explicitly declared here so that it's easier to debug the code.

	JLabel listenLabel;
	JButton continueButton;

	// Constructor
	public InstructionPanel(InstructionControl instructionControl){

		setBackground(new Color(255, 255, 255));
		//setBorder(new EmptyBorder(5, 5, 5, 5));

		
		JTextArea txtrShipPlacementSelect = new JTextArea();
		txtrShipPlacementSelect.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 13));
		txtrShipPlacementSelect.setText("Ship Placement:\r\n\r\nSelect the ship you want to place by selecting its radio button.\r\n\r\nYou can now toggle any cell in the grid to place that section of your ship."
				+ "\r\n\r\nYour ship configuration must match the given ship sizes given below.\r\n\r\nCarrier: 5\r\nBattleship: 4\r\nDestroyer: 3\r\nSubmarine: 3\r\nPatrol: 2"
				+ "\r\n\r\nWhen you're ready, press continue, and the server will validate your ship \r\nplacements. \r\n\r\nIf not valid, ensure that your ships are configured properly, and try again."
				+ "\r\n\r\nGameplay:\r\n\r\nIf you're labeled \"Player 1\" you will go first. You will see a message that says: \r\n\r\n\"Your Turn.\""
				+ "\r\n\r\nSimply take turns clicking on your firing grids until one player sinks all of his \r\nopponent's ships.\r\n\r\nWhite: miss\r\nRed: hit"
				+ "\r\nRed with a letter: hit and sunk that ship. The letter \r\nshows which ship you sank.");
		txtrShipPlacementSelect.setBounds(0, 0, 437, 587);
		add(txtrShipPlacementSelect, BorderLayout.SOUTH);
		
		JButton ContinueButton = new JButton("Continue");
		ContinueButton.setBackground(new Color(255, 255, 255));
		ContinueButton.setBounds(314, 598, 113, 23);
		add(ContinueButton);
		// Make the GUI using JComponents.
		listenLabel = new JLabel("Hey, listen!");
		continueButton = new JButton("Continue");
		continueButton.addActionListener(instructionControl);

		// Add components to JPanel.
		add(listenLabel, BorderLayout.CENTER);
		add(continueButton, BorderLayout.CENTER);

		// Show the JPanel.
		setSize(500, 500);
		setVisible(true);

	}

}