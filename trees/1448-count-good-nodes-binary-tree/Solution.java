
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
    public int goodNodes(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE);
    }

    //max represents the maximum number we seen so far
    private int dfs(TreeNode node, int max) {
        if (node == null) {
            return 0;
        }

        int count = 0;

        if (node.val >= max) {
            count++;
            max = node.val;
        }

        int left = dfs(node.left, max);
        int right = dfs(node.right, max);

        return count + left + right;
    }
}