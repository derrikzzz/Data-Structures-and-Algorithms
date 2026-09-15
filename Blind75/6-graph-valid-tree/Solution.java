import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// LeetCode 261. Graph Valid Tree (Blind 75 - Graph)
//
// n vertices labeled 0..n-1 and an undirected edge list. It is a valid tree iff:
//   1. exactly n - 1 edges, AND
//   2. the graph is connected (every vertex reachable from node 0).
// These two together force acyclicity: a connected graph with n-1 edges cannot
// contain a cycle, so no separate cycle check is needed.
public class Solution {

    public boolean validTree(int n, int[][] edges) {
        // A tree on n nodes has exactly n - 1 edges. Wrong count -> not a tree.
        // (Too few => disconnected; too many => must contain a cycle.)
        if (edges.length != n - 1) {
            return false;
        }

        // Build an undirected adjacency list.
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        // BFS from node 0; count how many vertices we can reach.
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        visited[0] = true;
        int seen = 1;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : adj.get(cur)) {
                if (!visited[next]) {
                    visited[next] = true;
                    seen++;
                    queue.add(next);
                }
            }
        }

        // Connected iff we reached every vertex. Combined with the n-1 edge
        // count above, connectivity guarantees it is a valid (acyclic) tree.
        return seen == n;
    }

    // Alternative: Union-Find. Same n-1 edge check, but detect cycles directly:
    // if an edge joins two nodes already in the same set, it closes a cycle.
    // With exactly n-1 edges and no cycle, the graph must be connected -> a tree.
    public boolean validTreeUnionFind(int n, int[][] edges) {
        if (edges.length != n - 1) {
            return false;
        }

        UFDS ufds = new UFDS(n);
        for (int[] e : edges) {
            // Both endpoints already connected => this edge forms a cycle.
            if (ufds.find(e[0]) == ufds.find(e[1])) {
                return false;
            }
            ufds.union(e[0], e[1]);
        }

        // n-1 edges, all unions succeeded (no cycle) => one connected component.
        return ufds.getCount() == 1;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        // Valid tree: 5 nodes, 4 edges, connected, no cycle.
        System.out.println(s.validTree(5, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 4}})); // true
        // Has a cycle (and 5 edges > n-1).
        System.out.println(s.validTree(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}})); // false
        // Correct edge count (n-1 = 3) but disconnected: {0-1, 2-3} + isolated 4.
        System.out.println(s.validTree(5, new int[][]{{0, 1}, {2, 3}, {2, 4}})); // false
        System.out.println(s.validTree(1, new int[][]{})); // true (single node, 0 edges)

        // Same cases via Union-Find should agree with BFS above.
        System.out.println("--- union-find ---");
        System.out.println(s.validTreeUnionFind(5, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 4}})); // true
        System.out.println(s.validTreeUnionFind(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}})); // false
        System.out.println(s.validTreeUnionFind(5, new int[][]{{0, 1}, {2, 3}, {2, 4}})); // false
        System.out.println(s.validTreeUnionFind(1, new int[][]{})); // true
    }
}

class UFDS {
    private final int[] parent;
    private final int[] rank;

    // count keeps track of the number of disjoint sets
    private int count;

    public UFDS(int n) {
        parent = new int[n];
        rank = new int[n];
        count = n; // initially, each element is in its own set
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int i) {
        if (parent[i] == i) {
            return i;
        }
        // path compression
        return parent[i] = find(parent[i]);
    }

    public void union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);

        if (rootI == rootJ) return;

        // Attach smaller rank tree under higher rank tree
        if (rank[rootI] < rank[rootJ]) {
            parent[rootI] = rootJ;
        } else if (rank[rootI] > rank[rootJ]) {
            parent[rootJ] = rootI;
        } else {
            parent[rootJ] = rootI;
            rank[rootI]++;
        }
        count--; // decrement count as two sets are merged into one
    }

    public int getCount() {
        return count;
    }
}
