import java.util.ArrayList;

/**
 * The class WeatherPatterns finds the longest span of days in which
 * each day’s temperature is higher than on the previous day in that sequence.
 *
 * @author Zach Blick
 * @author Beckett Porter
 */

public class WeatherPatterns
{
    private static ArrayList<Integer>[] adjacencyLists;
    private static int[] longestPaths;
    /**
     * Longest Warming Trend
     * @param temperatures
     * @return the longest run of days with increasing temperatures
     */
    public static int longestWarmingTrend(int[] temperatures)
    {
        adjacencyLists = new ArrayList[temperatures.length];
        longestPaths = new int[temperatures.length];

        for (int i = 0; i < adjacencyLists.length; i++)
        {
            adjacencyLists[i] = new ArrayList<>();
        }

        for (int i = 0; i < temperatures.length; i++)
        {
            for (int j = 0; j < i; j++)
            {
                // temps[i] = current temp we are checking,
                if (temperatures[j] < temperatures[i])
                {
                    adjacencyLists[i].add(j);
                }
            }
        }

        int currentMaxFound = 0;
        for (ArrayList<Integer> run : adjacencyLists)
        {
            int longest = findLongestPathTo(run);
            if (longest > currentMaxFound)
            {
                currentMaxFound = longest;
            }
        }

        return currentMaxFound;
    }

    private static int findLongestPathTo(ArrayList<Integer> ar)
    {
        int length = 0;
        // What do I return if it is empty ?
        if (ar.isEmpty())
        {
            return length;
        }

        for (int i = 0; i < ar.size(); i++)
        {
            if (longestPaths[i] != 0)
            {
                length = Math.max(ar.get(i), longestPaths[i]);
            }
            else
            {
                int n = findLongestPathTo(adjacencyLists[i]);

                length = Math.max(ar.get(i), n);

                longestPaths[i] = n;
            }
        }
        return 1 + length;
    }
}