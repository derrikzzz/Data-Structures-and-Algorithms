// Time: O(n + E * alpha(n)) — n to init UFDS, E union/find calls each near O(1) amortized with path compression + union by rank
// Space: O(n) — parent and rank arrays
class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        UFDS ufds = new UFDS(n);
        for (int[] edge : edges) {
            ufds.union(edge[0], edge[1]);
        }
        return ufds.find(source) == ufds.find(destination);
    }
}

class UFDS {
    private int[] parent;
    private int[] rank;
    private int count;

    public UFDS(int n) {
        parent = new int[n];
        rank = new int[n];
        count = n;
        // initially, each element is in its own set
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        parent[x] = find(parent[x]); // Path compression
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) return;

        // Union by rank
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        count--;
    }
}
