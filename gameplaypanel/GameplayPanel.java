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
	
	private JButton connectButton;
	private ArrayList<CellLabel> gameplayCellLabels;
	
	// Constructor
	public GameplayPanel(GameplayControl gameplayControl){
		
		connectButton = new JButton("Connect");
		connectButton.addActionListener(gameplayControl);
		
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

		
		// Add the components to the GameplayPanel
		add(connectButton);
		add(gameBoard);		
		
		
		
		// Show the JPanel.
		setSize(500, 500);
		setVisible(true);
		
	}
	
}
