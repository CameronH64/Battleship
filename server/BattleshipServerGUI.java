// Cameron Holbrook
// Lab 3 Out

package server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;

//import lab3out.ChatServer;

public class BattleshipServerGUI extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	// Declare JComponents
	private JLabel 		statusLabel;
	private JLabel 		connectionStatusLabel;
	private JLabel		serverLogLabel;
	
	private JButton 	listenButton;
	private JButton 	closeButton;
	private JButton 	quitButton;
	
	private JTextArea 	serverLogTextArea;
	
	// Other Declarations
	private BattleshipServer	server;
	
	
	public BattleshipServerGUI()
	{	
		
		// ------------------------ Create GUI ------------------------

		this.setTitle("Battleship Server");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Step 1: Instantiate JComponents
		connectionStatusLabel = new JLabel("Not Connected");
		connectionStatusLabel.setForeground(Color.RED);

		statusLabel		= new JLabel("Status: ");
		serverLogLabel	= new JLabel("Server Log: ");
		
		listenButton	= new JButton("Start Server");
		closeButton		= new JButton("Close");
		quitButton		= new JButton("Quit");

		// For TextArea and ScrollPanes surrounding them.
		serverLogTextArea = new JTextArea();
		serverLogTextArea.setEditable(false);
		JViewport serverViewport = new JViewport();
		serverViewport.add(serverLogTextArea);
		JScrollPane serverScrollPane = new JScrollPane(serverViewport);
		
		
		
		// Step 2: JPanel instantiation
		JPanel jFrameBuffer 	= new JPanel(new BorderLayout());
		JPanel north1 			= new JPanel(new FlowLayout());
		JPanel centerBuffer 	= new JPanel(new BorderLayout());
		JPanel south1 			= new JPanel(new FlowLayout());

		// Within centerBuffer...

		// Center North
		JPanel centerNorth 		= new JPanel(new BorderLayout());

		// Center South
		JPanel centerSouth 		= new JPanel(new BorderLayout());
		JPanel clientDataPanel 	= new JPanel(new BorderLayout());
		clientDataPanel.setPreferredSize(new Dimension(20, 110));
		JPanel serverDataPanel 	= new JPanel(new BorderLayout());
		serverDataPanel.setPreferredSize(new Dimension(20, 300));			// This adjusts the height of the server log window.



		// Step 3: Add JPanels to JFrame (think about stacking pieces of paper)
		this.add(jFrameBuffer, BorderLayout.CENTER);		
		jFrameBuffer.add(north1, BorderLayout.NORTH);
		jFrameBuffer.add(centerBuffer, BorderLayout.CENTER);
		jFrameBuffer.add(south1, BorderLayout.SOUTH);

		centerBuffer.add(centerNorth, BorderLayout.NORTH);
		centerBuffer.add(centerSouth, BorderLayout.SOUTH);

		centerSouth.add(clientDataPanel, BorderLayout.NORTH);
		centerSouth.add(serverDataPanel, BorderLayout.SOUTH);


		// For size of margins.
		int width = 50;
		JPanel leftWidth = new JPanel();
		leftWidth.setPreferredSize(new Dimension(width, 0));
		jFrameBuffer.add(leftWidth, BorderLayout.EAST);

		JPanel rightWidth = new JPanel();
		rightWidth.setPreferredSize(new Dimension(width, 0));
		jFrameBuffer.add(rightWidth, BorderLayout.WEST);
		
		
		
		// Step 4: Add JComponents to JPanels.
		north1.add(statusLabel);
		north1.add(connectionStatusLabel);

		// Add south JComponents.

		serverDataPanel.add(serverLogLabel, BorderLayout.NORTH);
		serverDataPanel.add(serverScrollPane, BorderLayout.CENTER);

		south1.add(listenButton);
		south1.add(closeButton);
		south1.add(quitButton);

		// ------------------------ / Create GUI ------------------------
		
		
		
		// --------------------- Server Instantiation ---------------------
		
		server = new BattleshipServer();
		server.setPort(8300);
		server.setTimeout(500);
		server.setLog(serverLogTextArea);
		server.setStatus(connectionStatusLabel);
		
		// --------------------- / Server Instantiation ---------------------

		
		
		// ------------------------ Event Handler ------------------------
		
		// Step 5: EventHandler
		BattleshipServerControl eh = new BattleshipServerControl(jFrameBuffer, serverLogTextArea, connectionStatusLabel, server);
		listenButton.addActionListener(eh);
		closeButton.addActionListener(eh);
		quitButton.addActionListener(eh);
		
		this.setSize(500, 500);
		setVisible(true);

		// ------------------------ / Event Handler ------------------------
		
		
	}

	public static void main(String[] args) {

		//		System.out.println("Current JVM version - " + Runtime.version());
		new BattleshipServerGUI(); //args[0] represents the title of the GUI

	}

}



