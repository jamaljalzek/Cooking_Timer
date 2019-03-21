import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;


public class TimeRemainingScreen extends JPanel
{
	private MainWindow mainWindow;
	
	private Timer timer;
	private JLabel timeRemainingDisplay;
	private int millisecondsRemaining;
	
	
	/**
	 * This is the screen (JPanel) that displays the hours and minutes remaining, which automatically updates every minute.
	 * Once millisecondsRemaining hits 0, a new NotificationWindow is launched.
	 * 
	 * @param mainWindow
	 * @param millisecondsRemaining
	 */
	public TimeRemainingScreen (MainWindow mainWindow, int millisecondsRemaining)
	{
		this.mainWindow = mainWindow;
		this.millisecondsRemaining = millisecondsRemaining;
		
		this.setLayout( new BoxLayout(this, BoxLayout.Y_AXIS) );
		this.createContents();
		
	} // End of Constructor.
	
	
	private void createContents ()
	{
		JButton reset, exit;
		JPanel buttonsHolder;
		
		
		// Every minute, this Timer will decrease millisecondsRemaining, and also update the timeRemainingDisplay.
		// Once millisecondsRemaining hits 0, the Timer is stopped in the attached TimerListener.
		timer = new Timer( 60000, new TimerListener() );
		timer.setRepeats(true);
		timer.start();
		
		// Create the timeRemainingDisplay.
		timeRemainingDisplay = new JLabel();
		timeRemainingDisplay.setHorizontalAlignment(SwingConstants.CENTER); // Move the text to the center of the label.
		timeRemainingDisplay.setAlignmentX(CENTER_ALIGNMENT); // Move the label to the center of the screen.
		updateRemainingTimeDisplay();
		
		// Create a button to return to the EnterCookingTimeScreen.
		reset = new JButton("Reset");
		reset.setAlignmentX(CENTER_ALIGNMENT); // Move the button to the center of the screen.
		reset.addActionListener( new ButtonListener() );
		
		// Create a button to exit this program.
		exit = new JButton("Exit");
		exit.setAlignmentX(CENTER_ALIGNMENT); // Move the button to the center of the screen.
		exit.addActionListener( new ButtonListener() );
		
		// Create a panel to hold these buttons.
		buttonsHolder = new JPanel();
		buttonsHolder.setLayout( new BoxLayout(buttonsHolder, BoxLayout.X_AXIS) );
		buttonsHolder.add( new JPanel() );
		buttonsHolder.add(reset);
		buttonsHolder.add( new JPanel() );
		buttonsHolder.add(exit);
		buttonsHolder.add( new JPanel() );
		
		// Assemble everything together.
		this.add( new JPanel() );
		this.add( new JPanel() );
		this.add(timeRemainingDisplay);
		this.add( new JPanel() );
		this.add(buttonsHolder);
		this.add( new JPanel() );
		
	} // End of method createContents.
	
	
	/**
	 * When called (every minute by the Timer), this method calculates the current amount of hours and minutes
	 * remaining based off of millisecondsRemaining.
	 * Then, it updates the timeRemainingDisplay label.
	 */
	private void updateRemainingTimeDisplay ()
	{
		int hoursRemaining, minutesRemaining;
		
		
		// Calculate the hours and minutes remaining.
		hoursRemaining = (millisecondsRemaining / 3600000);
		minutesRemaining = (millisecondsRemaining % 3600000) / 60000;
		
		// Update the timeRemainingDisplay.
		timeRemainingDisplay.setText("Time remaining: " + hoursRemaining + " hours, " + minutesRemaining + " minutes");
		timeRemainingDisplay.repaint();
		
	} // End of method updateRemainingTimeDisplay.
	
	
	private class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed (ActionEvent event)
		{
			JButton buttonClicked;
			
			
			buttonClicked = (JButton) event.getSource();
			
			if ( buttonClicked.getText().equals("Reset") )
			{
				timer.stop();
				
				mainWindow.displayNewScreen( new EnterCookingTimeScreen(mainWindow) );
			}
			
			else // Otherwise, "Exit" was selected.
			{
				// Terminate this program.
				System.exit(0);
			}
			
		} // End of method actionPerformed.
		
	} // End of class ButtonListener.
	
	
	public class TimerListener implements ActionListener
	{
		@Override
		public void actionPerformed (ActionEvent event)
		{
			// Decrement millisecondsRemaining by one minute.
			millisecondsRemaining -= 60000;
			
			updateRemainingTimeDisplay();
			
			if (millisecondsRemaining == 0)
			{
				timer.stop();
				
				new NotificationWindow();
			}
			
		} // End of method actionPerformed.
		
	} // End of class TimerListener.
	
} // End of class TimeRemainingScreen.
