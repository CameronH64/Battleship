package server;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

import dataclasses.ConfirmationData;
import dataclasses.CreateUserData;
import dataclasses.DeleteUserData;
import dataclasses.InvalidShotMessage;
// External imports
import dataclasses.MainMenuLoginData;
import dataclasses.OceanLabel;
import dataclasses.PlayerData;
import dataclasses.ShipPlacementData;
import dataclasses.ShotFiredData;
import dataclasses.SunkMessage;
import dataclasses.TargetLabel;
import dataclasses.UpdatedOceanGridData;
import dataclasses.UpdatedTargetGridData;
import dataclasses.WinLoseMessage;

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
			
			// Recieved from client: ShipPlacementData that holds an ArrayList<String>.
			
			// KEY: Extract the string data from the the ShipPlacementData object to generate an OceanLabels grid for the player.
			ShipPlacementData shipPlacementData = (ShipPlacementData)arg0;
			ArrayList<String> shipPlacementCharacters = shipPlacementData.getShipConfiguration();
			
			// We now have the ArrayList<String> of shipCharacters that need to be assigned to the player's ocean Grid.
			
			int i = 0;
			
			for (PlayerData playerData : playerStack) {			// Cycle through the player stack.
				
				if (playerData.getPlayerConnectionToClient().equals(arg1)) {		// Until the player (client) who sent the message is found.
					
					playerData.setOceanFromCharacters(shipPlacementCharacters);
					
					UpdatedOceanGridData updatedOceanGridData = new UpdatedOceanGridData(playerData.getPlayerOceanGrid());
					
					try {
						arg1.sendToClient(updatedOceanGridData);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					
				}
				
			}
			
			
			// TESTING: Print out all of the received data from the client.
//			System.out.println("Testing: Player 1 ShipPlacement characters: ");
//			
//			int count = 0;
//			
//			for (int row = 0; row < 10; row++) {
//				for (int col = 0; col < 10; col++) {
//					System.out.print(shipPlacementCharacters.get(count));
//					count++;
//				}
//				System.out.println();
//			}
			
			
			
			// TESTING: Print out all of the ship characters that have been stored inside of player data's OceanGrid.
//			System.out.println("Testing: Player 1 ocean labels: ");
//			ArrayList<OceanLabel> labels = playerStack.get(0).getPlayerOceanGrid();
//			
//			int count = 0;
//			
//			for (int row = 0; row < 10; row++) {
//				for (int col = 0; col < 10; col++) {
//					System.out.print(labels.get(count).getShipCharacter());
//					count++;
//				}
//				System.out.println();
//			}
			
			
		} else if (arg0 instanceof ShotFiredData) {

//			System.out.println("[SERVER] RECEIVED SHOTFIREDDATA");
			
			// Check the player's shot fired against the other player's ocean grid.
			
			// Confusing, but this is how it works:
			
			// Cycle through all the players (2) and find which player's ConnectionToClient ID matches the one sent.
			// This user is the one who fired the shot.
			
			
			// Player 1 will go first because the turnCounter starts at 0, which is even.
			
			ShotFiredData shotFired = (ShotFiredData)arg0;

			
			if (playerStack.size() < 2) {
				
//				System.out.println("You have to wait for a second player!");

				// Get the players involved (only two)
				PlayerData player1 = playerStack.get(0);

				try {
					player1.getPlayerConnectionToClient().sendToClient(new InvalidShotMessage("single"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			// If player 1's turn, based off of the turnCounter
			} else if (turnCounter % 2 == 0 && arg1.getId() == playerStack.get(0).getPlayerConnectionToClient().getId()) {
				
				// Get the players involved (only two)
				PlayerData player1 = playerStack.get(0);
				PlayerData player2 = playerStack.get(1);
				
				// Get the player 1 targeting grid and player 2 ocean grid.
				ArrayList<TargetLabel> player1TargetingGrid = player1.getPlayerTargetingGrid();
				ArrayList<OceanLabel> player2OceanGrid = player2.getPlayerOceanGrid();
				
				// The only pertinent information for firing and being hit is the position.
				OceanLabel hitOceanLabel = player2OceanGrid.get(shotFired.getPosition());
				
//				System.out.println("Aimed at character: ");
//				System.out.println(hitOceanLabel.getShipCharacter());
				
				
				// Check that the player hasn't fired at the location before.
				if (!player1.getPlayerTargetingGrid().get(shotFired.getPosition()).getHitCharacter().equals("0")) {		// Check that the cell hasn't been fired at before by checking that the target grid is not "1" or "2."
					
//					System.out.println("You already fired here.");
					
					try {
						player1.getPlayerConnectionToClient().sendToClient(new InvalidShotMessage("repeat"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				
				// --------------------- CHECK IF PLAYER 1 MISSES ------------------------
				} else if (hitOceanLabel.getShipCharacter().equals("0")) {
					
					player1TargetingGrid.get(shotFired.getPosition()).setHitCharacter("2");
					
//					System.out.println("Misses shot: ");
//					System.out.println(player1TargetingGrid.get(shotFired.getPosition()).getHitCharacter());
					
					try {
						player1.getPlayerConnectionToClient().sendToClient(new InvalidShotMessage("blank"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					turnCounter++;

					
				// --------------------- CHECK IF PLAYER 1 HITS PLAYER 2 ------------------------
				} else if (!hitOceanLabel.getShipCharacter().equals("0")) {
					
					hitOceanLabel.setShipHitStatus(true);
					
					switch (hitOceanLabel.getShipCharacter()) {
					
					case "C":						
						player2.setCarrierHitCount(player2.getCarrierHitCount() + 1);				// Increment the hit count.
						player1TargetingGrid.get(shotFired.getPosition()).setHitCharacter("1");		// Set the targeting grid character.
						try {
							player1.getPlayerConnectionToClient().sendToClient(new InvalidShotMessage("blank"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case "B":
						player2.setBattleshipHitCount(player2.getBattleshipHitCount() + 1);
						player1TargetingGrid.get(shotFired.getPosition()).setHitCharacter("1");
						try {
							player1.getPlayerConnectionToClient().sendToClient(new InvalidShotMessage("blank"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case "D":
						player2.setDestroyerHitCount(player2.getDestroyerHitCount() + 1);
						player1TargetingGrid.get(shotFired.getPosition()).setHitCharacter("1");
						try {
							player1.getPlayerConnectionToClient().sendToClient(new InvalidShotMessage("blank"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case "S":
						player2.setSubmarineHitCount(player2.getSubmarineHitCount() + 1);
						player1TargetingGrid.get(shotFired.getPosition()).setHitCharacter("1");
						try {
							player1.getPlayerConnectionToClient().sendToClient(new InvalidShotMessage("blank"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case "P":
						player2.setPatrolHitCount(player2.getPatrolHitCount() + 1);
						player1TargetingGrid.get(shotFired.getPosition()).setHitCharacter("1");
						try {
							player1.getPlayerConnectionToClient().sendToClient(new InvalidShotMessage("blank"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					}
					
					// Check if player one has sunk a ship.
					if (player2.getCarrierHitCount() == 5 && !player2.isCarrierSunk()) {
						
						player2.setCarrierSunk(true);
						
						try {
							player1.getPlayerConnectionToClient().sendToClient(new SunkMessage("carrier"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					} else if (player2.getBattleshipHitCount() == 4 && !player2.isBattleshipSunk()) {
						
						player2.setBattleshipSunk(true);

//						System.out.println("Battleship sunk!");
//						System.out.println();
						
						try {
							player1.getPlayerConnectionToClient().sendToClient(new SunkMessage("battleship"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					} else if (player2.getDestroyerHitCount() == 3 && !player2.isDestroyerSunk()) {
						
						player2.setDestroyerSunk(true);
						
						try {
							player1.getPlayerConnectionToClient().sendToClient(new SunkMessage("destroyer"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					} else if (player2.getSubmarineHitCount() == 3 && !player2.isSubmarineSunk()) {
						
						player2.setSubmarineSunk(true);
						
						try {
							player1.getPlayerConnectionToClient().sendToClient(new SunkMessage("submarine"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					} else if (player2.getPatrolHitCount() == 2 && !player2.isPatrolSunk()) {
						
						player2.setPatrolSunk(true);
						
						try {
							player1.getPlayerConnectionToClient().sendToClient(new SunkMessage("patrol"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
					turnCounter++;
					
				}
				
				
				
				
				
				
				
				
				// Send updated information to the clients.
				try {
					player1.getPlayerConnectionToClient().sendToClient(new UpdatedTargetGridData(player1TargetingGrid));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					player2.getPlayerConnectionToClient().sendToClient(new UpdatedOceanGridData(player2OceanGrid));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				// Check if this player has won by checking if all ships are sunk.
				
				if (player2.isCarrierSunk() && player2.isBattleshipSunk() && player2.isDestroyerSunk() && player2.isSubmarineSunk() && player2.isPatrolSunk()) {
					
					try {
						player1.getPlayerConnectionToClient().sendToClient(new WinLoseMessage("win"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					try {
						player2.getPlayerConnectionToClient().sendToClient(new WinLoseMessage("lose"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					
				}
				
				
				
				// Add two more things in BattleshipServer:
				// at the top, data.
				// Two more pieces of data to represent grids.
				// Player 1 update grid
				// Player 2 update grid
				
				
				
			// Or if player 2's turn.
			} else if (turnCounter % 2 == 1 && arg1.getId() == playerStack.get(1).getPlayerConnectionToClient().getId()) {

				// Get the players involved (only two)
				PlayerData player1 = playerStack.get(0);
				PlayerData player2 = playerStack.get(1);
				
				// Get the player 1 targeting grid and player 2 ocean grid.
				ArrayList<TargetLabel> player2TargetingGrid = player2.getPlayerTargetingGrid();
				ArrayList<OceanLabel> player1OceanGrid = player1.getPlayerOceanGrid();
				
				// The only pertinent information for firing and being hit is the position.
				OceanLabel hitOceanLabel = player1OceanGrid.get(shotFired.getPosition());
				
//				System.out.println("Aimed at character: ");
//				System.out.println(hitOceanLabel.getShipCharacter());
				
				
				// Check that the player hasn't fired at the location before.
				if (!player2.getPlayerTargetingGrid().get(shotFired.getPosition()).getHitCharacter().equals("0")) {		// Check that the cell hasn't been fired at before by checking that the target grid is not "1" or "2."
					
//					System.out.println("You already fired here.");
					
					try {
						player2.getPlayerConnectionToClient().sendToClient(new InvalidShotMessage("repeat"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				
				// --------------------- CHECK IF PLAYER 2 MISSES ------------------------
				} else if (hitOceanLabel.getShipCharacter().equals("0")) {
					
					player2TargetingGrid.get(shotFired.getPosition()).setHitCharacter("2");
					
//					System.out.println("Misses shot: ");
//					System.out.println(player1TargetingGrid.get(shotFired.getPosition()).getHitCharacter());
					
					try {
						player2.getPlayerConnectionToClient().sendToClient(new InvalidShotMessage("blank"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					turnCounter++;

					
				// --------------------- CHECK IF PLAYER 1 HITS PLAYER 2 ------------------------
				} else if (!hitOceanLabel.getShipCharacter().equals("0")) {
					
					hitOceanLabel.setShipHitStatus(true);
					
					switch (hitOceanLabel.getShipCharacter()) {
					
					case "C":				
						player1.setCarrierHitCount(player1.getCarrierHitCount() + 1);				// Increment the hit count.
						player2TargetingGrid.get(shotFired.getPosition()).setHitCharacter("1");		// Set the targeting grid character.
						try {
							player2.getPlayerConnectionToClient().sendToClient(new InvalidShotMessage("blank"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case "B":
						player1.setBattleshipHitCount(player1.getBattleshipHitCount() + 1);
						player2TargetingGrid.get(shotFired.getPosition()).setHitCharacter("1");
						try {
							player2.getPlayerConnectionToClient().sendToClient(new InvalidShotMessage("blank"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}break;
					case "D":
						player1.setDestroyerHitCount(player1.getDestroyerHitCount() + 1);
						player2TargetingGrid.get(shotFired.getPosition()).setHitCharacter("1");
						try {
							player2.getPlayerConnectionToClient().sendToClient(new InvalidShotMessage("blank"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}break;
					case "S":
						player1.setSubmarineHitCount(player1.getSubmarineHitCount() + 1);
						player2TargetingGrid.get(shotFired.getPosition()).setHitCharacter("1");
						try {
							player2.getPlayerConnectionToClient().sendToClient(new InvalidShotMessage("blank"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}break;
					case "P":
						player1.setPatrolHitCount(player1.getPatrolHitCount() + 1);
						player2TargetingGrid.get(shotFired.getPosition()).setHitCharacter("1");
						try {
							player2.getPlayerConnectionToClient().sendToClient(new InvalidShotMessage("blank"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}break;
					}
					
					// Check if player one has sunk a ship.
					if (player1.getCarrierHitCount() == 5 && !player1.isCarrierSunk()) {
						
						player1.setCarrierSunk(true);
						
						try {
							player2.getPlayerConnectionToClient().sendToClient(new SunkMessage("carrier"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					} else if (player1.getBattleshipHitCount() == 4 && !player1.isBattleshipSunk()) {
						
						player1.setBattleshipSunk(true);

						try {
							player2.getPlayerConnectionToClient().sendToClient(new SunkMessage("battleship"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					} else if (player1.getDestroyerHitCount() == 3 && !player1.isDestroyerSunk()) {
						
						player1.setDestroyerSunk(true);
						
						try {
							player2.getPlayerConnectionToClient().sendToClient(new SunkMessage("destroyer"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					} else if (player1.getSubmarineHitCount() == 3 && !player1.isSubmarineSunk()) {
						
						player1.setSubmarineSunk(true);
						
						try {
							player2.getPlayerConnectionToClient().sendToClient(new SunkMessage("submarine"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					} else if (player1.getPatrolHitCount() == 2 && !player1.isPatrolSunk()) {
						
						player1.setPatrolSunk(true);
						
						try {
							player2.getPlayerConnectionToClient().sendToClient(new SunkMessage("patrol"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
					turnCounter++;
					
				}
				
				
				
				
				
				
				
				
				// Send updated information to the clients.
				try {
					player2.getPlayerConnectionToClient().sendToClient(new UpdatedTargetGridData(player2TargetingGrid));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					player1.getPlayerConnectionToClient().sendToClient(new UpdatedOceanGridData(player1OceanGrid));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				// Check if this player has won by checking if all ships are sunk.
				
				if (player1.isCarrierSunk() && player1.isBattleshipSunk() && player1.isDestroyerSunk() && player1.isSubmarineSunk() && player1.isPatrolSunk()) {
					
					try {
						player2.getPlayerConnectionToClient().sendToClient(new WinLoseMessage("win"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					try {
						player1.getPlayerConnectionToClient().sendToClient(new WinLoseMessage("lose"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			} else {
				
				System.out.println("You're going out of turn!");
				
//				try {
//					player1.getPlayerConnectionToClient().sendToClient(new WinLoseMessage("lose"));
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
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
//			player.setTestingOceanGrid();						// For testing.
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
