package menucontrols;

import static org.junit.Assert.*;
import java.awt.*;
import java.awt.event.ActionListener;
import org.junit.*;
import java.util.*;
import client.BattleshipClient;
import javax.swing.*;
import menupanels.MainMenuPanel;

public class MainMenuControlTest extends JFrame {

	static Robot tester;
	static MainMenuPanel mmPanel; 
	static MainMenuControl mmControl;
	static BattleshipClient clientTest;
	//Constant values height and width of this GUI
	private static final int windowHeight = 500;
	private static final int windowWidth = 500;

	@BeforeClass
	public void setUp() {
		
		try {
			tester = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
		clientTest = new BattleshipClient();
	}

}
