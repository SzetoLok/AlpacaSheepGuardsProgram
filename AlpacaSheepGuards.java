import java.util.ArrayList;
import java.util.HashMap;

public class AlpacaSheepGuards
{

    private static final int NUM_SIMULATIONS = 10;

    private static final String[] PROTECTION_LEVELS = {"0 alpaca", "1 alpaca", "2 alpacas"};

    private HashMap<String, ArrayList<SimulationResult>> allSimulationResults;

    private Farm farm;

    private HashMap<String, ProtectionLevelResult> allProtectionLevelResults;

    // Default constructor
    public AlpacaSheepGuards()
    {
        this.farm = new Farm();
        this.allSimulationResults = new HashMap<String, ArrayList<SimulationResult>>();
        this.allProtectionLevelResults = new HashMap<String, ProtectionLevelResult>();
    }

    // Non-default constructor
    public AlpacaSheepGuards(Farm farm, HashMap<String, ArrayList<SimulationResult>> allResults,
                             HashMap<String, ProtectionLevelResult> allProtectionLevelResults)
    {
        this.farm = farm;
        this.allSimulationResults = allResults;
        this.allProtectionLevelResults = allProtectionLevelResults;
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

        HashMap<String, Double> avgKills = stats.calculateAveragePredatorKills(results);
        protectionLevelResult.setAveragePredatorKills(avgKills);
        protectionLevelResult.setMostTroublesomePredators(findMostTroublesomePredators(avgKills));
        protectionLevelResult.setZeroKillPredators(findZeroKillPredators(avgKills, predators));
        allProtectionLevelResults.put(protectionLevel, protectionLevelResult);
    }

    public void calculateProtectionLevelResults(Predator[] predators)
    {
        for (String protectionLevel : PROTECTION_LEVELS)
        {
            calculateProtectionLevelResultOnce(protectionLevel, allSimulationResults.get(protectionLevel), predators);
        }
    }

    public void displayEachLevel(ProtectionLevelResult protectionLevelResult, Predator[] predators)
    {
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
        for (HashMap.Entry<String, Double> entry : avgKills.entrySet())
        {
            String name = entry.getKey();
            if (name.equals("Feral Pig"))
            {
                name = "Pig";
            }
            else if (name.equals("Wedge-tailed Eagle"))
            {
                name = "Eagle";
            }
            System.out.printf("  %-12s: %.2f%n", name, entry.getValue());
        }

        System.out.println("\nMost Troublesome Predators:");
        ArrayList<String> mostTroublesome = protectionLevelResult.getMostTroublesomePredators();
        for (String predator : mostTroublesome)
        {
            System.out.println("  - " + predator);
        }

        ArrayList<String> zeroKillers = protectionLevelResult.getZeroKillPredators();
        if (!zeroKillers.isEmpty())
        {
            System.out.println("Predators with Zero Kills:");
            for (String predator : zeroKillers)
            {
                System.out.println("  - " + predator);
            }
        }
    }

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

    public void displayLevels()
    {
        for (String level : PROTECTION_LEVELS)
        {
            ProtectionLevelResult protectionLevelResult = allProtectionLevelResults.get(level);
            displayEachLevel(protectionLevelResult, farm.getState().getPredators());
        }
    }

    public String findRecommendedLevel()
    {
        String recommendedLevel = "";
        double lowestAverageCost = Double.MAX_VALUE;
        for (String level : allProtectionLevelResults.keySet())
        {
            double averageCost = allProtectionLevelResults.get(level).getAverageCost();
            if (averageCost < lowestAverageCost)
            {
                lowestAverageCost = averageCost;
                recommendedLevel = level;
            }
        }
        return recommendedLevel;
    }

    public ArrayList<String> findMostTroublesomePredators(HashMap<String, Double> averagePredatorKills)
    {
        ArrayList<String> predators = new ArrayList<String>();
        double maxKills = -1.0;
        for (HashMap.Entry<String, Double> entry : averagePredatorKills.entrySet())
        {
            double kills = entry.getValue();
            String predator = entry.getKey();
            if (kills > maxKills)
            {
                maxKills = kills;
                predators.clear();
                predators.add(predator);
            }
            else if (kills == maxKills)
            {
                predators.add(predator);
            }
        }
        return predators;
    }

