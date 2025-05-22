import java.util.ArrayList;
import java.util.HashMap;

public class AlpacaSheepGuards
{
    private static final int NUM_SIMULATIONS = 10;
    private static final String[] PROTECTION_LEVELS = {"0 alpaca", "1 alpaca", "2 alpacas"};

    // To store all simulation results
    private HashMap<String, ArrayList<SimulationResult>> allSimulationResults;    
    // Farm object
    private Farm farm;
    private HashMap<String, ProtectionLevelResult> allProtectionLevelResults;

    // Default constructor
    public AlpacaSheepGuards() 
    {
    this.farm = new Farm();
    this.allSimulationResults = new HashMap<String, ArrayList<SimulationResult>>();
    this.allProtectionLevelResults = new HashMap<>();
    }
// Non-default constructor
    public AlpacaSheepGuards(Farm farm, 
                            HashMap<String, ArrayList<SimulationResult>> allResults, 
                            HashMap<String, ProtectionLevelResult> allProtectionLevelResults) 
    {
    this.farm = farm;
    this.allSimulationResults = allResults;
    this.allProtectionLevelResults = allProtectionLevelResults;
    }
    public static void main(String[] args)
    {
        AlpacaSheepGuards program = new AlpacaSheepGuards();
        program.run();
    }

    public void initialize() 
    {
        // Load states from file
        FileIO fileIO = new FileIO("predators.txt");
        ArrayList<State> states = fileIO.readFile();
        if (states == null || states.isEmpty()) 
        {
            System.out.println("Error loading predators.txt");
            System.exit(1);
        }
        
        // Initialize farm using the setup helper
        FarmSetupHelper farmSetupHelper = new FarmSetupHelper();
        farm = farmSetupHelper.initializeFarm(states);
    
    }

  
    public void setAlpacas(Farm farm, int alpacaCount) 
    {
        ArrayList<Alpaca> alpacas = new ArrayList<>();
        for (int i = 0; i < alpacaCount; i++) 
        {
            alpacas.add(new Alpaca());
        }
        farm.setAlpacas(alpacas);
    }
    
    public void printSimulationResultsEachLevel(String protectionLevel) 
    {
        ArrayList<SimulationResult> resultsOfLevel = this.allSimulationResults.get(protectionLevel);
        System.out.println("Protection Level: " + protectionLevel);
            resultsOfLevel.forEach(protectionLevelResult -> 
            {
                System.out.println(protectionLevelResult.getTotalCost());
                System.out.println("  " + protectionLevelResult.toString());
                System.out.println("  Alpaca Maintenance Costs:");
                protectionLevelResult.farm.getAlpacas().forEach(alpaca -> System.out.println("    Alpaca " + alpaca.getName() + ": $" + alpaca.getMaintenanceCost()));
            });
            System.out.println();
    }
    // public void printAllSimulationResults() {
    //     allSimulationResults.forEach((protectionLevel, results) -> {
    //         System.out.println("Protection Level: " + protectionLevel);
    //         results.forEach(protectionLevelResult -> {
    //             System.out.println("  " + protectionLevelResult.toString());
    //             System.out.println("  Alpaca Maintenance Costs:");
    //             protectionLevelResult.farm.getAlpacas().forEach(alpaca -> System.out.println("    Alpaca " + alpaca.getName() + ": $" + alpaca.getMaintenanceCost()));
    //         });
    //         System.out.println();
    //     });
    // }

    public void run()
    {
        welcomeUser();
        initialize();
        runAllSimulations();
        calculateProtectionLevelResults(this.farm.getState().getPredators());
        displayLevels(this.farm.getState().getPredators());
        String recommendedLevel = findRecommendedLevel();
        displayFinalRecommendation(recommendedLevel);
        generateFileReport(recommendedLevel);
    }

    private void runAllSimulations() 
    {
        for (String protectionLevel : PROTECTION_LEVELS) {
            int alpacaCount = getAlpacaCountForLevel(protectionLevel);
            ArrayList<SimulationResult> resultsOfEachLevel = runSimulationsForEachLevel(protectionLevel, alpacaCount);
            storeResultsForEachLevel(protectionLevel, resultsOfEachLevel);
            // printSimulationResultsEachLevel(protectionLevel);
        }
    }
    
    // Helper methods below
    // ====================
    
