public class Predator 
{
    private String name;
    private double dangerFactor;
    
    public Predator()
    {
        this.name = "Unknown";
        this.dangerFactor = 0.0;
    }

    public Predator(String name, double dangerFactor) 
    {
        this.name = name;
        this.dangerFactor = dangerFactor;
    }

    public void display()
    {
        System.out.println(this.toString());
    }
    
    public double getDangerFactor() 
    {
        return this.dangerFactor;
    }

    public String getName() 
    {
        return this.name;
    }
        
    public void setDangerFactor(double dangerFactor) 
    {
        this.dangerFactor = dangerFactor;
    }

    public void setName(String name) 
    {
        this.name = name;
    }
    
    @Override
    public String toString() 
    {
        return "Predator [name=" + name + ", dangerFactor=" + dangerFactor + "]";
    }

}
