import java.util.HashMap;

/**
 * Class which stores information about a state, including its name and 
 * the predators present.
 *
 * @author Szeto Lok
 * @version ver1.0.0
 */
public class State
{
    private String stateName;
    private Predator[] predators;
    private final String [] PREDATOR_NAMES = {"Fox", 
                                            "Dingo", 
                                            "Feral Pig", 
                                            "Wedge-tailed Eagle"};

    /**
     * Default constructor which creates a state with default values.
     */
    public State()
    {
        this.stateName = "Unknown";
        this.predators = new Predator[0];
    }

    /**
     * Non-default constructor which creates a state from a string array of 
     * state data.
     *
     * @param stateData The string array containing state data.
     */
    public State(String[] stateData)
    {
        initializeState(stateData);
    }

    /**
     * Non-default constructor which creates a state with a given name and 
     * predators.
     *
     * @param stateName The name of the state.
     * @param predators The array of Predator objects.
     */
    public State(String stateName, Predator[] predators)
    {
        this.stateName = stateName;
        this.predators = predators;
    }

    /**
     * Creates and returns a deep copy of this State object.
     *
     * @return A new State object with the same name and predators.
     */
    public State copyState()
    {
        return new State(this.stateName, this.predators);
    }

    /**
     * Displays the state of the object by printing its string representation.
     */
    public void display()
    {
        System.out.print(this.toString());
    }

    /**
     * Accessor method to get the array of Predator objects.
     *
     * @return The array of Predator objects.
     */
    public Predator[] getPredators()
    {
        return this.predators;
    }

    /**
     * Accessor method to get the names of all predators in this state.
     *
     * @return An array of Strings representing predator names.
     */
    public String[] getPredatorsNames()
    {
        return this.PREDATOR_NAMES;
    }

    /**
     * Accessor method to get the predator names and danger factors.
     *
     * @return A HashMap with predator names as keys and danger factors as 
     * values.
     */
    public HashMap<String, Double> getPredatorsNamesAndDangerFactors()
    {
        HashMap<String, Double> predatorsInformation = new HashMap<>();
        for (Predator predator : this.predators)
        {
            predatorsInformation.put(predator.getName(), 
                                    predator.getDangerFactor());
        }
        return predatorsInformation;
    }

    /**
     * Accessor method to get a specific predator by index.
     *
     * @param index The index of the predator in the array.
     * @return The Predator object at the given index, or null if out of 
     * bounds.
     */
    public Predator getSpecificPredator(int index)
    {
        if (index >= 0 && index < this.predators.length)
        {
            return this.predators[index];
        }
        return null;
    }

    /**
     * Accessor method to get the state's name.
     *
     * @return The name of the state as a String.
     */
    public String getStateName()
    {
        return this.stateName;
    }

    /**
     * Initializes the predators array from a string array of danger 
     * factors.
     *
     * @param predatorData The string array of predator danger factors.
     */
    public void initializePredators(String[] predatorData)
    {
        this.predators = new Predator[predatorData.length];
        for (int index = 0; index < predatorData.length; index++)
        {
            double dangerFactor = Double.parseDouble(predatorData[index]);

            this
            .setSpecificPredator(index, new Predator(PREDATOR_NAMES[index], 
                                                        dangerFactor));
        }
    }

    /**
     * Initializes the state object from a string array of state data.
     *
     * @param stateData The string array containing state data.
     */
    public void initializeState(String[] stateData)
    {
        this.stateName = stateData[0];
        String[] predatorData = new String[stateData.length - 1];
        for (int index = 1; index < stateData.length; index++)
        {
            predatorData[index - 1] = stateData[index];
        }
        initializePredators(predatorData);
    }

    /**
     * Mutator method to set the array of Predator objects.
     *
     * @param predators The array of Predator objects to set.
     */
    public void setPredators(Predator[] predators)
    {
        this.predators = predators;
    }

    /**
     * Mutator method to set a specific predator by index.
     *
     * @param index The index to set.
     * @param predator The Predator object to set at the index.
     */
    public void setSpecificPredator(int index, Predator predator)
    {
        if (index >= 0 && index < this.predators.length)
        {
            this.predators[index] = predator;
        }
    }

    /**
     * Mutator method to set the state's name.
     *
     * @param stateName The name of the state as a String.
     */
    public void setStateName(String stateName)
    {
        this.stateName = stateName;
    }

    /**
     * Returns a string representation of the state, 
     * including its name and predators.
     *
     * @return The state of the object as a formatted String.
     */
    @Override
    public String toString()
    {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("State: ");
        stringBuffer.append(this.stateName);
        stringBuffer.append("\n");
        for (Predator predator : this.predators)
        {
            stringBuffer.append("Predator: ");
            stringBuffer.append(predator.getName());
            stringBuffer.append(", Danger Factor: ");
            stringBuffer.append(predator.getDangerFactor());
            stringBuffer.append("\n");
        }
        return stringBuffer.toString();
    }
}
