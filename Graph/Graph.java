// Data structure for a graph
//
// Author: Matthew Welk
// Date: February 28, 2017

import java.util.HashSet;
import java.util.HashMap;

public class Graph {

    // Data structure to represent each node within the graph
    static class Node {

        public int data;
        public HashSet<Integer> neighbors;
        public boolean visited;

        public Node(int data) {
            this.data = data;
            neighbors = new HashSet<Integer>();
            this.visited = false;
        }

    }

    private Node root;
    private int numNodes;
    private int numEdges;
    private HashMap<Integer, Node> nodes;

    // Constructor
    public Graph() {
        this.root = null;
        numNodes = 0;
        numEdges = 0;
        nodes = new HashMap<Integer, Node>();
    }

    // Overloaded constructor
    // @param val - value of root node
    public Graph(int val) {
        this.root = new Node(val);
        numNodes = 0;
        numEdges = 0;
        nodes = new HashMap<Integer, Node>();
    }

    // Return if an edge exists in the graph
    // @param nodeVal1 - value of first node of the edge
    // @param nodeVal2 - value of second node of the edge
    // @return true if the edge exists; false if the edge does not exist
    public boolean hasEdge(int nodeVal1, int nodeVal2) {
        if(!nodes.containsKey(nodeVal1) || !nodes.containsKey(nodeVal2)) {
            return false;
        } else {
            return nodes.get(nodeVal1).neighbors.contains(nodeVal2);
        }
    }

    // Return if a node exists in the graph or not
    // @param val - value of node to search for
    // @return true if the node is in the graph; false if not
    public boolean hasNode(int val) {
        return nodes.containsKey(val);
    }

    // Add an edge to the graph. Creates nodes if they do not exist.
    // @param nodeVal1 - value of first node of the edge
    // @param nodeVal2 - value of second node of the edge
    public void addEdge(int nodeVal1, int nodeVal2) {
        // Edge already exists in graph
        if(hasEdge(nodeVal1, nodeVal2)) {
            return;
        } else {
            Node node1 = getOrCreateNode(nodeVal1);
            Node node2 = getOrCreateNode(nodeVal2);
            node1.neighbors.add(nodeVal2);
            node2.neighbors.add(nodeVal1);
            numEdges++;
        }
    }

    // return the specified node or create a new node if it does not exist
    // @param val - value of node to get
    // @return the existing node or the newly created node
    public Node getOrCreateNode(int val) {
        Node myNode = nodes.get(val);
        if(myNode == null) {
            myNode = new Node(val);
            nodes.put(val, myNode);
            numNodes++;
        }
        return myNode;
    }

    // Return the specified node if it exists in the graph
    // @param val - value of node to return
    // @return the node if it is found; otherwise, return null
    public Node getNode(int val) {
        return nodes.get(val);
    }

    // Return the number of nodes in the graph
    public int getNumNodes() {
        return numNodes;
    }

    // Return the number of nodes in the graph
    public int getNumEdges() {
        return numEdges;
    }

    // Return the root node
    public Node getRoot() {
        return root;
    }

    // Return a list of nodes in the graph
    public HashMap<Integer, Node> getNodeMap() {
        return nodes;
    }
}
