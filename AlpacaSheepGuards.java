import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class which manages the simulation workflow for the Alpaca 
 * Sheep Guards program. Handles farm initialization, simulation runs,
 * statistical calculations, and reporting.
 *
 * @author Szeto Lok
 * @version ver1.0.0
 */
public class AlpacaSheepGuards
{

    private static final int NUM_SIMULATIONS = 10;
    private static final String[] PROTECTION_LEVELS = 
                        {"0 alpaca", "1 alpaca", "2 alpacas"};

    private Farm farm;
    private HashMap<String, ArrayList<SimulationResult>> allSimulationResults;
    private HashMap<String, ProtectionLevelResult> allProtectionLevelResults;

    /**
     * Default constructor which creates an AlpacaSheepGuards object with 
     * default values.
     * 
     */
    public AlpacaSheepGuards()
    {
        this.farm = new Farm();
        this.allSimulationResults = new HashMap<>();
        this.allProtectionLevelResults = new HashMap<>();
    }

    /**
     * Non-default constructor which creates an AlpacaSheepGuards object 
     * with specified fields.
     *
     * @param farm The Farm object to use.
     * @param allSimulationResults The simulation results map.
     * @param allProtectionLevelResults The protection level results map.
     */
    public AlpacaSheepGuards(Farm farm,
            HashMap<String, ArrayList<SimulationResult>> allSimulationResults,
            HashMap<String, ProtectionLevelResult> allProtectionLevelResults)
    {
        this.farm = farm;
        this.allSimulationResults = allSimulationResults;
        this.allProtectionLevelResults = allProtectionLevelResults;
    }

    /**
     * Builds the summary report string for the recommended protection level.
     *
     * @param protectionLevelResult The ProtectionLevelResult to report.
     * @return The formatted report string.
     */
    public String buildFileReport(ProtectionLevelResult protectionLevelResult)
    {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(formatFarmInfo());
        stringBuffer.append(formatProtectionInfo(protectionLevelResult));
        stringBuffer.append(formatAnimalLossInfo(protectionLevelResult));
        stringBuffer.append(
                    formatTroublesomePredatorsInfo(protectionLevelResult));
        return stringBuffer.toString();
    }

    /**
     * Calculates and stores statistics for all protection levels.
     */
    public void calculateProtectionLevelResults()
    {
        for (String protectionLevel : PROTECTION_LEVELS)
        {
            calculateProtectionLevelResultOnce(protectionLevel, 
                                allSimulationResults.get(protectionLevel));
        }
    }

    /**
     * Calculates and stores the statistics for a single protection level.
     *
     * @param protectionLevel The protection level to calculate for.
     * @param simulationResults The list of SimulationResult objects.
     */
    public void calculateProtectionLevelResultOnce(String protectionLevel, 
                                ArrayList<SimulationResult> simulationResults)
    {
        StatisticalCalculator calculator = new StatisticalCalculator();
        ProtectionLevelResult protectionLevelResult = 
                                    new ProtectionLevelResult();
        protectionLevelResult.setProtectionLevel(protectionLevel);
        protectionLevelResult
            .setAverageCost(calculator
                            .calculateAverageCost(simulationResults));

        protectionLevelResult
            .setLowestCost(calculator.calculateLowestCost(simulationResults));

        protectionLevelResult
            .setHighestCost(calculator.calculateHighestCost(simulationResults));

        protectionLevelResult
            .setAverageSheepLost(
                calculator.calculateAverageAnimalLoss(simulationResults, 
                                            "Sheep"));

        protectionLevelResult
            .setAverageLambLost(
                calculator.calculateAverageAnimalLoss(simulationResults,
                                            "Lamb"));

        protectionLevelResult
            .setAverageAlpacaLost(
                calculator.calculateAverageAnimalLoss(simulationResults, 
                                            "Alpaca"));

        HashMap<String, Double> averageKills = 
                                calculator.calculateAveragePredatorKills(
                                                        simulationResults);

        protectionLevelResult.setAveragePredatorKills(averageKills);
        protectionLevelResult
            .setMostTroublesomePredators(
                findMostTroublesomePredators(averageKills));

        protectionLevelResult
            .setZeroKillPredators(findZeroKillPredators(averageKills));

        allProtectionLevelResults.put(protectionLevel, protectionLevelResult);
    }

