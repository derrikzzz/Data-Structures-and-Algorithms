public class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        invertTreeHelper(root);
        return root;

    }

    private void invertTreeHelper(TreeNode node) {
        if (node == null) {
            return;
        }

        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;

        invertTreeHelper(node.left);
        invertTreeHelper(node.right);
    }
}
