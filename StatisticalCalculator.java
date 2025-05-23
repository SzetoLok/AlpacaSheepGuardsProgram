import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

public class StatisticalCalculator {
    public double calculateAverageAnimalLoss(ArrayList<SimulationResult> results, String animalType) {
    int totalLoss = 0;
    int count = 0;
    for (SimulationResult r : results) {
        totalLoss += r.getSpecificAnimalDeath(animalType);
        count++;
    }
    if (count == 0) return 0;
    return (double) totalLoss / count;
}

public double calculateLowestCost(ArrayList<SimulationResult> results) {
    double min = Double.MAX_VALUE;
    for (SimulationResult r : results) {
        if (r.getTotalCost() < min) min = r.getTotalCost();
    }
    return min;
}

public double calculateHighestCost(ArrayList<SimulationResult> results) {
    double max = 0;
    for (SimulationResult r : results) {
        if (r.getTotalCost() > max) max = r.getTotalCost();
    }
    return max;
}

public double calculateAverageCost(ArrayList<SimulationResult> results) {
    double sum = 0;
    int count = 0;
    for (SimulationResult r : results) {
        sum += r.getTotalCost();
        count++;
    }
    if (count == 0) return 0;
    return sum / count;
}

    public HashMap<String, Double> calculateAveragePredatorKills(ArrayList<SimulationResult> results) 
    {
        if (results.isEmpty()) return new HashMap<>();

        HashMap<String, Double> totalKills = new HashMap<>();
        for (SimulationResult result : results) 
        {
            HashMap<String, Integer> kills = result.getPredatorKills();

            for (HashMap.Entry<String, Integer> entry : kills.entrySet()) 
            {
                String predator = entry.getKey();
                int killCount = entry.getValue();
                totalKills.put(predator, (totalKills.containsKey(predator) ? totalKills.get(predator) : 0) + killCount);
            }
        }

        HashMap<String, Double> averageKills = new HashMap<>();
        for (HashMap.Entry<String, Double> entry : totalKills.entrySet()) 
        {
            averageKills.put(entry.getKey(), entry.getValue() / results.size());
        }
        return averageKills;
    }

}
