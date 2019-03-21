import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class NotificationWindow extends JFrame
{
	/**
	 * This is the new window that appears once the time remaining has hit 0. This window is
	 * designed to appear above all other windows that are currently open, even if they are
	 * not associated with this program. However, results will vary depending on the OS, which may
	 * or may not allow this.
	 */
	public NotificationWindow ()
	{
		this.setTitle("Cooking Timer");
		this.setSize(400, 200);
		this.setResizable(false);
		this.setLayout( new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS) );
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true); // This should be enough to bring this NotificationWindow to the top of all other windows opened.
		this.createContents();
		this.setVisible(true);
		
	} // End of Constructor.
	
	
	/**
	 * This is a helper method used in displaying a notification message.
	 * 
	 * @return
	 * Returns a random message from an array of pre-existing messages.
	 */
	private String pickAMessage ()
	{
		String [] messages;
		Random randomIndex;
		
		
		// Create a small collection of messages.
		messages = new String [5];
		messages [0] = "The food is done!";
		messages [1] = "Better hurry, or it's going to burn!";
		messages [2] = "Bon Appétit!";
		messages [3] = "Time to dig in!";
		messages [4] = "Let's eat!";
		
		// Select one of these messages randomly and return it.
		randomIndex = new Random();
		return messages [randomIndex.nextInt(5)];
		
	} // End of method pickAMessage.
	
	
	private void createContents ()
	{
		JLabel foodIsDoneMessage;
		JButton exitWindow;
		
		
		// Create the notification message to display.
		foodIsDoneMessage = new JLabel("<html>TIMES UP!<BR>" + pickAMessage() + "</html>");
		foodIsDoneMessage.setHorizontalAlignment(SwingConstants.CENTER); // Move the text to the center of the label.
		foodIsDoneMessage.setAlignmentX(CENTER_ALIGNMENT); // Move the label to the center of the screen.
		
		// Create a button that exits this window.
		exitWindow = new JButton("Alright!");
		exitWindow.addActionListener( new ButtonListener() );
		exitWindow.setAlignmentX(CENTER_ALIGNMENT); // Move the button to the center of the screen.
		
		// Assemble everything together.
		this.add( new JPanel() );
		this.add(foodIsDoneMessage);
		this.add( new JPanel() );
		this.add(exitWindow);
		this.add( new JPanel() );
		
	} // End of method createContents.
	
	
	public class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed (ActionEvent event)
		{
			// "Alright!" was clicked.
			dispose();
			
		} // End of method actionPerformed.
		
	} // End of class ButtonListener.
	
} // End of class NotificationWindow.
