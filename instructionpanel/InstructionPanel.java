import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;

public class Instructions extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Instructions frame = new Instructions();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Instructions() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Strip\\OneDrive\\Pictures\\Screenshots\\Screenshot 2023-04-13 192001.png"));
		setTitle("Instructions");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 453, 671);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txtrShipPlacementSelect = new JTextArea();
		txtrShipPlacementSelect.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 13));
		txtrShipPlacementSelect.setText("Ship Placement:\r\n\r\nSelect the ship you want to place by selecting its radio button.\r\n\r\nYou can now toggle any cell in the grid to place that section of your ship."
				+ "\r\n\r\nYour ship configuration must match the given ship sizes given below.\r\n\r\nCarrier: 5\r\nBattleship: 4\r\nDestroyer: 3\r\nSubmarine: 3\r\nPatrol: 2"
				+ "\r\n\r\nWhen you're ready, press continue, and the server will validate your ship \r\nplacements. \r\n\r\nIf not valid, ensure that your ships are configured properly, and try again."
				+ "\r\n\r\nGameplay:\r\n\r\nIf you're labeled \"Player 1\" you will go first. You will see a message that says: \r\n\r\n\"Your Turn.\""
				+ "\r\n\r\nSimply take turns clicking on your firing grids until one player sinks all of his \r\nopponent's ships.\r\n\r\nWhite: miss\r\nRed: hit"
				+ "\r\nRed with a letter: hit and sunk that ship. The letter \r\nshows which ship you sank.");
		txtrShipPlacementSelect.setBounds(0, 0, 437, 587);
		contentPane.add(txtrShipPlacementSelect);
		
		JButton ContinueButton = new JButton("Continue");
		ContinueButton.setBackground(new Color(255, 255, 255));
		ContinueButton.setBounds(314, 598, 113, 23);
		contentPane.add(ContinueButton);
	}

}
