package lab07.classes;

import java.util.Comparator;




public class StyleComparator implements Comparator<Beer>
{
    public int compare(Beer beer1, Beer beer2)
    {
        return beer2.getName().compareTo(beer1.getName());
    }
}
