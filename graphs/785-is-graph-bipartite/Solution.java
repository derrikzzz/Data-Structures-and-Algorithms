// LeetCode 785. Is Graph Bipartite?
//
// Undirected graph with n nodes labeled 0..n-1. graph[u] lists the nodes adjacent
// to u. Return true if the nodes can be split into two sets A and B so that every
// edge connects a node in A to a node in B.
//
// Constraints: 1 <= n <= 100; 0 <= graph[u].length < n; no self-edges, no
//              parallel edges; if v is in graph[u] then u is in graph[v];
//              the graph may be disconnected.
//
// Approach: BFS 2-coloring (color each node opposite to its parent; a neighbor
//           with the same color means an odd cycle); start a BFS from every
//           uncolored node so every component is checked
// Time: O(?)
// Space: O(?)
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

public class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;

        int[] colors = new int[n];

        for (int i = 0; i < n; i++) {
            if (colors[i] != 0) {
                continue;
            }

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            colors[i] = 1;

            while (!queue.isEmpty()) {
                int current = queue.poll();

                for (int neighbor : graph[current]) {
                    if (colors[neighbor] == 0) {
                        colors[neighbor] = -colors[current];
                        queue.offer(neighbor);
                    } else if (colors[neighbor] == colors[current]) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] inputs = {
            {{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}},              // LeetCode example 1
            {{1, 3}, {0, 2}, {1, 3}, {0, 2}},                    // LeetCode example 2
            {{}},                                                // one node, no edges
            {{}, {}, {}},                                        // only isolated nodes
            {{1, 2, 3}, {0}, {0}, {0}},                          // star (a tree is always bipartite)
            {{1, 5}, {0, 2}, {1, 3}, {2, 4}, {3, 5}, {4, 0}},    // even cycle of 6
            {{1, 4}, {0, 2}, {1, 3}, {2, 4}, {3, 0}},            // odd cycle of 5
            {{1}, {0}, {3}, {2}},                                // two components, both fine
            {{1}, {0}, {3, 4}, {2, 4}, {2, 3}},                  // triangle is in the 2nd component
            {{}, {2, 3}, {1, 3}, {1, 2}},                        // node 0 isolated: BFS only from 0 misses the triangle
        };
        boolean[] expected = {false, true, true, true, true, true, false, true, false, false};

        int passed = 0;
        for (int t = 0; t < inputs.length; t++) {
            boolean got = s.isBipartite(inputs[t]);
            boolean ok = got == expected[t];
            if (ok) passed++;
            System.out.printf("%-4s graph=%s -> %b (expected %b)%n", ok ? "ok" : "FAIL",
                    Arrays.deepToString(inputs[t]), got, expected[t]);
        }
        System.out.printf("%n%d/%d passed%n", passed, inputs.length);
    }
}