    /**
     * Display method to print the state of the AlpacaSheepGuards object.
     *
     * @return The state of the object as a formatted String.
     */
    public String display()
    {
        return this.toString();
    }

    /**
     * Displays all statistics for a protection level.
     *
     * @param protectionLevelResult The ProtectionLevelResult containing all statistics.
     */
    public void displayEachLevel(ProtectionLevelResult protectionLevelResult)
    {
        printProtectionLevelHeader(protectionLevelResult);
        printCostStatistics(protectionLevelResult);
        printAnimalLossStatistics(protectionLevelResult);
        printPredatorKills(protectionLevelResult);
    }

    /**
     * Displays the final recommendation report for the 
     * selected protection level.
     *
     * @param recommendedLevel The protection level to display results for.
     */
    public void displayFinalRecommendation(String recommendedLevel)
    {
        ProtectionLevelResult protectionLevelResult = allProtectionLevelResults
                                                        .get(recommendedLevel);
        printReportHeader();
        printCostAndProtectionDetails(protectionLevelResult);
        printTroublesomePredators(protectionLevelResult);
        printZeroKillPredators(protectionLevelResult);
        printReportFooter();
    }

    /**
     * Displays simulation results for each protection level.
     */
    public void displayLevels()
    {
        for (String protectionLevel : PROTECTION_LEVELS)
        {
            ProtectionLevelResult protectionLevelResult = 
                             allProtectionLevelResults.get(protectionLevel);
            displayEachLevel(protectionLevelResult);
        }
    }

    /**
     * Finds the predator(s) with the highest average kills.
     *
     * @param averagePredatorKills The map of average predator kills.
     * @return An ArrayList of the most troublesome predator names.
     */
    public ArrayList<String> findMostTroublesomePredators(
                                HashMap<String, Double> averagePredatorKills)
    {
        ArrayList<String> predators = new ArrayList<String>();
        double maximumKills = -1.0;
        for (HashMap.Entry<String, Double> entry : 
                            averagePredatorKills.entrySet())
        {
            double kills = entry.getValue();
            String predator = entry.getKey();
            if (kills > maximumKills)
            {
                maximumKills = kills;
                predators.clear();
                predators.add(predator);
            }
            else if (kills == maximumKills)
            {
                predators.add(predator);
            }
        }
        return predators;
    }

    /**
     * Finds and returns the recommended protection level 
     * (lowest average cost).
     *
     * @return The recommended protection level as a String.
     */
    public String findRecommendedLevel()
    {
        String recommendedLevel = "";
        double lowestAverageCost = Double.MAX_VALUE;
        for (String level : allProtectionLevelResults.keySet())
        {
            double averageCost = allProtectionLevelResults
                                .get(level).getAverageCost();
            if (averageCost < lowestAverageCost)
            {
                lowestAverageCost = averageCost;
                recommendedLevel = level;
            }
        }
        return recommendedLevel;
    }

    /**
     * Finds the predator(s) with zero kills.
     *
     * @param averagePredatorKills The map of average predator kills.
     * @return An ArrayList of predator names with zero kills.
     */
    public ArrayList<String> findZeroKillPredators(
                                HashMap<String, Double> averagePredatorKills)
    {
        ArrayList<String> zeroKillers = new ArrayList<String>();
        String[] predatorsNames = this.getFarm().getPredatorsNames();
        for (String predatorName : predatorsNames)
        {
            if (averagePredatorKills.getOrDefault(predatorName, 0.0) == 0.0)
            {
                zeroKillers.add(predatorName);
            }
        }
        return zeroKillers;
    }

    /**
     * Formats the predicted animal loss information for the report.
     *
     * @param protectionLevelResult The ProtectionLevelResult to report.
     * @return The formatted animal loss string.
     */
    public String formatAnimalLossInfo(
            ProtectionLevelResult protectionLevelResult)
    {
        double totalLost = protectionLevelResult.getAverageSheepLost()
            + protectionLevelResult.getAverageLambLost()
            + protectionLevelResult.getAverageAlpacaLost();
        return String.format("Average predicted number of animal lost: %.2f%n", 
                                totalLost);
    }

