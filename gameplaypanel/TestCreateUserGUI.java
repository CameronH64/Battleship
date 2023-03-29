package gameplaypanel;

import java.awt.*;
import javax.swing.*;

public class TestCreateUserGUI extends JFrame {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TestCreateUserGUI() {
		
		// JFrame setup
		setTitle("Create User GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Remember, this is a TESTING class, and it uses card layout.
		CardLayout cardLayout = new CardLayout();			// Need to declare this separately because the cardLayout instantiation will be used later.
		JPanel buffer = new JPanel(cardLayout);
		
		// For the control classes, you need to put the buffer panel inside of it.
		// This connects the whatever GUI (just a JPanel) to the action listener.
		CreateUserControl createUserControl = new CreateUserControl(buffer);
		CreateUserPanel createUserGUI = new CreateUserPanel(createUserControl);
		
		// Add the GUI (just a JPanel and has the actionListener connected to it) to the JFrame (this JFrame).
		add(createUserGUI, BorderLayout.CENTER);

		// Show the JFrame.
		setSize(550, 350);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new TestCreateUserGUI();
	}

}
