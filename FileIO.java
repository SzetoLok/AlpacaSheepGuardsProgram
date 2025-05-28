import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class which handles file input and output operations for state and 
 * predator data.
 *
 * @author Szeto Lok
 * @version ver1.0.0
 */
public class FileIO
{
    private String fileName;

    /**
     * Default constructor which creates a FileIO object with 
     * an unknown file name.
     * 
     */
    public FileIO()
    {
        this.fileName = "Unknown";
    }

    /**
     * Non-default constructor which creates a FileIO object with 
     * the given file name.
     *
     * @param fileName The name of the file.
     */
    public FileIO(String fileName)
    {
        this.fileName = fileName;
    }

    /**
     * Accessor method to get the file name.
     *
     * @return The file name as a String.
     */
    public String getFileName()
    {
        return this.fileName;
    }

    /**
     * Reads the state and predator data from the file.
     *
     * @return An ArrayList of String arrays, each representing 
     * a state's data.
     */
    public ArrayList<String[]> readFile()
    {
        ArrayList<String[]> statesData = new ArrayList<>();
        try (FileReader reader = new FileReader(fileName))
        {
            String line;
            Scanner fileInput = new Scanner(reader);
            while (fileInput.hasNextLine())
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

    /**
     * Mutator method to set the file name.
     *
     * @param fileName The file name as a String.
     */
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    /**
     * Writes the given content to the file.
     *
     * @param content The content to write to the file.
     */
    public void writeFile(String content)
    {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName)))
        {
            writer.println(content);
        }
        catch (IOException e)
        {
            System.out.println("Error writing to file: " + e);
        }
    }
}
