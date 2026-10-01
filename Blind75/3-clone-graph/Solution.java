import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


// LeetCode 133. Clone Graph (Blind 75 - Graph)
//
// Deep-copy a connected undirected graph. DFS from the start node, using a memo
// (original node -> its clone) so each node is cloned exactly once. The memo is
// what makes cycles safe: on revisiting a node we return its existing clone
// instead of recursing forever.
public class Solution {

    // Node definition (provided by LeetCode).
    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node(int val) {
            this.val = val;
            this.neighbors = new ArrayList<>();
        }
    }

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        return dfs(node, new HashMap<>());
    }

    private Node dfs(Node node, Map<Node, Node> memo) {
        // Already cloned? Return the stored copy (handles cycles + revisits).
        Node cached = memo.get(node);
        if (cached != null) {
            return cached;
        }

        // Create the clone before recursing, and record it in the memo now, so a
        // neighbour that points back to this node finds it and stops recursing.
        Node clone = new Node(node.val);
        memo.put(node, clone);

        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(dfs(neighbor, memo));
        }
        return clone;
    }

    private Node bfsIterative(Node node) {
        if (node == null) {
            return null;
        }

        Map<Node, Node> memo = new HashMap<>();   // original -> clone
        memo.put(node, new Node(node.val));        // seed the start node's clone

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);                          

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (Node neighbor : cur.neighbors) {
                if (!memo.containsKey(neighbor)) {
                    // First time seeing this neighbor: clone it and enqueue it.
                    memo.put(neighbor, new Node(neighbor.val));
                    queue.add(neighbor);
                }
                // Wire the edge on the clone side (works whether neighbor is new or not).
                memo.get(cur).neighbors.add(memo.get(neighbor));
            }
        }

        return memo.get(node);
    }
}
