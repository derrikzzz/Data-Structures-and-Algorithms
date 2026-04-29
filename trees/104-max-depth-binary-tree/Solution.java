class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public int maxDepth(TreeNode root) {
        Integer maxDepth = maxDepthHelper(root);
        return maxDepth;
    }

    private Integer maxDepthHelper(TreeNode node) {
        if (node == null) {
            return 0;
        }

        Integer leftHeight = maxDepthHelper(node.left);
        Integer rightHeight = maxDepthHelper(node.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }
}