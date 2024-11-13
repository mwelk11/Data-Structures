// Test class for Graph.java
//
// Author: Matthew Welk
// Date: March 14, 2017

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class GraphTest {
    private Graph G;
    private Graph emptyG;

    // Setup graph
    //        10
    //       / \
    //      5   3
    //       \ / \
    //        7   2
    //         \ /
    //          11
    @Before
    public void setUp() {
        G = new Graph(10);
        G.addEdge(10, 5);
        G.addEdge(10, 3);
        G.addEdge(5, 7);
        G.addEdge(3, 7);
        G.addEdge(3, 2);
        G.addEdge(7, 11);
        G.addEdge(2, 11);
    }

    @Test
    public void testGetNumNodes() {
        assertEquals(6, G.getNumNodes());
        G.addEdge(11, 20);
        assertEquals(7, G.getNumNodes());
    }

    @Test
    public void testGetNumEdges() {
        assertEquals(7, G.getNumEdges());
        G.addEdge(11, 20);
        assertEquals(8, G.getNumEdges());
    }

    @Test
    public void testgetNumNodesAndEdges_EmptyGraph() {
        Graph emptyG = new Graph();
        assertEquals(0, emptyG.getNumNodes());
        assertEquals(0, emptyG.getNumEdges());
    }

    @Test
    public void testAddingNode() {
        G.getOrCreateNode(100);
        assertEquals(7, G.getNumNodes()); // Check that one node was added to total node count
        assertEquals(7, G.getNumEdges()); // Check that edge count remained the same
        assertTrue(G.getNode(100) != null);
    }

    @Test
    public void testAddingDuplicateNode() {
        assertEquals(6, G.getNumNodes());
        G.getOrCreateNode(10);
        // Make sure node count stayed the same; no duplicat node was created
        assertEquals(6, G.getNumNodes());
    }

    @Test
    public void testGettingNode() {
        assertEquals(6, G.getNumNodes());
        // Get root node; verify value and neighbors
        Graph.Node root = G.getOrCreateNode(10);
        assertEquals(10, root.data);
        assertTrue(root.neighbors.contains(5));
        assertTrue(root.neighbors.contains(3));
        assertFalse(root.neighbors.contains(19));
        // Make sure numNodes remains the same; no new node was created
        assertEquals(6, G.getNumNodes());
    }

    @Test
    public void testAddEdge() {
        // test that all edges were added from setUp()
        assertTrue(G.getNode(10).neighbors.contains(5));
        assertTrue(G.getNode(10).neighbors.contains(3));
        assertTrue(G.getNode(5).neighbors.contains(10));
        assertTrue(G.getNode(5).neighbors.contains(7));
        assertTrue(G.getNode(3).neighbors.contains(10));
        assertTrue(G.getNode(3).neighbors.contains(7));
        assertTrue(G.getNode(3).neighbors.contains(2));
        assertTrue(G.getNode(7).neighbors.contains(5));
        assertTrue(G.getNode(7).neighbors.contains(3));
        assertTrue(G.getNode(7).neighbors.contains(11));
        assertTrue(G.getNode(2).neighbors.contains(3));
        assertTrue(G.getNode(2).neighbors.contains(11));
        assertTrue(G.getNode(11).neighbors.contains(7));
        assertTrue(G.getNode(11).neighbors.contains(2));

        // negative test case (11 is not a neighbor of 10)
        assertFalse(G.getNode(11).neighbors.contains(10));       

        // add a new edge
        G.addEdge(11, 20);
        assertTrue(G.getNode(11).neighbors.contains(20));
        assertTrue(G.getNode(20).neighbors.contains(11));
    }

    @Test
    public void testAddDuplicateEdge() {
        assertEquals(7, G.getNumEdges());
        G.addEdge(10, 5);
        // Make sure no edge was added
        assertEquals(7, G.getNumEdges());
    }

    @Test
    public void testHasEdge() {
        assertTrue(G.hasEdge(10, 5));
        assertTrue(G.hasEdge(10, 3));

        // Make sure order does not matter
        assertTrue(G.hasEdge(7, 11));
        assertTrue(G.hasEdge(11, 7));

        // Negative test case
        assertFalse(G.hasEdge(10, 11));
    }

    @Test
    public void testHasNode() {
        assertTrue(G.hasNode(10));
        assertTrue(G.hasNode(5));
        assertTrue(G.hasNode(3));
        assertTrue(G.hasNode(7));
        assertTrue(G.hasNode(11));
        assertTrue(G.hasNode(2));
        assertFalse(G.hasNode(45));
    }
}
