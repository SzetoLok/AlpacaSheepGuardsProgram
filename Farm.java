import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class which stores information and manages the state of a farm, 
 * including its name, state, and lists of sheep, lambs, and alpacas.
 *
 * @author Szeto Lok
 * @version ver1.0.0
 */
public class Farm
{
    private String farmName;
    private State state;
    private ArrayList<Sheep> sheeps;
    private ArrayList<Lamb> lambs;
    private ArrayList<Alpaca> alpacas;

    /**
     * Default constructor which creates a farm with default values.
     */
    public Farm()
    {
        this.farmName = "Unknown";
        this.state = new State();
        this.sheeps = new ArrayList<Sheep>();
        this.lambs = new ArrayList<Lamb>();
        this.alpacas = new ArrayList<Alpaca>();
    }

    /**
     * Non-default constructor which creates a farm with a given name, 
     * state, and lists of animals.
     *
     * @param farmName The name of the farm.
     * @param state The State object representing the farm's location.
     * @param sheeps The list of Sheep objects on the farm.
     * @param lambs The list of Lamb objects on the farm.
     * @param alpacas The list of Alpaca objects on the farm.
     */
    public Farm(String farmName, 
                State state, 
                ArrayList<Sheep> sheeps, 
                ArrayList<Lamb> lambs, 
                ArrayList<Alpaca> alpacas)
    {
        this.farmName = farmName;
        this.state = state;
        this.sheeps = sheeps;
        this.lambs = lambs;
        this.alpacas = alpacas;
    }

    /**
     * Adds a new alpaca to the farm.
     */
    public void addAlpacaOnce()
    {
        this.alpacas.add(new Alpaca(true, "Alpaca"));
    }

    /**
     * Adds a new lamb to the farm.
     */
    public void addLambOnce()
    {
        this.lambs.add(new Lamb(true, "Lamb"));
    }

    /**
     * Adds a new sheep to the farm.
     */
    public void addSheepOnce()
    {
        this.sheeps.add(new Sheep(true, "Sheep"));
    }

    /**
     * Returns a deep copy of this Farm object, including all animals 
     * and state.
     *
     * @return A new Farm object that is a deep copy of this farm.
     */
    public Farm copyFarm()
    {
        State stateCopy = this.state.copyState();
        Farm farmCopy = new Farm(this.farmName, 
                                stateCopy,
                                new ArrayList<Sheep>(),
                                new ArrayList<Lamb>(),
                                new ArrayList<Alpaca>());

        for (int index = 0; index < this.getSheeps().size(); index++)
        {
            farmCopy.addSheepOnce();
        }
        for (int index = 0; index < this.getLambs().size(); index++)
        {
            farmCopy.addLambOnce();
        }
        for (int index = 0; index < this.getAlpacas().size(); index++)
        {
            farmCopy.addAlpacaOnce();
        }
        return farmCopy;
    }

    /**
     * Displays the state of the farm by printing its string representation.
     */
    public void display()
    {
        System.out.println(this.toString());
    }

    /**
     * Accessor method to get the list of alpacas on the farm.
     *
     * @return An ArrayList of Alpaca objects.
     */
    public ArrayList<Alpaca> getAlpacas()
    {
        return this.alpacas;
    }

    /**
     * Accessor method to get the farm's name.
     *
     * @return The name of the farm as a String.
     */
    public String getFarmName()
    {
        return this.farmName;
    }

    /**
     * Accessor method to get the list of lambs on the farm.
     *
     * @return An ArrayList of Lamb objects.
     */
    public ArrayList<Lamb> getLambs()
    {
        return this.lambs;
    }

    /**
     * Accessor method to get the names of all predators in the farm's state.
     *
     * @return An array of Strings representing predator names.
     */
    public String[] getPredatorsNames()
    {
        return this.getState().getPredatorsNames();
    }

