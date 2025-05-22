public class State 
{
    private String stateName;
    private Predator[] predators;
    
    public State() 
    {
        this.stateName = "Unknown";
        this.predators = new Predator[0];
    }

    public State(String stateName, Predator[] predators) 
    {
        this.stateName = stateName;
        this.predators = predators;
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
    
    public Predator getSpecificPredator(int index) 
    {
        if (index >= 0 && index < this.predators.length) 
        {
            return this.predators[index];
        }
        return null;
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
