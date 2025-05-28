/**
 * Class which provides validation methods for user input and values.
 *
 * @author Szeto Lok
 * @version ver1.0.0
 */
public class Validation
{
    /**
     * Default constructor for the Validation class.
     * 
     */
    public Validation()
    {

    }

    /**
     * Checks if the input string is blank or null.
     *
     * @param input The input string.
     * @return true if the input is blank or null, false otherwise.
     */
    public boolean isBlank(String input)
    {
        return input == null || input.trim().length() == 0;
    }

    /**
     * Checks if an integer value is within the specified range 
     * (inclusive).
     *
     * @param value The integer value to check.
     * @param min The minimum allowed value.
     * @param max The maximum allowed value.
     * @return true if value is in range, false otherwise.
     */
    public boolean intInRange(int value, int min, int max)
    {
        return value >= min && value <= max;
    }

    /**
     * Checks if the length of the input string is within the specified range 
     * (inclusive).
     *
     * @param input The string to check.
     * @param min The minimum length.
     * @param max The maximum length.
     * @return true if the input length is in range, false otherwise.
     */
    public boolean stringLengthInRange(String input, int min, int max)
    {
        if (input == null)
        {
            return false;
        }
        int length = input.trim().length();
        return length >= min && length <= max;
    }
}
