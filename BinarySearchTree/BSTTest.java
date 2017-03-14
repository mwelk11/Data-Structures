// Test class for BST.java
//
// Author: Matthew Welk
// Date: February 26, 2017

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import java.util.ArrayList;

public class BSTTest {

    private BST bst;

    // Setup binary search tree
    //            10
    //           /  \
    //          5    15
    //         /    /  \
    //        2   12    40
    //       / \    \
    //      1   3    13
    @Before
    public void setUp() {
        bst = new BST(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(2);
        bst.insert(3);
        bst.insert(1);
        bst.insert(12);
        bst.insert(13);
        bst.insert(40);
    }

    @Test
    public void testGetNodeCount() {
        assertEquals(9, bst.getNodeCount());
        bst.insert(100);
        assertEquals(10, bst.getNodeCount());
    }

    @Test
    // Test that node count of empty tree is 0
    public void testGetNodeCountEmptyTree() {
        BST emptyBst = new BST();
        assertEquals(0, emptyBst.getNodeCount());
    }

    @Test
    public void testGetLeafNodeCount() {
        assertEquals(4, bst.getLeafNodeCount());
    }

    @Test
    public void testGetHeight() {
        assertEquals(4, bst.getHeight());
        bst.insert(0);
        assertEquals(5, bst.getHeight());
    }

    @Test
    // Test that height of an empty tree is 0
    public void testGetHeightEmptyTree() {
        BST emptyBst = new BST();
        assertEquals(0, emptyBst.getHeight());
    }

    @Test
    // Test that height of a tree with a single node is 1
    public void testGetHeightSingleNodeTree() {
        BST singleNodeBst = new BST(10);
        assertEquals(1, singleNodeBst.getHeight());
    }

    @Test
    // Test that each node was inserted in the proper position
    public void testInsert() {
        assertEquals(bst.getRoot().data, 10);
        assertEquals(bst.getRoot().left.data, 5);
        assertEquals(bst.getRoot().right.data, 15);
        assertEquals(bst.getRoot().left.left.data, 2);
        assertEquals(bst.getRoot().left.left.right.data, 3);
        assertEquals(bst.getRoot().left.left.left.data, 1);
        assertEquals(bst.getRoot().right.left.data, 12);
        assertEquals(bst.getRoot().right.left.right.data, 13);
        assertEquals(bst.getRoot().right.right.data, 40);
    }

    @Test
    // Test that duplicate value is not inserted into the tree
    public void testInsertDupilcateValue() {
        assertEquals(9, bst.getNodeCount());
        bst.insert(5);
        assertEquals(9, bst.getNodeCount());
    }

    @Test
    public void testGetMin() {
        assertEquals(1, bst.getMin());
        bst.insert(0);
        assertEquals(0, bst.getMin());
    }

    @Test
    public void testGetMax() {
        assertEquals(40, bst.getMax());
        bst.insert(100);
        assertEquals(100, bst.getMax());
    }

    @Test
    public void testSearch() {
        assertTrue(bst.search(10));
        assertTrue(bst.search(40));
        assertFalse(bst.search(32));
    }

    @Test
    // Test that in-order traversal of tree is correct
    public void testInOrderTraversal() {
        ArrayList<Integer> nodes = bst.inOrderTraversal();
        assertEquals(1, nodes.get(0).intValue());
        assertEquals(2, nodes.get(1).intValue());
        assertEquals(3, nodes.get(2).intValue());
        assertEquals(5, nodes.get(3).intValue());
        assertEquals(10, nodes.get(4).intValue());
        assertEquals(12, nodes.get(5).intValue());
        assertEquals(13, nodes.get(6).intValue());
        assertEquals(15, nodes.get(7).intValue());
        assertEquals(40, nodes.get(8).intValue());
    }

    @Test
    // Test that pre-order traversal of tree is correct
    public void testPreOrderTraversal() {
        ArrayList<Integer> nodes = bst.preOrderTraversal();
        assertEquals(10, nodes.get(0).intValue());
        assertEquals(5, nodes.get(1).intValue());
        assertEquals(2, nodes.get(2).intValue());
        assertEquals(1, nodes.get(3).intValue());
        assertEquals(3, nodes.get(4).intValue());
        assertEquals(15, nodes.get(5).intValue());
        assertEquals(12, nodes.get(6).intValue());
        assertEquals(13, nodes.get(7).intValue());
        assertEquals(40, nodes.get(8).intValue());
    }

    @Test
    // Test that post-order traversal of tree is correct
    public void testPostOrderTraversal() {
        ArrayList<Integer> nodes = bst.postOrderTraversal();
        assertEquals(1, nodes.get(0).intValue());
        assertEquals(3, nodes.get(1).intValue());
        assertEquals(2, nodes.get(2).intValue());
        assertEquals(5, nodes.get(3).intValue());
        assertEquals(13, nodes.get(4).intValue());
        assertEquals(12, nodes.get(5).intValue());
        assertEquals(40, nodes.get(6).intValue());
        assertEquals(15, nodes.get(7).intValue());
        assertEquals(10, nodes.get(8).intValue());
    }

    @Test
    // Test that a node with no children is deleted properly.
    // Node should be deleted with any other nodes moving.
    public void testDeleteNodeWithNoChildren() {
        assertEquals(bst.getRoot().left.left.left.data, 1);
        assertEquals(9, bst.getNodeCount());
        bst.delete(1);
        assertEquals(8, bst.getNodeCount());
        assertEquals(bst.getRoot().left.left.left, null);
    }

    @Test
    // Test that a node with one child is deleted properly.
    // Child node should move up and replace the deleted parent node.
    // No other nodes should move.
    public void testDeleteNodeWithOneChild() {
        assertEquals(bst.getRoot().right.left.data, 12);
        assertEquals(9, bst.getNodeCount());
        bst.delete(12);
        assertEquals(8, bst.getNodeCount());
        // Make sure that child node (13) moved up and replaced deleted parent node (12)
        assertEquals(bst.getRoot().right.left.data, 13);
        assertEquals(bst.getRoot().right.left.left, null);
        assertEquals(bst.getRoot().right.left.right, null);

        // Make sure no other nodes moved
        assertEquals(bst.getRoot().data, 10);
        assertEquals(bst.getRoot().left.data, 5);
        assertEquals(bst.getRoot().right.data, 15);
        assertEquals(bst.getRoot().left.left.data, 2);
        assertEquals(bst.getRoot().left.left.right.data, 3);
        assertEquals(bst.getRoot().left.left.left.data, 1);
        assertEquals(bst.getRoot().right.right.data, 40);
    }

    @Test
    // Test that a node with two children is deleted properly.
    // Deleted parent node should be replaced with largest node in the right subtree.
    // No other nodes should move.
    public void testDeleteNodeWithTwoChildren() {
        assertEquals(bst.getRoot().data, 10);
        assertEquals(9, bst.getNodeCount());
        bst.delete(10);
        assertEquals(8, bst.getNodeCount());
        // Make sure smallest node in right subtree (12) replaces the deleted parent node (10)
        assertEquals(bst.getRoot().data, 12);
        assertEquals(bst.getRoot().right.left.right, null);
        assertEquals(bst.getRoot().right.left.left, null);

        // Make sure no other nodes moved
        assertEquals(bst.getRoot().left.data, 5);
        assertEquals(bst.getRoot().right.data, 15);
        assertEquals(bst.getRoot().left.left.data, 2);
        assertEquals(bst.getRoot().left.left.right.data, 3);
        assertEquals(bst.getRoot().left.left.left.data, 1);
        assertEquals(bst.getRoot().right.left.data, 13);
        assertEquals(bst.getRoot().right.right.data, 40);
    }

    @Test
    // Test that entire tree is deleted properly
    public void testDeleteTree() {
        bst.deleteTree();
        assertEquals(null, bst.getRoot());
        assertEquals(0, bst.getNodeCount());
    }
}
