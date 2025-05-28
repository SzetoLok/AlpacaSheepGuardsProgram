import java.util.Scanner;

/**
 * Class which handles user input from the console.
 *
 * @author Szeto Lok
 * @version ver1.0.0
 */

public class Input
{
    /**
     * Default constructor for the Input class.
     * 
     */
    public Input()
    {

    }

    /**
     * Accepts an integer input from the user.
     *
     * @param message The prompt message to display.
     * @return The integer input from the user.
     * @throws NumberFormatException if the input is not a valid integer.
     */
    public int acceptIntegerInput(String message) 
    throws NumberFormatException
    {
        int input = Integer.parseInt(acceptStringInput(message));
        return input;
    }

    /**
     * Accepts a string input from the user.
     *
     * @param message The prompt message to display.
     * @return The string input from the user.
     */
    public String acceptStringInput(String message)
    {
        Scanner console = new Scanner(System.in);
        System.out.print(message);
        String input = console.nextLine().trim();
        return input;
    }
}
