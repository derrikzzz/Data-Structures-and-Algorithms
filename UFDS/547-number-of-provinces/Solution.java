
public class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        UFDS ufds = new UFDS(n);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) { //start j from i+1 to avoid redundant checks and self-connections
                if (isConnected[i][j] == 1) {
                    ufds.union(i, j);
                }
            }
        }

        return ufds.getCount();
    }
}

class UFDS {
    private int[] parent;
    private int[] rank;

    //count to keep track of number of disjoint sets
    private int count;

    public UFDS(int n) {
        parent = new int[n];
        rank = new int[n];
        count = n; //initially, each element is in its own set
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int i) {
        if (parent[i] == i) {
            return i;
        }

        //path compression
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
        count--; //decrement count as two sets are merged into one
    }

    public int getCount() {
        return count;
    }

}