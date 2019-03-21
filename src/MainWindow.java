import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainWindow extends JFrame
{
	/**
	 * This is the main window (JFrame) that displays the various screens (JPanels), depending on where the user
	 * navigates via clicking the specific buttons on each screen.
	 */
	public MainWindow ()
	{
		this.setTitle("Cooking Timer");
		this.setSize(400, 200);
		this.setResizable(false);
		this.setLayout( new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS) );
		this.setLocationRelativeTo(null); // Displays the window in the center of the screen when launched.
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
	} // End of Constructor.
	
	
	/**Removes the current screen (JPanel) that is displayed on the MainWindow.
	 * Then, it adds the passed in JPanel that is to be displayed.
	 * Finally, the MainWindow's Content Pane is updated.
	 * 
	 * @param screen
	 */
	public void displayNewScreen (JPanel screen)
	{
		// Clear everything off of this current window.
		this.getContentPane().removeAll();
		
		this.add(screen);
		
		// Update the appearance of this window.
		this.getContentPane().validate();
		this.getContentPane().repaint();				
		
	} // End of method displayNewScreen.
	
} // End of class MainWindow.
