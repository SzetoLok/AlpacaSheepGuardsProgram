/**
 * Class which represents an alpaca on the farm.
 * Each alpaca has a maintenance cost (whole dollars), a fixed hire cost,
 * a name, and a status (alive or dead).
 * When an alpaca dies, its maintenance cost is halved.
 *
 * @author Your Name
 * @version ver1.0.0
 */
public class Alpaca extends Animal
{
    /** The yearly maintenance cost of the alpaca in whole dollars. */
    private int maintenanceCost;

    /** The yearly hire cost for an alpaca (fixed for all alpacas). */
    public static final int HIRE_COST = 500;

    /** The name of the alpaca. */
    private String name;

    /**
     * Default constructor which creates an alpaca with a random maintenance cost
     * between $400 and $599 (inclusive) and no name.
     */
    public Alpaca()
    {
        super();
        this.maintenanceCost = 400 + (int)(Math.random() * 200);
        this.name = "alpaca";
    }

    /**
     * Non-default constructor which creates an alpaca with a specified maintenance cost and name.
     *
     * @param maintenanceCost   The yearly maintenance cost in whole dollars.
     * @param name              The name of the alpaca.
     */
    public Alpaca(int maintenanceCost, String name)
    {
        super();
        this.maintenanceCost = maintenanceCost;
        this.name = name;
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
     * Displays the state of the alpaca by printing its string representation.
     */
    public void display()
    {
        System.out.println(this.toString());
    }

    /**
     * Accessor method to get the hire cost for any alpaca.
     *
     * @return  The hire cost as an integer.
     */
    public static int getHireCost()
    {
        return HIRE_COST;
    }

    /**
     * Accessor method to get the maintenance cost of this alpaca.
     *
     * @return  The maintenance cost as an integer.
     */
    public int getMaintenanceCost()
    {
        return this.maintenanceCost;
    }

    /**
     * Accessor method to get the name of the alpaca.
     *
     * @return  The name as a String.
     */
    public String getName()
    {
        return this.name;
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
     * Mutator method to set the name of the alpaca.
     *
     * @param name  The new name as a String.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Mutator method to set the maintenance cost of the alpaca.
     *
     * @param maintenanceCost   The new maintenance cost as an integer.
     */
    public void setMaintenanceCost(int maintenanceCost)
    {
        this.maintenanceCost = maintenanceCost;
    }

    /**
     * Returns a string representation of the alpaca, including its name,
     * alive status, maintenance cost, and hire cost.
     *
     * @return  The state of the alpaca as a formatted String.
     */
    @Override
    public String toString()
    {
        return this.getName() + " [alive=" + getAlive() +
                "\nmaintenanceCost=" + this.maintenanceCost +
                "\nhireCost=" + HIRE_COST + "]";
    }
}
