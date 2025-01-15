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
    /**
     * Longest Warming Trend
     * @param temperatures
     * @return the longest run of days with increasing temperatures
     */
    public static int longestWarmingTrend(int[] temperatures)
    {
        ArrayList<Integer>[] runs = new ArrayList[temperatures.length];

        for (int i = 0; i < runs.length; i++)
        {
            runs[i] = new ArrayList<>();
            runs[i].add(temperatures[i]);
        }

        for (int i = 0; i < temperatures.length; i++)
        {
            for (int j = 0; j < i; j++)
            {
                // If the given temp is greater than the highest temp (the last temp) in a given run, add it to the run.
                if (temperatures[i] > runs[j].getLast())
                {
                    // copy, fix this
                    ArrayList<Integer> copy = new ArrayList<>();

                    copy.addAll(runs[j]);

                    copy.add(temperatures[i]);

                    runs[i] = copy;
                }
            }
        }

        int currentMaxFound = 0;
        for (ArrayList<Integer> run : runs)
        {
            if (run.size() > currentMaxFound)
            {
                currentMaxFound = run.size();
            }
        }

        return currentMaxFound;
    }
}