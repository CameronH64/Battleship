# Battleship

This was the final project for my CSCI 4490 class, Software Engineering. It is a Battleship game implemented with Java and the OCSF framework.

# Contents
- Installation
- Usage
- Special Notes

# Installation (WIP)

1. Import the project into your workspace.
2. You'll get errors. To fix these initial errors, add ocsf.jar and mysql-connector-java jar file to the build path.
3. Start mysql using xampp, and ensure that the "student_database" database exists.
4. It's possible you'll run into an "Editor does not contain a main type" error. To fix this, you must ensure that the Battleship project sees the folder as the "src" folder. To do this:

a. Right click project and select properties.  
b. Go to build path.  
c. Go to Source.  
d. Remove that folder there.  
e. Add a new folder, the whole Battleship project.  

5. You'll likely have errors for the JUnit classes because JUnit hasn't been added to the project. To do so:

a. Right click project and select properties.  
b. Go to build path.  
c. Go to Libraries.  
d. Select "Classpath" and click "Add Library..." to add JUnit to the project.  

e. It's also possible the .bat files won't work. During my own setup testing, a bin folder was created in the project.
Go to that folder, and run the .bat files in THAT folder. Those should work. I'm not entirely sure why a generated bin folder is created with copies of all the code files.
But the .bat files do work in this folder.

# Usage

There are two .bat files:

- BattleshipClientGUI.bat
- BattleshipServerGUI.bat

To run these:

1. Again, ensure that mysql is running.
2. First run "BattleshipServerGUI.bat."
3. Then, press the "Start Server" button.
4. Run "BattleshipClientGUI.bat" twice to get two players.
5. Very important: The first players that connects to the server using the "Connect" button will go first. Also, to play a new game, the server and clients must be closed and restarted.
6. You'll need to create two acconts to login with. The account table (assuming you have a student_space database) will actually be created automatically.
7. With these new accounts, login to each client with them.
8. Before you start, you must place your ships in a valid ship configuration. The instructions screen will explain this.
9. Reminder: the first client to connect will go first.
10. To play, simply click on the target grid (the top one) to fire.
11. Players alternate turns until there's a winner/loser.

# Some Design Notes

I have another GitHub repository that is dedicated to only the ship validation algorithm. That's worth checking out, as that repository has the details of how I designed that. It's quite interesting.

Perhaps the most interesting thing about this project is how the gameplay was updated. It's likely not the best way, but I thought it was quite creative, nonetheless.

This is how it works:

A game of Battleship has two grids for each player. I decided to call these grids a "target grid" and "ocean grid" with the target grid being the one that a player fires with and the ocean grid being the one that the player recieves shots on.

The BattleshipClient class instantiation has these two private datafields:

  private ArrayList<TargetLabel> displayTargetLabels;
  private ArrayList<OceanLabel> displayOceanLabels;

The datatypes TargetLabel and OceanLabel are simply classes that extend JLabel and have their own private datafields, such as position and ship character.

The GameplayGUI class, which is exactly what it sounds like, will cycle through these private datafields on the BattleshipClient and show them on the GameplayGUI. The displayTargetLabels data field is the only necessary one needed for game checking. The displayOceanLabels one is more for just display of current ship status.

And because these custom target labels are being stored in an ArrayList, they can be packaged up and sent to BattleshipServer for gameplay checking.

The server will send back an object that contains an updated ArrayList<TargetLabel>.

This updated ArrayList will be be cycled through, and the contents of the updated ArrayList<TargetLabel> will be assigned to the client-side ArrayList.

**This is key:**

When the client-side ArrayList of TargetLabels is updated, it's also updated in the GUI. Again, because this displayTargetLabel ArrayList is stored in the client as a private data field, the process of packaging up the displayTargetLabel ArrayList and sending it to the server and then sending information back to update that same ArrayList can be used to facilitate the whole gameplay.
