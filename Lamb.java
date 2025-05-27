public class Lamb extends Animal 
{
    public Lamb() 
    {
        super(false, "Unknown");
    }

    public Lamb(boolean alive, String name) 
    {
        super(alive, name);
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
    public double getDeathProbability(double deathProbability, int numberOfAlpacas) 
    {
        deathProbability *= 2.0;
        if (numberOfAlpacas == 1) 
            deathProbability /= 2.0;
        else if (numberOfAlpacas >= 2) 
            deathProbability /= 4.0;
        return deathProbability;
    }
}