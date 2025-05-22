import java.util.ArrayList;

public class FarmSetupHelper {

    private final Input input = new Input();
    private final Validation validation = new Validation();

    /**
     * Initializes the farm by collecting and validating all required user input.
     * @param states The list of available State objects.
     * @return A fully constructed Farm object.
     */
    public Farm initializeFarm(ArrayList<State> states) {
        String farmName = requestFarmName();
        State chosenState = requestState(states);
        int[] sheepAndLamb = requestSheepAndLamb();
        int sheepCount = sheepAndLamb[0];
        int lambCount = sheepAndLamb[1];

        Farm farm = new Farm(farmName, chosenState);
        for (int i = 0; i < sheepCount; i++) farm.addSheepOnce(new Sheep());
        for (int i = 0; i < lambCount; i++) farm.addLambOnce(new Lamb());
        return farm;
    }

    /**
     * Prompts the user for a valid farm name (at least 6 characters).
     */
    public String requestFarmName() {
        String farmName = "";
        boolean proceed = false;
        do {
            farmName = input.acceptStringInput("What is your farm's name: ");
            if (!validation.stringLengthInRange(farmName, 6, Integer.MAX_VALUE)) {
                System.out.println("Error: at least 6 characters is required");
            } else {
                proceed = true;
            }
        } while (!proceed);
        return farmName;
    }

    public State requestState(ArrayList<State> states) {
        State selectedState = null;
        boolean proceed = false;
        do {
            System.out.println("Which state?");
            for (State s : states) System.out.println("- " + s.getStateName());
            String stateInput = input.acceptStringInput("Choice: ");
            for (State s : states) {
                if (s.getStateName().equalsIgnoreCase(stateInput)) {
                    selectedState = s;
                    proceed = true;
                    break;
                }
            }
            if (!proceed) {
                System.out.println("Error: invalid state");
            }
        } while (!proceed);
        return selectedState;
    }

    /**
     * Requests the number of sheep and lambs, validating all constraints together.
     * @return An array: [0] = sheep count, [1] = lamb count
     */
    public int[] requestSheepAndLamb() {
        int sheepCount, lambCount;
        while (true) {
            sheepCount = requestSheepCount();
            lambCount = requestLambCount();
            int total = sheepCount + lambCount;
            if (total == 0) {
                System.out.println("Error: the total number of sheep and lambs must be more than 0");
            } else if (!validation.intInRange(total, 1, 100)) {
                System.out.println("Error: the total number of sheep and lambs must be no greater than 100");
            } else {
                break;
            }
        }
        return new int[]{sheepCount, lambCount};
    }

    /**
     * Prompts for a valid sheep count (integer >= 0).
     */
    public int requestSheepCount() {
        int sheepCount = -1;
        boolean proceed = false;
        do {
            String sheepCountString = input.acceptStringInput("How many sheep? ");
            try {
                sheepCount = Integer.parseInt(sheepCountString);
                if (!validation.intInRange(sheepCount, 0, 100)) {
                    if (sheepCount < 0)
                        System.out.println("Error: number must not be less than 0");
                    else 
                        System.out.println("Error: number must not be more than 100");
                } else {
                    proceed = true;
                }
            } catch (NumberFormatException e) {
                if (sheepCountString.length() == 0) {
                    System.out.println("Error: value is not a number");
                } else {
                    System.out.println("Error: value is not a number: '" + sheepCountString + "'");
                }
            }
        } while (proceed == false);
        return sheepCount;
    }


    public int requestLambCount() {
        int lambCount = -1;
        boolean proceed = false;
        do {
            String lambCountString = input.acceptStringInput("How many lamb? ");
            try {
                lambCount = Integer.parseInt(lambCountString);
                if (!validation.intInRange(lambCount, 0, 100)) {
                    if (lambCount < 0)
                        System.out.println("Error: number must not be less than 0");
                    else 
                        System.out.println("Error: number must not be more than 100");
                } else {
                    proceed = true;
                }
            } catch (NumberFormatException e) {
                if (lambCountString.length() == 0) {
                    System.out.println("Error: value is not a number");
                } else {
                    System.out.println("Error: value is not a number: '" + lambCountString + "'");
                }
            }
        } while (proceed == false);
        return lambCount;
    }

    public static void main(String[] args) {
        // Prepare states for 
        FileIO fileIO = new FileIO("predators.txt");
        ArrayList<State> states = fileIO.readFile();
        // System.err.println(states);
       FarmSetupHelper setup = new FarmSetupHelper();
       Farm farm = setup.initializeFarm(states);
       System.err.println(farm);
    }
}
