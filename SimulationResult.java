/**
 * Class which stores the results of a single simulation run,
 * including animal survivors, casualties, predator kills, total cost,
 * and protection level.
 *
 * @author Szeto Lok 35581492
 * @version ver1.0.0
 */

import java.util.HashMap;

public class SimulationResult
{
    private HashMap<String, Integer> animalAlive;
    private HashMap<String, Integer> animalDeath;
    private HashMap<String, Integer> predatorKills;
    private double totalCost;
    private String protectionLevel;
    public Farm farm;
    /**
     * Default constructor which creates the object of the class 
     * SimulationResult.
     *
     */
    public SimulationResult()
    {
        this.animalAlive = new HashMap<>();
        this.animalDeath = new HashMap<>();
        this.predatorKills = new HashMap<>();
        this.protectionLevel = "Unknown";
        this.totalCost = 0.0;
    }

    /**
     * Non-default constructor which creates the object of the class 
     * SimulationResult.
     *
     * @param animalAlive      A map with the number of each animal type alive.
     * @param animalDeath      A map with the number of each animal type dead.
     * @param predatorKills    A map with the number of kills by each predator.
     * @param protectionLevel  The protection level in the simulation.
     * @param totalCost        The total cost of a simulation.
     */
    public SimulationResult(HashMap<String, Integer> animalAlive,
                            HashMap<String, Integer> animalDeath,
                            HashMap<String, Integer> predatorKills,
                            String protectionLevel,
                            double totalCost,
                            Farm farm
                            )
    {
        this.animalAlive = animalAlive;
        this.animalDeath = animalDeath;
        this.predatorKills = predatorKills;
        this.protectionLevel = protectionLevel;
        this.totalCost = totalCost;
        this.farm = farm;
    }

    /**
     * Display method to print the state of the object.
     *
     */
    public void display() 
    {
        System.out.println(this.toString());
    }

    /**
     * Accessor method to get the map of animals alive.
     *
     * @return      A map of animal types to the number alive.
     */
    public HashMap<String, Integer> getAnimalAlive() 
    {
        return this.animalAlive;
    }

   /**
     * Accessor method to get the map of animals dead.
     *
     * @return      A map of animal types to the number dead.
     */
    public HashMap<String, Integer> getAnimalDeath() 
    {
        return this.animalDeath;
    }

    /**
     * Accessor method to get the map of predator kills.
     *
     * @return      A map of predator names to the number of kills.
     */
    public HashMap<String, Integer> getPredatorKills() 
    {
        return this.predatorKills;
    }
    
    /**
     * Accessor method to get the protection level.
     *
     * @return      The protection level as a String.
     */
    public String getProtectionLevel() 
    {
        return this.protectionLevel;
    }

    /**
     * Accessor method to get the total cost of the simulation run.
     *
     * @return      The total cost as a double.
     */
    public double getTotalCost()
    {
        return this.totalCost;
    }

    /**
     * Accessor method to get the number of a specific animal type alive.
     *
     * @param animalType    The type of animal as a String.
     * @return              The number of that animal type alive.
     */
    public int getSpecificAnimalAlive(String animalType) 
    {
        return this.animalAlive.getOrDefault(animalType, 0);
    }

    /**
     * Accessor method to get the number of a specific animal type dead.
     *
     * @param animalType    The type of animal as a String.
     * @return              The number of that animal type dead.
     */
    public int getSpecificAnimalDeath(String animalType) 
    {
        return this.animalDeath.getOrDefault(animalType, 0);
    }

    /**
     * Accessor method to get the number of kills by a specific predator.
     *
     * @param predatorName  The name of the predator as a String.
     * @return              The number of kills by that predator.
     */
    public int getSpecificPredatorKills(String predatorName) 
    {
        return this.predatorKills.getOrDefault(predatorName, 0);
    }

    /**
     * Mutator method to set the map of animals alive.
     *
     * @param animalAlive   A map of animal types to the number alive.
     */
    public void setAnimalAlive(HashMap<String, Integer> animalAlive) 
    {
        this.animalAlive = animalAlive;
    }

    /**
     * Mutator method to set the map of animals dead.
     *
     * @param animalDeath   A map of animal types to the number dead.
     */
    public void setAnimalDeath(HashMap<String, Integer> animalDeath) 
    {
        this.animalDeath = animalDeath;
    }

    /**
     * Mutator method to set the map of predator kills.
     *
     * @param predatorKills A map of predator names to the number of kills.
     */
    public void setPredatorKills(HashMap<String, Integer> predatorKills) 
    {
        this.predatorKills = predatorKills;
    }

    /**
     * Mutator method to set the protection level.
     *
     * @param protectionLevel   The protection level as a String.
     */
    public void setProtectionLevel(String protectionLevel) 
    {
        this.protectionLevel = protectionLevel;
    }

    /**
     * Mutator method to set the total cost of the simulation run.
     *
     * @param totalCost     The total cost as a double.
     */
    public void setTotalCost(double totalCost) 
    {
        this.totalCost = totalCost;
    }

    /**
     * Returns a string representation of the SimulationResult object.
     *
     * @return      The state of the object as a formatted String.
     */
    @Override
    public String toString() 
    {
        return "SimulationResult " +
                "\n  Protection Level: " + protectionLevel +
                "\n  Animals Alive: " + animalAlive +
                "\n  Animals Dead: " + animalDeath +
                "\n  Predator Kills: " + predatorKills +
                "\n  Total Cost: $" + String.format("%.2f", totalCost);
    }
}
