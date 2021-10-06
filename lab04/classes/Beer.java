package lab04.classes;

import java.io.Serializable;




public class Beer implements Serializable
{

    private String name;
    private String style;
    private double strength;

    public Beer(String name, String style, double strength)
    {
        this.name = name;
        this.style = style;
        this.strength = strength;
    }


    public String getName()
    {
        return this.name;
    }


    public String getStyle()
    {
        return this.style;
    }


    public double getStrength()
    {
        return this.strength;
    }


    @Override
    public String toString()
    {
        return "name=" + this.name + " style=" + this.style + " strength=" + this.strength;
    }

}
