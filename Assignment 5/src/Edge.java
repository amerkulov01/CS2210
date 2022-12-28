// Andrei Merkulov, 251145994
// CS2210, Assignment 5

public class Edge {
    
    private Node firstPoint, secPoint;
    private String type;

    public Edge(Node u, Node v, String type) { // constructor for Edge class
        firstPoint = u;
        secPoint = v;
        this.type = type;
    }

    public Node firstNode() {   // method to get the first node of an edge
        return firstPoint;
    }

    public Node secondNode() {  // method to get the second node of an edge
        return secPoint;
    }

    public String getType() {   // method to get the type of an edge
        return type;
    }
}
