// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.Random;

// /**
//  * Class which runs a single simulation round for a farm and protection level.
//  */
// public class Simulation2 {

//     private Farm farm;
//     private String protectionLevel;

//     // These are now private fields
//     private HashMap<String, Integer> animalAliveMap;
//     private HashMap<String, Integer> animalDeathMap;
//     private HashMap<String, Integer> predatorKillsMap;

//     public Simulation2(Farm farm, String protectionLevel) {
//         this.farm = farm;
//         this.protectionLevel = protectionLevel;
//         this.animalAliveMap = new HashMap<String, Integer>();
//         this.animalDeathMap = new HashMap<String, Integer>();
//         this.predatorKillsMap = initializePredatorKills();
//     }

//     public SimulationResult run() {
//         int numberOfAlpacas = farm.getTotalAlpacas();

//         processSheepDeaths(numberOfAlpacas);
//         processLambDeaths(numberOfAlpacas);
//         processAlpacaDeaths(numberOfAlpacas);

//         double totalCost = calculateTotalCost();

//         return new SimulationResult(animalAliveMap, animalDeathMap, predatorKillsMap, protectionLevel, totalCost);
//     }

//     public HashMap<String, Integer> initializePredatorKills() 
//     {
//         HashMap<String, Integer> predatorKillsMap = new HashMap<String, Integer>();
//         // State farmState = farm.getState();
//         Predator[] predatorArray = farm.getState().getPredators();
//         for (Predator predator : predatorArray) {
//             predatorKillsMap.put(predator.getName(), 0);
//         }
//         return predatorKillsMap;
//     }

//     // public int processSheepDeaths(int numberOfAlpacas) {
//     //     int numberOfSheepDeaths = 0;
//     //     // State farmState = farm.getState();
//     //     Predator[] predatorArray = farm.getState().getPredators();
//     //     java.util.Random randomGenerator = new java.util.Random();

//     //     for (Object sheepObject : farm.getSheeps()) {
//     //         Sheep sheep = (Sheep) sheepObject;
//     //         if (!sheep.getAlive()) continue;
//     //         for (Predator predator : predatorArray) {
//     //             double sheepDeathProbability = getSheepDeathProbability(predator, numberOfAlpacas);
//     //             if (randomGenerator.nextDouble() < sheepDeathProbability) {
//     //                 sheep.die();
//     //                 numberOfSheepDeaths++;
//     //                 this.predatorKillsMap.put(predator.getName(), this.predatorKillsMap.get(predator.getName()) + 1);
//     //                 break;
//     //             }
//     //         }
//     //     }
//     //     this.animalAliveMap.put("Sheep", farm.getTotalSheeps() - numberOfSheepDeaths);
//     //     this.animalDeathMap.put("Sheep", numberOfSheepDeaths);
//     //     return numberOfSheepDeaths;
//     // }

//     public int processSheepDeaths(int numberOfAlpacas) {
//         int numberOfSheepDeaths = 0;
//         // State farmState = farm.getState();
//         Predator[] predatorArray = farm.getState().getPredators();
//         Random random = new Random();

//         for (Sheep sheepObject : farm.getSheeps()) {
//             Sheep sheep = (Sheep) sheepObject;
//             if (!sheep.getAlive()) continue;
//             for (Predator predator : predatorArray) {
//                 double sheepDeathProbability = getSheepDeathProbability(predator, numberOfAlpacas);
//                 if (random.nextDouble() < sheepDeathProbability) {
//                     sheep.die();
//                     numberOfSheepDeaths++;
//                     this.predatorKillsMap.put(predator.getName(), this.predatorKillsMap.get(predator.getName()) + 1);
//                     break;
//                 }
//             }
//         }
//         this.animalAliveMap.put("Sheep", farm.getTotalSheeps() - numberOfSheepDeaths);
//         this.animalDeathMap.put("Sheep", numberOfSheepDeaths);
//         return numberOfSheepDeaths;
//     }


//     private int processLambDeaths(int numberOfAlpacas) {
//         int numberOfLambDeaths = 0;
//         State farmState = farm.getState();
//         Predator[] predatorArray = farmState.getPredators();
//         java.util.Random randomGenerator = new java.util.Random();

//         for (Object lambObject : farm.getLambs()) {
//             Lamb lamb = (Lamb) lambObject;
//             if (!lamb.getAlive()) continue;
//             for (Predator predator : predatorArray) {
//                 double lambDeathProbability = getLambDeathProbability(predator, numberOfAlpacas);
//                 if (randomGenerator.nextDouble() < lambDeathProbability) {
//                     lamb.die();
//                     numberOfLambDeaths++;
//                     this.predatorKillsMap.put(predator.getName(), this.predatorKillsMap.get(predator.getName()) + 1);
//                     break;
//                 }
//             }
//         }
//         this.animalAliveMap.put("Lamb", farm.getTotalLambs() - numberOfLambDeaths);
//         this.animalDeathMap.put("Lamb", numberOfLambDeaths);
//         return numberOfLambDeaths;
//     }

