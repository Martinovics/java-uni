package lab07.classes;

import java.util.Comparator;




public class StrengthComparator implements Comparator<Beer>
{
    public int compare(Beer beer1, Beer beer2)
    {
        return Double.compare(beer1.getStrength(), beer2.getStrength());
    }
}
