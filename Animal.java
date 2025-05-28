/**
 * Abstract class which represents an animal on the farm.
 * Each animal has a name and an alive status.
 * Subclasses must implement death probability logic.
 *
 * @author Szeto Lok
 * @version ver1.0.0
 */
public abstract class Animal
{

    private boolean alive;
    private String name;

    /**
     * Default constructor which creates an animal with unknown name and not alive.
     */
    public Animal()
    {
        this.alive = false;
        this.name = "Unknown";
    }

    /**
     * Non-default constructor which creates an animal with the given alive status and name.
     *
     * @param alive The alive status of the animal.
     * @param name The name of the animal.
     */
    public Animal(boolean alive, String name)
    {
        this.alive = alive;
        this.name = name;
    }

    /**
     * Marks the animal as dead.
     */
    public void die()
    {
        this.alive = false;
    }

    /**
     * Displays the state of the animal by printing its string representation.
     */
    public void display()
    {
        System.out.println(this.toString());
    }

    /**
     * Accessor method to get the alive status of the animal.
     *
     * @return true if the animal is alive, false otherwise.
     */
    public boolean getAlive()
    {
        return this.alive;
    }

    /**
     * Abstract method to get the death probability for this animal given a base probability.
     *
     * @param deathProbability The base death probability.
     * @param numberOfAlpacas The number of alpacas protecting the farm.
     * @return The probability of death as a double.
     */
    public abstract double getDeathProbability(double deathProbability, int numberOfAlpacas);

    /**
     * Accessor method to get the name of the animal.
     *
     * @return The name of the animal as a String.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Mutator method to set the alive status of the animal.
     *
     * @param alive The alive status to set.
     */
    public void setAlive(boolean alive)
    {
        this.alive = alive;
    }

    /**
     * Mutator method to set the name of the animal.
     *
     * @param name The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Returns a string representation of the animal.
     *
     * @return The animal's name and alive status as a String.
     */
    @Override
    public String toString()
    {
        return this.name + " [alive=" + this.alive + "]";
    }
}
