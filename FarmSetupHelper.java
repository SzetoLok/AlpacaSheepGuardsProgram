// import java.util.ArrayList;

// public class FarmSetupHelper {

//     private final Input input = new Input();
//     private final Validation validation = new Validation();

//     public Farm initializeFarm(ArrayList<String[]> statesData)
//     {
//         String farmName = requestFarmName();
//         String[] selectedStateData = requestState(statesData);
        
//         Farm farm = new Farm();
//         farm.initializeState(selectedStateData);
        
//         int[] sheepLambCounts = requestSheepAndLamb();
//         int sheepCounts = sheepLambCounts[0];
//         int lambCounts = sheepLambCounts[1];

//         for (int i = 0; i < sheepCounts; i++)
//         {
//             farm.addSheepOnce();
//         }
//         for (int i = 0; i < lambCounts; i++)
//         {
//             farm.addLambOnce();
//         }
//         return farm;
//     }

//     /**
//      * Prompts the user for a valid farm name (at least 6 characters).
//      */
//     public String requestFarmName() {
//         String farmName = "";
//         boolean proceed = false;
//         do {
//             farmName = input.acceptStringInput("What is your farm's name: ");
//             if (!validation.stringLengthInRange(farmName, 6, Integer.MAX_VALUE)) {
//                 System.out.println("Error: at least 6 characters is required");
//             } else {
//                 proceed = true;
//             }
//         } while (!proceed);
//         return farmName;
//     }


//     public String[] requestState(ArrayList<String[]> statesData)
//     {
//         String[] selectedState = null;
//         boolean proceed = false;
        
//         while (!proceed)
//         {
//             System.out.println("Which state?");
//             for (String[] state : statesData)
//             {
//                 System.out.println("- " + state[0]);
//             }
//             String inputState = input.acceptStringInput("Choice: ");
            
//             for (String[] state : statesData)
//             {
//                 if (state[0].equalsIgnoreCase(inputState))
//                 {
//                     selectedState = state;
//                     proceed = true;
//                     break;
//                 }
//             }
            
//             if (!proceed)
//             {
//                 System.out.println("Error: invalid state");
//             }
//         }
//         return selectedState;
//     }

//     /**
//      * Requests the number of sheep and lambs, validating all constraints together.
//      * @return An array: [0] = sheep count, [1] = lamb count
//      */
//     public int[] requestSheepAndLamb() {
//         int sheepCount, lambCount;
//         while (true) {
//             sheepCount = requestSheepCount();
//             lambCount = requestLambCount();
//             int total = sheepCount + lambCount;
//             if (total == 0) {
//                 System.out.println("Error: the total number of sheep and lambs must be more than 0");
//             } else if (!validation.intInRange(total, 1, 100)) {
//                 System.out.println("Error: the total number of sheep and lambs must be no greater than 100");
//             } else {
//                 break;
//             }
//         }
//         return new int[]{sheepCount, lambCount};
//     }

//     /**
//      * Prompts for a valid sheep count (integer >= 0).
//      */
//     public int requestSheepCount() {
//         int sheepCount = -1;
//         boolean proceed = false;
//         do {
//             String sheepCountString = input.acceptStringInput("How many sheep? ");
//             try {
//                 sheepCount = Integer.parseInt(sheepCountString);
//                 if (!validation.intInRange(sheepCount, 0, 100)) {
//                     if (sheepCount < 0)
//                         System.out.println("Error: number must not be less than 0");
//                     else 
//                         System.out.println("Error: number must not be more than 100");
//                 } else {
//                     proceed = true;
//                 }
//             } catch (NumberFormatException e) {
//                 if (sheepCountString.length() == 0) {
//                     System.out.println("Error: value is not a number");
//                 } else {
//                     System.out.println("Error: value is not a number: '" + sheepCountString + "'");
//                 }
//             }
//         } while (proceed == false);
//         return sheepCount;
//     }


//     public int requestLambCount() {
//         int lambCount = -1;
//         boolean proceed = false;
//         do {
//             String lambCountString = input.acceptStringInput("How many lamb? ");
//             try {
//                 lambCount = Integer.parseInt(lambCountString);
//                 if (!validation.intInRange(lambCount, 0, 100)) {
//                     if (lambCount < 0)
//                         System.out.println("Error: number must not be less than 0");
//                     else 
//                         System.out.println("Error: number must not be more than 100");
//                 } else {
//                     proceed = true;
//                 }
//             } catch (NumberFormatException e) {
//                 if (lambCountString.length() == 0) {
//                     System.out.println("Error: value is not a number");
//                 } else {
//                     System.out.println("Error: value is not a number: '" + lambCountString + "'");
//                 }
//             }
//         } while (proceed == false);
//         return lambCount;
//     }

//     public static void main(String[] args) {
//         // Prepare states for 
//         FileIO fileIO = new FileIO("predators.txt");
//         ArrayList<String[]> states = fileIO.readFile();
//         // System.err.println(states);
//        FarmSetupHelper setup = new FarmSetupHelper();
//        Farm farm = setup.initializeFarm(states);
//        System.out.println(farm);
//     }
// }