    public ArrayList<String> findZeroKillPredators(HashMap<String, Double> averagePredatorKills, Predator[] predators)
    {
        ArrayList<String> zeroKillers = new ArrayList<String>();
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

    public Farm getFarm()
    {
        return this.farm;
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
            sb.append(String.format(" %s, kill average: %.1f%n", predator, kills));
        }

        FileIO fileIO = new FileIO("alpacaSheepGuardViability.txt");
        fileIO.writeFile(sb.toString());
    }

    // public void initialize()
    // {
    //     FileIO fileIO = new FileIO("predators.txt");
    //     ArrayList<String[]> statesData = fileIO.readFile();
        
    //     if (statesData.isEmpty())
    //     {
    //         System.out.println("Error loading predators.txt");
    //         return;
    //     }
        
    //     FarmSetupHelper setupHelper = new FarmSetupHelper();
    //     farm = setupHelper.initializeFarm(statesData);
    // }

    /**
     * Initializes the program by loading predator data and setting up the farm.
     */
    public void initialize()
    {
        FileIO fileIO = new FileIO("predators.txt");
        ArrayList<String[]> statesData = fileIO.readFile();
        
        if (statesData.isEmpty())
        {
            System.out.println("Error loading predators.txt");
            return;
        }
        
        String farmName = requestFarmName();
        String[] selectedStateData = requestState(statesData);
        int[] animalCounts = requestSheepAndLamb();
        
        farm.setFarmName(farmName);
        farm.initializeState(selectedStateData);
        
        for (int i = 0; i < animalCounts[0]; i++)
        {
            farm.addSheepOnce();
        }
        for (int i = 0; i < animalCounts[1]; i++)
        {
            farm.addLambOnce();
        }

        System.out.println(farm);
    }

    private Farm createFarmCopy()
    {
        Farm copy = new Farm(farm.getFarmName(), farm.getState());
        for (int i = 0; i < farm.getTotalSheeps(); i++)
        {
            copy.addSheepOnce();
        }
        for (int i = 0; i < farm.getTotalLambs(); i++)
        {
            copy.addLambOnce();
        }
        return copy;
    }

    private void runAllSimulations()
    {
        for (String protectionLevel : PROTECTION_LEVELS)
        {
            int alpacaCount = getAlpacaCountForLevel(protectionLevel);
            ArrayList<SimulationResult> resultsOfEachLevel = runSimulationsForEachLevel(protectionLevel, alpacaCount);
            storeResultsForEachLevel(protectionLevel, resultsOfEachLevel);
            printSimulationResultsEachLevel(protectionLevel);
        }
    }

    private ArrayList<SimulationResult> runSimulationsForEachLevel(String level, int alpacaCount)
    {
        ArrayList<SimulationResult> results = new ArrayList<SimulationResult>();
        for (int i = 0; i < NUM_SIMULATIONS; i++)
        {
            Farm simFarm = createFarmCopy();
            setupAlpacaProtection(simFarm, alpacaCount);
            SimulationResult protectionLevelResult = runSimulationOnce(simFarm, level);
            results.add(protectionLevelResult);
        }
        return results;
    }


    private void setupAlpacaProtection(Farm farm, int alpacaCount)
    {
        for (int i = 0; i < alpacaCount; i++)
        {
           farm.addAlpacaOnce();
        }
    }

    private SimulationResult runSimulationOnce(Farm farm, String protectionLevel)
    {
        Simulation sim = new Simulation(farm, protectionLevel);
        return sim.run();
    }

    private void storeResultsForEachLevel(String protectionLevel, ArrayList<SimulationResult> results)
    {
        allSimulationResults.put(protectionLevel, results);
    }

    public static void main(String[] args)
    {
        AlpacaSheepGuards program = new AlpacaSheepGuards();
        program.run();
    }

