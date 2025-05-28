/**
 * Class which represents a predator in a state.
 * Each predator has a name and a danger factor.
 *
 * @author Your Name
 * @version ver1.0.0
 */
public class Predator
{
    private String name;
    private double dangerFactor;

    /**
     * Default constructor which creates a predator with unknown 
     * name and zero danger factor.
     * 
     */
    public Predator()
    {
        this.name = "Unknown";
        this.dangerFactor = 0.0;
    }

    /**
     * Non-default constructor which creates a predator with 
     * the given name and danger factor.
     *
     * @param name The name of the predator.
     * @param dangerFactor The danger factor of the predator.
     */
    public Predator(String name, double dangerFactor)
    {
        this.name = name;
        this.dangerFactor = dangerFactor;
    }

    /**
     * Displays the state of the predator by printing its 
     * string representation.
     */
    public void display()
    {
        System.out.println(this.toString());
    }

    /**
     * Accessor method to get the danger factor of the predator.
     *
     * @return The danger factor as a double.
     */
    public double getDangerFactor()
    {
        return this.dangerFactor;
    }

    /**
     * Accessor method to get the name of the predator.
     *
     * @return The name of the predator as a String.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Mutator method to set the danger factor of the predator.
     *
     * @param dangerFactor The danger factor to set.
     */
    public void setDangerFactor(double dangerFactor)
    {
        this.dangerFactor = dangerFactor;
    }

    /**
     * Mutator method to set the name of the predator.
     *
     * @param name The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Returns a string representation of the predator, including 
     * its name and danger factor.
     *
     * @return The state of the predator as a formatted String.
     */
    @Override
    public String toString()
    {
        return "Predator [name=" + name + 
                ", dangerFactor=" + dangerFactor + "]";
    }

}
