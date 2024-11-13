// Binary Search Tree data structure with no duplicate values
//
// Author: Matthew Welk
// Date: February 25, 2017

import java.lang.Math;
import java.util.ArrayList;

public class BST {
    
    // Data structure to represent each node within the tree
    static class Node {
        
        public Node left;
        public Node right;
        public int data;
        
        public Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    Node root;

    // Constructor
    public BST() {
        root = null;
    }

    // Overloaded Constructor
    public BST(int val) {
        this.root = new Node(val);
    }

    // Insert value into tree
    // -------------------------------------------------------
    // INPUT
    // val - value to insert into tree
    // -------------------------------------------------------
    // Time Complexity = O(log n) [avg]; O(n) [worst]
    public void insert(int val) {
        root = insert(root, val);
    }

    // Recursive method to insert value into tree and return new root node.
    // No duplicate values.
    private Node insert(Node root, int val) {
        if(root == null) {                    // base case
            return new Node(val);
        } else if(root.data == val) {         // value is already present, return
            return root;
        } else if(root.data > val) {
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }
        return root;
    }

    // Search the tree for a specified value
    // -------------------------------------------------------
    // INPUT
    // val - value to search for
    // OUTPUT
    // True if val is found
    // False if val is not found
    // -------------------------------------------------------
    // Time Complexity - O(log n) [avg]; O(n) [worst]
    public boolean search(int val) {
        return search(root, val);
    }

    // Recursive function to search for a value in the tree
    private boolean search(Node root, int val) {
        if(root == null) {                      // Value not found
            return false;
        } else if(root.data == val) {           // Value found
            return true;
        } else if(root.data > val) {            // Search left subtree
            return search(root.left, val);
        } else {                                // Search right subtree
            return search(root.right, val);
        }
    }

    // Delete a node from the tree
    // -------------------------------------------------------
    // INPUT
    // val - value to be deleted from the tree
    // -------------------------------------------------------
    // Time Complexity - O(log n) [avg]; O(n) [worst]
    public void delete(int val) {
        root = delete(root, val);
    }

    // Recursive function to delete a node from the tree
    private Node delete(Node root, int val) {
        if(root == null) {
            return root;
        } else if(root.data > val) {
            root.left = delete(root.left, val);
        } else if(root.data < val) {
            root.right = delete(root.right, val);
        } else {
            // Node with one child or no children
            if(root.left == null) {
                return root.right;
            } else if(root.right == null) {
                return root.left;
            // Node with two children
            } else {
                // Replace node to be deleted with minimum value in right subtree
                root.data = getMin(root.right);
                // Delete minimum value in right subtree (duplicate node)
                root.right = delete(root.right, root.data);
            }
        }
        return root;
    }

    // Return total node count of tree
    // -------------------------------------------------------
    // Time Complexity - O(n) -> must reach every node exactly once
    public int getNodeCount() {
        return getNodeCount(root);
    }

    // Recursive function to get total node count
    private int getNodeCount(Node root) {
        if(root == null) {
            return 0;
        }
        // Return count of left subtree plus count of right subtree plus 1 for root node
        return 1 + getNodeCount(root.left) + getNodeCount(root.right);
    }

    // Return total leaf node count of tree. A leaf node is defined as node with no children.
    // -------------------------------------------------------
    // Time Complexity - O(n) -> must reach every node exactly once
    public int getLeafNodeCount() {
        return getLeafNodeCount(root);
    }

    // Recursive function to get total leaf node count
    private int getLeafNodeCount(Node root) {
        if(root == null) {
            return 0;
        } else if(root.left == null && root.right == null) {
            return 1;
        } else {
            return getLeafNodeCount(root.left) + getLeafNodeCount(root.right);
        }
    }

    // Return height of the tree
    // Height is defined as the number of nodes on the longest path from the root to a leaf.
    // Height of tree with one node is one.
    // -------------------------------------------------------
    // Time Complexity - O(n) -> must reach every node exactly once
    public int getHeight() {
        return getHeight(root);
    }

    // Recursive function to get the height of the tree
    private int getHeight(Node root) {
        if(root == null) {
            return 0;
        } else {
            return 1 + Math.max(getHeight(root.left), getHeight(root.right));
        }
    }

    // Return minimum value of tree
    // -------------------------------------------------------
    // Time Complexity - O(n) [worst] -> tree is entirely skewed left
    public int getMin() {
        return getMin(root);
    }

    // Return the minimum value of the subtree from the given root
    // --------------------------------------------------------
    // INPUT
    // root - root node of the subtree to get the minimum value from
    public int getMin(Node root) {
        while(root.left != null) {
            root = root.left;
        }
        return root.data;
    }

    // Return maximum value of tree
    // -------------------------------------------------------
    // Time Complexity - O(n) [worst] -> tree is entirely skewed right
    public int getMax() {
        return getMax(root);
    }

    // Return the maximum value of the subtree from the given root
    // --------------------------------------------------------
    // INPUT
    // root - root node of the subtree to get the maximum value from
    public int getMax(Node root) {
        while(root.right != null) {
            root = root.right;
        }
        return root.data;
    }

    // Delete all nodes in the tree
    // -------------------------------------------------------
    // Time Complexity - O(n) -> must reach every node exactly once
    public void deleteTree() {
        root = deleteTree(root);
    }

    // Recursive function to delete all nodes in the tree
    private Node deleteTree(Node root) {
        if(root != null) {
            root.left = deleteTree(root.left);
            root.right = deleteTree(root.right);
            root = null;
        }
        return root;
    } 

    // Return list of values with in-order traversal
    // -------------------------------------------------------
    // Time Complexity - O(n) -> must reach every node exactly once
    public ArrayList<Integer> inOrderTraversal() {
        ArrayList<Integer> nodes = new ArrayList<Integer>();
        return inOrderTraversal(root, nodes);
    }

    // Recursive function to get values with in-order traversal
    private ArrayList<Integer> inOrderTraversal(Node root, ArrayList<Integer> nodes) {
        if(root != null) {
            inOrderTraversal(root.left, nodes);
            nodes.add(root.data);
            inOrderTraversal(root.right, nodes);
        }
        return nodes;
    }

    // Return list of values with pre-order traversal
    // -------------------------------------------------------
    // Time Complexity - O(n) -> must reach every node exactly once
    public ArrayList<Integer> preOrderTraversal() {
        ArrayList<Integer> nodes = new ArrayList<Integer>();
        return preOrderTraversal(root, nodes);
    }

    // Recursive function to get values with pre-order traversal
    private ArrayList<Integer> preOrderTraversal(Node root, ArrayList<Integer> nodes) {
        if(root != null) {
            nodes.add(root.data);
            preOrderTraversal(root.left, nodes);
            preOrderTraversal(root.right, nodes);
        }
        return nodes;
    }   

    // Return list of values with post-order traversal
    // -------------------------------------------------------
    // Time Complexity - O(n) -> must reach every node exactly once
    public ArrayList<Integer> postOrderTraversal() {
        ArrayList<Integer> nodes = new ArrayList<Integer>();
        return postOrderTraversal(root, nodes);
    }

    // Recursive function to print values in order
    private ArrayList<Integer> postOrderTraversal(Node root, ArrayList<Integer> nodes) {
        if(root != null) {
            postOrderTraversal(root.left, nodes);
            postOrderTraversal(root.right, nodes);
            nodes.add(root.data);
        }
        return nodes;
    }

    public Node getRoot() {
        return this.root;
    }
}
