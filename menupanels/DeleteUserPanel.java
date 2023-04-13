//Jared Bratton

package menupanels;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import menucontrols.DeleteUserControl;

public class DeleteUserPanel extends JPanel {

	/**
	 * push+pull test
	 */
	private static final long serialVersionUID = 1L;

	// Private Data Fields and JComponents.
	// Generally, it's better to have these explicitly declared here so that it's easier to debug the code.

	//Data fields for JLabels on this screen
	private JLabel labelEnterUsername;
	private JLabel labelEnterPassword;
	private JLabel labelStatusResponse;
	//Data fields for text fields on this screen
	private JTextField fieldUsername;
	private JTextField fieldPassword;
	//Data fields for the buttons on this screen
	private JButton buttonMainMenu;
	private JButton buttonDeleteUser;
	private JButton buttonQuit;
	//Data fields for buffer panels
	private JPanel panelGeneralBuffer;
	private JPanel panelBufferCenterCenter;
	private JPanel panelBufferCenter;
	private JPanel panelBufferBottom;
	private JPanel panelBufferBottomButtons;
	private JPanel panelBufferDeleteUser;
	private JPanel panelBufferStatus;
	private JPanel[] panelBufferLabels;
	//Data fields for the logo image
	private BufferedImage logo;
	private JLabel logoLabel;
	//Constant values height and width of this GUI
	private static final int windowHeight = 500;
	private static final int windowWidth = 500;

	// Constructor
	public DeleteUserPanel(DeleteUserControl deleteUserControl){		

		//Initialize buttons and labels
		labelEnterUsername = new JLabel("Enter Username: ");
		labelEnterPassword = new JLabel("Enter Password: ");
		labelStatusResponse = new JLabel("Enter account credentials to delete account");
		fieldUsername = new JTextField("");
		fieldPassword = new JTextField("");
		buttonMainMenu = new JButton("Main Menu");
		buttonMainMenu.addActionListener(deleteUserControl);
		buttonDeleteUser = new JButton("Delete User");
		buttonDeleteUser.addActionListener(deleteUserControl);
		buttonQuit = new JButton("Quit");
		buttonQuit.addActionListener(deleteUserControl);


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
		panelBufferDeleteUser = new JPanel();

		//Put the labels on their own small buffer panels to align text 
		panelBufferLabels = new JPanel[2];
		for (int i = 0; i < panelBufferLabels.length; i++)
		{
			panelBufferLabels[i] = new JPanel(new BorderLayout());
		}

		//This one holds the username label 
		panelBufferLabels[0].add(labelEnterUsername, BorderLayout.EAST);
		//This one holds the password label 
		panelBufferLabels[1].add(labelEnterPassword, BorderLayout.EAST);

		//Add labels and text fields to the inner buffer (the order matters here!)
		panelBufferCenterCenter.add(panelBufferLabels[0]);
		panelBufferCenterCenter.add(fieldUsername);
		panelBufferCenterCenter.add(panelBufferLabels[1]);
		panelBufferCenterCenter.add(fieldPassword);

		//Add the inner buffer to the outer buffer 
		panelBufferCenter.add(panelBufferCenterCenter, BorderLayout.CENTER);
		//Add the Log In Button to the center of the screen 
		panelBufferDeleteUser.add(buttonDeleteUser);
		panelBufferCenter.add(panelBufferDeleteUser, BorderLayout.SOUTH);		


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

	public JLabel getLabelStatusResponse() {
		return labelStatusResponse;
	}

	public void setLabelStatusResponse(JLabel labelStatusResponse) {
		this.labelStatusResponse = labelStatusResponse;
	}

	public JButton getButtonMainMenu() {
		return buttonMainMenu;
	}

	public void setButtonMainMenu(JButton buttonMainMenu) {
		this.buttonMainMenu = buttonMainMenu;
	}

	public JButton getButtonDeleteUser() {
		return buttonDeleteUser;
	}

	public void setButtonDeleteUser(JButton buttonDeleteUser) {
		this.buttonDeleteUser = buttonDeleteUser;
	}

	public JButton getButtonQuit() {
		return buttonQuit;
	}

	public void setButtonQuit(JButton buttonQuit) {
		this.buttonQuit = buttonQuit;
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


	public JTextField getFieldUsername() {
		return fieldUsername;
	}


	public void setFieldUsername(JTextField fieldUsername) {
		this.fieldUsername = fieldUsername;
	}


	public JTextField getFieldPassword() {
		return fieldPassword;
	}


	public void setFieldPassword(JTextField fieldPassword) {
		this.fieldPassword = fieldPassword;
	}


	public JPanel getPanelGeneralBuffer() {
		return panelGeneralBuffer;
	}


	public void setPanelGeneralBuffer(JPanel panelGeneralBuffer) {
		this.panelGeneralBuffer = panelGeneralBuffer;
	}


	public JPanel getPanelBufferDeleteUser() {
		return panelBufferDeleteUser;
	}


	public void setPanelBufferDeleteUser(JPanel panelBufferDeleteUser) {
		this.panelBufferDeleteUser = panelBufferDeleteUser;
	}

}
