import java.util.Scanner;

public class Input
{
    public Input()
    {

    }
    
    public int acceptIntegerInput(String message) throws NumberFormatException
    {
        int input = Integer.parseInt(acceptStringInput(message));
        return input;
    }

    // public double acceptDoubleInput(String message)
    // {
    //     Double input = Double.parseDouble(acceptStringInput(message));
    //     return input;
    // }
    
    public String acceptStringInput(String message)
    {   
        Scanner console = new Scanner(System.in);
        System.out.print(message);
        String input = console.nextLine().trim();
        // System.out.println(input);
        // System.out.println();
        return input;
    }

    public static void main(String[] args)
    {
        // Input input = new Input();
        // input.acceptStringInput("Enter your name: ");
        // // input.acceptDoubleInput("Enter your height: ");
        // input.acceptStringInput("Enter your age: ");

    }
}
