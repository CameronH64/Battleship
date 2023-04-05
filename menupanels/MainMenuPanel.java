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
	
	//Data fields for JLabels and their corresponding JTextFields in the center on this screen
	protected JLabel labelEnterIPAddress;
	protected JLabel labelEnterUsername;
	protected JLabel labelEnterPassword;
	protected JLabel labelStatusResponse;
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
	//Data fields for the logo image
	private BufferedImage logo;
	private JLabel logoLabel;
	//Constant values height and width of this gui
	private static final int windowHeight = 500;
	private static final int windowWidth = 500;
	
	// Constructor
	public MainMenuPanel(MainMenuControl mainMenuControl){
		
		//Initialize buttons and labels 
		labelEnterIPAddress = new JLabel("Enter an IP Address: ");
		labelEnterUsername = new JLabel("Enter Username: ");
		labelEnterPassword = new JLabel("Enter Password: ");
		labelStatusResponse = new JLabel("");
		fieldIPAddress = new JTextField("address");
		fieldUsername = new JTextField("username");
		fieldPassword = new JTextField("password");
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
			 * to however large this window is. 30% (0.30 in the double here) 
			 * seems to be a good fit as long as the window is a square
			 */
			double logoHeightPercent = 0.30;
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
		//Add labels and text fields to the inner buffer
		panelBufferCenterCenter.add(labelEnterIPAddress,2,0);
		panelBufferCenterCenter.add(fieldIPAddress,2,1);
		panelBufferCenterCenter.add(labelEnterUsername,1,0);
		panelBufferCenterCenter.add(fieldUsername,1,1);
		panelBufferCenterCenter.add(labelEnterPassword,0,0);
		panelBufferCenterCenter.add(fieldPassword,0,1);
		//Add the inner buffer to the outer buffer 
		panelBufferCenter.add(panelBufferCenterCenter,BorderLayout.CENTER);
		
		
		
		// Add components to JPanel.
		this.add(logoLabel, BorderLayout.NORTH);
		
		this.add(panelBufferCenter);
		
		
		// Show the JPanel.
		this.setSize(windowWidth, windowHeight);
		this.setVisible(true);
		
	}
	
}
