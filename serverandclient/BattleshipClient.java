// Cameron Holbrook
// Lab 3 Out

package serverandclient;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import ocsf.client.AbstractClient;

public class BattleshipClient extends AbstractClient{
	
	private JPanel container;

	public BattleshipClient() {
		super("localhost", 8300);
	}
	
	public void setContainer(JPanel container) { this.container = container; }
	
	public void connectionEstablished() {
//		statusLabel.setText("Connected");
//		statusLabel.setForeground(Color.GREEN);
		
		
		
	}
	
	public void handleMessageFromServer(Object arg0) {
		
		// Reminder to myself: This method is a big if-else statement that takes in server information, and does stuff with it.
		
//		serverMessageArea.append("Server: " + (String)arg0 + "\n");
//		clientIDTextField.setText((String)arg0);
		
		boolean loginStatus = (boolean)arg0;
		
		if (loginStatus == true) {
			
//			System.out.println("Can now display contact panel.");		// Debuggin
			
			CardLayout cardLayout = (CardLayout)container.getLayout();
			cardLayout.show(container, "4");

			
		}
		
	}
	
	public void connectionClosed() {
//		statusLabel.setText("Not Connected");
//		statusLabel.setForeground(Color.RED);
		
		
		
	}
	
}