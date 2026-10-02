package interval-dp.56-merge-intervals;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int[][] merge(int[][] intervals) {
        // handle empty input
        if (intervals.length == 0) {
            return new int[0][];
        }

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();
        merged.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] currentInterval = intervals[i];
            int[] lastMerged = merged.get(merged.size() - 1);

            if (currentInterval[0] <=lastMerged[1]) {
                lastMerged[1] = Math.max(lastMerged[1], currentInterval[1]);
            } else {
                merged.add(currentInterval);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }
}
