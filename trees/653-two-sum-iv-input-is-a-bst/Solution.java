import java.util.HashSet;
import java.util.Set;

public class Solution {
    public class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return dfs(root, k, set);
    }

    private boolean dfs(TreeNode node, int k, Set<Integer> set) {
        if (node == null) {
            return false;
        }

        if (set.contains(k - node.val)) {
            return true;
        }

        set.add(node.val);

        // recurse on left and right tree
        return dfs(node.left, k, set) || dfs(node.right, k, set);
    }
}
