/**
 * @author Jamal Alzek
 * 
 * This the main class that is referenced when the program executes.
 * Specifically, a new MainWindow is created, which then immediately displays the EnterCookingTimeScreen.
 */
public class StartHere
{
	public static void main (String [] args)
	{
		MainWindow mainWindow;
		
		mainWindow = new MainWindow();
		mainWindow.displayNewScreen( new EnterCookingTimeScreen(mainWindow) );
		
	} // End of method main.
	
} // End of class StartHere.