    public void printSimulationResultsEachLevel(String protectionLevel)
    {
        ArrayList<SimulationResult> resultsOfLevel = this.allSimulationResults.get(protectionLevel);
        System.out.println("Protection Level: " + protectionLevel);
        resultsOfLevel.forEach(protectionLevelResult ->
        {
            System.out.println(protectionLevelResult.getTotalCost());
            System.out.println(" " + protectionLevelResult.toString());
            System.out.println(" Alpaca Maintenance Costs:");
            protectionLevelResult.farm.getAlpacas().forEach(alpaca -> System.out.println(" Alpaca " + alpaca.getName() + ": $" + alpaca.getMaintenanceCost()));
        });
        System.out.println();
        System.out.println();
    }

        /**
     * Prompts user for a valid farm name (at least 6 characters).
     */
    private String requestFarmName()
    {
        String farmName = "";
        boolean proceed = false;
        Input input = new Input();
        Validation validation = new Validation();

        do
        {
            farmName = input.acceptStringInput("What is your farm's name: ");
            if (!validation.stringLengthInRange(farmName, 6, Integer.MAX_VALUE))
            {
                System.out.println("Error: at least 6 characters is required");
            }
            else
            {
                proceed = true;
            }
        } while (!proceed);
        
        return farmName;
    }

    /**
     * Prompts user to select a valid state from file data.
     */
    private String[] requestState(ArrayList<String[]> statesData)
    {
        String[] selectedState = null;
        boolean proceed = false;
        Input input = new Input();

        while (!proceed)
        {
            System.out.println("Which state?");
            for (String[] state : statesData)
            {
                System.out.println("- " + state[0]);
            }
            String inputState = input.acceptStringInput("Choice: ");
            
            for (String[] state : statesData)
            {
                if (state[0].equalsIgnoreCase(inputState))
                {
                    selectedState = state;
                    proceed = true;
                    break;
                }
            }
            
            if (!proceed)
            {
                System.out.println("Error: invalid state");
            }
        }
        return selectedState;
    }

    /**
     * Requests and validates sheep/lamb counts with total constraints.
     */
    private int[] requestSheepAndLamb()
    {
        int sheepCount = -1;
        int lambCount = -1;
        boolean proceed = false;
        Validation validation = new Validation();
        
        while (!proceed)
        {
            sheepCount = requestAnimalCount("sheep");
            lambCount = requestAnimalCount("lamb");
            
            int total = sheepCount + lambCount;
            if (total == 0)
            {
                System.out.println("Error: total must be more than 0");
            }
            else if (!validation.intInRange(total, 1, 100))
            {
                System.out.println("Error: total must not exceed 100");
            }
            else
            {
                proceed = true;
            }
        }
        return new int[]{sheepCount, lambCount};
    }

    /**
     * Prompts for valid animal count (>=0).
     */
    private int requestAnimalCount(String animalType)
    {
        int count = -1;
        boolean proceed = false;
        Input input = new Input();
        Validation validation = new Validation();

        do
        {
            String inputCountString = input.acceptStringInput("How many " + animalType + "? ");
            try
            {
                count = Integer.parseInt(inputCountString);
                if (!validation.intInRange(count, 0, 100))
                {
                    if (count < 0)
                        System.out.println("Error: number must not be less than 0");
                    else 
                        System.out.println("Error: number must not be more than 100");
                }
                else
                {
                    proceed = true;
                }
            }
            catch (NumberFormatException e)
            {
                if (inputCountString.isEmpty())
                {
                    System.out.println("Error: value is not a number");
                }
                else
                {
                    System.out.println("Error: value is not a number: '" + inputCountString + "'");
                }
            }
        } while (!proceed);
        
        return count;
    }

    public void run()
    {
        welcomeUser();
        initialize();
        runAllSimulations();
        calculateProtectionLevelResults(this.farm.getState().getPredators());
        displayLevels();
        String recommendedLevel = findRecommendedLevel();
        displayFinalRecommendation(recommendedLevel);
        generateFileReport(recommendedLevel);
    }

    public void welcomeUser()
    {
        System.out.println("Welcome to the Alpaca Sheep Guards Program");
    }

}

