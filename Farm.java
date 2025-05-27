import java.util.ArrayList;
import java.util.HashMap;

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


    public void addSheepOnce() 
    {
        this.sheeps.add(new Sheep(true, "Sheep"));
    }
    
    public void addLambOnce() 
    {
        this.lambs.add(new Lamb(true, "Lamb"));
    }
    
    public void addAlpacaOnce() 
    {
        this.alpacas.add(new Alpaca(true, "Alpaca"));
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

    public String[] getPredatorsNames() 
    {
        return this.getState().getPredatorsNames();
    }

    public HashMap<String, Double> getPredatorsNamesAndDangerFactors() 
    {
        return this.getState().getPredatorsNamesAndDangerFactors();
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

    public int getTotalAlpacaMaintenanceCost() 
    {
        int totalCost = 0;

        for (Alpaca alpaca : this.alpacas) 
        {
            totalCost += alpaca.getMaintenanceCost();
        }
        return totalCost;
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

    public void initializeState(String[] stateData)
    {
        this.state = new State(stateData);
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

    public void setSheeps(ArrayList<Sheep> sheeps) 
    {
        this.sheeps = sheeps;
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

}
