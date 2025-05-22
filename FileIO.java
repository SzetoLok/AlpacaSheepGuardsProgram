import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO 
{
    private String fileName;

    public FileIO()
    {
        this.fileName = "Unknown";
    }

    public FileIO(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return this.fileName;
    }

    public ArrayList<State> readFile() 
    {
        ArrayList<State> states = new ArrayList<State>();

        try (FileReader reader = new FileReader(fileName)) 
        {
            String line;
            String[] predatorNames = {"Fox", "Dingo", "Feral Pig", "Wedge-tailed Eagle"};
            Scanner fileInput = new Scanner(reader);

            while (fileInput.hasNextLine() == true) 
            {
                line = fileInput.nextLine().trim();
                String[] parts = line.split(",");

                String stateName = parts[0]; 
                Predator[] predators = new Predator[parts.length - 1];
                
                for (int i = 0; i < parts.length - 1; i++) 
                {
                    double dangerFactor = Double.parseDouble(parts[i + 1]);
                    predators[i] = new Predator(predatorNames[i], 
                                                dangerFactor);
                }
                
                State state = new State(stateName, predators);
                states.add(state);
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error reading file: " + e);
        }
        return states;
    }
        
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }
    
    public void writeFile(String content) 
    {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) 
        {
            writer.println(content);
        } catch (IOException e) 
        {
            System.out.println("Error writing to file: " + e);
        }
    }
     public static void main(String[] args) 
     {

        FileIO fileIO = new FileIO("predators.txt");
        System.out.println("Reading predator data from file:");
        ArrayList<State> states = fileIO.readFile();
        
        for (State state : states) {
            System.out.println("\nState: " + state.getStateName());
            for (Predator predator : state.getPredators()) {
                System.out.println("  " + predator.getName() + 
                                    ": " + predator.getDangerFactor());
            }
        }
     }
}
