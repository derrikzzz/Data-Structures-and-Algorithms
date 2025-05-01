import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

}

class BinarySearchTree {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] values = new int[n];

        for (int i = 0; i < n; i++) {
            values[i] = Integer.parseInt(br.readLine());
        }

        TreeNode root = null;
        int sumDepths = 0;

        for (int value : values) {
            if (root == null) {
                root = new TreeNode(value); //initialise BST with first number as roote
                pw.println(sumDepths); 

            } else {
                sumDepths += insertIntoBST(root, value,  0);//recursive function to insert into BST, keep finding 
                pw.println(sumDepths);
            }
        }

        pw.flush();
        pw.close();
        br.close();
        br.close();

        
    }

    public static int insertIntoBST(TreeNode node, int value, int currentDepthOfNode) {

        if (value < node.value) {
            if (node.left == null) { //no left child
                node.left = new TreeNode(value); //insert as the left child of current node
                return currentDepthOfNode + 1;
            } else {
                return insertIntoBST(node.left, value, currentDepthOfNode + 1);
            }
        } else {
            if (node.right == null) { //no right child
                node.right = new TreeNode(value); //insert as right child of curent node
                return currentDepthOfNode + 1;
            } else {
                return insertIntoBST(node.right, value, currentDepthOfNode + 1);
            }
        }
    }
}