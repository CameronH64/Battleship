package shipplacementpanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

// Importing classes from other packages
import shipplacementcontrol.ShipPlacementControl;

public class TestShipPlacementPanel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	TestShipPlacementPanel(){

		// Set the title and default close operation.
		this.setTitle("Client GUI");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Remember, this is a TESTING class, and it uses card layout.
		CardLayout cardLayout = new CardLayout();			// Need to declare this separately because the cardLayout instantiation will be used later.
		JPanel buffer = new JPanel(cardLayout);
		
		// Instantiate panels and their control classes.
		ShipPlacementControl shipPlacementControl = new ShipPlacementControl(buffer);
		ShipPlacementPanel shipPlacementPanel = new ShipPlacementPanel(shipPlacementControl);
		
		// Add the JPanels to the buffer JPanel.
		buffer.add(shipPlacementPanel, "1");
		
		// Add the card layout container to the JFrame.
		this.add(buffer, BorderLayout.CENTER);
		
		// Show the JFrame.
		this.setSize(500, 500);
		this.setVisible(true);

		cardLayout.show(buffer, "1");		// Default screen.
		
	}

	public static void main(String[] args) {
		
		new TestShipPlacementPanel();

	}

}
