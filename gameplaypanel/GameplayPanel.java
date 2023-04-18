package gameplaypanel;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import client.BattleshipClient;
import dataclasses.OceanLabel;
import dataclasses.TargetLabel;
import gameplaycontrol.GameplayControl;
import gameplaycontrol.TargetLabelControl;

import java.awt.*;
import java.util.ArrayList;

public class GameplayPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<TargetLabel> targetLabels;
	private ArrayList<OceanLabel> oceanLabels;
	JButton newGameButton;
	
	BattleshipClient battleshipClient;
	
	// Constructor
	public GameplayPanel(GameplayControl gameplayControl, BattleshipClient battleshipClient){
		
		
		
		targetLabels = new ArrayList<TargetLabel>();
		oceanLabels = new ArrayList<OceanLabel>();
		
		this.battleshipClient = battleshipClient;
		
		
		
		// Initialize the primary BorderLayouts and place them, ready for adding stuff into them.
		JPanel bufferBorderLayout = new JPanel(new BorderLayout());

		JPanel northBorderLayout = new JPanel(new BorderLayout());
		JPanel centerBorderLayout = new JPanel(new BorderLayout());
		JPanel southBorderLayout = new JPanel(new BorderLayout());

		bufferBorderLayout.add(northBorderLayout, BorderLayout.NORTH);
		bufferBorderLayout.add(centerBorderLayout, BorderLayout.CENTER);
		bufferBorderLayout.add(southBorderLayout, BorderLayout.SOUTH);



		// 1. North Game Section
		JLabel targetGridLabel = new JLabel("Targeting Grid");
		targetGridLabel.setHorizontalAlignment(JLabel.CENTER);
		JPanel targetGrid = createTargetGrid();

		northBorderLayout.add(targetGrid, BorderLayout.NORTH);
		northBorderLayout.add(targetGridLabel, BorderLayout.CENTER);



		// 2. Center Game Section

		JLabel oceanGridLabel = new JLabel("Ocean Grid");
		oceanGridLabel.setHorizontalAlignment(JLabel.CENTER);
		JPanel oceanGrid = createOceanGrid();

		centerBorderLayout.add(oceanGridLabel, BorderLayout.NORTH);
		centerBorderLayout.add(oceanGrid, BorderLayout.CENTER);



		// 3. South Game Section

		// Declare panels
		JPanel northPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel southPanel = new JPanel();

		// North panel
		JLabel youLabel = new JLabel("You: ");
		JLabel winLoseLabel = new JLabel("temp");
		JLabel opponentLabel = new JLabel("Opponent: ");
		northPanel.add(youLabel);
		northPanel.add(winLoseLabel);
		northPanel.add(opponentLabel);

		// Center panel
		JLabel errorMessageLabel = new JLabel("Messages: ");
		centerPanel.add(errorMessageLabel);

		// South panel
		newGameButton = new JButton("New Game");
		newGameButton.addActionListener(gameplayControl);
		newGameButton.setEnabled(false);
		
		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(gameplayControl);		
		
		
		
		JButton connectButton = new JButton("Connect");
		connectButton.addActionListener(gameplayControl);
		
		
		
		southPanel.add(newGameButton);
		southPanel.add(quitButton);
		southPanel.add(connectButton);
		
		southBorderLayout.add(northPanel, BorderLayout.NORTH);
		southBorderLayout.add(centerPanel, BorderLayout.CENTER);
		southBorderLayout.add(southPanel, BorderLayout.SOUTH);



		// Finally, add the buffer BorderLayout
		add(bufferBorderLayout);

		setSize(500, 500);
		setVisible(true);

	}


	public JButton getNewGameButton() {

		return newGameButton;

	}

	private JPanel createTargetGrid() {

		// Variables
		Color gridColor = new Color(0, 0, 220);
		int cellSize = 32;



		// Create the gameboard, a 10x10 GridLayout JPanel
		JPanel gameBoard = new JPanel(new GridLayout(10, 10));
		//		gameBoard.setPreferredSize(new Dimension(200, 200));

		// Incrementing for placing each CellLabel in place.
		int count = 0;

		// For each cell in the GridLayout, place a CellLabel in it.
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {

				TargetLabel targetLabel = new TargetLabel(count);

				targetLabel.setOpaque(true);
				targetLabel.setPreferredSize(new Dimension(cellSize, cellSize));
				targetLabel.setBackground(gridColor);
				targetLabel.setBorder(new MatteBorder(1, 1, (row == 9 ? 1 : 0), (col == 9 ? 1 : 0), Color.BLACK));

				targetLabel.setVerticalAlignment(TargetLabel.CENTER);
				targetLabel.setHorizontalAlignment(TargetLabel.CENTER);
				Font font = targetLabel.getFont();
				int fontSize = 20; // set the font size to 20
				targetLabel.setFont(new Font(font.getName(), Font.PLAIN, fontSize));

				// Add the MouseListener to cellLabel so it will be able to do stuff.
				
				targetLabel.addMouseListener(new TargetLabelControl(targetLabel, battleshipClient));
				
				
				// Add the cellLabel to the gameboard, the 10x10 GridLayout
				targetLabels.add(targetLabel);
				gameBoard.add(targetLabel);
				
				count++;	// Increment the count so that the next CellLabel will have the correct position.

			}
		}
		return gameBoard;
	}
	
	
	
	private JPanel createOceanGrid() {

		// Variables
		Color gridColor = new Color(0, 0, 220);
		int cellSize = 32;


		// Create the gameboard, a 10x10 GridLayout JPanel
		JPanel gameBoard = new JPanel(new GridLayout(10, 10));
		//		gameBoard.setPreferredSize(new Dimension(200, 200));

		// Incrementing for placing each CellLabel in place.
		int count = 0;

		// For each cell in the GridLayout, place a CellLabel in it.
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {

				OceanLabel oceanLabel = new OceanLabel(count);

				oceanLabel.setOpaque(true);
				oceanLabel.setPreferredSize(new Dimension(cellSize, cellSize));
				oceanLabel.setBackground(gridColor);
				oceanLabel.setBorder(new MatteBorder(1, 1, (row == 9 ? 1 : 0), (col == 9 ? 1 : 0), Color.BLACK));

				oceanLabel.setVerticalAlignment(OceanLabel.CENTER);
				oceanLabel.setHorizontalAlignment(OceanLabel.CENTER);
				Font font = oceanLabel.getFont();
				int fontSize = 20; // set the font size to 20
				oceanLabel.setFont(new Font(font.getName(), Font.PLAIN, fontSize));

				// Add the MouseListener to cellLabel so it will be able to do stuff.
//								cellLabel.addMouseListener(new CellLabelControl(cellLabel));		// Commented out because this grid isn't touched by the user.

				// Add the cellLabel to the gameboard, the 10x10 GridLayout
				oceanLabels.add(oceanLabel);
				gameBoard.add(oceanLabel);
				
				count++;	// Increment the count so that the next CellLabel will have the correct position.

			}
		}
		return gameBoard;
	}

}
