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
    /**
     * Longest Warming Trend
     * @param temperatures
     * @return the longest run of days with increasing temperatures
     */
    public static int longestWarmingTrend(int[] temperatures)
    {
        // Do I actually need to store this as an arraylist or could it just be a single int
        // with the highest temp for that run?
        adjacencyLists = new ArrayList[temperatures.length];

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

        for (int i = 0; i < ar.size(); i++)
        {
            length = Math.max(ar.get(i), findLongestPathTo(adjacencyLists[i]));
        }
        return 1 + length;
    }
}