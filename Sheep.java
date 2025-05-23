public class Sheep extends Animal 
{
    public Sheep() 
    {
        super(true, "Sheep");
    }

    public Sheep(String name) 
    {
        super(true, name);
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
    public String toString() 
    {
        return this.getName() + " [alive=" + this.getAlive() + "]";
    }

}