    /**
     * Accessor method to get the predator names and danger factors 
     * in the farm's state.
     *
     * @return A HashMap with predator names as keys and danger factors 
     * as values.
     */
    public HashMap<String, Double> getPredatorsNamesAndDangerFactors()
    {
        return this.getState().getPredatorsNamesAndDangerFactors();
    }

    /**
     * Accessor method to get the list of sheep on the farm.
     *
     * @return An ArrayList of Sheep objects.
     */
    public ArrayList<Sheep> getSheeps()
    {
        return this.sheeps;
    }

    /**
     * Accessor method to get a specific alpaca by index.
     *
     * @param index The index of the alpaca in the list.
     * @return The Alpaca object at the given index, or null if out of 
     * bounds.
     * 
     */
    public Alpaca getSpecificAlpaca(int index)
    {
        if (index >= 0 && index < this.alpacas.size())
        {
            return this.alpacas.get(index);
        }
        return null;
    }

    /**
     * Accessor method to get the state object for the farm.
     *
     * @return The State object representing the farm's state.
     */
    public State getState()
    {
        return this.state;
    }

    /**
     * Accessor method to get the total alpaca maintenance cost for 
     * the farm.
     *
     * @return The total maintenance cost as an int.
     */
    public int getTotalAlpacaMaintenanceCost()
    {
        int totalCost = 0;
        for (Alpaca alpaca : this.alpacas)
        {
            totalCost += alpaca.getMaintenanceCost();
        }
        return totalCost;
    }

    /**
     * Accessor method to get the total number of alpacas on the farm.
     *
     * @return The number of alpacas as an int.
     */
    public int getTotalAlpacas()
    {
        return this.alpacas.size();
    }

    /**
     * Accessor method to get the total number of lambs on the farm.
     *
     * @return The number of lambs as an int.
     */
    public int getTotalLambs()
    {
        return this.lambs.size();
    }

    /**
     * Accessor method to get the total number of sheep on the farm.
     *
     * @return The number of sheep as an int.
     */
    public int getTotalSheeps()
    {
        return this.sheeps.size();
    }

    /**
     * Initializes the state of the farm using data from a string array.
     *
     * @param stateData The string array containing state data.
     */
    public void initializeState(String[] stateData)
    {
        this.state = new State(stateData);
    }

    /**
     * Mutator method to set the list of alpacas on the farm.
     *
     * @param alpacas The ArrayList of Alpaca objects to set.
     */
    public void setAlpacas(ArrayList<Alpaca> alpacas)
    {
        this.alpacas = alpacas;
    }

    /**
     * Mutator method to set the farm's name.
     *
     * @param farmName The name of the farm as a String.
     */
    public void setFarmName(String farmName)
    {
        this.farmName = farmName;
    }

    /**
     * Mutator method to set the list of lambs on the farm.
     *
     * @param lambs The ArrayList of Lamb objects to set.
     */
    public void setLambs(ArrayList<Lamb> lambs)
    {
        this.lambs = lambs;
    }

    /**
     * Mutator method to set the list of sheep on the farm.
     *
     * @param sheeps The ArrayList of Sheep objects to set.
     */
    public void setSheeps(ArrayList<Sheep> sheeps)
    {
        this.sheeps = sheeps;
    }

    /**
     * Returns a string representation of the farm, including its name,
     * state, and the number of each animal.
     *
     * @return The state of the farm as a formatted string.
     */
    @Override
    public String toString()
    {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Farm: ");
        stringBuffer.append(this.farmName);
        stringBuffer.append("\n");
        stringBuffer.append(this.state.toString());
        stringBuffer.append("Number of Alpaca: ");
        stringBuffer.append(this.getTotalAlpacas());
        stringBuffer.append("\n");
        stringBuffer.append("Number of Sheeps: ");
        stringBuffer.append(this.getTotalSheeps());
        stringBuffer.append("\n");
        stringBuffer.append("Number of Lambs: ");
        stringBuffer.append(this.getTotalLambs());
        stringBuffer.append("\n");
        return stringBuffer.toString();
    }
}
