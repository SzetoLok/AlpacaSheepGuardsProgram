public class Sheep extends Animal 
{
    private String name;

    public Sheep() 
    {
        super();
        this.name = "sheep";
    }

    public Sheep(String name) 
    {
        super();
        this.name = name;
    }
    
    @Override
    public void display() 
    {
        System.out.println(this.toString());
    }

    public String getName()
    {
        return this.name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    @Override
    public String toString() 
    {
        return this.getName() + " [alive=" + getAlive() + "]";
    }
}
