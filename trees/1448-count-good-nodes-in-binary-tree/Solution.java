public class Solution {
    public int goodNodes(TreeNode root) {
        return goodNodesHelper(root, Integer.MIN_VALUE);
    }

    private int goodNodesHelper(TreeNode node, int maxSoFar) {
        if (node == null) {
            return 0;
        }

        int count = 0;

        if (node.val >= maxSoFar) {
            // Good node is found, update count and maxSoFar
            count++;
            maxSoFar = node.val;
        }

        int left = goodNodesHelper(node.left, maxSoFar);
        int right = goodNodesHelper(node.right, maxSoFar);

        return count + left + right;
    }
}
