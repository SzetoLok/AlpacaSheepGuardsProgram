public class Lamb extends Animal 
{
    public Lamb() 
    {
        super(true, "Lamb");
    }

    public Lamb(String name) 
    {
        super(true, name);
    }
    
    @Override
    public double getDeathProbability(Predator predator, int numberOfAlpacas) 
    {
        double deathProbability = predator.getDangerFactor() * 2.0;
        if (numberOfAlpacas == 1) 
            deathProbability /= 2.0;
        else if (numberOfAlpacas >= 2) 
            deathProbability /= 4.0;
        return deathProbability;
    }

    @Override
    public String toString() 
    {
        return this.getName() + " [alive=" + this.getAlive() + "]";
    }

}