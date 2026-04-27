class Solution {
    // TreeNode definition (provided by LeetCode)
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right; 

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode (int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isValidBST(TreeNode root) {
        return isValidTreeHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidTreeHelper(TreeNode node, long leftLimit, long rightLimit) {
        if (node == null) {
            return true;
        }

        if (node.val <= leftLimit || node.val >= rightLimit) {
            return false;
        }

        return isValidTreeHelper(node.left, leftLimit, node.val) && isValidTreeHelper(node.right, node.val, rightLimit);
    }
}