    /**
     * Formats the farm's basic information for the report.
     *
     * @return The formatted farm information string.
     */
    public String formatFarmInfo()
    {
        return String.format(
            "Farm name: %s%nNumber of sheep: %d%nNumber of lambs: %d%n",
            farm.getFarmName(),
            farm.getTotalSheeps(),
            farm.getTotalLambs());
    }

    /**
     * Formats predator names for display.
     *
     * @param name The original predator name.
     * @return The formatted display name.
     */
    public String formatPredatorName(String name)
    {
        if (name.equals("Feral Pig")) return "Pig";
        if (name.equals("Wedge-tailed Eagle")) return "Eagle";
        return name;
    }

    /**
     * Formats the protection level and cost information for the report.
     *
     * @param protectionLevelResult The ProtectionLevelResult to report.
     * @return The formatted protection level and cost string.
     */
    public String formatProtectionInfo(
                            ProtectionLevelResult protectionLevelResult)
    {
        StringBuffer stringBuffer = new StringBuffer();
        int alpacaCount = getAlpacaCountForLevel(protectionLevelResult
                                                .getProtectionLevel());
        stringBuffer.append("Recommended level of protection: ")
                    .append(String.format(
                "Protection: %d alpaca%s%nProtection cost: $%.2f%n",
                        alpacaCount,
                        ((alpacaCount != 0 && alpacaCount != 1) ? "s" : ""),
                        protectionLevelResult.getAverageCost()));
    //     return String.format(
    // "Recommended level of protection: Protection: %d alpaca%s%nProtection cost: $%.2f%n",
    //         alpacaCount,
    //         ((alpacaCount != 0 && alpacaCount != 1) ? "s" : ""),
    //         protectionLevelResult.getAverageCost()
    //     );
        return stringBuffer.toString();
    }
 
    /**
     * Formats the most troublesome predator(s) information for the report.
     *
     * @param protectionLevelResult The ProtectionLevelResult to report.
     * @return The formatted troublesome predator string.
     */
    public String formatTroublesomePredatorsInfo(
                            ProtectionLevelResult protectionLevelResult)
    {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Most troublesome predator:\n");
        for (String predator : protectionLevelResult
                                .getMostTroublesomePredators())
        {
            double kills = protectionLevelResult
                            .getAveragePredatorKills().get(predator);
            stringBuffer.append(String.format("  %s, kill average: %.1f%n",
                                             predator, 
                                             kills));
        }
        return stringBuffer.toString();
    }

    /**
     * Generates and writes the summary report for the recommended 
     * protection level.
     *
     * @param recommendedLevel The recommended protection level string.
     */
    public void generateFileReport(String recommendedLevel)
    {
        ProtectionLevelResult protectionLevelResult = allProtectionLevelResults
                                                        .get(recommendedLevel);
        String report = buildFileReport(protectionLevelResult);
        FileIO fileIO = new FileIO("alpacaSheepGuardViability.txt");
        fileIO.writeFile(report);
    }

    /**
     * Accessor method to get the map of all protection level results.
     *
     * @return The HashMap of protection level results.
     */
    public HashMap<String,ProtectionLevelResult> getAllProtectionLevelResults()
    {
        return this.allProtectionLevelResults;
    }

    /**
     * Accessor method to get the map of all simulation results.
     *
     * @return The HashMap of simulation results.
     */
    public HashMap<String,ArrayList<SimulationResult>> getAllSimulationResults(
                                                                              )
    {
        return this.allSimulationResults;
    }

    /**
     * Accessor method to get the number of alpacas for a given 
     * protection level.
     *
     * @param protectionLevel The protection level as a String.
     * @return The number of alpacas as an int.
     */
    public int getAlpacaCountForLevel(String protectionLevel)
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

    /**
     * Accessor method to get the Farm object.
     *
     * @return The Farm object.
     */
    public Farm getFarm()
    {
        return this.farm;
    }

    /**
     * Accessor method to get the array of protection levels.
     *
     * @return The array of protection level strings.
     */
    public static String[] getProtectionLevels()
    {
        return PROTECTION_LEVELS;
    }

    /**
     * Accessor method to get the number of simulations to run per level.
     *
     * @return The number of simulations as an int.
     */
    public static int getNumSimulations()
    {
        return NUM_SIMULATIONS;
    }

