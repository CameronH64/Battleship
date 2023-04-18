package server;

import javax.swing.*;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.*;
import java.io.IOException;
import java.io.Serializable;

public class BattleshipServerControl implements ActionListener
{
	// Private data fields.
	private JPanel container;
	private JTextArea serverLogTextArea;
	private JLabel connectionStatusLabel;
	private BattleshipServer server;

	// This constructor connects the outside components so that the control panel can affect things.
	public BattleshipServerControl(JPanel container, JTextArea serverLogTextArea, JLabel connectionStatusLabel, BattleshipServer server)
	{
		this.container = container;
		this.serverLogTextArea = serverLogTextArea;
		this.connectionStatusLabel = connectionStatusLabel;
		this.server = server;
	}

	// Handle button clicks.
	public void actionPerformed(ActionEvent ae)
	{


		// Get the name of the button clicked.
		String command = ae.getActionCommand();



		// The Cancel button takes the user back to the initial panel.
		if (command.equals("Start Server")) {

			// In a try catch because a non-Java connection will crash the program.
			try {
				
				connectionStatusLabel.setForeground(Color.GREEN);
				connectionStatusLabel.setText("Connected");
				
				// Default values.
				server.setPort(8300);
//				server.setTimeout(500);
				server.listen();
				// Note: listen() runs both serverStarted() and setLog() methods.

			} catch (IOException e) {
				e.printStackTrace();
			}



		} else if (command.equals("Close")) {

			if (!server.isListening()) {

				serverLogTextArea.append("Close invalid. Server is not currently running.\n");
				server.setLog(serverLogTextArea);

				return;
			}

			try {
				server.close();
				// Note: server.close() invokes both serverClosed() and serverStopped() methods.

				connectionStatusLabel.setForeground(Color.RED);
				connectionStatusLabel.setText("Not Connected");

				serverLogTextArea.append("Server closed.\n");
//				server.setLog(serverLogTextArea);

			} catch (Exception e) {
				System.out.println("server.close() broke somehow. :/");
				e.printStackTrace();
			}



		} else if (command.equals("Quit")) {

			System.out.println("Server shutting down...");
			System.exit(0);

			// If pressed, server shuts down and GUI closes.

		}



	}

}
