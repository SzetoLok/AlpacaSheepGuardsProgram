import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Test 
{
    public void testState()
    {
        State state = new State("hong kong", new Predator[]{new Predator("spider", 1), new Predator("eagle", 2)});
        System.out.println(state);
    }

    public static void main(String[] args)
    {
        Test test = new Test();
        test.testState();
    }
    /**
 * Processes deaths for a list of animals, updating death and alive counts and
 * predator kills.
 *
 * @param animals           The list of animals to process.
 * @param numberOfAlpacas   The number of alpacas protecting the farm.
 * @param animalType        The type of animal (e.g., "Sheep", "Lamb", "Alpaca").
 */
public void processAnimalDeaths(ArrayList<? extends Animal> animals,
                               int numberOfAlpacas,
                               String animalType)
{
    int deaths = 0;
    Random random = new Random();
    HashMap<String, Double> predatorDangerFactors =
        this.getFarm().getPredatorsNamesAndDangerFactors();

    for (Animal animal : animals)
    {
        if (processAnimalDeath(animal, predatorDangerFactors, numberOfAlpacas, random))
        {
            deaths++;
        }
    }

    updateCounts(animalType, deaths, animals.size());
}

/**
 * Processes death for a single animal.
 * Returns true if the animal dies, false otherwise.
 *
 * @param animal                The animal to process.
 * @param predatorDangerFactors The map of predator names to danger factors.
 * @param numberOfAlpacas       The number of alpacas protecting the farm.
 * @param random                The Random object for probability checks.
 * @return true if the animal dies, false otherwise.
 */
private boolean processAnimalDeath(Animal animal,
                                  HashMap<String, Double> predatorDangerFactors,
                                  int numberOfAlpacas,
                                  Random random)
{
    if (!animal.getAlive())
    {
        return false;
    }

    for (HashMap.Entry<String, Double> predator : predatorDangerFactors.entrySet())
    {
        String killerPredator = attemptPredatorAttack(
            animal, predator, numberOfAlpacas, random);
        if (killerPredator != null)
        {
            incrementPredatorKills(killerPredator);
            return true;
        }
    }
    return false;
}

/**
 * Determines if an animal dies from a predator attack.
 * Returns the predator name if killed, otherwise null.
 *
 * @param animal          The animal being attacked.
 * @param predator        The predator entry (name and danger factor).
 * @param numberOfAlpacas The number of alpacas protecting the farm.
 * @param random          The Random object for probability checks.
 * @return The predator name if the animal dies, or null if it survives.
 */
private String attemptPredatorAttack(Animal animal,
                                    HashMap.Entry<String, Double> predator,
                                    int numberOfAlpacas,
                                    Random random)
{
    String predatorName = predator.getKey();
    double dangerFactor = predator.getValue();
    double probability = animal.getDeathProbability(dangerFactor, numberOfAlpacas);

    if (random.nextDouble() < probability)
    {
        animal.die();
        return predatorName;
    }
    return null;
}

/**
 * Increments the kill count for a predator.
 *
 * @param predatorName The name of the predator.
 */
private void incrementPredatorKills(String predatorName)
{
    predatorKillsMap.put(
        predatorName, predatorKillsMap.getOrDefault(predatorName, 0) + 1);
}

/**
 * Updates death and alive counts for the animal type.
 *
 * @param animalType   The type of animal (e.g., "Sheep", "Lamb", "Alpaca").
 * @param deaths       The number of deaths for this animal type.
 * @param totalAnimals The total number of animals of this type.
 */
private void updateCounts(String animalType, int deaths, int totalAnimals)
{
    animalDeathMap.put(animalType, deaths);
    animalAliveMap.put(animalType, totalAnimals - deaths);
}

}
