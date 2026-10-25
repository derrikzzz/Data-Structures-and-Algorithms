// Time: O(n) — visits every node once
// Space: O(h) — recursion stack, h = height of tree
public class Solution {
    public class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    private int maxLen = 0;

    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return maxLen;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;

        int left = dfs(node.left);
        int right = dfs(node.right);

        int leftArm = (node.left != null && node.left.val == node.val) ? left + 1 : 0;
        int rightArm = (node.right != null && node.right.val == node.val) ? right + 1 : 0;

        maxLen = Math.max(maxLen, leftArm + rightArm);
        return Math.max(leftArm, rightArm);
    }
}
