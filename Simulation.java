import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Class which runs a single simulation round for a farm and protection level.
 * Calculates animal deaths, predator kills, and total cost (all as integers).
 *
 * @author Szeto Lok
 * @version ver1.0.0
 */
public class Simulation
{

    private Farm farm;

    private String protectionLevel;

    private HashMap<String, Integer> animalAliveMap;

    private HashMap<String, Integer> animalDeathMap;

    private HashMap<String, Integer> predatorKillsMap;

    /**
     * Default constructor which creates a simulation with default values.
     * 
     */
    public Simulation()
    {
        this.farm = new Farm();
        this.protectionLevel = "Unknown";
        this.animalAliveMap = new HashMap<String, Integer>();
        this.animalDeathMap = new HashMap<String, Integer>();
        this.predatorKillsMap = new HashMap<String, Integer>();
    }

    /**
     * Non-default constructor which creates a Simulation object 
     * for a given farm and protection level.
     *
     * @param baseFarm The farm to simulate.
     * @param protectionLevel The protection level for this simulation.
     */
    public Simulation(Farm baseFarm, String protectionLevel)
    {
        this.farm = baseFarm.copyFarm();
        this.protectionLevel = protectionLevel;
        this.setupAlpacaProtection();
        this.animalAliveMap = new HashMap<String, Integer>();
        this.animalDeathMap = new HashMap<String, Integer>();
        this.predatorKillsMap = initializePredatorKills();
    }

    /**
     * Non-default constructor which creates a Simulation object 
     * with all fields specified.
     *
     * @param farm The farm to simulate.
     * @param protectionLevel The protection level for this simulation.
     * @param animalAliveMap The map of animals alive.
     * @param animalDeathMap The map of animals dead.
     * @param predatorKillsMap The map of predator kills.
     */
    public Simulation(Farm farm,
                     String protectionLevel,
                     HashMap<String, Integer> animalAliveMap,
                     HashMap<String, Integer> animalDeathMap,
                     HashMap<String, Integer> predatorKillsMap)
    {
        this.farm = farm.copyFarm();
        this.protectionLevel = protectionLevel;
        this.setupAlpacaProtection();
        this.animalAliveMap = animalAliveMap;
        this.animalDeathMap = animalDeathMap;
        this.predatorKillsMap = predatorKillsMap;
    }

    /**
     * Calculates the total cost of this simulation run, as an integer.
     * Includes stock lost, alpaca hiring cost, and alpaca maintenance cost.
     *
     * @return The total cost as an int (whole dollars).
     */
    public int calculateTotalCost()
    {
        int numberOfSheepLost = this.animalDeathMap
                                .getOrDefault("Sheep", 0);

        int numberOfLambsLost = this.animalDeathMap
                                .getOrDefault("Lamb", 0);

        int numberOfAlpacasLost = this.animalDeathMap
                                .getOrDefault("Alpaca", 0);

        int totalStockLostValue = numberOfSheepLost * 150
                               + numberOfLambsLost * 250
                               + numberOfAlpacasLost * 1000;

        int totalAlpacaHiringCost = farm.getAlpacas().size() 
                                    * Alpaca.HIRE_COST;
        int totalAlpacaMaintenanceCost = farm.getTotalAlpacaMaintenanceCost();

        return totalStockLostValue 
            + totalAlpacaHiringCost
            + totalAlpacaMaintenanceCost;
    }

    /**
     * Display method to print the state of the simulation.
     */
    public void display()
    {
        System.out.println(this.toString());
    }

    /**
     * Accessor method to get the map of animals alive after simulation.
     *
     * @return A HashMap mapping animal types to the number alive.
     */
    public HashMap<String, Integer> getAnimalAliveMap()
    {
        return this.animalAliveMap;
    }

    /**
     * Accessor method to get the map of animals dead after simulation.
     *
     * @return A HashMap mapping animal types to the number dead.
     */
    public HashMap<String, Integer> getAnimalDeathMap()
    {
        return this.animalDeathMap;
    }

    /**
     * Accessor method to get the farm used in this simulation.
     *
     * @return The Farm object.
     */
    public Farm getFarm()
    {
        return this.farm;
    }

    /**
     * Accessor method to get the number of alpacas for the current 
     * protection level.
     *
     * @return The number of alpacas as an int.
     */
    public int getAlpacaCountForLevel()
    {
        switch (this.protectionLevel)
        {
            case "0 alpaca":
                return 0;
            case "1 alpaca":
                return 1;
            case "2 alpacas":
                return 2;
            default:
                return 0;
        }
    }

    /**
     * Accessor method to get the map of predator kills after simulation.
     *
     * @return A HashMap mapping predator names to the number of kills.
     */
    public HashMap<String, Integer> getPredatorKillsMap()
    {
        return this.predatorKillsMap;
    }

    /**
     * Accessor method to get the protection level for this simulation.
     *
     * @return The protection level as a String.
     */
    public String getProtectionLevel()
    {
        return this.protectionLevel;
    }