    /**
     * Initializes the program by loading predator data and setting up 
     * the farm.
     * 
     */
    public void requestFarmInformation()
    {
        FileIO fileIO = new FileIO("predators.txt");
        ArrayList<String[]> statesData = fileIO.readFile();
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
    }

    public static void main(String[] args)
    {
        AlpacaSheepGuards alpacaSheepGuards = new AlpacaSheepGuards();
        alpacaSheepGuards.run();
    }

    /**
     * Prints average animal loss statistics.
     *
     * @param protectionLevelResult The ProtectionLevelResult 
     * containing animal loss stats.
     */
    public void printAnimalLossStatistics(
                            ProtectionLevelResult protectionLevelResult)
    {
        System.out.println("\n    Average number of animal lost:");
        System.out.printf("      Sheep: %.2f%n", 
                            protectionLevelResult.getAverageSheepLost());
        System.out.printf("      Lambs: %.2f%n", 
                            protectionLevelResult.getAverageLambLost());
        System.out.printf("      Alpacas: %.2f%n", 
                            protectionLevelResult.getAverageAlpacaLost());
    }
    
    /**
     * Prints cost and protection level details.
     *
     * @param protectionLevelResult Contains the cost statistics.
     */
    public void printCostAndProtectionDetails(
                            ProtectionLevelResult protectionLevelResult)
    {
        System.out.printf("Lowest average cost: $%.2f%n", 
                                protectionLevelResult.getAverageCost());
        int alpacaCount = getAlpacaCountForLevel(protectionLevelResult
                                                    .getProtectionLevel());
        System.out.println("Protection: " + alpacaCount + " alpaca" +
            ((alpacaCount != 0 && alpacaCount != 1) ? "s" : ""));
    }

    /**
     * Prints cost statistics (average, lowest, highest).
     *
     * @param protectionLevelResult The ProtectionLevelResult 
     * containing cost stats.
     */
    public void printCostStatistics(ProtectionLevelResult protectionLevelResult)
    {
        System.out.printf("    Lowest Cost: $%.2f%n", 
                            protectionLevelResult.getLowestCost());
        System.out.printf("    Highest Cost: $%.2f%n", 
                            protectionLevelResult.getHighestCost());
        System.out.printf("    Average Cost: $%.2f%n", 
                            protectionLevelResult.getAverageCost());
    }

    /**
     * Prints formatted predator kill statistics.
     *
     * @param protectionLevelResult The ProtectionLevelResult containing 
     * predator kill stats.
     */
    public void printPredatorKills(ProtectionLevelResult protectionLevelResult)
    {
        System.out.println("\n    Average number of animals killed"
                            + " by each predator:");

        HashMap<String, Double> avgKills = protectionLevelResult
                                            .getAveragePredatorKills();
        for (HashMap.Entry<String, Double> entry : avgKills.entrySet())
        {
            String displayName = formatPredatorName(entry.getKey());
            System.out.printf("%11s: %.2f%n", displayName, entry.getValue());
        }
    }

    /**
     * Prints the protection level header.
     *
     * @param protectionLevelResult The ProtectionLevelResult containing 
     * the protection level.
     */
    public void printProtectionLevelHeader(
                                ProtectionLevelResult protectionLevelResult)
    {
        System.out.println("\n* Running simulation with " 
                            + protectionLevelResult.getProtectionLevel());
    }

    /**
     * Prints the report footer with file location information.
     */
    public void printReportFooter()
    {
        System.out.println("\nA report has been written to: "
                            + " alpacaSheepGuardViability.txt");
        System.out.println("Goodbye");
    }

    /**
     * Prints the report header with title bars.
     */
    public void printReportHeader()
    {
        System.out.println("\n=============================");
        System.out.println("End of Simulation Report");
        System.out.println("=============================");
    }

