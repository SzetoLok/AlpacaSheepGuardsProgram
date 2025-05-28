/**
 * Class which represents a lamb on the farm.
 * Each lamb has a name and an alive status.
 *
 * @author Szeto Lok
 * @version ver1.0.0
 */
public class Lamb extends Animal
{
    /**
     * Default constructor which creates a lamb with unknown name and 
     * not alive.
     */
    public Lamb()
    {
        super(false, "Unknown");
    }

    /**
     * Non-default constructor which creates a lamb with the given 
     * alive status and name.
     *
     * @param alive The alive status of the lamb.
     * @param name The name of the lamb.
     */
    public Lamb(boolean alive, String name)
    {
        super(alive, name);
    }

    /**
     * Returns the death probability for this lamb given a base probability 
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
        deathProbability *= 2.0;
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
