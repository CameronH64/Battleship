//Jared Bratton

package menupanels;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

import menucontrols.CreateUserControl;

public class CreateUserPanel extends JPanel {

	/**
	 * Test GitHub push+pull
	 */
	private static final long serialVersionUID = 1L;

	// Private Data Fields and JComponents.
	// Generally, it's better to have these explicitly declared here so that it's easier to debug the code.

	//Data fields for JLabels on this screen
	private JLabel labelEnterUsername;
	private JLabel labelEnterPassword;
	private JLabel labelConfirmPassword;
	private JLabel labelStatusResponse;
	//Data fields for text fields on this screen
	private JTextField fieldUsername;
	private JPasswordField fieldPassword;
	private JPasswordField fieldConfirmPassword;
	//Data fields for the buttons on this screen
	private JButton buttonMainMenu;
	private JButton buttonCreateUser;
	private JButton buttonQuit;
	//Data fields for buffer panels
	private JPanel panelGeneralBuffer;
	private JPanel panelBufferCenterCenter;
	private JPanel panelBufferCenter;
	private JPanel panelBufferBottom;
	private JPanel panelBufferCreateUser;
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
	public CreateUserPanel(CreateUserControl createUserControl){

		//Initialize buttons and labels
		labelEnterUsername = new JLabel("Enter Username: ");
		labelEnterPassword = new JLabel("Enter Password: ");
		labelConfirmPassword = new JLabel("Confirm Password: ");
		labelStatusResponse = new JLabel("Enter credentials to create account");
		fieldUsername = new JTextField("");
		fieldPassword = new JPasswordField("");
		fieldConfirmPassword = new JPasswordField("");
		buttonMainMenu = new JButton("Main Menu");
		buttonMainMenu.addActionListener(createUserControl);
		buttonCreateUser = new JButton("Create User");
		buttonCreateUser.addActionListener(createUserControl);
		buttonQuit = new JButton("Quit");
		buttonQuit.addActionListener(createUserControl);

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


		//Make buffer panels
		panelGeneralBuffer = new JPanel(new BorderLayout());
		panelBufferCenter = new JPanel(new BorderLayout());
		panelBufferCenterCenter = new JPanel(new GridLayout(3,2));
		panelBufferCreateUser = new JPanel();

		//Put the labels on their own small buffer panels to align text 
		panelBufferLabels = new JPanel[3];
		for (int i = 0; i < panelBufferLabels.length; i++)
		{
			panelBufferLabels[i] = new JPanel(new BorderLayout());
		}

		//This one holds the username label 
		panelBufferLabels[0].add(labelEnterUsername, BorderLayout.EAST);
		//This one holds the password label 
		panelBufferLabels[1].add(labelEnterPassword, BorderLayout.EAST);
		//This one holds the confirm password label
		panelBufferLabels[2].add(labelConfirmPassword, BorderLayout.EAST);

		//Add labels and text fields to the inner buffer (the order matters here!)
		panelBufferCenterCenter.add(panelBufferLabels[0]);
		panelBufferCenterCenter.add(fieldUsername);
		panelBufferCenterCenter.add(panelBufferLabels[1]);
		panelBufferCenterCenter.add(fieldPassword);
		panelBufferCenterCenter.add(panelBufferLabels[2]);
		panelBufferCenterCenter.add(fieldConfirmPassword);

		//Add the inner buffer to the outer buffer 
		panelBufferCenter.add(panelBufferCenterCenter, BorderLayout.CENTER);
		//Add the Log In Button to the center of the screen 

		panelBufferCreateUser.add(buttonCreateUser);
		panelBufferCenter.add(panelBufferCreateUser, BorderLayout.SOUTH);	


		//Make bottom buffer panel
		panelBufferBottom = new JPanel(new BorderLayout());
		panelBufferBottomButtons = new JPanel(new GridLayout(1,4));
		panelBufferStatus = new JPanel();

		//Add the buttons at the bottom of the window and status bar
		panelBufferStatus.add(labelStatusResponse);
		panelBufferBottom.add(panelBufferStatus, BorderLayout.NORTH);
		panelBufferBottomButtons.add(buttonQuit);
		panelBufferBottomButtons.add(buttonMainMenu);

		//Put the buttons buffer panel on the outer buffer panel
		panelBufferBottom.add(panelBufferBottomButtons, BorderLayout.SOUTH);

		// Add components to JPanel.
		panelGeneralBuffer.add(logoLabel, BorderLayout.NORTH);
		panelGeneralBuffer.add(panelBufferCenter, BorderLayout.CENTER);
		panelGeneralBuffer.add(panelBufferBottom, BorderLayout.SOUTH);
		this.add(panelGeneralBuffer);


		// Show the JPanel.
		setSize(windowWidth, windowHeight);
		setVisible(true);

	}


