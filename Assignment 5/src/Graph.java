// Andrei Merkulov, 251145994
// CS2210, Assignment 5

import java.util.*;

public class Graph implements GraphADT {
    
    private Node nodeArray[];
    private int numNodes;
    private Edge edgeMat [][];

    public Graph(int n) {
        this.nodeArray = new Node[n]; // create an array of nodes
        this.edgeMat = new Edge[n][n];  // create a matrix of edges
        this.numNodes = n;              // set the number of nodes
        for (int i = 0; i < n; i++) {   // create the nodes
            Node newNode = new Node(i); 
            nodeArray[i] = newNode;    // add the node to the array
            for (int j = 0; j < n; j++) {   // create the edges
                edgeMat[i][j] = null;
            }
        }
    }

    public Node getNode(int id) throws GraphException { // method to get a node
        if (id < 0 || id >= numNodes) { // check if the id is valid
            throw new GraphException("Invalid node id");
        } else {
            return nodeArray[id];   // return the node
        }
    }

    public void addEdge(Node u, Node v, String edgeType) throws GraphException {    // method to add an edge
        if (inGraph(u, v)) {    // check if the nodes are in the graph
            Edge edge = edgeMat[u.getId()][v.getId()];  // get the edge
            if (edge == null) { // check if the edge is null
                edgeMat[u.getId()][v.getId()] = new Edge(u, v, edgeType);   // add the edge
                edgeMat[v.getId()][u.getId()] = new Edge(v, u, edgeType);   // add the edge
            } else {
                throw new GraphException("Edge already exists");    // throw an exception
            }
        } else {
            throw new GraphException("Invalid node id");    // throw an exception
        }
    }

    private boolean inGraph(Node u, Node v) {
        try {
            getNode(u.getId()); // check if the first node is in the graph
            getNode(v.getId()); // check if the second node is in the graph
            return true;
        } catch (GraphException e) {
            return false;
        }
    }

    public Iterator incidentEdges(Node u) throws GraphException {     // Returns a Java Iterator storing all the edges incident on node u. It returns null if u does not have any edges incident on it.
        if (inGraph(u)) {
            Stack edgeStack = new Stack();  // create a stack to store the edges
            for (int i = 0; i < numNodes; i++) { // loop through the edges
                if (edgeMat[u.getId()][i] != null) { // check if the edge is null
                    edgeStack.push(edgeMat[u.getId()][i]); // add the edge to the stack
                }
            }
            return edgeStack.iterator();    // return the iterator
        } else {
            throw new GraphException("Invalid node id");    // throw an exception
        }
    }

    private boolean inGraph(Node u) {
        try {
            getNode(u.getId()); // check if the node is in the graph
            return true;
        } catch (GraphException e) {
            return false;
        }
    }

    public Edge getEdge(Node u, Node v) throws GraphException{
        if (inGraph(u, v)) {    // check if the nodes are in the graph
            Edge result = edgeMat[u.getId()][v.getId()];  // get the edge
            if (result == null) { // check if the edge is null
                throw new GraphException("Edge does not exist");    // throw an exception
            } else {
                return result;    // return the edge
            }
        } else {
            throw new GraphException("Invalid node id");    // throw an exception
        }
    }

    public boolean areAdjacent(Node u, Node v) throws GraphException {
        if (inGraph(u, v)) {    // check if the nodes are in the graph
            Edge result = edgeMat[u.getId()][v.getId()];  // get the edge
            if (result == null) { // check if the edge is null
                return false;   // return false
            } else {
                return true;    // return true
            }
        } else {
            throw new GraphException("Invalid node id");    // throw an exception
        }
    }
}


