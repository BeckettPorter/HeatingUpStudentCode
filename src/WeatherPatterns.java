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
    // Create these as instance variables because they are used in multiple places. They are re-initialized each
    // run through longestWarmingTrend.
    private static ArrayList<Integer>[] adjacencyLists;
    private static int[] longestPaths;
    /**
     * Longest Warming Trend
     * @param temperatures
     * @return the longest run of days with increasing temperatures
     */
    public static int longestWarmingTrend(int[] temperatures)
    {
        // Create the adjacency list to keep track of which previous days lead to a given day.
        adjacencyLists = new ArrayList[temperatures.length];
        // Create the longest paths array to keep track of the current longest calculated path to a given point.
        longestPaths = new int[temperatures.length];

        // Go through the entire adjacency list and initialize it with empty arrayLists.
        for (int i = 0; i < adjacencyLists.length; i++)
        {
            adjacencyLists[i] = new ArrayList<>();
        }

        // Go through each day's temperature (or as I call it, point), and compare it to all previous points. If we
        // find that the earlier point's temp is lower than the current point, this means this earlier point has an
        // edge with the current point and points to it, so add it to the current point's adjacency list.
        for (int i = 0; i < temperatures.length; i++)
        {
            for (int j = 0; j < i; j++)
            {
                // temps[i] = current temp we are checking, temps[j] = previous temp we are checking
                if (temperatures[j] < temperatures[i])
                {
                    adjacencyLists[i].add(j);
                }
            }
        }

        // For each point in our adjacency list, calculate the longest path that goes to it and
        // keep the highest we find.
        int currentMaxFound = 0;
        for (ArrayList<Integer> point : adjacencyLists)
        {
            currentMaxFound = Math.max(findLongestPathTo(point), currentMaxFound);
        }

        return currentMaxFound;
    }

    // Recursive method that finds the longest path to a given point. It takes in an arrayList of all the points that
    // point to the target point.
    private static int findLongestPathTo(ArrayList<Integer> currentPoint)
    {
        int length = 0;

        // For each point that goes to the point we are currently at
        for (int incomingPoint : currentPoint)
        {
            // If this path has not already been calculated
            if (longestPaths[incomingPoint] == 0)
            {
                // Then calculate the longest path for this point.
                longestPaths[incomingPoint] = findLongestPathTo(adjacencyLists[incomingPoint]);
            }
            // And set the length to either the newly found longestPath or to length depending on which is bigger.
            length = Math.max(length, longestPaths[incomingPoint]);
        }
        // Return 1 + length to account for visiting this point.
        return 1 + length;
    }
}