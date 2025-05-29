import java.util.HashMap;
import java.util.ArrayList;

/**
 * Class which stores all statistics for a single protection level
 * in the alpaca sheep guard simulation program.
 *
 * @author Szeto Lok
 * @version 1.0.0
 */
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
        this.averagePredatorKills = new HashMap<String, Double>();
        this.mostTroublesomePredators = new ArrayList<String>();
        this.zeroKillPredators = new ArrayList<String>();
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
     * @param mostTroublesomePredators The most troublesome predators.
     * @param zeroKillPredators ArrayList of predator names with zero kills.
     */
    public ProtectionLevelResult(String protectionLevel, 
                                double averageCost, 
                                double lowestCost,
                                double highestCost, 
                                double averageSheepLost, 
                                double averageLambLost,
                                double averageAlpacaLost, 
                                HashMap<String, Double> averagePredatorKills,
                                ArrayList<String> mostTroublesomePredators, 
                                ArrayList<String> zeroKillPredators)
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
     * Display method to print the state of the FileIO object.
     * 
     */
    public void display()
    {
        System.out.println(this.toString());
    }

    /**
     * Accessor method to get the average number of alpacas lost.
     *
     * @return The average number of alpacas lost.
     */
    public double getAverageAlpacaLost()
    {
        return this.averageAlpacaLost;
    }

    /**
     * Accessor method to get the average cost.
     *
     * @return The average total cost.
     */
    public double getAverageCost()
    {
        return this.averageCost;
    }

    /**
     * Accessor method to get the average number of lambs lost.
     *
     * @return The average number of lambs lost.
     */
    public double getAverageLambLost()
    {
        return this.averageLambLost;
    }

    /**
     * Accessor method to get the average predator kills map.
     *
     * @return The average predator kills.
     */
    public HashMap<String, Double> getAveragePredatorKills()
    {
        return this.averagePredatorKills;
    }

    /**
     * Accessor method to get the average number of sheep lost.
     *
     * @return The average number of sheep lost.
     */
    public double getAverageSheepLost()
    {
        return this.averageSheepLost;
    }

    /**
     * Accessor method to get the highest cost.
     *
     * @return The highest total cost.
     */
    public double getHighestCost()
    {
        return this.highestCost;
    }

    /**
     * Accessor method to get the lowest cost.
     *
     * @return The lowest total cost.
     */
    public double getLowestCost()
    {
        return this.lowestCost;
    }

    /**
     * Accessor method to get the most troublesome predators.
     *
     * @return The ArrayList of most troublesome predator names.
     */
    public ArrayList<String> getMostTroublesomePredators()
    {
        return this.mostTroublesomePredators;
    }

    /**
     * Accessor method to get the protection level.
     *
     * @return The protection level name.
     */
    public String getProtectionLevel()
    {
        return this.protectionLevel;
    }

    /**
     * Accessor method to get the ArrayList of predators with zero kills.
     *
     * @return The ArrayList of predator names with zero kills.
     */
    public ArrayList<String> getZeroKillPredators()
    {
        return this.zeroKillPredators;
    }

    /**
     * Mutator method to set the average number of alpacas lost.
     *
     * @param averageAlpacaLost The average number of alpacas lost.
     */
    public void setAverageAlpacaLost(double averageAlpacaLost)
    {
        this.averageAlpacaLost = averageAlpacaLost;
    }

    /**
     * Mutator method to set the average cost.
     *
     * @param averageCost The average total cost.
     */
    public void setAverageCost(double averageCost)
    {
        this.averageCost = averageCost;
    }

    /**
     * Mutator method to set the average number of lambs lost.
     *
     * @param averageLambLost The average number of lambs lost.
     */
    public void setAverageLambLost(double averageLambLost)
    {
        this.averageLambLost = averageLambLost;
    }

    /**
     * Mutator method to set the average predator kills map.
     *
     * @param averagePredatorKills The average predator kills.
     */
    public void setAveragePredatorKills(
                    HashMap<String, Double> averagePredatorKills)
    {
        this.averagePredatorKills = averagePredatorKills;
    }

    /**
     * Mutator method to set the average number of sheep lost.
     *
     * @param averageSheepLost The average number of sheep lost.
     */
    public void setAverageSheepLost(double averageSheepLost)
    {
        this.averageSheepLost = averageSheepLost;
    }

    /**
     * Mutator method to set the highest cost.
     *
     * @param highestCost The highest total cost.
     */
    public void setHighestCost(double highestCost)
    {
        this.highestCost = highestCost;
    }

    /**
     * Mutator method to set the lowest cost.
     *
     * @param lowestCost The lowest total cost.
     */
    public void setLowestCost(double lowestCost)
    {
        this.lowestCost = lowestCost;
    }

    /**
     * Mutator method to set the most troublesome predators.
     *
     * @param mostTroublesomePredators The ArrayList of most 
     * troublesome predator names.
     */
    public void setMostTroublesomePredators(
                        ArrayList<String>  mostTroublesomePredators)
    {
        this.mostTroublesomePredators = mostTroublesomePredators;
    }

    /**
     * Mutator method to set the protection level.
     *
     * @param protectionLevel The protection level name.
     */
    public void setProtectionLevel(String protectionLevel)
    {
        this.protectionLevel = protectionLevel;
    }

    /**
     * Mutator method to set the ArrayList of predators with zero kills.
     *
     * @param zeroKillPredators The ArrayList of predator names with 
     * zero kills.
     */
    public void setZeroKillPredators(ArrayList<String> zeroKillPredators)
    {
        this.zeroKillPredators = zeroKillPredators;
    }

    /**
     * Returns a string representation of the ProtectionLevelResult object,
     * including protection level, costs, animal losses, and predator 
     * statistics.
     *
     * @return The state of the ProtectionLevelResult object as a 
     * formatted String.
     */
    @Override
    public String toString()
    {
        StringBuffer stringBuffer = new StringBuffer()
            .append("Protection Level: ")
            .append(this.protectionLevel)
            .append("\n")
            .append("Average Cost: $")
            .append(String.format("%.2f", this.averageCost))
            .append("\n")
            .append("Lowest Cost: $")
            .append(String.format("%.2f", this.lowestCost))
            .append("\n")
            .append("Highest Cost: $")
            .append(String.format("%.2f", this.highestCost))
            .append("\n")
            .append("Average Sheep Lost: ")
            .append(String.format("%.2f", this.averageSheepLost))
            .append("\n")
            .append("Average Lamb Lost: ")
            .append(String.format("%.2f", this.averageLambLost))
            .append("\n")
            .append("Average Alpaca Lost: ")
            .append(String.format("%.2f", this.averageAlpacaLost))
            .append("\n")
            .append("Average Predator Kills: ")
            .append(this.averagePredatorKills)
            .append("\n")
            .append("Most Troublesome Predators: ")
            .append(this.mostTroublesomePredators)
            .append("\n")
            .append("Zero Kill Predators: ")
            .append(this.zeroKillPredators)
            .append("\n");
        return stringBuffer.toString();
    }
}
