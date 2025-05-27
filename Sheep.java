public class Sheep extends Animal 
{
    public Sheep() 
    {
        super(false, "Unknown");
    }

    public Sheep(boolean alive, String name) 
    {
        super(alive, name);
    }
    
    @Override
    public double getDeathProbability(Predator predator, int numberOfAlpacas) 
    {
        double deathProbability = predator.getDangerFactor();
        if (numberOfAlpacas == 1) 
            deathProbability /= 2.0;
        else if (numberOfAlpacas >= 2) 
            deathProbability /= 4.0;
        return deathProbability;
    }

    @Override
    public double getDeathProbability(double deathProbability, int numberOfAlpacas) 
    {
        if (numberOfAlpacas == 1) 
            deathProbability /= 2.0;
        else if (numberOfAlpacas >= 2) 
            deathProbability /= 4.0;
        return deathProbability;
    }
}