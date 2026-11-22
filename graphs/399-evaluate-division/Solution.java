// LeetCode 399. Evaluate Division
//
// equations[i] = [A_i, B_i] and values[i] say A_i / B_i = values[i]. Each variable
// is a string. For each query [C_j, D_j], return C_j / D_j, or -1.0 if it can't be
// determined. A variable that never appears in equations is undefined, so x / x
// for an unknown x is -1.0.
//
// Constraints: 1 <= equations.length <= 20; 1 <= A_i.length, B_i.length <= 5;
//              0.0 < values[i] <= 20.0; 1 <= queries.length <= 20;
//              no contradictions and no division by zero.
//
// Approach: BFS over a weighted graph (edge a -> b with weight a/b, and b -> a with
//           weight 1/(a/b)); multiply weights along the path from C to D
// Time: O(?)
// Space: O(?)
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Solution {
    class Pair {
        String node;
        double weight;

        Pair(String node, double weight) {
            this.node = node;
            this.weight = weight;
        }
    }
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, List<Pair>> graph = new HashMap<>();

        // Build weighted adjancency list
        for (int i = 0; i < equations.size(); i++) {
            String u = equations.get(i).get(0);
            String v = equations.get(i).get(1);
            double val = values[i];
            
            graph.putIfAbsent(u, new ArrayList<>());
            graph.putIfAbsent(v, new ArrayList<>());
            
            graph.get(u).add(new Pair(v, val));
            graph.get(v).add(new Pair(u, 1.0 / val));
        }

        // Process each query using BFS
        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String start = queries.get(i).get(0);
            String end = queries.get(i).get(1);
            results[i] = bfs(start, end, graph);
        }
        
        return results;
    }

    private double bfs(String start, String end, Map<String, List<Pair>> graph) {
        // If either variable does not exist in graph
        if (!graph.containsKey(start) || !graph.containsKey(end)) {
            return -1.0;
        }

        if (start.equals(end)) {
            return 1.0;
        }

        Queue<Pair> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(new Pair(start, 1.0));
        visited.add(start);

        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            String currNode = current.node;
            double currProduct = current.weight;

            if (currNode.equals(end)) {
                return currProduct;
            }

            for (Pair neighbor : graph.get(currNode)) {
                if (!visited.contains(neighbor.node)) {
                    visited.add(neighbor.node);
                    queue.offer(new Pair(neighbor.node, currProduct * neighbor.weight));
                }
            }
        }

        return -1.0;
    }

    private static List<List<String>> pairs(String[][] raw) {
        return Arrays.stream(raw).map(Arrays::asList).toList();
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String[][][] equations = {
            {{"a", "b"}, {"b", "c"}},                            // LeetCode example 1
            {{"a", "b"}, {"b", "c"}, {"bc", "cd"}},              // LeetCode example 2
            {{"a", "b"}},                                        // LeetCode example 3
            {{"a", "b"}, {"c", "d"}},                            // two separate components
            {{"a", "b"}, {"b", "c"}, {"c", "a"}},                // cycle: don't loop forever
            {{"a", "b"}, {"b", "c"}, {"c", "d"}, {"d", "e"}},    // long chain: multiply all the way
        };
        double[][] values = {
            {2.0, 3.0},
            {1.5, 2.5, 5.0},
            {0.5},
            {2.0, 3.0},
            {2.0, 3.0, 1.0 / 6},
            {2.0, 2.0, 2.0, 2.0},
        };
        String[][][] queries = {
            {{"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}},
            {{"a", "c"}, {"c", "b"}, {"bc", "cd"}, {"cd", "bc"}},
            {{"a", "b"}, {"b", "a"}, {"a", "c"}, {"x", "y"}},
            {{"a", "d"}, {"c", "d"}, {"d", "c"}, {"b", "b"}},     // across components is -1
            {{"a", "c"}, {"c", "a"}, {"b", "a"}},
            {{"a", "e"}, {"e", "a"}, {"c", "e"}},
        };
        double[][] expected = {
            {6.0, 0.5, -1.0, 1.0, -1.0},
            {3.75, 0.4, 5.0, 0.2},
            {0.5, 2.0, -1.0, -1.0},
            {-1.0, 3.0, 1.0 / 3, 1.0},
            {6.0, 1.0 / 6, 0.5},
            {16.0, 0.0625, 4.0},
        };

        int passed = 0;
        for (int t = 0; t < equations.length; t++) {
            double[] got = s.calcEquation(pairs(equations[t]), values[t], pairs(queries[t]));
            boolean ok = got.length == expected[t].length;
            for (int i = 0; ok && i < got.length; i++) {
                ok = Math.abs(got[i] - expected[t][i]) < 1e-5;
            }
            if (ok) passed++;
            System.out.printf("%-4s equations=%s values=%s queries=%s%n     -> %s (expected %s)%n",
                    ok ? "ok" : "FAIL", Arrays.deepToString(equations[t]), Arrays.toString(values[t]),
                    Arrays.deepToString(queries[t]), Arrays.toString(got), Arrays.toString(expected[t]));
        }
        System.out.printf("%n%d/%d passed%n", passed, equations.length);
    }
}
