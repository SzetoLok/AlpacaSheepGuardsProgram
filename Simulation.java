import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Class which runs a single simulation round for a farm and protection level.
 * Calculates animal deaths, predator kills, and total cost (all as integers).
 *
 * @author Your Name
 * @version ver1.0.0
 */
public class Simulation {

    /** The farm being simulated. */
    private Farm farm;

    /** The protection level for this simulation (e.g., None, Single Alpaca, Pair of Alpacas). */
    private String protectionLevel;

    /** Map storing the number of each animal type alive after simulation. */
    private HashMap<String, Integer> animalAliveMap;

    /** Map storing the number of each animal type dead after simulation. */
    private HashMap<String, Integer> animalDeathMap;

    /** Map storing the number of kills by each predator after simulation. */
    private HashMap<String, Integer> predatorKillsMap;

    /**
     * Constructor which creates a Simulation2 object for a given farm and protection level.
     *
     * @param farm             The farm to simulate.
     * @param protectionLevel  The protection level for this simulation.
     */
    public Simulation(Farm farm, String protectionLevel) {
        this.farm = farm;
        this.protectionLevel = protectionLevel;
        this.animalAliveMap = new HashMap<>();
        this.animalDeathMap = new HashMap<>();
        this.predatorKillsMap = initializePredatorKills();
    }

    /**
     * Runs the simulation round and returns the result.
     *
     * @return  A SimulationResult object containing all results of this simulation.
     */
    public SimulationResult run() {
        int numberOfAlpacas = farm.getTotalAlpacas();
        processAnimalDeaths(farm.getSheeps(), numberOfAlpacas, "Sheep");
        processAnimalDeaths(farm.getLambs(), numberOfAlpacas, "Lamb");
        processAnimalDeaths(farm.getAlpacas(), numberOfAlpacas, "Alpaca");
        int totalCost = calculateTotalCost();
        return new SimulationResult(animalAliveMap, animalDeathMap, predatorKillsMap, protectionLevel, totalCost, farm);
    }

    /**
     * Initializes the predator kills map with all predators set to zero kills.
     *
     * @return  A HashMap with predator names as keys and zero as values.
     */
    public HashMap<String, Integer> initializePredatorKills() {
        HashMap<String, Integer> predatorKillsMap = new HashMap<>();
        Predator[] predatorArray = farm.getState().getPredators();
        for (Predator predator : predatorArray) {
            predatorKillsMap.put(predator.getName(), 0);
        }
        return predatorKillsMap;
    }

    public int processAnimalDeaths(List<? extends Animal> animals, 
                                    int numberOfAlpacas, 
                                    String animalType) 
    {
        int deaths = 0;
        Predator[] predators = farm.getState().getPredators();
        Random random = new Random();

        for (Animal animal : animals) {
            if (!animal.getAlive()) 
                continue;
            for (Predator predator : predators) 
            {
                double probability = animal.getDeathProbability(predator, numberOfAlpacas);
                if (random.nextDouble() < probability) 
                {
                    animal.die();
                    deaths++;
                    predatorKillsMap.put(predator.getName(), 
                        predatorKillsMap.get(predator.getName()) + 1);
                    break; // Animal only dies once per simulation
                }
            }
        }

        // Update statistics
        animalDeathMap.put(animalType, deaths);
        animalAliveMap.put(animalType, animals.size() - deaths);
        return deaths;
    }

    /**
     * Calculates the total cost of this simulation run, as an integer.
     * Includes stock lost, alpaca hiring cost, and alpaca maintenance cost.
     *
     * @return  The total cost as an int (whole dollars).
     */
    public int calculateTotalCost() {
        int numberOfSheepLost = this.animalDeathMap.getOrDefault("Sheep", 0);
        int numberOfLambsLost = this.animalDeathMap.getOrDefault("Lamb", 0);
        int numberOfAlpacasLost = this.animalDeathMap.getOrDefault("Alpaca", 0);

        int totalStockLostValue = numberOfSheepLost * 150 + numberOfLambsLost * 250 + numberOfAlpacasLost * 1000;
        int totalAlpacaHiringCost = farm.getAlpacas().size() * Alpaca.HIRE_COST;
        int totalAlpacaMaintenanceCost = 0;
        for (Alpaca alpaca : farm.getAlpacas()) {
            totalAlpacaMaintenanceCost += alpaca.getMaintenanceCost();
        }
        return totalStockLostValue + totalAlpacaHiringCost + totalAlpacaMaintenanceCost;
    }
}