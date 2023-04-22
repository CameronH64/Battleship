package menucontrols;

import static org.junit.Assert.*;
import java.awt.*;
import org.junit.*;
import menupanels.MainMenuPanel;
import client.BattleshipClient;

public class MainMenuControlTest {

	Robot tester;
	MainMenuPanel mmPanel; 
	MainMenuControl mmControl;
	BattleshipClient clientTest;

	@Before
	public void setUp() {
		
		try {
			tester = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
		clientTest = new BattleshipClient();
	}

}
