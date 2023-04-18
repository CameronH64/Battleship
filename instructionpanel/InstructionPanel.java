package instructionpanel;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import instructioncontrol.InstructionControl;

public class InstructionPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JButton continueButton;
	JTextArea instructionText;
	
	// Constructor
	public InstructionPanel(InstructionControl instructionControl){

		setBackground(new Color(255, 255, 255));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setLayout(new BorderLayout());
		
		// Make the GUI using JComponents.
		
		instructionText = new JTextArea();
		instructionText.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 13));
		instructionText.setText("Ship Placement:\r\n\r\nSelect the ship you want to place by selecting its radio button.\r\n\r\nYou can now toggle any cell in the grid to place that section of your ship."
				+ "\r\n\r\nYour ship configuration must match the given ship sizes given below.\r\n\r\nCarrier: 5\r\nBattleship: 4\r\nDestroyer: 3\r\nSubmarine: 3\r\nPatrol: 2"
				+ "\r\n\r\nWhen you're ready, press continue, and the server will validate your ship \r\nplacements. \r\n\r\nIf not valid, ensure that your ships are configured properly, and try again."
				+ "\r\n\r\nGameplay:\r\n\r\nIf you're labeled \"Player 1\" you will go first. You will see a message that says: \r\n\r\n\"Your Turn.\""
				+ "\r\n\r\nSimply take turns clicking on your firing grids until one player sinks all of his \r\nopponent's ships.\r\n\r\nWhite: miss\r\nRed: hit"
				+ "\r\nRed with a letter: hit and sunk that ship. The letter \r\nshows which ship you sank.");
		
		instructionText.setBounds(0, 0, 437, 587);
		instructionText.setEditable(false);
		
		continueButton = new JButton("Continue");
		continueButton.addActionListener(instructionControl);
		
		
		
		// Add components to JPanel.
		add(instructionText, BorderLayout.NORTH);
		add(continueButton, BorderLayout.SOUTH);

		// Show the JPanel.
		setSize(500, 500);
		setVisible(true);

	}

}