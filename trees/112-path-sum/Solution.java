// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        int currentSum = 0;
        return hasPathSumHelper(root, targetSum, currentSum);
    }

    private boolean hasPathSumHelper(TreeNode node, int targetSum, int currentSum) {
        if (node == null) {
            return false;
        }

        currentSum += node.val;

        if (node.left == null && node.right == null) {
            return currentSum == targetSum;
        }

        return hasPathSumHelper(node.left, targetSum, currentSum) || hasPathSumHelper(node.right, targetSum, currentSum);
    }
}