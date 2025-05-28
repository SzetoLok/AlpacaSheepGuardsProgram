/**
 * Class which represents an alpaca on the farm.
 * Each alpaca has a maintenance cost (whole dollars), a fixed hire cost,
 * a name, and a status (alive or dead).
 * When an alpaca dies, its maintenance cost is halved.
 *
 * @author Szeto Lok
 * @version ver1.0.0
 */
public class Alpaca extends Animal
{

    /** The yearly maintenance cost of the alpaca in whole dollars. */
    private int maintenanceCost;

    /** The yearly hire cost for an alpaca (fixed for all alpacas). */
    public static final int HIRE_COST = 500;

    /**
     * Default constructor which creates an alpaca with a random 
     * maintenance cost between $400 and $599 (inclusive) and no name.
     * 
     */
    public Alpaca()
    {
        super(false, "Unknown");
        this.maintenanceCost = 0;
    }

    /**
     * Non-default constructor which creates an alpaca with a 
     * specified maintenance cost and name.
     *
     * @param alive The alive status of the alpaca.
     * @param name The name of the alpaca.
     */
    public Alpaca(boolean alive, String name)
    {
        super(alive, name);
        this.maintenanceCost = 400 + (int)(Math.random() * 200);
    }

    /**
     * Marks the alpaca as dead and halves its maintenance cost.
     */
    @Override
    public void die()
    {
        super.die();
        this.halfMaintenanceCost();
    }

    /**
     * Returns the death probability for this alpaca given a 
     * base probability and number of alpacas.
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
        return deathProbability / 100.0;
    }

    /**
     * Accessor method to get the hire cost for any alpaca.
     *
     * @return The hire cost as an integer.
     */
    public static int getHireCost()
    {
        return HIRE_COST;
    }

    /**
     * Accessor method to get the maintenance cost of this alpaca.
     *
     * @return The maintenance cost as an integer.
     */
    public int getMaintenanceCost()
    {
        return this.maintenanceCost;
    }

    /**
     * Halves the maintenance cost of this alpaca.
     * This should be called when the alpaca dies.
     */
    public void halfMaintenanceCost()
    {
        this.setMaintenanceCost(this.maintenanceCost / 2);
    }

    /**
     * Mutator method to set the maintenance cost of the alpaca.
     *
     * @param maintenanceCost The new maintenance cost as an integer.
     */
    public void setMaintenanceCost(int maintenanceCost)
    {
        this.maintenanceCost = maintenanceCost;
    }

    /**
     * Returns a string representation of the alpaca, including its name,
     * alive status, maintenance cost, and hire cost.
     *
     * @return The state of the alpaca as a formatted String.
     */
    @Override
    public String toString()
    {
        return super.toString() +
            "\nmaintenanceCost=" + this.maintenanceCost +
            "\nhireCost=" + HIRE_COST;
    }
}
