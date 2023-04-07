//Jared Bratton

package menupanels;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import javax.imageio.*;
import javax.swing.*;
import menucontrols.MainMenuControl;

public class MainMenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	// Private Data Fields and JComponents.
	// Generally, it's better to have these explicitly declared here so that it's easier to debug the code.

	//Data fields for JLabels on this screen
	protected JLabel labelEnterIPAddress;
	protected JLabel labelEnterUsername;
	protected JLabel labelEnterPassword;
	protected JLabel labelStatusResponse;
	//Data fields for text fields on this screen
	protected JTextField fieldIPAddress;
	protected JTextField fieldUsername;
	protected JTextField fieldPassword;
	//Data fields for the buttons on this screen
	protected JButton buttonQuit;
	protected JButton buttonGoToCreateUser;
	protected JButton buttonGoToDeleteUser;
	protected JButton buttonConnect;
	protected JButton buttonLogIn;
	//Data fields for buffer panels
	private JPanel panelBufferCenterCenter;
	private JPanel panelBufferCenter;
	private JPanel panelBufferBottom;
	private JPanel panelBufferBottomButtons;
	private JPanel panelBufferStatus;
	private JPanel[] panelBufferLabels;
	//Data fields for the logo image
	private BufferedImage logo;
	private JLabel logoLabel;
	//Constant values height and width of this GUI
	private static final int windowHeight = 500;
	private static final int windowWidth = 500;

	// Constructor
	public MainMenuPanel(MainMenuControl mainMenuControl){

		//Initialize buttons and labels 
		labelEnterIPAddress = new JLabel("Enter an IP Address: ");
		labelEnterUsername = new JLabel("Enter Username: ");
		labelEnterPassword = new JLabel("Enter Password: ");
		labelStatusResponse = new JLabel("Please connect before logging in!");
		fieldIPAddress = new JTextField("");
		fieldUsername = new JTextField("");
		fieldPassword = new JTextField("");
		fieldUsername.setEditable(false);
		fieldPassword.setEditable(false);
		buttonQuit = new JButton("Quit");
		buttonQuit.addActionListener(mainMenuControl);
		buttonGoToCreateUser = new JButton("Create User");
		buttonGoToCreateUser.addActionListener(mainMenuControl);
		buttonGoToDeleteUser = new JButton("Delete User");
		buttonGoToDeleteUser.addActionListener(mainMenuControl);
		buttonConnect = new JButton("Connect");
		buttonConnect.addActionListener(mainMenuControl);
		buttonLogIn = new JButton("Log In");
		buttonLogIn.addActionListener(mainMenuControl);


		//Make the logo image a swing component 
		try
		{
			//Read the image file
			logo = ImageIO.read(new File("./assets/battleshipLogo.png"));
			//Store the read image into a JLabel
			/*
			 * The original logo image's aspect ratio is 800px width by 310px height
			 * The scaled width of the logo will be based on what percent of the window its height is
			 * This way, the image retains its size ratio but can be scaled 
			 * to however large this window is. 35% (0.35 in the double here) 
			 * seems to be a good fit as long as the window is a square
			 */
			double logoHeightPercent = 0.35;
			int logoScaledWidth = (int)(((double)windowHeight * logoHeightPercent) * (800D / 310D));
			logoLabel = new JLabel(new ImageIcon(logo.getScaledInstance(logoScaledWidth, (int)(windowHeight * logoHeightPercent), Image.SCALE_DEFAULT)));
		}
		catch (Exception exceptionImageReader)
		{
			exceptionImageReader.printStackTrace();
		}


		//Make central buffer panels, where the JLabels and JTextFields will go on
		panelBufferCenter = new JPanel(new BorderLayout());
		panelBufferCenterCenter = new JPanel(new GridLayout(3,2));

		//Put the labels on their own small buffer panels to align text 
		panelBufferLabels = new JPanel[3];
		for (int i = 0; i < panelBufferLabels.length; i++)
		{
			panelBufferLabels[i] = new JPanel(new BorderLayout());
		}

		//This one holds the IP address label
		panelBufferLabels[0].add(labelEnterIPAddress, BorderLayout.WEST);
		//This one holds the username label 
		panelBufferLabels[1].add(labelEnterUsername, BorderLayout.WEST);
		//This one holds the password label 
		panelBufferLabels[2].add(labelEnterPassword, BorderLayout.WEST);

		//Add labels and text fields to the inner buffer (the order matters here!)
		panelBufferCenterCenter.add(panelBufferLabels[0]);
		panelBufferCenterCenter.add(fieldIPAddress);
		panelBufferCenterCenter.add(panelBufferLabels[1]);
		panelBufferCenterCenter.add(fieldUsername);
		panelBufferCenterCenter.add(panelBufferLabels[2]);
		panelBufferCenterCenter.add(fieldPassword);

		//Add the inner buffer to the outer buffer 
		panelBufferCenter.add(panelBufferCenterCenter, BorderLayout.CENTER);
		//Add the Log In Button to the center of the screen 
		panelBufferCenter.add(buttonLogIn, BorderLayout.SOUTH);		
		
		
		//Make bottom buffer panel
		panelBufferBottom = new JPanel(new BorderLayout());
		panelBufferBottomButtons = new JPanel(new GridLayout(1,4));
		panelBufferStatus = new JPanel();
		
		//Add the buttons at the bottom of the window and status bar
		panelBufferStatus.add(labelStatusResponse);
		panelBufferBottom.add(panelBufferStatus, BorderLayout.NORTH);
		panelBufferBottomButtons.add(buttonQuit);
		panelBufferBottomButtons.add(buttonGoToDeleteUser);
		panelBufferBottomButtons.add(buttonGoToCreateUser);
		panelBufferBottomButtons.add(buttonConnect);
		
		//Put the buttons buffer panel on the outer buffer panel
		panelBufferBottom.add(panelBufferBottomButtons, BorderLayout.SOUTH);

		// Add components to JPanel.
		this.add(logoLabel, BorderLayout.NORTH);
		this.add(panelBufferCenter); 
		this.add(panelBufferBottom, BorderLayout.SOUTH);


		// Show the JPanel.
		this.setSize(windowWidth, windowHeight);
		this.setVisible(true);

	}

}
