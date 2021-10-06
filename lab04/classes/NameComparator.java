package lab04.classes;

import java.util.Comparator;




public class NameComparator implements Comparator<Beer>
{
    public int compare(Beer beer1, Beer beer2)
    {
        return beer1.getName().compareTo(beer2.getName());
    }
}
