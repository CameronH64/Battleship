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
	
	public void getTest() {
		
		System.out.println("test");
		
	}
	
	// Constructor
	public GameplayPanel(GameplayControl gameplayControl){
		
		
		
		// create the game board UI
		JPanel gameBoard = new JPanel(new GridLayout(10, 10));
		
		int count = 0;
		
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

				cellLabel.addMouseListener(new CellLabelControl(cellLabel));
//				cellLabel.addMouseListener(gameplayControl);
				
				gameBoard.add(cellLabel);
//				gameplayCellLabels.add(cellLabel);
				
				count++;
				
			}
		}


		add(gameBoard);		
		
		
		
		// Show the JPanel.
		setSize(500, 500);
		setVisible(true);
		
	}
	
}
