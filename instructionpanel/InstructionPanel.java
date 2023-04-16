package instructionpanel;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

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