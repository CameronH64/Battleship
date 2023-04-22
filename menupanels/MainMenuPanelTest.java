package menupanels;

import static org.junit.Assert.*;
import java.awt.*;
import java.awt.event.ActionListener;
import org.junit.*;
import java.util.*;
import client.BattleshipClient;
import javax.swing.*;
import menucontrols.MainMenuControl;

public class MainMenuPanelTest {
	
	static Robot tester;
	static MainMenuPanel mmPanel; 
	static MainMenuControl mmControl;
	static TestMenuPanels panels;
	static BattleshipClient clientTest;
	static JPanel bufferPanel;
	static String[] credentials = {"username", "password"};
	static JButton buttonConnect;
	static JButton buttonQuit;
	static JButton buttonLogIn;
	static JButton buttonGoToDeleteUser;
	static JButton buttonGoToCreateUser;
	static CreateUserPanel panelCU;
	static DeleteUserPanel panelDU;
	static JButton buttonCUGoToMM;
	static JButton buttonDUGoToMM;
	static JTextField fieldUsername;
	static JTextField fieldPassword;
	static ArrayList<JButton> buttonsList;

	@BeforeClass
	public static void setUp() 
	{
		
		try {
			tester = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
		clientTest = new BattleshipClient();
		panels = new TestMenuPanels();
		/*
		 * The MainMenuPanel in the TestMenuPanels is deep in a 
		 * hierarchy of embedded JPanels and stuff. The following 
		 * code exists to dig through that 
		 */
		Component[] componentArray = panels.getComponents();
		JRootPane rootPane = (JRootPane)componentArray[0];
		JLayeredPane layeredPane = rootPane.getLayeredPane();
		bufferPanel = (JPanel)layeredPane.getComponent(0); 
		//This gets the buffer that MainMenuPanel should be on
		bufferPanel = (JPanel)bufferPanel.getComponent(0);
		mmPanel = (MainMenuPanel)bufferPanel.getComponent(0);
		JButton buttonConnect = mmPanel.getButtonConnect();
		//To get the controller, go through the button
		ActionListener[] controlListeners = buttonConnect.getActionListeners();
		mmControl = (MainMenuControl)controlListeners[0];
		
		buttonsList = new ArrayList<JButton>();
	}
	
	@Before
	public void setUpBeforeTest()
	{
		buttonConnect = mmPanel.getButtonConnect();
		buttonQuit = mmPanel.getButtonQuit();
		buttonLogIn = mmPanel.getButtonLogIn();
		buttonGoToDeleteUser = mmPanel.getButtonGoToDeleteUser();
		buttonGoToCreateUser = mmPanel.getButtonGoToCreateUser();
		panelCU = (CreateUserPanel)bufferPanel.getComponent(1);
		panelDU = (DeleteUserPanel)bufferPanel.getComponent(2);
		buttonCUGoToMM = panelCU.getButtonMainMenu();
		buttonDUGoToMM = panelDU.getButtonMainMenu();
		fieldUsername = mmPanel.getFieldUsername();
		fieldPassword = mmPanel.getFieldPassword();
		buttonsList.add(buttonConnect);
		buttonsList.add(buttonQuit);
		buttonsList.add(buttonLogIn);
		buttonsList.add(buttonGoToDeleteUser);
		buttonsList.add(buttonGoToCreateUser);
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
		
		
		
	}

}
