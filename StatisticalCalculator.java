import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class which provides statistical calculation methods for simulation results,
 * including averages, minimum, and maximum for costs and animal losses.
 *
 * @author Szeto Lok
 * @version ver1.0.0
 */
public class StatisticalCalculator
{
    /**
     * Default constructor for the StatisticalCalculator class.
     */
    public StatisticalCalculator()
    {

    }

    /**
     * Calculates the average animal loss for a given animal type 
     * from simulation results.
     *
     * @param results The ArrayList of SimulationResult objects.
     * @param animalType The type of animal ("Sheep", "Lamb", "Alpaca").
     * @return The average number of animals lost as a double.
     */
    public double calculateAverageAnimalLoss(
                                        ArrayList<SimulationResult> results, 
                                        String animalType)
    {
        int totalLoss = 0;
        int count = 0;
        for (SimulationResult result : results)
        {
            totalLoss += result.getSpecificAnimalDeath(animalType);
            count++;
        }
        if (count == 0)
        {
            return 0;
        }
        return (double) totalLoss / count;
    }

    /**
     * Calculates the average cost from a list of SimulationResult objects.
     *
     * @param results The ArrayList of SimulationResult objects.
     * @return The average cost as a double.
     */
    public double calculateAverageCost(ArrayList<SimulationResult> results)
    {
        double totalCost = 0;
        int count = 0;
        for (SimulationResult result : results)
        {
            totalCost += result.getTotalCost();
            count++;
        }
        if (count == 0)
        {
            return 0;
        }
        return totalCost / count;
    }

    /**
     * Calculates the average number of kills for each predator 
     * across all simulations.
     *
     * @param results The ArrayList of SimulationResult objects.
     * @return A HashMap mapping predator names to their average kills as Double.
     */
    public HashMap<String, Double> calculateAveragePredatorKills(
                                        ArrayList<SimulationResult> results)
    {
        if (results.isEmpty())
        {
            return new HashMap<String, Double>();
        }

        HashMap<String, Double> totalKills = new HashMap<String, Double>();
        for (SimulationResult result : results)
        {
            HashMap<String, Integer> kills = result.getPredatorKills();
            for (HashMap.Entry<String, Integer> entry : kills.entrySet())
            {
                String predatorName = entry.getKey();
                int killCount = entry.getValue();

                double previousTotal;
                if (totalKills.containsKey(predatorName))
                {
                    previousTotal = totalKills.get(predatorName);
                }
                else
                {
                    previousTotal = 0.0;
                }

                double newTotal = previousTotal + killCount;
                totalKills.put(predatorName, newTotal);
            }
        }

        HashMap<String, Double> averageKills = new HashMap<String, Double>();
        for (HashMap.Entry<String, Double> entry : totalKills.entrySet())
        {
            averageKills.put(entry.getKey(), 
                            entry.getValue() / results.size());
        }
        return averageKills;
    }

    /**
     * Calculates the highest cost from a list of SimulationResult objects.
     *
     * @param results The ArrayList of SimulationResult objects.
     * @return The highest cost as a double.
     */
    public double calculateHighestCost(ArrayList<SimulationResult> results)
    {
        double maximum = Double.NEGATIVE_INFINITY;
        for (SimulationResult result : results)
        {
            if (result.getTotalCost() > maximum)
            {
                maximum = result.getTotalCost();
            }
        }
        if (results.isEmpty())
        {
            return 0;
        }
        return maximum;
    }

    /**
     * Calculates the lowest cost from a list of SimulationResult objects.
     *
     * @param results The ArrayList of SimulationResult objects.
     * @return The lowest cost as a double.
     */
    public double calculateLowestCost(ArrayList<SimulationResult> results)
    {
        double minimum = Double.POSITIVE_INFINITY;
        for (SimulationResult result : results)
        {
            if (result.getTotalCost() < minimum)
            {
                minimum = result.getTotalCost();
            }
        }
        if (results.isEmpty())
        {
            return 0;
        }
        return minimum;
    }
}
