public class Validation 
{
    
    public Validation() 
    {
        // Default constructor
    }
    
    public boolean isBlank(String input) 
    {
        return input == null || input.trim().length() == 0;
    }

    public boolean intInRange(int value, int min, int max) 
    {
        return value >= min && value <= max;
    }

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