//     // private int processAlpacaDeaths(int numberOfAlpacas) {
//     //     int numberOfAlpacaDeaths = 0;
//     //     State farmState = farm.getState();
//     //     Predator[] predatorArray = farmState.getPredators();
//     //     ArrayList<Alpaca> alpacaList = farm.getAlpacas();
//     //     java.util.Random randomGenerator = new java.util.Random();

//     //     for (Alpaca alpaca : alpacaList) {
//     //         for (Predator predator : predatorArray) {
//     //             double alpacaDeathProbability = getAlpacaDeathProbability(predator, numberOfAlpacas);
//     //             if (randomGenerator.nextDouble() < alpacaDeathProbability) {
//     //                 alpaca.die();
//     //                 numberOfAlpacaDeaths++;
//     //                 this.predatorKillsMap.put(predator.getName(), this.predatorKillsMap.get(predator.getName()) + 1);
//     //                 break;
//     //             }
//     //         }
//     //     }
//     //     this.animalAliveMap.put("Alpaca", alpacaList.size() - numberOfAlpacaDeaths);
//     //     this.animalDeathMap.put("Alpaca", numberOfAlpacaDeaths);
//     //     return numberOfAlpacaDeaths;
//     // }

//     public int processAlpacaDeaths(int numberOfAlpacas) {
//         int numberOfAlpacaDeaths = 0;
//         State farmState = farm.getState();
//         Predator[] predatorArray = farmState.getPredators();
//         ArrayList<Alpaca> alpacaList = farm.getAlpacas();
//         java.util.Random randomGenerator = new java.util.Random();

//         for (Alpaca alpaca : alpacaList) {
//             for (Predator predator : predatorArray) {
//                 // double alpacaDeathProbability = getAlpacaDeathProbability(predator, numberOfAlpacas);
//                 // if (randomGenerator.nextDouble() < alpacaDeathProbability) {
//                     System.out.println("Original Maintenance: " + alpaca.getMaintenanceCost());
//                     alpaca.die();
//                     numberOfAlpacaDeaths++;
//                     this.predatorKillsMap.put(predator.getName(), this.predatorKillsMap.get(predator.getName()) + 1);
//                     break;
//                 // }
//             }
//         }
//         this.animalAliveMap.put("Alpaca", alpacaList.size() - numberOfAlpacaDeaths);
//         this.animalDeathMap.put("Alpaca", numberOfAlpacaDeaths);
//         return numberOfAlpacaDeaths;
//     }
//     public double getSheepDeathProbability(Predator predator, int numberOfAlpacas) {
//         double probability = predator.getDangerFactor();
//         if (numberOfAlpacas == 1) probability /= 2.0;
//         else if (numberOfAlpacas >= 2) probability /= 4.0;
//         return probability;
//     }

//     public double getLambDeathProbability(Predator predator, int numberOfAlpacas) {
//         double probability = predator.getDangerFactor() * 2.0;
//         if (numberOfAlpacas == 1) probability /= 2.0;
//         else if (numberOfAlpacas >= 2) probability /= 4.0;
//         return probability;
//     }

//     public double getAlpacaDeathProbability(Predator predator, int numberOfAlpacas) {
//         double baseSheepDeathProbability = predator.getDangerFactor();
//         if (numberOfAlpacas == 1) baseSheepDeathProbability /= 2.0;
//         else if (numberOfAlpacas >= 2) baseSheepDeathProbability /= 4.0;
//         return baseSheepDeathProbability / 100.0;
//     }

//     public double calculateTotalCost() {
//         int numberOfSheepLost = this.animalDeathMap.getOrDefault("Sheep", 0);
//         int numberOfLambsLost = this.animalDeathMap.getOrDefault("Lamb", 0);
//         int numberOfAlpacasLost = this.animalDeathMap.getOrDefault("Alpaca", 0);

//         double totalStockLostValue = numberOfSheepLost * 150 + numberOfLambsLost * 250 + numberOfAlpacasLost * 1000;
//         double totalAlpacaHiringCost = farm.getAlpacas().size() * Alpaca.HIRE_COST;
//         double totalAlpacaMaintenanceCost = 0.0;
//         for (Alpaca alpaca : farm.getAlpacas()) {
//             totalAlpacaMaintenanceCost += alpaca.getMaintenanceCost();
//         }
//         return totalStockLostValue + totalAlpacaHiringCost + totalAlpacaMaintenanceCost;
//     }
// }


