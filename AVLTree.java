import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * AVL Tree Node - includes height for balance calculations
 */
class AVLNode {
    int value;
    AVLNode left;
    AVLNode right;
    int height; // Height of the subtree rooted at this node
    
    AVLNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.height = 1; // New nodes start with height 1
    }
}

/**
 * AVL Tree implementation with automatic balancing
 * AVL Property: For any node, height difference between left and right subtrees ≤ 1
 */
class AVLTree {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] values = new int[n];

        for (int i = 0; i < n; i++) {
            values[i] = Integer.parseInt(br.readLine());
        }

        AVLNode root = null;
        int sumDepths = 0;

        for (int value : values) {
            if (root == null) {
                root = new AVLNode(value); // Initialize AVL tree with first number as root
                pw.println(sumDepths); // First element is at depth 0
            } else {
                InsertResult result = insertIntoAVL(root, value, 0);
                root = result.newRoot; // Root might change due to rotations
                sumDepths += result.insertionDepth;
                pw.println(sumDepths);
            }
        }

        pw.flush();
        pw.close();
        br.close();
    }

    /**
     * Helper class to return both the new root and insertion depth
     */
    static class InsertResult {
        AVLNode newRoot;
        int insertionDepth;
        
        InsertResult(AVLNode newRoot, int insertionDepth) {
            this.newRoot = newRoot;
            this.insertionDepth = insertionDepth;
        }
    }

    /**
     * Insert a value into AVL tree with balancing
     * Returns both the new root (after potential rotations) and insertion depth
     */
    public static InsertResult insertIntoAVL(AVLNode node, int value, int currentDepth) {
        // Step 1: Perform normal BST insertion
        if (node == null) {
            return new InsertResult(new AVLNode(value), currentDepth);
        }

        int insertionDepth;
        if (value < node.value) {
            InsertResult leftResult = insertIntoAVL(node.left, value, currentDepth + 1);
            node.left = leftResult.newRoot;
            insertionDepth = leftResult.insertionDepth;
        } else if (value > node.value) {
            InsertResult rightResult = insertIntoAVL(node.right, value, currentDepth + 1);
            node.right = rightResult.newRoot;
            insertionDepth = rightResult.insertionDepth;
        } else {
            // Duplicate values - don't insert, return current depth
            return new InsertResult(node, currentDepth);
        }

        // Step 2: Update height of current node
        updateHeight(node);

        // Step 3: Get balance factor and perform rotations if needed
        int balance = getBalance(node);

        // Left Left Case (Right rotation needed)
        if (balance > 1 && value < node.left.value) {
            node = rightRotate(node);
        }
        
        // Right Right Case (Left rotation needed)
        else if (balance < -1 && value > node.right.value) {
            node = leftRotate(node);
        }
        
        // Left Right Case (Left-Right rotation needed)
        else if (balance > 1 && value > node.left.value) {
            node.left = leftRotate(node.left);
            node = rightRotate(node);
        }
        
        // Right Left Case (Right-Left rotation needed)
        else if (balance < -1 && value < node.right.value) {
            node.right = rightRotate(node.right);
            node = leftRotate(node);
        }

        return new InsertResult(node, insertionDepth);
    }

    /**
     * Get height of a node (handles null nodes)
     */
    public static int getHeight(AVLNode node) {
        if (node == null) return 0;
        return node.height;
    }

    /**
     * Update height of a node based on its children
     */
    public static void updateHeight(AVLNode node) {
        if (node != null) {
            node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        }
    }

    /**
     * Get balance factor of a node
     * Balance factor = height(left subtree) - height(right subtree)
     * AVL property: balance factor must be -1, 0, or 1
     */
    public static int getBalance(AVLNode node) {
        if (node == null) return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    /**
     * Right rotate (used for Left-Left case)
     *     y                x
     *    / \              / \
     *   x   T3           T1  y
     *  / \         =>       / \
     * T1  T2               T2  T3
     */
    public static AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        updateHeight(y);
        updateHeight(x);

        // Return new root
        return x;
    }

    /**
     * Left rotate (used for Right-Right case)
     *   x                 y
     *  / \               / \
     * T1  y             x   T3
     *    / \     =>    / \
     *   T2  T3        T1  T2
     */
    public static AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        updateHeight(x);
        updateHeight(y);

        // Return new root
        return y;
    }

    // ========== ADDITIONAL UTILITY METHODS FOR COMPLETENESS ==========

    /**
     * Search for a value in the AVL tree
     */
    public static boolean search(AVLNode root, int value) {
        if (root == null) return false;
        
        if (value == root.value) return true;
        else if (value < root.value) return search(root.left, value);
        else return search(root.right, value);
    }

    /**
     * Inorder traversal (gives sorted output)
     */
    public static void inorderTraversal(AVLNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.print(root.value + " ");
            inorderTraversal(root.right);
        }
    }

    /**
     * Print tree structure with heights and balance factors
     */
    public static void printTree(AVLNode root, String prefix, boolean isLast) {
        if (root == null) return;
        
        System.out.println(prefix + (isLast ? "└── " : "├── ") + 
                          root.value + " (h:" + root.height + ", bf:" + getBalance(root) + ")");
        
        if (root.left != null || root.right != null) {
            if (root.right != null) {
                printTree(root.right, prefix + (isLast ? "    " : "│   "), root.left == null);
            }
            if (root.left != null) {
                printTree(root.left, prefix + (isLast ? "    " : "│   "), true);
            }
        }
    }

    /**
     * Validate AVL property - all nodes must have balance factor between -1 and 1
     */
    public static boolean isValidAVL(AVLNode root) {
        if (root == null) return true;
        
        int balance = getBalance(root);
        if (Math.abs(balance) > 1) return false;
        
        return isValidAVL(root.left) && isValidAVL(root.right);
    }

    /**
     * Count total nodes in the tree
     */
    public static int countNodes(AVLNode root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    /**
     * Demo method showing AVL tree operations
     */
    public static void demonstrateAVL() {
        System.out.println("=== AVL Tree Demonstration ===");
        
        AVLNode root = null;
        int[] values = {10, 20, 30, 40, 50, 25};
        
        System.out.println("Inserting values: " + Arrays.toString(values));
        System.out.println();
        
        for (int value : values) {
            System.out.println("Inserting " + value + ":");
            if (root == null) {
                root = new AVLNode(value);
            } else {
                InsertResult result = insertIntoAVL(root, value, 0);
                root = result.newRoot;
            }
            
            printTree(root, "", true);
            System.out.println("Valid AVL: " + isValidAVL(root));
            System.out.print("Inorder: ");
            inorderTraversal(root);
            System.out.println("\n" + "=".repeat(40));
        }
        
        System.out.println("Final tree statistics:");
        System.out.println("Total nodes: " + countNodes(root));
        System.out.println("Tree height: " + getHeight(root));
        System.out.println("Is valid AVL: " + isValidAVL(root));
    }
}

