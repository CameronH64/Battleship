package gameplaypanel;

import javax.swing.JButton;
import javax.swing.JPanel;

import gameplaycontrol.TestingPanelSwitchControl;

public class TestingPanelSwitchPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton connectButton;
	private JButton switchButton;
	
	public TestingPanelSwitchPanel(TestingPanelSwitchControl testingPanelSwitchControl) {
		
		connectButton = new JButton("Connect");
		connectButton.addActionListener(testingPanelSwitchControl);
		
		switchButton = new JButton("Switch");
		switchButton.addActionListener(testingPanelSwitchControl);

		add(connectButton);
		add(switchButton);

	}

}
