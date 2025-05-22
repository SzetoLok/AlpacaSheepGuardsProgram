public abstract class Animal
{
    private boolean alive;

    public Animal()
    {
        this.alive = true;
    }

    public Animal(boolean alive)
    {
        this.alive = alive;
    }      
    
    public void die() 
    {
        this.alive = false;
    }

    public void display()
    {
        System.out.print(this.toString());
    }

    public boolean getAlive() 
    {
        return this.alive;
    }
    
    public void setAlive(boolean alive) 
    {
        this.alive = alive;
    }
    
    @Override
    public String toString() 
    {
        return "Animal [alive=" + this.alive + "]";
    }
}
