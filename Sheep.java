/**
 * Class which represents a sheep on the farm.
 * Each sheep has a name and an alive status.
 *
 * @author Szeto Lok
 * @version ver1.0.0
 */
public class Sheep extends Animal
{
    /**
     * Default constructor which creates a sheep with unknown 
     * name and not alive.
     * 
     */
    public Sheep()
    {
        super(false, "Unknown");
    }

    /**
     * Non-default constructor which creates a sheep with the given 
     * alive status and name.
     *
     * @param alive The alive status of the sheep.
     * @param name The name of the sheep.
     */
    public Sheep(boolean alive, String name)
    {
        super(alive, name);
    }

    /**
     * Returns the death probability for this sheep given a base probability 
     * and number of alpacas.
     *
     * @param deathProbability The base death probability.
     * @param numberOfAlpacas The number of alpacas protecting the farm.
     * @return The probability of death as a double.
     */
    @Override
    public double getDeathProbability(double deathProbability, 
                                        int numberOfAlpacas)
    {
        if (numberOfAlpacas == 1)
        {
            deathProbability /= 2.0;
        }
        else if (numberOfAlpacas >= 2)
        {
            deathProbability /= 4.0;
        }
        return deathProbability;
    }
}
