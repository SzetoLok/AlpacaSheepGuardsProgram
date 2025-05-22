/**
 * ProtectionLevelResult stores all statistics for a single protection level
 * in the alpaca sheep guard simulation program.
 *
 * @author Your Name
 * @version 1.0.0
 */
import java.util.HashMap;
import java.util.ArrayList;

public class ProtectionLevelResult
{

    private String protectionLevel;

    private double averageCost;

    private double lowestCost;

    private double highestCost;

    private double averageSheepLost;

    private double averageLambLost;

    private double averageAlpacaLost;

    private HashMap<String, Double> averagePredatorKills;

    private ArrayList<String> mostTroublesomePredators;

    private ArrayList<String> zeroKillPredators;

    /**
     * Default constructor which creates a ProtectionLevelResult object
     * with default values.
     */
    public ProtectionLevelResult()
    {
        this.protectionLevel = "Unknown";
        this.averageCost = 0.0;
        this.lowestCost = 0.0;
        this.highestCost = 0.0;
        this.averageSheepLost = 0.0;
        this.averageLambLost = 0.0;
        this.averageAlpacaLost = 0.0;
        this.averagePredatorKills = new HashMap<>();
        this.mostTroublesomePredators = new ArrayList<>();
        this.zeroKillPredators = new ArrayList<>();
    }

    /**
     * Non-default constructor which creates a ProtectionLevelResult object
     * with all fields specified.
     *
     * @param protectionLevel The protection level name.
     * @param averageCost The average total cost.
     * @param lowestCost The lowest total cost.
     * @param highestCost The highest total cost.
     * @param averageSheepLost The average number of sheep lost.
     * @param averageLambLost The average number of lambs lost.
     * @param averageAlpacaLost The average number of alpacas lost.
     * @param averagePredatorKills The average predator kills.
     * @param mostTroublesomePredator The most troublesome predator.
     * @param zeroKillPredators ArrayList of predator names with zero kills.
     */
    public ProtectionLevelResult(String protectionLevel, double averageCost, double lowestCost,
                                double highestCost, double averageSheepLost, double averageLambLost,
                                double averageAlpacaLost, HashMap<String, Double> averagePredatorKills,
                                ArrayList<String> mostTroublesomePredators, ArrayList<String> zeroKillPredators)
    {
        this.protectionLevel = protectionLevel;
        this.averageCost = averageCost;
        this.lowestCost = lowestCost;
        this.highestCost = highestCost;
        this.averageSheepLost = averageSheepLost;
        this.averageLambLost = averageLambLost;
        this.averageAlpacaLost = averageAlpacaLost;
        this.averagePredatorKills = averagePredatorKills;
        this.mostTroublesomePredators = mostTroublesomePredators;
        this.zeroKillPredators = zeroKillPredators;
    }

    /**
     * Returns the average number of alpacas lost.
     *
     * @return The average number of alpacas lost.
     */
    public double getAverageAlpacaLost()
    {
        return this.averageAlpacaLost;
    }

    /**
     * Returns the average cost.
     *
     * @return The average total cost.
     */
    public double getAverageCost()
    {
        return this.averageCost;
    }

    /**
     * Returns the average number of lambs lost.
     *
     * @return The average number of lambs lost.
     */
    public double getAverageLambLost()
    {
        return this.averageLambLost;
    }

    /**
     * Returns the average number of sheep lost.
     *
     * @return The average number of sheep lost.
     */
    public double getAverageSheepLost()
    {
        return this.averageSheepLost;
    }

    /**
     * Returns the average predator kills map.
     *
     * @return The average predator kills.
     */
    public HashMap<String, Double> getAveragePredatorKills()
    {
        return this.averagePredatorKills;
    }

    /**
     * Returns the highest cost.
     *
     * @return The highest total cost.
     */
    public double getHighestCost()
    {
        return this.highestCost;
    }

    /**
     * Returns the lowest cost.
     *
     * @return The lowest total cost.
     */
    public double getLowestCost()
    {
        return this.lowestCost;
    }

    public ArrayList<String> getMostTroublesomePredators() 
    {
        return this.mostTroublesomePredators;
    }
    /**
     * Returns the protection level.
     *
     * @return The protection level name.
     */
    public String getProtectionLevel()
    {
        return this.protectionLevel;
    }

    /**
     * Returns the ArrayList of predators with zero kills.
     *
     * @return The ArrayList of predator names with zero kills.
     */
    public ArrayList<String> getZeroKillPredators()
    {
        return this.zeroKillPredators;
    }

    /**
     * Sets the average number of alpacas lost.
     *
     * @param averageAlpacaLost The average number of alpacas lost.
     */
    public void setAverageAlpacaLost(double averageAlpacaLost)
    {
        this.averageAlpacaLost = averageAlpacaLost;
    }

    /**
     * Sets the average cost.
     *
     * @param averageCost The average total cost.
     */
    public void setAverageCost(double averageCost)
    {
        this.averageCost = averageCost;
    }

    /**
     * Sets the average number of lambs lost.
     *
     * @param averageLambLost The average number of lambs lost.
     */
    public void setAverageLambLost(double averageLambLost)
    {
        this.averageLambLost = averageLambLost;
    }

    /**
     * Sets the average number of sheep lost.
     *
     * @param averageSheepLost The average number of sheep lost.
     */
    public void setAverageSheepLost(double averageSheepLost)
    {
        this.averageSheepLost = averageSheepLost;
    }

    /**
     * Sets the average predator kills map.
     *
     * @param averagePredatorKills The average predator kills.
     */
    public void setAveragePredatorKills(HashMap<String, Double> averagePredatorKills)
    {
        this.averagePredatorKills = averagePredatorKills;
    }

    /**
     * Sets the highest cost.
     *
     * @param highestCost The highest total cost.
     */
    public void setHighestCost(double highestCost)
    {
        this.highestCost = highestCost;
    }

    /**
     * Sets the lowest cost.
     *
     * @param lowestCost The lowest total cost.
     */
    public void setLowestCost(double lowestCost)
    {
        this.lowestCost = lowestCost;
    }

    public void setMostTroublesomePredators(ArrayList<String> mostTroublesomePredators) 
    {
        this.mostTroublesomePredators = mostTroublesomePredators;
    }

    public void setProtectionLevel(String protectionLevel)
    {
        this.protectionLevel = protectionLevel;
    }

    public void setZeroKillPredators(ArrayList<String> zeroKillPredators) 
    {
        this.zeroKillPredators = zeroKillPredators;
    }

}