    /**
     * Prints simulation results for each protection level.
     *
     * @param protectionLevel The protection level to print.
     */
    // public void printSimulationResultsEachLevel(String protectionLevel)
    // {
    //     ArrayList<SimulationResult> resultsOfLevel = this.allSimulationResults.get(protectionLevel);
    //     System.out.println("Protection Level: " + protectionLevel);
    //     for (SimulationResult protectionLevelResult : resultsOfLevel)
    //     {
    //         System.out.println(protectionLevelResult.getTotalCost());
    //         System.out.println(" " + protectionLevelResult.toString());
    //         System.out.println(" Alpaca Maintenance Costs:");
    //         protectionLevelResult.farm.getAlpacas().forEach(alpaca ->
    //             System.out.println(" Alpaca " + alpaca.getName() + ": $" + alpaca.getMaintenanceCost()));
    //     }
    //     System.out.println();
    //     System.out.println();
    // }

    /**
     * Prints details about the most troublesome predators.
     *
     * @param protectionLevelResult Contains predator statistics.
     */
    public void printTroublesomePredators(
                                ProtectionLevelResult protectionLevelResult)
    {
        System.out.println("  Troublesome predator:");
        for (String predator : protectionLevelResult
                                .getMostTroublesomePredators())
        {
            double kills = protectionLevelResult
                                .getAveragePredatorKills().get(predator);
                                
            System.out.printf("    %s, kill average: %.1f%n", predator, kills);
        }
    }

    /**
     * Prints information about predators with zero kills.
     *
     * @param protectionLevelResult Contains predator statistics.
     */
    public void printZeroKillPredators(
                        ProtectionLevelResult protectionLevelResult)
    {
        ArrayList<String> zeroKillers = protectionLevelResult
                                        .getZeroKillPredators();
        if (zeroKillers.isEmpty())
        {
            System.out.println("  All predators killed at least one animal");
        }
        else
        {
            System.out.println("  " + zeroKillers.size() 
                                    + " predators that had no kill:");

            System.out.println("    " + String.join(", ", zeroKillers));
        }
    }

    /**
     * Prompts for valid animal count (>=0).
     *
     * @param animalType The type of animal ("sheep" or "lamb").
     * @return The validated animal count as an int.
     */
    public int requestAnimalCount(String animalType)
    {
        int count = -1;
        boolean proceed = false;
        Input input = new Input();
        Validation validation = new Validation();

        while (!proceed == true)
        {
            String inputCountString = input.acceptStringInput("How many " 
                                                            + animalType 
                                                            + "? ");
            try
            {
                count = Integer.parseInt(inputCountString);
                if (!validation.intInRange(count, 0, 100))
                {
                    if (count < 0)
                        System.out.println("Error: number must not " 
                                            + "be less than 0");
                    else
                        System.out.println("Error: number must not "
                                            + "be more than 100");
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
                    System.out.println("Error: value is not a number: '"
                                        + inputCountString + "'");
                }
            }
        }
        return count;
    }

    /**
     * Prompts user for a valid farm name (at least 6 characters).
     *
     * @return The validated farm name as a String.
     */
    public String requestFarmName()
    {
        String farmName = "";
        boolean proceed = false;
        Input input = new Input();
        Validation validation = new Validation();

        while (!proceed == true)
        {
            farmName = input
                        .acceptStringInput("What is your farm's name: ");
            if (!validation.stringLengthInRange(farmName, 
                                            6, 
                                                Integer.MAX_VALUE))
            {
                System.out.println("Error: at least 6 characters is required");
            }
            else
            {
                proceed = true;
            }
        } 
        return farmName;
    }

    /**
     * Requests and validates sheep/lamb counts with total constraints.
     *
     * @return An array of two integers: [sheepCount, lambCount].
     */
    public int[] requestSheepAndLamb()
    {
        int sheepCount = -1;
        int lambCount = -1;
        boolean proceed = false;
        Validation validation = new Validation();
        while (!proceed == true)
        {
            sheepCount = requestAnimalCount("sheep");
            lambCount = requestAnimalCount("lamb");
            int totalCount = sheepCount + lambCount;
            if (totalCount == 0)
            {
                System.out.println("Error: the total number of sheep and lambs"
                                    + " must be more than 0");
            }
            else if (!validation.intInRange(totalCount, 1, 100))
            {
                System.out.println("Error: the total number of sheep and lambs"
                                    + " must not exceed 100");
            }
            else
            {
                proceed = true;
            }
        }
        return new int[]{sheepCount, lambCount};
    }

