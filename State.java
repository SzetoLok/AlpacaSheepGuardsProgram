import java.util.HashMap;
import java.util.Map;

public class State 
{
    private String stateName;
    private Predator[] predators;
    
    public State() 
    {
        this.stateName = "Unknown";
        this.predators = new Predator[0];
    }

    public State(String[] stateData) 
    {
        // this.stateName = stateName;
        // this.predators = predators;
        initializeState(stateData);
    }

    public void display() 
    {
        System.out.print(this.toString());
    }
    
    public String getStateName() 
    {
        return this.stateName;
    }
    
    public Predator[] getPredators() 
    {
        return this.predators;
    }
    public String[] getPredatorsNames() 
    {
        String[] predatorsName = new String[this.predators.length];
        for (int i = 0; i < this.predators.length; i++) {
            predatorsName[i] = this.predators[i].getName();
        }
        return predatorsName;
    }

    public HashMap<String, Double> getPredatorsNamesAndDangerFactors() 
    {
        HashMap<String, Double> predatorsInfomation = new HashMap<>();

        for (Predator predator : this.predators) 
        {
            predatorsInfomation.put(predator.getName(), predator.getDangerFactor());
        }
        return predatorsInfomation;
    }

    public Predator getSpecificPredator(int index) 
    {
        if (index >= 0 && index < this.predators.length) 
        {
            return this.predators[index];
        }
        return null;
    }

    public void initializeState(String[] stateData)
    {
        this.stateName = stateData[0];
        String[] predatorData = new String[stateData.length - 1];

        for (int i = 1; i < stateData.length; i++)
        {
            predatorData[i - 1] = stateData[i];
        }

        initializePredators(predatorData);
    }

    public void initializePredators(String[] predatorData)
    {
        String[] predatorNames = {"Fox", "Dingo", "Feral Pig", "Wedge-tailed Eagle"};
        this.predators = new Predator[predatorData.length];

        for (int i = 0; i < predatorData.length; i++)
        {
            double dangerFactor = Double.parseDouble(predatorData[i]);
            this.setSpecificPredator(i, new Predator(predatorNames[i], dangerFactor));
        }
    }

    public void setPredators(Predator[] predators) 
    {
        this.predators = predators;
    }
    
    public void setSpecificPredator(int index, Predator predator) 
    {
        if (index >= 0 && index < this.predators.length) 
        {
            this.predators[index] = predator;
        }
    }

    public void setStateName(String stateName)
    {
        this.stateName = stateName;
    }

    public String toString()
    {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("State: ");
        stringBuffer.append(this.stateName);
        stringBuffer.append("\n");

        for (Predator predator : this.predators)
        {
            stringBuffer.append("Predator: ");
            stringBuffer.append(predator.getName());
            stringBuffer.append(", Danger Factor: ");
            stringBuffer.append(predator.getDangerFactor());
            stringBuffer.append("\n");
        }

        return stringBuffer.toString();
    }
}
