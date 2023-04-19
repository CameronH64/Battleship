package server;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

import dataclasses.ConfirmationData;
import dataclasses.CreateUserData;
import dataclasses.DeleteUserData;
// External imports
import dataclasses.MainMenuLoginData;
import dataclasses.PlayerData;
import dataclasses.ShipPlacementData;
import dataclasses.ShotFiredData;

// This also has the main method to start the BattleshipServer.

public class BattleshipServer extends AbstractServer
{
	private JTextArea serverLogTextArea;
	private JLabel serverConnectionStatusLabel;
	
	private int turnCounter = 0;
	
	Stack<PlayerData> playerStack = new Stack<PlayerData>();		// This will manage all the players. The ConnectionClient objects will also be assigned to these.
	private boolean player1Win;
	private boolean player2Win;
	
	
	
	public BattleshipServer()
	{
		
		super(8300);
		
		// Randomly choose which player goes first (1 means player 1, 2 means player 2)
//		turnFlag = random.nextInt(2)+1;
		
		player1Win = false;
		player2Win = false;
		
		
		
	}
	
	public BattleshipServer(int port)
	{
		super(port);
	}

	public void setLog(JTextArea log) { this.serverLogTextArea = log;	}
	public void setStatus(JLabel status) { this.serverConnectionStatusLabel = status; }