    /**
     * Initializes the predator kills map with all predators set to 
     * zero kills.
     *
     * @return A HashMap with predator names as keys and zero as values.
     */
    public HashMap<String, Integer> initializePredatorKills()
    {
        HashMap<String, Integer> predatorKillsMap = new HashMap<>();
        String[] predatorsNames = farm.getPredatorsNames();
        for (String name : predatorsNames)
        {
            predatorKillsMap.put(name, 0);
        }
        return predatorKillsMap;
    }

    /**
     * Processes deaths for a list of animals, updating death and 
     * alive counts and predator kills.
     *
     * @param animals List of animals to process.
     * @param numberOfAlpacas Number of alpacas protecting the farm.
     * @param animalType The type of animal (e.g., "Sheep", "Lamb", "Alpaca").
     */
    public void processAnimalDeaths(ArrayList<? extends Animal> animals,
                                   int numberOfAlpacas,
                                   String animalType)
    {
        int deaths = 0;
        Random random = new Random();
        HashMap<String, Double> predatorsNamesAndDangerFactor =
            this.getFarm().getPredatorsNamesAndDangerFactors();

        for (Animal animal : animals)
        {
            if (!animal.getAlive())
            {
                continue;
            }
            for (HashMap.Entry<String, Double> entry : 
                predatorsNamesAndDangerFactor.entrySet())
            {
                String predatorName = entry.getKey();
                Double dangerFactor = entry.getValue();
                double probability = animal.getDeathProbability(dangerFactor,
                                                             numberOfAlpacas);
                if (random.nextDouble() < probability)
                {
                    animal.die();
                    deaths++;
                    predatorKillsMap.put(predatorName, 
                                    predatorKillsMap.get(predatorName) + 1);
                    break; // Animal only dies once per simulation
                }
            }
        }
        animalDeathMap.put(animalType, deaths);
        animalAliveMap.put(animalType, animals.size() - deaths);
    }

    /**
     * Mutator method to set the map of animals alive after simulation.
     *
     * @param animalAliveMap The HashMap to set.
     */
    public void setAnimalAliveMap(HashMap<String, Integer> animalAliveMap)
    {
        this.animalAliveMap = animalAliveMap;
    }

    /**
     * Mutator method to set the map of animals dead after simulation.
     *
     * @param animalDeathMap The HashMap to set.
     */
    public void setAnimalDeathMap(HashMap<String, Integer> animalDeathMap)
    {
        this.animalDeathMap = animalDeathMap;
    }

    /**
     * Mutator method to set the farm used in this simulation.
     *
     * @param farm The Farm object to set.
     */
    public void setFarm(Farm farm)
    {
        this.farm = farm;
    }

    /**
     * Mutator method to set the map of predator kills after simulation.
     *
     * @param predatorKillsMap The HashMap to set.
     */
    public void setPredatorKillsMap(HashMap<String, Integer> predatorKillsMap)
    {
        this.predatorKillsMap = predatorKillsMap;
    }

    /**
     * Mutator method to set the protection level for this simulation.
     *
     * @param protectionLevel The protection level as a String.
     */
    public void setProtectionLevel(String protectionLevel)
    {
        this.protectionLevel = protectionLevel;
    }

    /**
     * Runs the simulation round and returns the result.
     *
     * @return A SimulationResult object containing all results of 
     * this simulation.
     */
    public SimulationResult run()
    {
        int numberOfAlpacas = farm.getTotalAlpacas();
        processAnimalDeaths(farm.getSheeps(), numberOfAlpacas, "Sheep");
        processAnimalDeaths(farm.getLambs(), numberOfAlpacas, "Lamb");
        processAnimalDeaths(farm.getAlpacas(), numberOfAlpacas, "Alpaca");
        int totalCost = calculateTotalCost();
        return new SimulationResult(animalAliveMap, 
                                    animalDeathMap, 
                                    predatorKillsMap,
                                    protectionLevel, 
                                    totalCost, 
                                    farm);
    }

    /**
     * Adds the required number of alpacas for the protection level to the farm.
     */
    public void setupAlpacaProtection()
    {
        int alpacaCount = this.getAlpacaCountForLevel();
        for (int index = 0; index < alpacaCount; index++)
        {
            farm.addAlpacaOnce();
        }
    }

    /**
     * Returns a string representation of the simulation, including the protection level,
     * the number of animals alive and dead, predator kills, and the total cost.
     *
     * @return The state of the simulation as a formatted String.
     */
    @Override
    public String toString()
    {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Simulation for Protection Level: ");
        stringBuffer.append(this.protectionLevel);
        stringBuffer.append("\nAnimals Alive: ");
        stringBuffer.append(animalAliveMap);
        stringBuffer.append("\nAnimals Dead: ");
        stringBuffer.append(animalDeathMap);
        stringBuffer.append("\nPredator Kills: ");
        stringBuffer.append(predatorKillsMap);
        stringBuffer.append("\nTotal Cost: $");
        stringBuffer.append(this.calculateTotalCost());
        stringBuffer.append("\n");
        return stringBuffer.toString();
    }
}