    /**
     * Prompts user to select a valid state from file data.
     *
     * @param statesData The list of state data arrays.
     * @return The selected state data array.
     */
    public String[] requestState(ArrayList<String[]> statesData)
    {
        String[] selectedState = new String[0];
        boolean proceed = false;
        Input input = new Input();

        while (!proceed == true)
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
            if (proceed == false)
            {
                System.out.println("Error: invalid state\n");
            }
        }
        return selectedState;
    }

    /**
     * Runs the main simulation workflow for the program.
     */
    public void run()
    {
        welcomeUser();
        requestFarmInformation();
        runAllSimulations();
        calculateProtectionLevelResults();
        displayLevels();
        String recommendedLevel = findRecommendedLevel();
        displayFinalRecommendation(recommendedLevel);
        generateFileReport(recommendedLevel);
    }

    /**
     * Runs simulations for all protection levels and stores results.
     */
    public void runAllSimulations()
    {
        for (String protectionLevel : PROTECTION_LEVELS)
        {
            ArrayList<SimulationResult> results = 
                            runSimulationsForProtectionLevel(protectionLevel);

            storeSimulationResults(protectionLevel, results);
        }
    }

    /**
     * Executes a single simulation run for the given protection level.
     *
     * @param protectionLevel The protection level to use.
     * @return Result of the single simulation.
     */
    public SimulationResult runSimulationOnce(String protectionLevel)
    {
        Simulation simulation = new Simulation(farm, protectionLevel);
        return simulation.run();
    }

    /**
     * Runs NUM_SIMULATIONS rounds for a specific protection level.
     *
     * @param protectionLevel The protection level to simulate.
     * @return List of simulation results for this protection level.
     */
    public ArrayList<SimulationResult> runSimulationsForProtectionLevel(
                                                        String protectionLevel)
    {
        ArrayList<SimulationResult> results = 
                                            new ArrayList<SimulationResult>();

        for (int i = 0; i < NUM_SIMULATIONS; i++)
        {
            results.add(runSimulationOnce(protectionLevel));
        }
        return results;
    }

    /**
     * Mutator method to set the map of all protection level results.
     *
     * @param allProtectionLevelResults The HashMap of protection 
     * level results to set.
     */
    public void setAllProtectionLevelResults(HashMap<String, 
                            ProtectionLevelResult> allProtectionLevelResults)
    {
        this.allProtectionLevelResults = allProtectionLevelResults;
    }

    /**
     * Mutator method to set the map of all simulation results.
     *
     * @param allSimulationResults The HashMap of simulation results to set.
     */
    public void setAllSimulationResults(HashMap<String, 
                            ArrayList<SimulationResult>> allSimulationResults)
    {
        this.allSimulationResults = allSimulationResults;
    }

    /**
     * Mutator method to set the Farm object.
     *
     * @param farm The Farm object to set.
     */
    public void setFarm(Farm farm)
    {
        this.farm = farm;
    }

    /**
     * Stores simulation results for a protection level in the results map.
     *
     * @param protectionLevel The protection level being stored.
     * @param results List of simulation results to store.
     */
    public void storeSimulationResults(String protectionLevel,
                                         ArrayList<SimulationResult> results)
    {
        allSimulationResults.put(protectionLevel, results);
    }

    /**
     * Returns a string representation of the AlpacaSheepGuards object,
     * including the farm and summary of simulation/protection level results.
     *
     * @return The state of the object as a formatted String.
     */
    @Override
    public String toString()
    {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Alpaca Sheep Guards Program State:\n")
                    .append("Farm:\n")
                    .append(farm.toString())
                    .append("\nSimulation Results by Protection Level:\n");
        for (String level : allSimulationResults.keySet())
        {
            stringBuffer.append("  ")
                        .append(level)
                        .append(": ")
                        .append(allSimulationResults.get(level).size())
                        .append(" simulations\n");
        }
        stringBuffer.append("\nProtection Level Results:\n");
        for (String level : allProtectionLevelResults.keySet())
        {
            stringBuffer.append("  ")
                        .append(level)
                        .append(": ")
                        .append(allProtectionLevelResults.get(level).toString())
                        .append("\n");
        }
        return stringBuffer.toString();
    }

    /**
     * Welcomes the user to the program.
     */
    public void welcomeUser()
    {
        System.out.println("Welcome to the Alpaca Sheep Guards Program");
    }
}