import java.util.ArrayList;
import java.util.HashMap;
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
        processSheepDeaths(numberOfAlpacas);
        processLambDeaths(numberOfAlpacas);
        processAlpacaDeaths(numberOfAlpacas);
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

    /**
     * Processes sheep deaths for this simulation round.
     *
     * @param numberOfAlpacas  The number of alpacas protecting the flock.
     * @return                 The number of sheep that died.
     */
    public int processSheepDeaths(int numberOfAlpacas) {
        int numberOfSheepDeaths = 0;
        Predator[] predatorArray = farm.getState().getPredators();
        Random random = new Random();

        for (Sheep sheep : farm.getSheeps()) {
            // Sheep sheep = (Sheep) sheepObject;
            if (sheep.getAlive() == false) continue;
            for (Predator predator : predatorArray) {
                double sheepDeathProbability = getSheepDeathProbability(predator, numberOfAlpacas);
                if (random.nextDouble() < sheepDeathProbability) {
                    sheep.die();
                    numberOfSheepDeaths++;
                    this.predatorKillsMap.put(predator.getName(), this.predatorKillsMap.get(predator.getName()) + 1);
                    break;
                }
            }
        }
        this.animalAliveMap.put("Sheep", farm.getTotalSheeps() - numberOfSheepDeaths);
        this.animalDeathMap.put("Sheep", numberOfSheepDeaths);
        return numberOfSheepDeaths;
    }

    /**
     * Processes lamb deaths for this simulation round.
     *
     * @param numberOfAlpacas  The number of alpacas protecting the flock.
     * @return                 The number of lambs that died.
     */
    public int processLambDeaths(int numberOfAlpacas) {
        int numberOfLambDeaths = 0;
        Predator[] predatorArray = farm.getState().getPredators();
        Random random = new Random();

        for (Lamb lamb : farm.getLambs()) {
            // Lamb lamb = (Lamb) lambObject;
            if (lamb.getAlive() == false) continue;
            for (Predator predator : predatorArray) {
                double lambDeathProbability = getLambDeathProbability(predator, numberOfAlpacas);
                if (random.nextDouble() < lambDeathProbability) {
                    lamb.die();
                    numberOfLambDeaths++;
                    this.predatorKillsMap.put(predator.getName(), this.predatorKillsMap.get(predator.getName()) + 1);
                    break;
                }
            }
        }
        this.animalAliveMap.put("Lamb", farm.getTotalLambs() - numberOfLambDeaths);
        this.animalDeathMap.put("Lamb", numberOfLambDeaths);
        return numberOfLambDeaths;
    }

    /**
     * Processes alpaca deaths for this simulation round.
     *
     * @param numberOfAlpacas  The number of alpacas protecting the flock.
     * @return                 The number of alpacas that died.
     */
    public int processAlpacaDeaths(int numberOfAlpacas) {
        int numberOfAlpacaDeaths = 0;
        Predator[] predatorArray = farm.getState().getPredators();
        ArrayList<Alpaca> alpacaList = farm.getAlpacas();
        Random random = new Random();

        for (Alpaca alpaca : alpacaList) {
            if (alpaca.getAlive() == false) continue;
            for (Predator predator : predatorArray) {
                double alpacaDeathProbability = getAlpacaDeathProbability(predator, numberOfAlpacas);
                if (random.nextDouble() < alpacaDeathProbability) {
                    alpaca.die();
                    alpaca.halfMaintenanceCost();
                    numberOfAlpacaDeaths++;
                    this.predatorKillsMap.put(predator.getName(), this.predatorKillsMap.get(predator.getName()) + 1);
                    break;
                }
            }
        }
        this.animalAliveMap.put("Alpaca", alpacaList.size() - numberOfAlpacaDeaths);
        this.animalDeathMap.put("Alpaca", numberOfAlpacaDeaths);
        return numberOfAlpacaDeaths;
    }

    /**
     * Calculates the probability that a sheep will die from a given predator, based on protection.
     *
     * @param predator         The predator in question.
     * @param numberOfAlpacas  The number of alpacas protecting the flock.
     * @return                 The probability (0.0 to 1.0) that a sheep will die from this predator.
     */
    public double getSheepDeathProbability(Predator predator, int numberOfAlpacas) {
        double probability = predator.getDangerFactor();
        if (numberOfAlpacas == 1) probability /= 2.0;
        else if (numberOfAlpacas >= 2) probability /= 4.0;
        return probability;
    }

    /**
     * Calculates the probability that a lamb will die from a given predator, based on protection.
     *
     * @param predator         The predator in question.
     * @param numberOfAlpacas  The number of alpacas protecting the flock.
     * @return                 The probability (0.0 to 1.0) that a lamb will die from this predator.
     */
    public double getLambDeathProbability(Predator predator, int numberOfAlpacas) {
        double probability = predator.getDangerFactor() * 2.0;
        if (numberOfAlpacas == 1) probability /= 2.0;
        else if (numberOfAlpacas >= 2) probability /= 4.0;
        return probability;
    }

    /**
     * Calculates the probability that an alpaca will die from a given predator, based on protection.
     * This is always 1/100 of the adjusted sheep probability.
     *
     * @param predator         The predator in question.
     * @param numberOfAlpacas  The number of alpacas protecting the flock.
     * @return                 The probability (0.0 to 1.0) that an alpaca will die from this predator.
     */
    public double getAlpacaDeathProbability(Predator predator, int numberOfAlpacas) {
        double baseSheepDeathProbability = predator.getDangerFactor();
        if (numberOfAlpacas == 1) baseSheepDeathProbability /= 2.0;
        else if (numberOfAlpacas >= 2) baseSheepDeathProbability /= 4.0;
        return baseSheepDeathProbability / 100.0;
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
