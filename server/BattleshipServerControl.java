package server;

import javax.swing.*;

import java.awt.CardLayout;
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
		if (command.equals("Listen")) {

//			String portNumberText = portNumberTextField.getText();		// Get the text from the JTextFields.
//			String timeoutText = timeoutTextField.getText();
//
//			try {													// Make sure that the 
//				Integer.parseInt(portNumberText);
//				Integer.parseInt(timeoutText);					
//
//			} catch (Exception e) {
//				serverLogTextArea.append("Port number/timeout not entered.\n");
//				server.setLog(serverLogTextArea);
//				return;
//			}

			// As of now, assume the correct values are being entered. This can be improved later.
//			int portNumber = Integer.parseInt(portNumberText);
//			int timeout = Integer.parseInt(timeoutText);	

			// In a try catch because a non-Java connection will crash the program.
			try {

				// Default values.
				server.setPort(8333);
				server.setTimeout(500);
				server.listen();					// Then, the server can listen.					
				
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

			} catch (Exception e) {
				System.out.println("server.close() broke somehow. :/");
				e.printStackTrace();
			}



		} else if (command.equals("Stop")) {

			if (!server.isListening()) {

				serverLogTextArea.append("Stop invalid. Server is not currently running.\n");
				server.setLog(serverLogTextArea);

				return;
			}

			server.stopListening();



		} else if (command.equals("Quit")) {

			System.out.println("Server shutting down...");
			System.exit(0);

			// If pressed, server shuts down and GUI closes.

		}

		// Have more if-else statements here for every action that happens (buttons, etc).



	}

}
