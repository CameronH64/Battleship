package shipplacementpanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.MatteBorder;

import dataclasses.PlacementLabel;
import dataclasses.TargetLabel;
import shipplacementcontrol.PlacementLabelControl;
import shipplacementcontrol.ShipPlacementControl;

public class ShipPlacementPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Private Data Fields and JComponents.
	// Generally, it's better to have these explicitly declared here so that it's easier to debug the code.
	
	/*
	
	Beckett:
	
	You'll need:
	A gridLayout JPanel for the ship placement area
	Radio buttons for the ship selection
	Figure out a way to represent the ships in the 	"shipyard" so to speak.
	
	You don't have to make it look exactly like the picture because these JPanels are difficult to code.
	But it should have all the functionality and not look ugly.
	
	Like, if it helps to put the confirm placement button somewhere else, feel free to put it there.
	As long as it's there. Same goes for the invalid fleet configuration message that will appear.
	
	
	*/
	
	JButton confirmPlacementButton;
	
	ButtonGroup shipSelectionGroup;
	
	JRadioButton carrierRadioButton;
	JRadioButton battleshipRadioButton;
	JRadioButton destroyerRadioButton;
	JRadioButton submarineRadioButton;
	JRadioButton patrolRadioButton;
	
	ArrayList<PlacementLabel> placementLabels;

	// Constructor
	public ShipPlacementPanel(ShipPlacementControl shipPlacementControl){
		
		
		
		placementLabels = new ArrayList<PlacementLabel>();
		
		setLayout(new GridLayout(2, 1, 1, 1));
		
		
		// 1. Create the placementGrid JPanel.
		JPanel placementGrid = createPlacementGrid();
		
		
		
		// 2. Create the radioButton JPanel.
		
		JPanel radioButtonPanelTop = new JPanel();
		JPanel radioButtonPanelBottom = new JPanel();
		JPanel radioButtonPanelBoth = new JPanel();
		
		shipSelectionGroup = new ButtonGroup();
        
        carrierRadioButton = new JRadioButton("Carrier");
        carrierRadioButton.setSelected(true);
        battleshipRadioButton = new JRadioButton("Battleship");
        destroyerRadioButton = new JRadioButton("Destroyer");
        submarineRadioButton = new JRadioButton("Submarine");
        patrolRadioButton = new JRadioButton("Patrol");
        
        shipSelectionGroup.add(carrierRadioButton);
        shipSelectionGroup.add(battleshipRadioButton);
        shipSelectionGroup.add(destroyerRadioButton);
        shipSelectionGroup.add(submarineRadioButton);
        shipSelectionGroup.add(patrolRadioButton);
        
        radioButtonPanelTop.add(carrierRadioButton);
        radioButtonPanelTop.add(battleshipRadioButton);
        radioButtonPanelTop.add(destroyerRadioButton);
        radioButtonPanelBottom.add(submarineRadioButton);
        radioButtonPanelBottom.add(patrolRadioButton);
        
        radioButtonPanelBoth.add(radioButtonPanelTop);
        radioButtonPanelBoth.add(radioButtonPanelBottom);
        
        
        
		// 3. Make the confirm button panel.
        
        JPanel buttonPanel = new JPanel();
        
		confirmPlacementButton = new JButton("Confirm Placement");
		confirmPlacementButton.addActionListener(shipPlacementControl);

        buttonPanel.add(confirmPlacementButton);
        
        
        
        
        
        
		// Add the three panels to ShipPlacementPanel.
		add(placementGrid);
		
		JPanel testPanel = new JPanel(new GridLayout(2, 1, 1, 1));
		testPanel.add(radioButtonPanelBoth);
		testPanel.add(buttonPanel);
		
		add(testPanel);
        
        
        
		
		// Show the JPanel.
		setSize(500, 500);
		setVisible(true);
		
	}
	
	
	
	private JPanel createPlacementGrid() {

		// Variables
		Color gridColor = new Color(0, 0, 255);
		int cellSize = 32;



		// Create the placementboard, a 10x10 GridLayout JPanel
		JPanel placementBoard = new JPanel(new GridLayout(10, 10));
		placementBoard.setPreferredSize(new Dimension(200, 200));

		// Incrementing for placing each CellLabel in place.
		int count = 0;

		// For each cell in the GridLayout, place a PlacementLabel in it.
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {

				PlacementLabel placementLabel = new PlacementLabel(count);

				placementLabel.setOpaque(true);
				placementLabel.setPreferredSize(new Dimension(cellSize, cellSize));
				placementLabel.setBackground(gridColor);
				placementLabel.setBorder(new MatteBorder(1, 1, (row == 9 ? 1 : 0), (col == 9 ? 1 : 0), Color.BLACK));
				placementLabel.setVerticalAlignment(TargetLabel.CENTER);
				placementLabel.setHorizontalAlignment(TargetLabel.CENTER);
				Font font = placementLabel.getFont();
				int fontSize = 20; // set the font size to 20
				placementLabel.setFont(new Font(font.getName(), Font.PLAIN, fontSize));
				
				
				
				// Add the MouseListener to placementLabel so it will be able to do stuff.
				PlacementLabelControl placementLabelControl = new PlacementLabelControl(placementLabel);
				placementLabel.addMouseListener(placementLabelControl);
				placementLabelControl.setPlacementPanel(this);
				
				
				// Add the placementLabel to the gameboard, the 10x10 GridLayout
				placementLabels.add(placementLabel);
				placementBoard.add(placementLabel);
				
				
				
				count++;	// Increment the count so that the next CellLabel will have the correct position.

			}
		}
		return placementBoard;
	}

	public void redrawPlacementPanel() {
		
		this.revalidate();
		
	}
	
	// Getters and setters
	
	public ButtonGroup getShipSelectionGroup() {
		return shipSelectionGroup;
	}

	public ArrayList<PlacementLabel> getPlacementLabels() {
		return placementLabels;
	}

	public void setPlacementLabels(ArrayList<PlacementLabel> placementLabels) {
		this.placementLabels = placementLabels;
	}

	public void setShipSelectionGroup(ButtonGroup shipSelectionGroup) {
		this.shipSelectionGroup = shipSelectionGroup;
	}

	public JRadioButton getCarrierRadioButton() {
		return carrierRadioButton;
	}



	public void setCarrierRadioButton(JRadioButton carrierRadioButton) {
		this.carrierRadioButton = carrierRadioButton;
	}



	public JRadioButton getBattleshipRadioButton() {
		return battleshipRadioButton;
	}



	public void setBattleshipRadioButton(JRadioButton battleshipRadioButton) {
		this.battleshipRadioButton = battleshipRadioButton;
	}



	public JRadioButton getDestroyerRadioButton() {
		return destroyerRadioButton;
	}



	public void setDestroyerRadioButton(JRadioButton destroyRadioButton) {
		this.destroyerRadioButton = destroyRadioButton;
	}



	public JRadioButton getSubmarineRadioButton() {
		return submarineRadioButton;
	}



	public void setSubmarineRadioButton(JRadioButton submarineRadioButton) {
		this.submarineRadioButton = submarineRadioButton;
	}



	public JRadioButton getPatrolRadioButton() {
		return patrolRadioButton;
	}



	public void setPatrolRadioButton(JRadioButton patrolRadioButton) {
		this.patrolRadioButton = patrolRadioButton;
	}
	
}