/**
 * Complete AVL Tree class with all operations
 */
class CompleteAVLTree {
    private AVLNode root;
    
    public void insert(int value) {
        if (root == null) {
            root = new AVLNode(value);
        } else {
            AVLTree.InsertResult result = AVLTree.insertIntoAVL(root, value, 0);
            root = result.newRoot;
        }
    }
    
    public boolean search(int value) {
        return AVLTree.search(root, value);
    }
    
    public void printTree() {
        if (root == null) {
            System.out.println("Empty tree");
        } else {
            AVLTree.printTree(root, "", true);
        }
    }
    
    public void inorderTraversal() {
        AVLTree.inorderTraversal(root);
        System.out.println();
    }
    
    public boolean isValidAVL() {
        return AVLTree.isValidAVL(root);
    }
    
    public int getHeight() {
        return AVLTree.getHeight(root);
    }
    
    public int size() {
        return AVLTree.countNodes(root);
    }
    
    /**
     * Delete operation for AVL tree (bonus implementation)
     */
    public void delete(int value) {
        root = deleteFromAVL(root, value);
    }
    
    private AVLNode deleteFromAVL(AVLNode root, int value) {
        // Step 1: Perform normal BST deletion
        if (root == null) return root;
        
        if (value < root.value) {
            root.left = deleteFromAVL(root.left, value);
        } else if (value > root.value) {
            root.right = deleteFromAVL(root.right, value);
        } else {
            // Node to be deleted found
            if (root.left == null || root.right == null) {
                AVLNode temp = (root.left != null) ? root.left : root.right;
                
                if (temp == null) {
                    temp = root;
                    root = null;
                } else {
                    root = temp; // Copy contents of non-empty child
                }
            } else {
                // Node with two children: get inorder successor
                AVLNode temp = findMin(root.right);
                root.value = temp.value;
                root.right = deleteFromAVL(root.right, temp.value);
            }
        }
        
        if (root == null) return root;
        
        // Step 2: Update height
        AVLTree.updateHeight(root);
        
        // Step 3: Get balance and perform rotations if needed
        int balance = AVLTree.getBalance(root);
        
        // Left Left Case
        if (balance > 1 && AVLTree.getBalance(root.left) >= 0) {
            return AVLTree.rightRotate(root);
        }
        
        // Left Right Case
        if (balance > 1 && AVLTree.getBalance(root.left) < 0) {
            root.left = AVLTree.leftRotate(root.left);
            return AVLTree.rightRotate(root);
        }
        
        // Right Right Case
        if (balance < -1 && AVLTree.getBalance(root.right) <= 0) {
            return AVLTree.leftRotate(root);
        }
        
        // Right Left Case
        if (balance < -1 && AVLTree.getBalance(root.right) > 0) {
            root.right = AVLTree.rightRotate(root.right);
            return AVLTree.leftRotate(root);
        }
        
        return root;
    }
    
    private AVLNode findMin(AVLNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
