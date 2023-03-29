package shipplacementpanel;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import shipplacementcontrol.ShipPlacementControl;

public class ShipPlacementPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Private Data Fields and JComponents.
	// Generally, it's better to have these explicitly declared here so that it's easier to debug the code.
	
	/*
	
	Beckett:
	
	You'll need:
	A gridLayout JPanel for the ship placement area
	Radio buttons for the ship selection
	Figure out a way to represent the ships in the 	"shipyard" so to speak.
	
	You don't have to make it look exactly like the picture because these JPanels are difficult to code.
	But it should have all the functionality and not look ugly.
	
	Like, if it helps to put the confirm placement button somewhere else, feel free to put it there.
	As long as it's there. Same goes for the invalid fleet configuration message that will appear.
	
	
	*/
	
	JLabel listenLabel;
	JButton confirmPlacementButton;
	
	// Constructor
	public ShipPlacementPanel(ShipPlacementControl shipPlacementControl){
		
		// Make the GUI using JComponents.
		listenLabel = new JLabel("Hey, listen! Press the button!");
		confirmPlacementButton = new JButton("Confirm Placement");
		confirmPlacementButton.addActionListener(shipPlacementControl);
		
		// Add components to JPanel.
		add(listenLabel, BorderLayout.CENTER);
		add(confirmPlacementButton);
		
		// Show the JPanel.
		setSize(500, 500);
		setVisible(true);
		
	}
	
}
