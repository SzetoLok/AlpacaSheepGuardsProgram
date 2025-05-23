public abstract class Animal
{
    private boolean alive;
    private String name;

    public Animal()
    {
        this.alive = false;
        this.name = "Unknown";
    }

    public Animal(boolean alive, String name)
    {
        this.alive = alive;
        this.name = name;
    }      
    
    public void die() 
    {
        this.alive = false;
    }

    public void display()
    {
        System.out.println(this.toString());
    }

    public boolean getAlive() 
    {
        return this.alive;
    }
    
    public abstract double getDeathProbability(Predator predator, int numberOfAlpacas);
    
    public String getName() 
    {
        return this.name;
    }
    
    public void setAlive(boolean alive) 
    {
        this.alive = alive;
    }

    public void setName(String name) 
    {
        this.name = name;
    }
    
    @Override
    public String toString() 
    {
        return this.name + " [alive=" + this.alive + "]";
    }
}
