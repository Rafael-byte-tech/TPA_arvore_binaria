package testing;

import java.util.Comparator;

public class ComparatorInteger implements Comparator<Integer>
{
    public int compare(Integer d1, Integer d2)
    {
        return Integer.compare(d1, d2);
    }
}