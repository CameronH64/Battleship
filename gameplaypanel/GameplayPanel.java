package gameplaypanel;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import client.BattleshipClient;
import dataclasses.CellButton;
import dataclasses.CellLabel;
import gameplaycontrol.CellLabelControl;
import gameplaycontrol.GameplayControl;

import java.awt.*;
import java.util.ArrayList;

public class GameplayPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Private Data Fields and JComponents.
	// Generally, it's better to have these explicitly declared here so that it's easier to debug the code.
	
	private ArrayList<CellLabel> gameplayCellLabels;
	
	// Constructor
	public GameplayPanel(GameplayControl gameplayControl){
		
		
		// Initialize the primary BorderLayouts.
		setLayout(new BorderLayout());
		JPanel northBorderLayout = new JPanel(new BorderLayout());
		JPanel centerBorderLayout = new JPanel(new BorderLayout());
		
		add(northBorderLayout, BorderLayout.NORTH);
		add(centerBorderLayout, BorderLayout.CENTER);
		
		
		
		// Make the north BorderLayout panels.
		JLabel firingGridLabel = new JLabel("Firing Grid");
		
		// Create the gameboard, a 10x10 GridLayout JPanel
		JPanel gameBoard = new JPanel(new GridLayout(10, 10));
		
		// Incrementing for placing each CellLabel in place.
		int count = 0;
		
		// For each cell in the GridLayout, place a CellLabel in it.
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				
				CellLabel cellLabel = new CellLabel(count);
				
				cellLabel.setOpaque(true);
				cellLabel.setPreferredSize(new Dimension(40, 40));
				cellLabel.setBackground(Color.BLUE);
				cellLabel.setBorder(new MatteBorder(1, 1, (row == 9 ? 1 : 0), (col == 9 ? 1 : 0), Color.BLACK));
				
				cellLabel.setVerticalAlignment(CellLabel.CENTER);
				cellLabel.setHorizontalAlignment(CellLabel.CENTER);
				Font font = cellLabel.getFont();
				int fontSize = 20; // set the font size to 20
				cellLabel.setFont(new Font(font.getName(), Font.PLAIN, fontSize));
				
				// Add the MouseListener to cellLabel so it will be able to do stuff.
				cellLabel.addMouseListener(new CellLabelControl(cellLabel));
				
				// Add the cellLabel to the gameboard, the 10x10 GridLayout
				gameBoard.add(cellLabel);
				
				count++;	// Increment the count so that the next CellLabel will have the correct position.
				
			}
		}
		
		JPanel firingGridPanel = new JPanel(new GridLayout(2, 2));		// (Can also have spacing between)
		
		firingGridPanel.add(new JLabel());								// Add nothing
		
		JLabel numberRow = new JLabel("1 2 3 4 5 6 7 8 9 10");
		numberRow.setSize(new Dimension(10, 10));
		firingGridPanel.add(numberRow);		// Add the top numbers
		
		
		firingGridPanel.add(new JLabel("A\nB\nC\nD\nE\nF\nG\nH\nI\nJ"));
		
		firingGridPanel.add(gameBoard);
		
		
		
		
		// Add the components to the GameplayPanel
		add(firingGridPanel);		
		
		
		
		// Show the JPanel.
		setSize(1000, 500);
		setVisible(true);
		
	}
	
}
