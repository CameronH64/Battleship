package menucontrols;


import static org.junit.Assert.*;
import java.awt.*;
import java.awt.event.*;

import org.junit.*;
import java.util.*;
import client.BattleshipClient;
import dataclasses.MainMenuLoginData;

import javax.swing.*;
import menupanels.MainMenuPanel;

public class MainMenuControlTest extends JFrame {

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
	public static void setUp() {

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

		//Make the fields editable and buttons pressable 
		fieldUsername.setEditable(true);
		fieldPassword.setEditable(true);
		buttonLogIn.setEnabled(true);

		//Make some expected fields to use for testing 
		String[] credentials = {"username","password"};

		MainMenuLoginData testCredentials = new MainMenuLoginData(credentials[0],credentials[1]);

		//Select the username field
		tester.mouseMove(windowWidth - 100, windowHeight - 250);
		tester.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		tester.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		try 
		{
			Thread.sleep(1000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}

		//Type in username and password
		tester.keyPress(KeyEvent.VK_U);
		tester.keyRelease(KeyEvent.VK_U);
		tester.keyPress(KeyEvent.VK_S);
		tester.keyRelease(KeyEvent.VK_S);
		tester.keyPress(KeyEvent.VK_E);
		tester.keyRelease(KeyEvent.VK_E);
		tester.keyPress(KeyEvent.VK_R);
		tester.keyRelease(KeyEvent.VK_R);
		tester.keyPress(KeyEvent.VK_N);
		tester.keyRelease(KeyEvent.VK_N);
		tester.keyPress(KeyEvent.VK_A);
		tester.keyRelease(KeyEvent.VK_A);
		tester.keyPress(KeyEvent.VK_M);
		tester.keyRelease(KeyEvent.VK_M);
		tester.keyPress(KeyEvent.VK_E);
		tester.keyRelease(KeyEvent.VK_E);
		//Pause
		try 
		{
			Thread.sleep(1000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		tester.keyPress(KeyEvent.VK_TAB);
		tester.keyRelease(KeyEvent.VK_TAB);
		tester.keyPress(KeyEvent.VK_P);
		tester.keyRelease(KeyEvent.VK_P);
		tester.keyPress(KeyEvent.VK_A);
		tester.keyRelease(KeyEvent.VK_A);
		tester.keyPress(KeyEvent.VK_S);
		tester.keyRelease(KeyEvent.VK_S);
		tester.keyPress(KeyEvent.VK_S);
		tester.keyRelease(KeyEvent.VK_S);
		tester.keyPress(KeyEvent.VK_W);
		tester.keyRelease(KeyEvent.VK_W);
		tester.keyPress(KeyEvent.VK_O);
		tester.keyRelease(KeyEvent.VK_O);
		tester.keyPress(KeyEvent.VK_R);
		tester.keyRelease(KeyEvent.VK_R);
		tester.keyPress(KeyEvent.VK_D);
		tester.keyRelease(KeyEvent.VK_D);

		//Pause
		try 
		{
			Thread.sleep(1000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}

		//Move mouse to Log In button and press it
		tester.mouseMove(windowWidth - 250, windowHeight - 200);
		tester.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		tester.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		//Pause
		try 
		{
			Thread.sleep(1000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}

		//Retrieve control's credentials
		MainMenuLoginData cd = mmControl.getCredentials();
		//Check these credentials against expected username and password
		assertEquals("check main menu control login data",testCredentials.getUsername(),cd.getUsername());
		assertEquals("check main menu control login data",testCredentials.getPassword(),cd.getPassword());

		try 
		{
			Thread.sleep(2000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}

}
