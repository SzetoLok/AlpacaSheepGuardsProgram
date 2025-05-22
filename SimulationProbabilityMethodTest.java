public class SimulationProbabilityMethodTest {
    public static void main(String[] args) {
        // Setup predators (using VIC row from predators.txt)
        Predator[] predators = {
            new Predator("Fox", 0.08),
            new Predator("Dingo", 0.06),
            new Predator("Feral Pig", 0.02),
            new Predator("Eagle", 0.0)
        };

        // Dummy farm and simulation for method access
        State state = new State("VIC", predators);
        Farm farm = new Farm("TestFarm", state);
        Simulation sim = new Simulation(farm, "None");

        int[] alpacaCounts = {0, 1, 2};
        String[] protectionLabels = {"No protection", "Single alpaca", "Pair of alpacas"};

        for (int i = 0; i < alpacaCounts.length; i++) {
            int alpacaCount = alpacaCounts[i];
            System.out.println("\n--- " + protectionLabels[i] + " ---");
            for (Predator predator : predators) {
                double danger = predator.getDangerFactor();

                // Sheep
                double expectedSheepProb = danger;
                if (alpacaCount == 1) expectedSheepProb /= 2.0;
                else if (alpacaCount == 2) expectedSheepProb /= 4.0;
                double actualSheepProb = sim.getSheepDeathProbability(predator, alpacaCount);

                // Lamb
                double expectedLambProb = danger * 2.0;
                if (alpacaCount == 1) expectedLambProb /= 2.0;
                else if (alpacaCount == 2) expectedLambProb /= 4.0;
                double actualLambProb = sim.getLambDeathProbability(predator, alpacaCount);

                // Alpaca
                double expectedAlpacaProb = danger;
                if (alpacaCount == 1) expectedAlpacaProb /= 2.0;
                else if (alpacaCount == 2) expectedAlpacaProb /= 4.0;
                expectedAlpacaProb = expectedAlpacaProb / 100.0;
                double actualAlpacaProb = sim.getAlpacaDeathProbability(predator, alpacaCount);

                System.out.printf(
                    "Predator: %-10s | Sheep: expected=%.4f, actual=%.4f | Lamb: expected=%.4f, actual=%.4f | Alpaca: expected=%.6f, actual=%.6f%n",
                    predator.getName(),
                    expectedSheepProb, actualSheepProb,
                    expectedLambProb, actualLambProb,
                    expectedAlpacaProb, actualAlpacaProb
                );
            }
        }
    }
}
