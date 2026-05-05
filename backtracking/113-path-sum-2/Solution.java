import java.util.ArrayList;
import java.util.List;

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
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(root, new ArrayList<>(), targetSum, result);
        return result;
    }

    private void backtrack(TreeNode node, List<Integer> path, int remaining, List<List<Integer>> result) {
        if (node == null) return;

        path.add(node.val);
        remaining -= node.val;

        if (node.left == null && node.right == null) {
            if (remaining == 0) {
                result.add(new ArrayList<>(path));
            }
        } else {
            backtrack(node.left, path, remaining, result);
            backtrack(node.right, path, remaining, result);
        }

        path.remove(path.size() - 1);   // cleanup point
    }
}