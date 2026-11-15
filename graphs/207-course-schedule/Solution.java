import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

// Time: O(V + E) — build graph + process each node and edge once
// Space: O(V + E) — adjacency list + in-degree array + queue
public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        int[] inDegrees = new int[numCourses];

        for (int[] p : prerequisites) {
            graph.get(p[1]).add(p[0]); // b -> a, [a, b]
            // p = [a, b]: take b before a. The edge b -> a points INTO a, so a gets one
            // more prerequisite. In-degree goes up for whichever node the edge points to.
            inDegrees[p[0]]++;
        }

        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                queue.add(i);
            }
        }

        int taken = 0;

        while (!queue.isEmpty()) {
            int course = queue.poll();
            taken++;

            for (int next : graph.get(course)) {
                inDegrees[next]--;
                if (inDegrees[next] == 0) {
                    queue.add(next);
                }
            }
        }

        return taken == numCourses;
    }
}
