package server;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// External imports
import dataclasses.LoginData;
import dataclasses.ShipPlacementData;
import dataclasses.ShotFiredData;

// This also has the main method to start the BattleshipServer.

public class BattleshipServer extends AbstractServer
{
	private JTextArea log;
	private JLabel status;
	
	private ArrayList<String> player1Ships;
	private ArrayList<String> player2Ships;
	private boolean player1Win;
	private boolean player2Win;
	
	
	public BattleshipServer()
	{		
		super(12345);
		
		player1Ships = new ArrayList<String>();
		player2Ships = new ArrayList<String>();
		player1Win = false;
		player2Win = false;
	
	}

	public BattleshipServer(int port)
	{
		super(port);
	}

	public void setLog(JTextArea log) { this.log = log;	}

	public void setStatus(JLabel status) { this.status = status; }

	@Override
	protected void handleMessageFromClient(Object arg0, ConnectionToClient arg1)
	{
		
		/*
		
		Reminder to myself: This method is a big if-else ladder that checks what kind of data is being inputted.
		
		If login data, do server stuff
		
		If shot fired data, do confirmation stuff.
		
		If ship configuration data, assign it to the server's player configurations.
		
		
		*/
		
		LoginData data = (LoginData)arg0;
		
		// Cycle through users (separated by commas)
		// When you hit a user with the same name, check the password after that.
		// If completely cycled and nothing is found, return false or something.
		
		if (validateUser(data)) {
			
//			System.out.println("Valid!");
			sendToAllClients(true);
			
		} else {
			
//			System.out.println("Invalid!");
			sendToAllClients(false);
			
		}
		
		// Server-side debugging
		log.append("Debugging: " + data.getUsername() + " " + data.getPassword() + " " + arg1.toString() + "\n");

	}
	
	protected boolean validateUser(LoginData data) {
		
		String username = data.getUsername();
		String password = data.getPassword();
		
		try {
			File myObj = new File("lab5out/users.txt");
			Scanner myReader = new Scanner(myObj);
			
			while (myReader.hasNextLine()) {
				String line = myReader.nextLine();
				
				if(line.split(",", 2)[0].equals(username)) {
					
					if (line.split(",")[1].equals(password)) {
						myReader.close();
						return true;
					}
					
				}
				
			}
			
			myReader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	protected void listeningException(Throwable exception) 
	{
		//Display info about the exception
		System.out.println("Listening Exception:" + exception);
		exception.printStackTrace();
		System.out.println(exception.getMessage());

		/*if (this.isListening())
    {
      log.append("Server not Listening\n");
      status.setText("Not Connected");
      status.setForeground(Color.RED);
    }*/

	}

	protected void serverStarted() 
	{
		System.out.println("Server Started");
		//log.append("Server Started\n");
	}

	protected void serverStopped() 
	{
		System.out.println("Server Stopped");
		//log.append("Server Stopped Accepting New Clients - Press Listen to Start Accepting New Clients\n");
	}

	protected void serverClosed() 
	{
		System.out.println("Server and all current clients are closed - Press Listen to Restart");
		//log.append("Server and all current clients are closed - Press Listen to Restart\n");
	}


	protected void clientConnected(ConnectionToClient client) 
	{

		// Debugging
		System.out.println("Client Connected");

		status.setText("Connected");
		status.setForeground(Color.green);
		//log.append("Client Connected\n");
	}
	
}
