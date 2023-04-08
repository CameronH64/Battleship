//Jared Bratton

package menupanels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

// Importing classes from other packages
import menucontrols.MainMenuControl;
import menucontrols.CreateUserControl;
import menucontrols.DeleteUserControl;

public class TestMenuPanels extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Constant values height and width of this GUI
	private static final int windowHeight = 500;
	private static final int windowWidth = 500;

	TestMenuPanels(){

		// Set the title and default close operation.
		this.setTitle("Client GUI");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Remember, this is a TESTING class, and it uses card layout.
		CardLayout cardLayout = new CardLayout();			// Need to declare this separately because the cardLayout instantiation will be used later.
		JPanel buffer = new JPanel(cardLayout);

		// Instantiate panels and their control classes.
		MainMenuControl mainMenuControl = new MainMenuControl(buffer);
		MainMenuPanel mainMenuPanel = new MainMenuPanel(mainMenuControl);
		mainMenuControl.setMainMenu(mainMenuPanel);

		CreateUserControl createUserControl = new CreateUserControl(buffer);
		CreateUserPanel createUserPanel = new CreateUserPanel(createUserControl);
		createUserControl.setCreateUserMenu(createUserPanel);

		DeleteUserControl deleteUserControl = new DeleteUserControl(buffer);
		DeleteUserPanel deleteUserPanel = new DeleteUserPanel(deleteUserControl);
		deleteUserControl.setDeleteUserMenu(deleteUserPanel);

		// Add the JPanels to the buffer JPanel.
		buffer.add(mainMenuPanel, "1");
		buffer.add(createUserPanel, "2");
		buffer.add(deleteUserPanel, "3");

		// Add the card layout container to the JFrame.
		this.add(buffer, BorderLayout.CENTER);

		// Show the JFrame.
		setSize(windowWidth, windowHeight);
		this.setVisible(true);

		cardLayout.show(buffer, "1");		// Default screen.

	}

	public static void main(String[] args) {

		new TestMenuPanels();

	}

}