    /**
     * Converts protection level name to the number of alpacas (0, 1, or 2).
     */
    private int getAlpacaCountForLevel(String protectionLevel) 
    {
        switch (protectionLevel) 
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
    
    public void generateFileReport(String recommendedLevel)
    {
        ProtectionLevelResult result = allProtectionLevelResults.get(recommendedLevel);
        double lowestAvgCost = result.getAverageCost();
        int alpacaCount = getAlpacaCountForLevel(recommendedLevel);
    
        StringBuilder sb = new StringBuilder();
        sb.append("Farm name: ").append(farm.getFarmName()).append("\n");
        sb.append("Number of sheep: ").append(farm.getTotalSheeps()).append("\n");
        sb.append("Number of lambs: ").append(farm.getTotalLambs()).append("\n");
        sb.append("Recommended level of protection: Protection: ")
          .append(alpacaCount).append(" alpaca").append(alpacaCount != 1 ? "s" : "").append("\n");
        sb.append(String.format("Protection cost: $%.2f%n", lowestAvgCost));
        double totalLost = result.getAverageSheepLost() + result.getAverageLambLost() + result.getAverageAlpacaLost();
        sb.append(String.format("Average predicted number of animal lost: %.2f%n", totalLost));
        sb.append("Most troublesome predator:\n");
        for (String predator : result.getMostTroublesomePredators())
        {
            double kills = result.getAveragePredatorKills().get(predator);
            sb.append(String.format("    %s, kill average: %.1f%n", predator, kills));
        }
    
        // Use FileIO to write the file
        FileIO fileIO = new FileIO("alpacaSheepGuardViability.txt");
        fileIO.writeFile(sb.toString());
    }

    /**
     * Runs 10 simulations for a specific protection level and returns the results.
     */
    private ArrayList<SimulationResult> runSimulationsForEachLevel(String level, int alpacaCount) {
        ArrayList<SimulationResult> results = new ArrayList<>();
        for (int i = 0; i < NUM_SIMULATIONS; i++) 
        {
            Farm simFarm = createFarmCopy();
            setupAlpacaProtection(simFarm, alpacaCount);
            SimulationResult protectionLevelResult = runSimulationOnce(simFarm, level);
            results.add(protectionLevelResult);
        }
        return results;
    }
    
    /**
     * Creates a fresh copy of the base farm with the same animals.
     */
    private Farm createFarmCopy() 
    {
        Farm copy = new Farm(farm.getFarmName(), farm.getState());
        // Copy sheep
        for (int i = 0; i < farm.getTotalSheeps(); i++) {
            copy.addSheepOnce(new Sheep());
        }
        // Copy lambs
        for (int i = 0; i < farm.getTotalLambs(); i++) {
            copy.addLambOnce(new Lamb());
        }
        return copy;
    }
    
    /**
     * Adds alpacas to the farm based on the protection level.
     */
    private void setupAlpacaProtection(Farm farm, int alpacaCount) {
        ArrayList<Alpaca> alpacas = new ArrayList<>();
        for (int i = 0; i < alpacaCount; i++) {
            alpacas.add(new Alpaca());
        }
        farm.setAlpacas(alpacas);
    }
    
    /**
     * Runs a single simulation round for a farm and protection level.
     */
    private SimulationResult runSimulationOnce(Farm farm, String protectionLevel) {
        Simulation sim = new Simulation(farm, protectionLevel);
        return sim.run();
    }
    
    /**
     * Stores the simulation results for a protection level.
     */
    private void storeResultsForEachLevel(String protectionLevel, ArrayList<SimulationResult> results) 
    {
        allSimulationResults.put(protectionLevel, results);
    }
    
        /**
     * Displays all statistics for a protection level using the ProtectionLevelResult object.
     *
     * @param protectionLevelResult The ProtectionLevelResult containing all statistics.
     * @param predators             The array of Predator objects for display order and names.
     */
    public void displayLevels(Predator[] predators)
    {
        for (String level : PROTECTION_LEVELS) 
        {
            ProtectionLevelResult protectionLevelResult = allProtectionLevelResults.get(level);
            System.out.println("\n=== " + protectionLevelResult.getProtectionLevel() + " ===");
            System.out.printf("Average Total Cost: $%.2f%n", protectionLevelResult.getAverageCost());
            System.out.printf("Lowest Total Cost: $%.2f%n", protectionLevelResult.getLowestCost());
            System.out.printf("Highest Total Cost: $%.2f%n", protectionLevelResult.getHighestCost());

            System.out.println("\nAverage Animals Lost:");
            System.out.printf("  Sheep:   %.2f%n", protectionLevelResult.getAverageSheepLost());
            System.out.printf("  Lambs:   %.2f%n", protectionLevelResult.getAverageLambLost());
            System.out.printf("  Alpacas: %.2f%n", protectionLevelResult.getAverageAlpacaLost());

            System.out.println("\nAverage Predator Kills:");
            HashMap<String, Double> avgKills = protectionLevelResult.getAveragePredatorKills();
            for (Predator predator : predators)
            {
                String name = predator.getName();
                String displayName = name;
                if (name.equals("Feral Pig"))
                {
                    displayName = "Pig";
                }
                else if (name.equals("Wedge-tailed Eagle"))
                {
                    displayName = "Eagle";
                }
                System.out.printf("  %-12s: %.2f%n", displayName, avgKills.getOrDefault(name, 0.0));
            }

            // System.out.println("\nMost Troublesome Predators:");
            // ArrayList<String> mostTroublesome = protectionLevelResult.getMostTroublesomePredators();
            // for (String predator : mostTroublesome)
            // {
            //     System.out.println("  - " + predator);
            // }

            // ArrayList<String> zeroKillers = protectionLevelResult.getZeroKillPredators();
            // if (!zeroKillers.isEmpty())
            // {
            //     System.out.println("Predators with Zero Kills:");
            //     for (String predator : zeroKillers)
            //     {
            //         System.out.println("  - " + predator);
            //     }
            // }
        }
    }

    ////////Needs redo
    public void displayFinalRecommendation(String recommendedLevel)
    {
        ProtectionLevelResult result = allProtectionLevelResults.get(recommendedLevel);
        double lowestAvgCost = result.getAverageCost();
        int alpacaCount = getAlpacaCountForLevel(recommendedLevel);

        System.out.println("\n=============================");
        System.out.println("End of Simulation Report");
        System.out.println("=============================");
        System.out.printf("Lowest average cost: $%.2f%n", lowestAvgCost);
        System.out.println("Protection: " + alpacaCount + " alpaca" + (alpacaCount != 1 ? "s" : ""));

        System.out.println("    Troublesome predator:");
        for (String predator : result.getMostTroublesomePredators())
        {
            double kills = result.getAveragePredatorKills().get(predator);
            System.out.printf("        %s, kill average: %.1f%n", predator, kills);
        }
        ArrayList<String> zeroKillers = result.getZeroKillPredators();
        if (zeroKillers.isEmpty())
        {
            System.out.println("    All predators killed at least one animal");
        }
        else
        {
            System.out.println("    " + zeroKillers.size() + " predators that had no kill:");
            System.out.println("        " + String.join(", ", zeroKillers));
        }
        System.out.println("\nA report has been written to: alpacaSheepGuardViability.txt");
        System.out.println("Goodbye");
    }
    

    public void calculateProtectionLevelResults(Predator[] predators)
    {
        for (String protectionLevel : PROTECTION_LEVELS)
        {
            calculateProtectionLevelResultOnce(protectionLevel, allSimulationResults.get(protectionLevel), predators);
        }
    }

    public void calculateProtectionLevelResultOnce(String protectionLevel, ArrayList<SimulationResult> results, Predator[] predators)
    {
        StatisticalCalculator stats = new StatisticalCalculator();
        ProtectionLevelResult protectionLevelResult = new ProtectionLevelResult();

        protectionLevelResult.setProtectionLevel(protectionLevel);
        protectionLevelResult.setAverageCost(stats.calculateAverageCost(results));
        protectionLevelResult.setLowestCost(stats.calculateLowestCost(results));
        protectionLevelResult.setHighestCost(stats.calculateHighestCost(results));
        protectionLevelResult.setAverageSheepLost(stats.calculateAverageAnimalLoss(results, "Sheep"));
        protectionLevelResult.setAverageLambLost(stats.calculateAverageAnimalLoss(results, "Lamb"));
        protectionLevelResult.setAverageAlpacaLost(stats.calculateAverageAnimalLoss(results, "Alpaca"));

        // Calculate average predator kills
        HashMap<String, Double> avgKills = stats.calculateAveragePredatorKills(results);
        protectionLevelResult.setAveragePredatorKills(avgKills);

        // Find most troublesome predator
        protectionLevelResult.setMostTroublesomePredators(findMostTroublesomePredators(avgKills));

        // Find zero-kill predators
        protectionLevelResult.setZeroKillPredators(findZeroKillPredators(avgKills, predators));

        allProtectionLevelResults.put(protectionLevel, protectionLevelResult);
    }

    /**
     * Finds the predator with the highest average kills.
     */
    public ArrayList<String> findMostTroublesomePredators(HashMap<String, Double> averagePredatorKills) 
    {
        ArrayList<String> predators = new ArrayList<>();
        double maxKills = -1.0;
    
        for (HashMap.Entry<String, Double> entry : averagePredatorKills.entrySet()) 
        {
            double kills = entry.getValue();
            String predator = entry.getKey();
    
            if (kills > maxKills) 
            {
                maxKills = kills;
                predators.clear();  // Reset list for new max
                predators.add(predator);
            } 
            else if (kills == maxKills) 
            {
                predators.add(predator);  // Add tied predators
            }
        }
        return predators;
    }

/**
 * Finds predators with zero average kills from the given statistics.
 * 
 * @param averagePredatorKills Map of predator names to their average kills
 * @param predators Array of Predator objects to check
 * @return ArrayList of predator names with zero kills
 */
    public ArrayList<String> findZeroKillPredators(HashMap<String, Double> averagePredatorKills, 
                                                    Predator[] predators) 
    {
        ArrayList<String> zeroKillers = new ArrayList<>();
        for (Predator predator : predators) 
        {
            String name = predator.getName();
            if (averagePredatorKills.getOrDefault(name, 0.0) == 0.0) 
            {
                zeroKillers.add(name);
            }
        }
        return zeroKillers;
    }

    public String findRecommendedLevel() 
    {
        String recommendedLevel = "";
        double lowestAverageCost = Double.MAX_VALUE;
        
        for (String level : allProtectionLevelResults.keySet()) 
        {
            double avgerageCost = allProtectionLevelResults.get(level).getAverageCost();

            if (avgerageCost < lowestAverageCost) 
            {
                lowestAverageCost = avgerageCost;
                recommendedLevel = level;
            }
        }
        return recommendedLevel;
    }

    public void welcomeUser()
    {
        System.out.println("Welcome to the Alpaca Sheep Guards Program");
    }
}
