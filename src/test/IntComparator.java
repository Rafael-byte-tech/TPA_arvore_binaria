package test;

import java.util.Comparator;

public class IntComparator implements Comparator<Integer>
{
    @Override
    public int compare(Integer x, Integer y)
    {
        return Integer.compare(x, y);
    }
}