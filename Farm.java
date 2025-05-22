import java.util.ArrayList;

public class Farm 
{
    private String farmName;
    private State state;
    private ArrayList<Sheep> sheeps;
    private ArrayList<Lamb> lambs;
    private ArrayList<Alpaca> alpacas;

    public Farm()
    {
        this.farmName = "Unknown";
        this.state = new State();
        this.sheeps = new ArrayList<>();
        this.lambs = new ArrayList<>();
        this.alpacas = new ArrayList<>();
    }

    public Farm(String farmName, State state)
    {
        this.farmName = farmName;
        this.state = state;
        this.sheeps = new ArrayList<Sheep>();
        this.lambs = new ArrayList<Lamb>();
        this.alpacas = new ArrayList<Alpaca>();
    }

    public void addSheepOnce(Sheep sheep) 
    {
        this.sheeps.add(sheep);
    }
    
    public void addLambOnce(Lamb lamb) 
    {
        this.lambs.add(lamb);
    }
    
    public void addAlpacaOnce(Alpaca alpaca) 
    {
        this.alpacas.add(alpaca);
    }

    public void display()
    {
        System.out.println(this.toString());
    }
    
    public ArrayList<Alpaca> getAlpacas() 
    {
        return this.alpacas;
    }

    public String getFarmName() 
    {
        return this.farmName;
    }

    public int getTotalAlpacas() 
    {
        return this.alpacas.size();
    }

    public ArrayList<Lamb> getLambs() 
    {
        return this.lambs;
    }

    public int getTotalLambs() 
    {
        return this.lambs.size();
    }


    public ArrayList<Sheep> getSheeps() 
    {
        return this.sheeps;
    }

    public int getTotalSheeps() 
    {
        return this.sheeps.size();
    }

    public State getState() 
    {
        return this.state;
    }

    public Alpaca getSpecificAlpaca(int index)
    {
        if (index >= 0 && index < this.alpacas.size()) 
        {
            return this.alpacas.get(index);
        }
        return null;
    }

    public void setAlpacas(ArrayList<Alpaca> alpacas) 
    {
        this.alpacas = alpacas;
    }

    public void setFarmName(String farmName)
    {
        this.farmName = farmName;
    }

    public void setLambs(ArrayList<Lamb> lambs) 
    {
        this.lambs = lambs;
    }

    public void setPredators(Predator[] predators) 
    {
        this.state.setPredators(predators);
    }

    public void setSheeps(ArrayList<Sheep> sheeps) 
    {
        this.sheeps = sheeps;
    }


    public void setSpecificPredator(int index, Predator predator) 
    {
        state.setSpecificPredator(index, predator);
    }

    @Override
    public String toString() 
    {
        // return "Farm [name=" + this.farname + ", state=" + state.display() +
        //         ", sheep=" + sheep.size() + ", lambs=" + lambs.size() +
        //         ", alpacas=" + alpacas.size() + "]";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Farm: ");
        stringBuffer.append(this.farmName);
        stringBuffer.append("\n");
        stringBuffer.append(this.state.toString());
        stringBuffer.append("Number of Alpaca: ");
        stringBuffer.append(this.getTotalAlpacas());
        stringBuffer.append("\n");
        stringBuffer.append("Number of Sheeps: ");
        stringBuffer.append(this.getTotalSheeps());
        stringBuffer.append("\n");
        stringBuffer.append("Number of Lambs: ");
        stringBuffer.append(this.getTotalLambs());
        stringBuffer.append("\n");

        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        FileIO fileIO = new FileIO("predators.txt");
        ArrayList<State> states = fileIO.readFile();

        // 2. Check if states were loaded
        if (states == null || states.isEmpty()) {
            System.out.println("No states loaded from file.");
            return;
        }

        // 3. Use the first state for the test farm (e.g., VIC)
        State testState = states.get(2);

        // 4. Create the farm
        Farm farm = new Farm("Green Pastures", testState);

        // 5. Add animals
        for (int i = 0; i < 5; i++) farm.addSheepOnce(new Sheep());
        for (int i = 0; i < 3; i++) farm.addLambOnce(new Lamb());
        farm.addAlpacaOnce(new Alpaca());

        // 6. Display farm info
        System.out.println("\nFarm information:");
        farm.display();

        // 7. Show state and predator info
        System.out.println("\nState and predator information:");
        testState.display();


    }

}
