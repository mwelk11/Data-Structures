// Data structure for a graph
//
// Author: Matthew Welk
// Date: February 28, 2017

import java.util.HashSet;

public class Graph {

    // Data structure to represent each node within the graph
    static class Node {

        public int data;
        public HashSet<Node> neighbors;
        public boolean visited;

        public Node(data) {
            this.data = data;
           neighbors = new HashSet<Node>();
           this.visited = false;
        }

    }

    Node root;

    // Constructor
    public Graph() {
        this.root = null;
    }

    // Overloaded constructor
    public Graph(int val) {
        this.root = new Node(val);
    }

    // Add a node to the graph
    public void addNode(int val) {

    }

    // Add an edge to the graph. Creates nodes if they do not exist.
    public void addEdge(int val1, int val2) {
            
        val1.neighbors.add(val2);
        val2.neighbors.add(val1);
    }
}
