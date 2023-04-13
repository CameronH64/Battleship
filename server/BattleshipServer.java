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

	private ArrayList<String> player1Reference;
	private ArrayList<String> player1Gameplay;
	
	private ArrayList<String> player2Reference;
	private ArrayList<String> player2Gameplay;
	
	private ArrayList<ConnectionToClient> clientList;

	private boolean player1Win;
	private boolean player2Win;


	public BattleshipServer()
	{
		super(8300);

		player1Reference = new ArrayList<String>();
		player2Reference = new ArrayList<String>();

		player1Win = false;
		player2Win = false;

	}

	public void testingSetUp() {

		String player1FleetArray[][] = {{"0","C","C","C","C","C","0","0","0","0"},
				{"0","0","0","P","P","0","0","0","0","0"},
				{"0","0","0","0","0","0","0","0","0","0"},
				{"0","0","0","0","B","B","B","B","0","0"},
				{"0","0","0","0","0","0","0","0","0","0"},
				{"0","0","0","0","0","0","0","0","0","0"},
				{"0","0","0","0","D","0","S","0","0","0"},
				{"0","0","0","0","D","0","S","0","0","0"},
				{"0","0","0","0","D","0","S","0","0","0"},
				{"0","0","0","0","0","0","0","0","0","0"}};

		ArrayList<String> player1FlatFleet = new ArrayList<String>();

		// First, flatten the array into a 1D array.
		for(int i = 0; i < 10; i++) {				// Check each row.
			for(int j = 0; j < 10; j++) {			// Check each column.
				player1FlatFleet.add(player1FleetArray[i][j]);
			}
		}

		// Now have a flat fleet for player 1.

		String player2FleetArray[][] = {{"0","C","C","C","C","C","0","0","0","0"},
				{"0","0","0","P","P","0","0","0","0","0"},
				{"0","0","0","0","0","0","0","0","0","0"},
				{"0","0","0","0","B","B","B","B","0","0"},
				{"0","0","0","0","0","0","0","0","0","0"},
				{"0","0","0","0","0","0","0","0","0","0"},
				{"0","0","0","0","D","0","S","0","0","0"},
				{"0","0","0","0","D","0","S","0","0","0"},
				{"0","0","0","0","D","0","S","0","0","0"},
				{"0","0","0","0","0","0","0","0","0","0"}};

		ArrayList<String> player2FlatFleet = new ArrayList<String>();

		// First, flatten the array into a 1D array.
		for(int i = 0; i < 10; i++) {				// Check each row.
			for(int j = 0; j < 10; j++) {			// Check each column.
				player2FlatFleet.add(player2FleetArray[i][j]);
			}
		}

		// Now have a flat fleet for player 2.


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

		Also, "ConnectionToClient arg1" is the connected client that send the message.

		 */

		System.out.println("I just received some data here!");

		if (arg0 instanceof LoginData) {

			//			log.append("Server-side: Recieved a LoginData object!");

			String username = ((LoginData) arg0).getUsername();
			String password = ((LoginData) arg0).getPassword();

			System.out.println(username + password);

			System.out.println("Server-side: Received a LoginData object!");
			//			System.out.println(username);
			//			System.out.println(password);
			//			


		} else if (arg0 instanceof ShotFiredData) {

			System.out.println("Server-side: Received a ShotFiredData object!");
			//			System.out.println(x);
			//			System.out.println(y);

		}

		// Server-side debugging
		//		log.append("Debugging: " + data.getUsername() + " " + data.getPassword() + " " + arg1.toString() + "\n");

	}




	// More game logic methods go here.


	// Check if only 2 clients are connected.
	// Show when they connect on the BattleshipServerGUI.
	// Use an array or something for logic checking.
	// When two clients connected and on the gameplay screen, randomize first move.
	// If a user exits mid-game, run code to show the winner on the other screen.


	// Check if a hit.
	// Compare the reference ArrayList<CellLabel> to gameplay ArrayList for both players.

	// Check if a ship is sunk
	// Count how many of a certain ship letter there is.

	// Check if a whole fleet is sunk
	// Count how many ship letters there are (17 means sunk).



	// ------------------------- BATTLESHIP CLASSES -------------------------

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
	
	protected boolean checkTwoOrFewerClients() {

		return true;

	}
	
	
	
	// ------------------------- OCSF CLASSES -------------------------
	
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
		System.out.println("Server-side: Server Started\n");
		//log.append("Server Started\n");
	}

	protected void serverStopped() 
	{
		System.out.println("Server-side: Server Stopped");
		//log.append("Server Stopped Accepting New Clients - Press Listen to Start Accepting New Clients\n");
	}

	protected void serverClosed()
	{
		System.out.println("Server and all current clients are closed - Press Listen to Restart");
		//log.append("Server and all current clients are closed - Press Listen to Restart\n");
	}

//	protected void handleAcceptException(Throwable exception) {
//        if (getNumberOfClients() >= 2) {
//            System.out.println("Max connections reached, cannot accept new clients");
//        } else {
//            System.out.println("error message?");
//            
//        }
//    }
	
	protected void clientConnected(ConnectionToClient client) 
	{

		// Will have player connection stuff here.
		// Probably have an array of two ConnectionToClient objects to 
		// keep track of the players (only two are allowed).
		// Will need an if statement to check that there are only two players.
		// Heck, I could write a method to do that.
		
		
		// Sure, connect the client.
		
		// But then, see if that would be the third client.
		// If it is, remove it. It doesn't belong here.
		
		// Not not, just let it be.

		System.out.println("Server-side: Client connected. " + client.getId());
		
		System.out.println("Number of connections: " + getClientConnections().length);
		
		int numberOfClients = getClientConnections().length;
		
		if (numberOfClients >= 2) {
			
			System.out.println("Remove a client here.");
			
			try {
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
//			// Using threads?
//			Thread thread[] = getClientConnections();
//			
//			thread[2].interrupt();
//			
//			System.out.println("Debugging:");
//			
//			for (ConnectionToClient connectionToClient : clientList) {
//				System.out.println(connectionToClient.getId());
//			}
			
			
		} else {
			
//			System.out.println("Client Not Connected: Too many connections.");
			System.out.println("Added a client here.");
			System.out.println();
			
		}
		
//		status.setText("Connected");
//		status.setForeground(Color.green);
		//log.append("Client Connected\n");
	}


	protected void clientDisconnected(ConnectionToClient client) {

		// This is run when a the server disconnects a client.
		
		// Cycle through the connected clients (0, 1, or 2)
		// If the disconnected client is found, remove it from clientList.
		
		
		System.out.println("Server-side: Client Disconnected.");
		System.out.println("Number of connections: " + getClientConnections().length);
		System.out.println();
		
//		int count = 0;
//		for (ConnectionToClient connectionToClient : clientList) {
//			if (client.getId() == (connectionToClient.getId())){
//				
//				log.append(client + ": disconnected.");
//				clientList.remove(count);
//				
//			}
//			count++;
//		}
		
		

	}

}
