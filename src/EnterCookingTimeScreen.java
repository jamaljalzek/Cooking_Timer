import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class EnterCookingTimeScreen extends JPanel
{
	private MainWindow mainWindow;

	private JTextField enterHoursToCook, enterMinutesToCook;
	private int timeInMillisecondsToCook;
	
	
	/**
	 * This is the main screen, where the user types in the cooking time in hours and minutes.
	 * Once they click on "Start!", the MainWindow then displays either the TimeRemainingScreen, if they entered a valid time,
	 * or the ErrorScreen, if they entered an invalid time.
	 * 
	 * @param mainWindow
	 */
	public EnterCookingTimeScreen (MainWindow mainWindow)
	{
		this.mainWindow = mainWindow;
		
		this.setLayout( new BoxLayout(this, BoxLayout.Y_AXIS) );
		this.createContents();
		
	} // End of Constructor.
	
	
	private void createContents ()
	{
		JPanel timeToCook, buttonsHolder;
		JButton startTimer, exit;
		String blankSpace;
		
		
		// Create the text fields for user input.
		enterHoursToCook = new JTextField();
		enterMinutesToCook = new JTextField();
		
		// Create a panel that holds these text fields.
		timeToCook = new JPanel();
		timeToCook.setLayout( new BoxLayout(timeToCook, BoxLayout.X_AXIS) );
		timeToCook.add( new JLabel("          Enter time to cook (HOURS,  MINUTES):     ") );
		timeToCook.add(enterHoursToCook);
		timeToCook.add( new JLabel("     ") );
		timeToCook.add(enterMinutesToCook);
		timeToCook.add( new JLabel("          ") );
		
		// Create a button to start the timer.
		startTimer = new JButton("Start!");
		startTimer.setAlignmentX(CENTER_ALIGNMENT); // Move the button to the center of the screen.
		startTimer.addActionListener( new ButtonListener() );
		
		// Create a button to exit this program.
		exit = new JButton("Exit");
		exit.setAlignmentX(CENTER_ALIGNMENT); // Move the button to the center of the screen.
		exit.addActionListener( new ButtonListener() );
		
		// Create a panel to hold these buttons.
		buttonsHolder = new JPanel();
		buttonsHolder.setLayout( new BoxLayout(buttonsHolder, BoxLayout.X_AXIS) );
		buttonsHolder.add( new JPanel() );
		buttonsHolder.add(startTimer);
		buttonsHolder.add( new JPanel() );
		buttonsHolder.add(exit);
		buttonsHolder.add( new JPanel() );

		// Assemble everything together.
		blankSpace = "<html><br><br></html>";
		this.add( new JLabel(blankSpace) );
		this.add(timeToCook);
		this.add( new JLabel(blankSpace) );
		this.add(buttonsHolder);
		this.add( new JLabel(blankSpace) );
		
	} // End of method createContents.
	
	
	private class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed (ActionEvent event)
		{
			JButton buttonClicked;
			String hoursToCookEntered, minutesToCookEntered;
			int hoursToCook, minutesToCook;
			
			
			buttonClicked = (JButton) event.getSource();
			
			if ( buttonClicked.getText().equals("Start!") )
			{
				hoursToCookEntered = enterHoursToCook.getText();
				minutesToCookEntered = enterMinutesToCook.getText();	
				
				// Input validation.
				if ( hoursToCookEntered.isEmpty() || minutesToCookEntered.isEmpty() )
				{
					mainWindow.displayNewScreen( new ErrorScreen(mainWindow, "Please enter the cooking time in both fields!") );
					return;
				}
				else if ( !onlyDigitsEntered(hoursToCookEntered) || !onlyDigitsEntered(minutesToCookEntered) )
				{
					mainWindow.displayNewScreen( new ErrorScreen(mainWindow, "Please only enter numbers for the cooking time!") );
					return;
				}
				
				// Otherwise, a valid number was entered in both of the text fields (could be 0 hours and 0 minutes entered).
				hoursToCook = Integer.parseInt(hoursToCookEntered);
				minutesToCook = Integer.parseInt(minutesToCookEntered);
				
				// Convert the hours and minutes to cook into milliseconds for the Timer.
				timeInMillisecondsToCook = hoursToCook * 3600000 + minutesToCook * 60000;
				
				// Check if the user entered 0 for both fields.
				if (timeInMillisecondsToCook == 0)
				{
					mainWindow.displayNewScreen( new ErrorScreen(mainWindow, "Please enter a cooking time greater than 0!") );
					return;
				}
				
				// Otherwise, start the Timer and display the TimeRemainingScreen.
				mainWindow.displayNewScreen( new TimeRemainingScreen(mainWindow, timeInMillisecondsToCook) );

			} // End of if ( buttonClicked.getText().equals("Start!") ).
			
			else // Otherwise, "Exit" was clicked.
			{
				// Terminate this program.
				System.exit(0);
			}
			
		} // End of method actionPerformed.
		
		
		/**
		 * This is a helper method that checks if each character in the passed in String is a digit.
		 * 
		 * @param
		 * textEntered
		 * 
		 * @return
		 * When the passed in String contains only digits, then this method returns true.
		 * Otherwise, it returns false.
		 */
		private boolean onlyDigitsEntered (String textEntered)
		{
			for (int index = 0; index < textEntered.length(); ++index)
			{
				// If the current character is not a digit, stop looping and return false.
				if ( !Character.isDigit(textEntered.charAt(index)) )
				{
					return false;
				}
			}
			
			// Otherwise, all characters in textEntered are digits.
			return true;
			
		} // End of method onlyDigitsEntered.
		
	} // End of class ButtonListener.
	
} // End of class EnterCookingTimeScreen.
