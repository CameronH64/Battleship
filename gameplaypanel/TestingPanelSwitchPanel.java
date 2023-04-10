package gameplaypanel;

import javax.swing.JButton;
import javax.swing.JPanel;

import gameplaycontrol.TestingPanelSwitchControl;

public class TestingPanelSwitchPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton switchButton;
	
	public TestingPanelSwitchPanel(TestingPanelSwitchControl testingPanelSwitchControl) {
		
		switchButton = new JButton("Switch");
		switchButton.addActionListener(testingPanelSwitchControl);
		
		add(switchButton);
		
	}
	
}
