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

     
    public ArrayList<String[]> readFile()
    {
        ArrayList<String[]> statesData = new ArrayList<>();

        try (FileReader reader = new FileReader(fileName))
        {
            String line;
            Scanner fileInput = new Scanner(reader);

            while (fileInput.hasNextLine() == true)
            {
                line = fileInput.nextLine().trim();
                String[] stateParts = line.split(",");
                statesData.add(stateParts);
            }
        }
        catch (IOException e)
        {
            System.out.println("Error reading file: " + e);
        }
        return statesData;
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
        ArrayList<String[]> states = fileIO.readFile();
        
        for (String[] state : states) 
        {
            for (String info : state)
            {
                System.out.println(info);
            }
            System.out.println("Next States: \n");
        }
    }
     
}