	@Override
	protected void handleMessageFromClient(Object arg0, ConnectionToClient arg1)
	{

		/*

		Reminder to myself: This method is a big if-else ladder that checks what kind of data is being inputted.

		If login data, do server stuff
		If deleteUserData, do server stuff
		If shot fired data, do confirmation stuff.
		If ship configuration data, assign it to the server's player configurations.
		
		Also, "ConnectionToClient arg1" is the connected client that send the message.

		 */

		if (arg0 instanceof MainMenuLoginData) {

			//			log.append("Server-side: Recieved a LoginData object!");

			String username = ((MainMenuLoginData) arg0).getUsername();
			String password = ((MainMenuLoginData) arg0).getPassword();

			System.out.println("[SERVER] RECEIVED LOGINDATA");
			System.out.println("[SERVER] DEBUG: LOGINDATA: " + username + ", " + password);
			
			ConfirmationData loginConfirmationData = new ConfirmationData(true);
			
			try {
				arg1.sendToClient(loginConfirmationData);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// I actually don't need to do this.
			// When player logs in, want to set up their play materials.
//			ArrayList<String> playerOceanGrid = new ArrayList<String>();
//			playerStack.get(getNumberOfClients()).setPlayerTargetingGrid(playerOceanGrid);
			
			
			
		} else if (arg0 instanceof DeleteUserData) {
			
			System.out.println("[SERVER] RECEIVED DELETEUSERDATA");
			
			// Receives a DeleteUserData object username/password to delete.
			
			// Will use the verify account method to check that the account exists to be deleted.
			
			// If exists, executeDML to delete it.
			// Then, send to client a message that the user was deleted.
			
			// If doesn't exists, don't do any SQL stuff.
			// Then, send to client a message the user was not deleted.
			
		} else if (arg0 instanceof CreateUserData) {
			
			System.out.println("[SERVER] RECEIVED CREATEUSERDATA");
			
			// Receives a CreateUserData object username/password to delete.
			
			// Will use the verify account method to check that the account DOES NOT exist to be created.
			
			// If exists, don't do any SQL stuff.
			// Then, send to client a message that the user wasn't created (already exists).
			
			// If doesn't exists, executeDML to create the user.
			// Then, send to client a message that the user was created.
			
		} else if (arg0 instanceof ShipPlacementData) {
			
			// Depending on which client ID, assign this placement data to the PlayerData object (inside playerStack).
			
			
			
		} else if (arg0 instanceof ShotFiredData) {

			System.out.println("[SERVER] RECEIVED SHOTFIREDDATA");
			
			// Check the player's shot fired against the other player's ocean grid.
			
			// Confusing, but this is how it works:
			
			// Cycle through all the players (2) and find which player's ConnectionToClient ID matches the one sent.
			// This user is the one who fired the shot.
			
			
			// Player 1 will go first because the turnCounter starts at 0, which is even.
			
			ShotFiredData shotFired = (ShotFiredData)arg0;
			
			// If player 1's turn, based off of the turnCounter
			if (turnCounter % 2 == 0) {
				
				// Get the player's involved (only two)
				PlayerData player1 = playerStack.get(0);
				PlayerData player2 = playerStack.get(1);
				
				ArrayList<String> player1TargetingGrid = player1.getPlayerTargetingGrid();
				ArrayList<String> player2OceanGrid = player2.getPlayerOceanGrid();
				
				String hitSpot = player2OceanGrid.get(shotFired.getPosition());
				
				// Check if player 1 hit player 2
				if (hitSpot != "0") {
					
					System.out.println("Server side: Player 1 hit player 2!");
					
					switch (hitSpot) {
					
					case "C":
						player2.setCarrierHitCount(player2.getCarrierHitCount() + 1);
						break;
					case "B":
						player2.setBattleshipHitCount(player2.getBattleshipHitCount() + 1);
						break;
					case "D":
						player2.setDestroyerHitCount(player2.getDestroyerHitCount() + 1);
						break;
					case "S":
						player2.setSubmarineHitCount(player2.getSubmarineHitCount() + 1);
						break;
					case "P":
						player2.setPatrolHitCount(player2.getPatrolHitCount() + 1);
						break;
					// I don't think I need a default?
					}
					
					// Check if player one has sunk a ship.
					if (player2.getCarrierHitCount() == 5 && !player2.isCarrierSunk()) {
						
						player2.setCarrierSunk(true);
						
						System.out.println("Carrier sunk!");
						System.out.println();
						
					} else if (player2.getBattleshipHitCount() == 4 && !player2.isBattleshipSunk()) {
						
						player2.setBattleshipSunk(true);

						System.out.println("Battleship sunk!");
						System.out.println();
						
					} else if (player2.getDestroyerHitCount() == 3 && !player2.isDestroyerSunk()) {
						
						player2.setDestroyerSunk(true);
						
						System.out.println("Destroyer sunk!");
						System.out.println();
						
					} else if (player2.getSubmarineHitCount() == 3 && !player2.isSubmarineSunk()) {
						
						player2.setSubmarineSunk(true);
						
						System.out.println("Submarine sunk!");
						System.out.println();
						
					} else if (player2.getPatrolHitCount() == 2 && !player2.isPatrolSunk()) {
						
						player2.setPatrolSunk(true);
						
						System.out.println("Patrol sunk!");
						System.out.println();
						
					}
					
					
				} else if (hitSpot == "0") {
					
					System.out.println("Server side: Player 1 did not hit player 2!");
					System.out.println();
					
				}
				
//				turnCounter++;
				
			// Or if player 2's turn.
			} else if (turnCounter % 2 == 1) {
				
				PlayerData player1 = playerStack.get(0);
				PlayerData player2 = playerStack.get(1);
				
				
				
				turnCounter++;
				
			}
			
			
			
//			PlayerData playerFired = new PlayerData();
//			
//			for (PlayerData playerData : playerStack) {
//				if (playerData.getPlayerConnectionToClient().getId() == arg1.getId()) {
//					
//					playerFired = playerData;
//					
//				}
//			}
			
			
			
		}

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



	protected boolean validateUser(MainMenuLoginData data) {

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
	
	protected void resetGame() {

		turnCounter = 0;
		playerStack = new Stack<PlayerData>();
		
	}
	
	
	
	protected void listeningException(Throwable exception) 
	{
		//Display info about the exception
		System.out.println("[SERVER] LISTENING EXCEPTION" + exception);
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
		System.out.println("[SERVER] SERVER STARTED");
		System.out.println();
		//log.append("Server Started\n");
	}

	protected void serverStopped() 
	{
		System.out.println("[SERVER] SERVER STOPPED");
		System.out.println();
		//log.append("Server Stopped Accepting New Clients - Press Listen to Start Accepting New Clients\n");
	}

	protected void serverClosed()
	{
		System.out.println("[SERVER] SERVER AND CLIENTS CLOSED. PRESS LISTEN TO RESTART.");
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
		
		if (getClientConnections().length > 2) {
			
			System.out.println("[SERVER] REMOVED CLIENT HERE");
			
			try {
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		} else {
			
			System.out.println("[SERVER] CLIENT CONNECTED: CLIENT " + client.getId());
			System.out.println("[SERVER] CONNECTIONS: " + getClientConnections().length);
			System.out.println();
			
			int numberOfClients = getClientConnections().length;
			
			
			
			// Add the new player to the player data structure!
			PlayerData player = new PlayerData();
			player.setPlayerNumber(numberOfClients);
			player.setPlayerConnectionToClient(client);			// This gives an ID to the player that can be referenced and accessed.
			player.setTestingOceanGrid();						// For testing.
			playerStack.add(player);
			
			
			
			/*
	 		
	 		This method is quite interesting, and I put a lot of thought into the backend for the players and player data.
	 		
	 		Having a stack of players would be a good idea.
	 		
	 		First, declare a PlayerData object to be put into the stack called playerData.
	 		
	 		Depending on the number of connections to the server there are, that will be the player number.
			Assign it to playerData
			
			Then, assign the ConnectionToClient object to playerData.
			assign it to playerData.
			
			Player ocean grid will be added later, through handleMessageFromClient.
			Player targeting grid is actually made by default in the PlayerData class.	(Again, the "targeting grid" is just a list of zeros.)
			
			Lastly, add the player to the stack.

			*/
			
		}

		
//		status.setText("Connected");
//		status.setForeground(Color.green);
		//log.append("Client Connected\n");
	}


	protected void clientDisconnected(ConnectionToClient client) {

		// This is run when a the server disconnects a client.
		
		// Cycle through the connected clients (0, 1, or 2)
		// If the disconnected client is found, remove it from clientList.
		
		
		System.out.println("[SERVER] CLIENT DISCONNECTED");
		System.out.println("[SERVER] CONNECTIONS: " + getClientConnections().length);
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
