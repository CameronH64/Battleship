package instructioncontrol;

import javax.swing.*;

import java.awt.CardLayout;
import java.awt.event.*;
import java.io.IOException;
import java.io.Serializable;

public class InstructionControl implements ActionListener
{
	// Private data fields.
	private JPanel container;
	
	// This constructor connects the outside components so that the control panel can affect things.
	public InstructionControl(JPanel container)
	{
		this.container = container;
	}

	// Handle button clicks.
	public void actionPerformed(ActionEvent ae)
	{
		
		
		// Get the name of the button clicked.
		String command = ae.getActionCommand();
		
		
		
		// The Cancel button takes the user back to the initial panel.
		if (command == "Continue")
		{
			System.out.println("Continued pressed.");
			
		}
		
		// Testing code. Use this code to switch to another JPanel. (Make a new JPanel for yourself for testing.
		
//		CardLayout cardLayout = (CardLayout)container.getLayout();
//		cardLayout.show(container, "1");

		
	}

}