	/*
	 * Setters and Getters beyond this point
	 */
	public JLabel getLabelEnterUsername() {
		return labelEnterUsername;
	}

	public void setLabelEnterUsername(JLabel labelEnterUsername) {
		this.labelEnterUsername = labelEnterUsername;
	}

	public JLabel getLabelEnterPassword() {
		return labelEnterPassword;
	}

	public void setLabelEnterPassword(JLabel labelEnterPassword) {
		this.labelEnterPassword = labelEnterPassword;
	}

	public JLabel getLabelConfirmPassword() {
		return labelConfirmPassword;
	}

	public void setLabelConfirmPassword(JLabel labelConfirmPassword) {
		this.labelConfirmPassword = labelConfirmPassword;
	}

	public JLabel getLabelStatusResponse() {
		return labelStatusResponse;
	}

	public void setLabelStatusResponse(JLabel labelStatusResponse) {
		this.labelStatusResponse = labelStatusResponse;
	}

	public JTextField getFieldUsername() {
		return fieldUsername;
	}

	public void setFieldUsername(JTextField fieldUsername) {
		this.fieldUsername = fieldUsername;
	}

	public JPasswordField getFieldPassword() {
		return fieldPassword;
	}

	public void setFieldPassword(JPasswordField fieldPassword) {
		this.fieldPassword = fieldPassword;
	}

	public JPasswordField getFieldConfirmPassword() {
		return fieldConfirmPassword;
	}

	public void setFieldConfirmPassword(JPasswordField fieldConfirmPassword) {
		this.fieldConfirmPassword = fieldConfirmPassword;
	}

	public JButton getButtonMainMenu() {
		return buttonMainMenu;
	}

	public void setButtonMainMenu(JButton buttonMainMenu) {
		this.buttonMainMenu = buttonMainMenu;
	}

	public JButton getButtonCreateUser() {
		return buttonCreateUser;
	}

	public void setButtonCreateUser(JButton buttonCreateUser) {
		this.buttonCreateUser = buttonCreateUser;
	}

	public JButton getButtonQuit() {
		return buttonQuit;
	}

	public void setButtonQuit(JButton buttonQuit) {
		this.buttonQuit = buttonQuit;
	}

	public JPanel getPanelGeneralBuffer() {
		return panelGeneralBuffer;
	}

	public void setPanelGeneralBuffer(JPanel panelGeneralBuffer) {
		this.panelGeneralBuffer = panelGeneralBuffer;
	}

	public JPanel getPanelBufferCenterCenter() {
		return panelBufferCenterCenter;
	}

	public void setPanelBufferCenterCenter(JPanel panelBufferCenterCenter) {
		this.panelBufferCenterCenter = panelBufferCenterCenter;
	}

	public JPanel getPanelBufferCenter() {
		return panelBufferCenter;
	}

	public void setPanelBufferCenter(JPanel panelBufferCenter) {
		this.panelBufferCenter = panelBufferCenter;
	}

	public JPanel getPanelBufferBottom() {
		return panelBufferBottom;
	}

	public void setPanelBufferBottom(JPanel panelBufferBottom) {
		this.panelBufferBottom = panelBufferBottom;
	}

	public JPanel getPanelBufferCreateUser() {
		return panelBufferCreateUser;
	}

	public void setPanelBufferCreateUser(JPanel panelBufferCreateUser) {
		this.panelBufferCreateUser = panelBufferCreateUser;
	}

	public JPanel getPanelBufferBottomButtons() {
		return panelBufferBottomButtons;
	}

	public void setPanelBufferBottomButtons(JPanel panelBufferBottomButtons) {
		this.panelBufferBottomButtons = panelBufferBottomButtons;
	}

	public JPanel getPanelBufferStatus() {
		return panelBufferStatus;
	}

	public void setPanelBufferStatus(JPanel panelBufferStatus) {
		this.panelBufferStatus = panelBufferStatus;
	}

	public JPanel[] getPanelBufferLabels() {
		return panelBufferLabels;
	}

	public void setPanelBufferLabels(JPanel[] panelBufferLabels) {
		this.panelBufferLabels = panelBufferLabels;
	}

	public BufferedImage getLogo() {
		return logo;
	}

	public void setLogo(BufferedImage logo) {
		this.logo = logo;
	}

	public JLabel getLogoLabel() {
		return logoLabel;
	}

	public void setLogoLabel(JLabel logoLabel) {
		this.logoLabel = logoLabel;
	}

}
