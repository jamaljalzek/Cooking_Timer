import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class ErrorScreen extends JPanel
{
	private MainWindow mainWindow;
	
	private String errorCause;
	
	
	/**
	 * This screen (JPanel) appears when the user typed in an invalid time, such as leaving one or both fields blank,
	 * or entering anything in other than digits.
	 * 
	 * @param mainWindow
	 * @param errorCause
	 */
	public ErrorScreen (MainWindow mainWindow, String errorCause)
	{
		this.mainWindow = mainWindow;
		this.errorCause = errorCause;
		
		this.setLayout( new BoxLayout(this, BoxLayout.Y_AXIS) );
		this.createContents();
		
	} // End of Constructor.
	
	
	private void createContents ()
	{
		JLabel errorMessage;
		JButton okay;
		
		
		// Create the error message to display.
		errorMessage = new JLabel("<html>ERROR:<br>" + errorCause + "</html>");
		errorMessage.setHorizontalAlignment(SwingConstants.CENTER); // Move the text to the center of the label.
		errorMessage.setAlignmentX(CENTER_ALIGNMENT); // Move the label to the center of the screen.
		
		// Create the button that exits this screen.
		okay = new JButton("Okay");
		okay.setAlignmentX(CENTER_ALIGNMENT); // Move the button to the center of the screen.
		okay.addActionListener( new ButtonListener() );
		
		// Assemble everything together.
		this.add( new JPanel() );
		this.add(errorMessage);
		this.add( new JPanel() );
		this.add(okay);
		this.add( new JPanel() );
		
	} // End of method createContents.
	
	
	private class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed (ActionEvent event)
		{
			// "Okay" was clicked.
			mainWindow.displayNewScreen( new EnterCookingTimeScreen(mainWindow) );
			
		} // End of method actionPerformed.
		
	} // End of class ButtonListener.
	
} // End of class ErrorScreen.
