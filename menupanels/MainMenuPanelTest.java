package menupanels;

import static org.junit.Assert.*;
import java.awt.*;
import java.awt.event.ActionListener;
import org.junit.*;
import java.util.*;
import client.BattleshipClient;
import javax.swing.*;
import menucontrols.MainMenuControl;

public class MainMenuPanelTest extends JFrame{

	static Robot tester;
	static MainMenuPanel mmPanel; 
	static MainMenuControl mmControl;
	static BattleshipClient clientTest;
	static JPanel bufferPanel;
	static JButton buttonConnect;
	static JButton buttonQuit;
	static JButton buttonLogIn;
	static JButton buttonGoToDeleteUser;
	static JButton buttonGoToCreateUser;
	static JTextField fieldUsername;
	static JTextField fieldPassword;
	//Constant values height and width of this GUI
	private static final int windowHeight = 500;
	private static final int windowWidth = 500;

	@BeforeClass
	public static void setUp() 
	{

		try {
			tester = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}

		clientTest = new BattleshipClient();
		bufferPanel = new JPanel();
		mmControl = new MainMenuControl(bufferPanel, clientTest);
		mmPanel = new MainMenuPanel(mmControl);
		mmControl.setMainMenu(mmPanel);
	}

	@Before
	public void setUpBeforeTest()
	{
		buttonConnect = mmPanel.getButtonConnect();
		buttonQuit = mmPanel.getButtonQuit();
		buttonLogIn = mmPanel.getButtonLogIn();
		buttonGoToDeleteUser = mmPanel.getButtonGoToDeleteUser();
		buttonGoToCreateUser = mmPanel.getButtonGoToCreateUser();
		fieldUsername = mmPanel.getFieldUsername();
		fieldPassword = mmPanel.getFieldPassword();

		// Put components on the panel
		bufferPanel.add(mmPanel);
		this.add(bufferPanel);
		// Show the JFrame.
		setSize(windowWidth, windowHeight);
		this.setVisible(true);
	}

	@Test 
	public void test()
	{
		try 
		{
			Thread.sleep(2000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}

		//Try the connect button
		String expectedResponse = "Connected to Battleship Server.";
		JLabel labelStatusResponse = mmPanel.getLabelStatusResponse();
		buttonConnect.doClick(500);
		if (!labelStatusResponse.getText().equals(expectedResponse))
		{
			assertEquals("check MainMenuPanel",labelStatusResponse.getText(),expectedResponse);
		}
		else 
		{
			try 
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}

		//Try the Log In button
		expectedResponse = "Sent credentials to server.";
		buttonLogIn.doClick(500);
		if (!labelStatusResponse.getText().equals(expectedResponse))
		{
			assertEquals("check MainMenuPanel",labelStatusResponse.getText(),expectedResponse);
		}
		else
		{
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}

		//Test the Delete User button
		expectedResponse = "Changing to Delete User menu.";
		buttonGoToDeleteUser.doClick(500);
		if (!labelStatusResponse.getText().equals(expectedResponse)) 
		{
			assertEquals("check MainMenuPanel",labelStatusResponse.getText(),expectedResponse);
		}
		else 
		{
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}

		//Test the Create User button
		expectedResponse = "Changing to Create User menu.";
		buttonGoToCreateUser.doClick(500);
		if (!labelStatusResponse.getText().equals(expectedResponse))
		{
			assertEquals("check MainMenuPanel",labelStatusResponse.getText(),expectedResponse);
		}
		else
		{
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}

	}

}
