import java.util.List;


class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}

public class Solution {
    public int maxDepth(Node root) {
        return maxDepthHelper(root);
    }

    private int maxDepthHelper(Node node) {
        if (node == null) {
            return 0;
        }

        int maxChildDepth = 0;

        for (Node child : node.children) {
            maxChildDepth = Math.max(maxChildDepth, maxDepthHelper(child));
        }

        return 1 + maxChildDepth;
    }
}
