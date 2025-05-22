import java.util.ArrayList;
import java.util.HashMap;

public class SimulationManualTest {
    public static void main(String[] args) {
        // 1. Setup test environment
        Predator[] predators = {
            new Predator("Fox", 0.08),
            new Predator("Dingo", 0.06),
            new Predator("Feral Pig", 0.02),
            new Predator("Eagle", 0.00)
        };
        
        State state = new State("TEST", predators);
        Farm farm = new Farm("Test Farm", state);
        
        // Add animals
        for(int i=0; i<20; i++) farm.addSheepOnce(new Sheep());
        for(int i=0; i<10; i++) farm.addLambOnce(new Lamb());
        for(int i=0; i<2; i++) farm.addAlpacaOnce(new Alpaca());

        // 2. Test with different protection levels
        testProtectionLevel(farm, "None");
        testProtectionLevel(farm, "Single Alpaca");
        testProtectionLevel(farm, "Pair of Alpacas");
    }

    private static void testProtectionLevel(Farm originalFarm, String protectionLevel) {
        System.out.println("\n===== TESTING PROTECTION LEVEL: " + protectionLevel + " =====");
        
        // Create fresh farm copy
        Farm farm = cloneFarm(originalFarm);
        setAlpacasForProtection(farm, protectionLevel);
        
        // Simulation simulation = new Simulation(farm, protectionLevel);
        Simulation simulation2 = new Simulation(farm, protectionLevel);

        SimulationResult result = simulation2.run();

        // 3. Verify results
        printResults(result, farm);
        verifyAnimalStatuses(farm);
        // verifyCostCalculation(result, farm);
    }

    private static Farm cloneFarm(Farm original) {
        Farm clone = new Farm(original.getFarmName(), original.getState());
        for(Object o : original.getSheeps()) clone.addSheepOnce(new Sheep());
        for(Object o : original.getLambs()) clone.addLambOnce(new Lamb());
        for(Alpaca a : original.getAlpacas()) clone.addAlpacaOnce(new Alpaca());
        return clone;
    }

    private static void setAlpacasForProtection(Farm farm, String level) {
        farm.setAlpacas(new ArrayList<>());
        if(level.equals("Single Alpaca")) {
            farm.addAlpacaOnce(new Alpaca());
        } 
        else if(level.equals("Pair of Alpacas")) {
            farm.addAlpacaOnce(new Alpaca());
            farm.addAlpacaOnce(new Alpaca());
        }
    }

    private static void printResults(SimulationResult result, Farm farm) {
        System.out.println("=== SIMULATION RESULTS ===");
        System.out.println("Protection Level: " + result.getProtectionLevel());
        
        // Calculate cost components
        int sheepDeaths = result.getSpecificAnimalDeath("Sheep");
        int lambDeaths = result.getSpecificAnimalDeath("Lamb");
        int alpacaDeaths = result.getSpecificAnimalDeath("Alpaca");
        
        double stockLoss = sheepDeaths * 150 + lambDeaths * 250 + alpacaDeaths * 1000;
        int totalAlpacas = farm.getAlpacas().size();
        double hiringCost = totalAlpacas * Alpaca.HIRE_COST;
        
        // Calculate and display maintenance costs
        System.out.println("\nAlpaca Maintenance Costs:");
        double totalMaintenance = 0.0;
        int alpacaNumber = 1;
        for(Alpaca alpaca : farm.getAlpacas()) {
            double cost = alpaca.getMaintenanceCost();
            System.out.printf("Alpaca #%d: $%.2f (%s)%n", 
                alpacaNumber, 
                cost,
                alpaca.getAlive() ? "alive" : "dead");
            totalMaintenance += cost;
            alpacaNumber++;
        }
        
        // Display cost breakdown
        System.out.println("\nCost Breakdown:");
        System.out.printf("Stock Loss:     $%9.2f%n", stockLoss);
        System.out.printf("Hiring Cost:    $%9.2f%n", hiringCost);
        System.out.printf("Maintenance:    $%9.2f%n", totalMaintenance);
        System.out.printf("Total Cost:     $%9.2f%n", result.getTotalCost());
        
        // Display other results
        System.out.println("\nAdditional Results:");
        System.out.println("Animals Alive: " + result.getAnimalAlive());
        System.out.println("Animals Dead: " + result.getAnimalDeath());
        System.out.println("Predator Kills: " + result.getPredatorKills());
    }

    private static void verifyAnimalStatuses(Farm farm) {
        System.out.println("\n=== ANIMAL STATUS VERIFICATION ===");
        
        // Verify sheep statuses
        int deadSheep = 0;
        for(Object o : farm.getSheeps()) {
            if(!((Sheep) o).getAlive()) deadSheep++;
        }
        System.out.println("Dead sheep count: " + deadSheep);

        // Verify lamb statuses
        int deadLambs = 0;
        for(Object o : farm.getLambs()) {
            if(!((Lamb) o).getAlive()) deadLambs++;
        }
        System.out.println("Dead lamb count: " + deadLambs);

        // Verify alpaca statuses
        int deadAlpacas = 0;
        for(Alpaca a : farm.getAlpacas()) {
            if(!a.getAlive()) deadAlpacas++;
        }
        System.out.println("Dead alpaca count: " + deadAlpacas);
    }

    private static void verifyCostCalculation(SimulationResult result, Farm farm) {
        System.out.println("\n=== COST CALCULATION VERIFICATION ===");
        
        // Calculate expected values
        double expectedStockLoss = 
            result.getSpecificAnimalDeath("Sheep") * 150 +
            result.getSpecificAnimalDeath("Lamb") * 250 +
            result.getSpecificAnimalDeath("Alpaca") * 1000;
        
        double expectedHiringCost = farm.getAlpacas().size() * Alpaca.HIRE_COST;
        
        double actualMaintenanceCost = 0;
        for(Alpaca a : farm.getAlpacas()) {
            actualMaintenanceCost += a.getMaintenanceCost();
        }

        double expectedTotal = expectedStockLoss + expectedHiringCost + actualMaintenanceCost;
        
        System.out.printf("Expected Total Cost: $%.2f\n", expectedTotal);
        System.out.printf("Actual Total Cost: $%.2f\n", result.getTotalCost());
        System.out.println("Cost calculation " + 
            (Math.abs(expectedTotal - result.getTotalCost()) < 0.01 ? "VALID" : "INVALID"));
    }
}
