// Andrei Merkulov, 251145994
// CS2210, Assignment 5

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Stack;

import javax.print.attribute.standard.Destination;

public class MyMap {
    
    private Graph G;
    private int width, length, scale, start, destination;
    private int pRoad, cRoad; // private and construction roads

    public MyMap (String inputFile) throws MapException{
        try {
            BufferedReader input = new BufferedReader(new FileReader(inputFile));

            scale = Integer.parseInt(input.readLine());
            start = Integer.parseInt(input.readLine());
            destination = Integer.parseInt(input.readLine());
            width = Integer.parseInt(input.readLine());
            length = Integer.parseInt(input.readLine());
            pRoad = Integer.parseInt(input.readLine());
            cRoad = Integer.parseInt(input.readLine());

            G = new Graph(width*length);

            String ln;
            int row = -1;
            boolean isANode;

            while ((ln = input.readLine()) != null) {   // read the file
                row++;
                for (int col=0; col<ln.length(); col++) {   // loop through the line
                    isANode = (col%2 == 0 && row%2 == 0);   // check if it is a node
                    if (isANode) {
                        if(ln.charAt(col) == start) {   // check if it is the start
                            start = (col/2) + (row/2)*width;    // set the start
                        } else if (ln.charAt(col) == destination) { // check if it is the destination
                            destination = (col/2) + (row/2)*width;  // set the destination  
                        }
                    } else { // '+' for intersection, 'V' for private road, 'C' for construction road, 'B' for block of houses
                        if (ln.charAt(col) == '+') { // intersection
                            G.addEdge(G.getNode((col/2)+(row/2)*width), G.getNode((col/2)+(row/2)*width+1), "Intersection"); // add an edge to the right
                            G.addEdge(G.getNode((col/2)+(row/2)*width), G.getNode((col/2)+(row/2)*width+width), "Intersection"); // add an edge below
                        } else if (ln.charAt(col) == 'V') { // private road
                            G.addEdge(G.getNode((col/2)+(row/2)*width), G.getNode((col/2)+(row/2)*width+1), "Private Road"); // add an edge to the right
                            G.addEdge(G.getNode((col/2)+(row/2)*width), G.getNode((col/2)+(row/2)*width+width), "Private Road"); // add an edge below
                        } else if (ln.charAt(col) == 'C') { // construction road
                            G.addEdge(G.getNode((col/2)+(row/2)*width), G.getNode((col/2)+(row/2)*width+1), "Construction Road"); // add an edge to the right
                            G.addEdge(G.getNode((col/2)+(row/2)*width), G.getNode((col/2)+(row/2)*width+width), "Construction Road"); // add an edge below
                        } else if (ln.charAt(col) == 'B') { // block of houses
                            G.addEdge(G.getNode((col/2)+(row/2)*width), G.getNode((col/2)+(row/2)*width+1), "Block of Houses"); // add an edge to the right
                            G.addEdge(G.getNode((col/2)+(row/2)*width), G.getNode((col/2)+(row/2)*width+width), "Block of Houses"); // add an edge below
                        } 
                    }
                }
                
            }
            input.close();
        }
        catch (Exception e) {
            throw new MapException("Error reading file");
        } 
    }

    public Graph getGraph() throws MapException{
        if (G == null) {
            throw new MapException("Graph is null");
        } else {
            return G;
        }
    }

    public int getStartingNode() {      // return the starting node
        return start;
    }

    public int getDestinationNode() {       // return the destination node
        return destination;
    }

    public int maxPrivateRoads() {      // return the number of private roads
        return pRoad;
    }

    public int maxConstructionRoads() {    // return the number of construction roads
        return cRoad;       
    }

    public Iterator findPath (int start, int destination, int maxPrivate, int maxConstruction) {    // find the path
        try {
            Stack trypath = new Stack(), bestPath = new Stack();    // create two stacks
            Node current, s = G.getNode(start), d = G.getNode(destination);   // get the start and destination nodes
            boolean found;
            Edge e = null;

            bestPath.push(s);
            s.markNode(true);   // mark the start node
            while (!bestPath.isEmpty()) {   // while the best path is not empty
                current = (Node) bestPath.peek();
                if (current == d) { // if the current node is the destination
                    return bestPath.iterator();
                } else {
                    found = false;
                    Iterator it = G.incidentEdges(current); // get the incident edges
                    while (it.hasNext()) {  // while there are more edges
                        current = e.secondNode();   // get the second node
                        e = (Edge) it.next();   // get the next edge
                        if (!current.getMark()) {   // if the node is not marked
                            if (e.getType() == "Private Road") {    // if the edge is a private road
                                if (maxPrivate > 0) {   // if there are more private roads
                                    trypath.push(current);
                                    current.markNode(true);
                                    maxPrivate--;
                                    found = true;
                                }
                            } else if (e.getType() == "Construction Road") {    // if there are more construction roads
                                if (maxConstruction > 0) {
                                    trypath.push(current);
                                    current.markNode(true);
                                    maxConstruction--;
                                    found = true;
                                }
                            } 
                        }
                    }
                    if (!found) {   // if there are no more edges
                        if (G.getEdge((Node)bestPath.pop(), (Node)bestPath.peek()).getType() == "Private Road") {   // if the edge is a private road
                            maxPrivate++;
                        } else if (G.getEdge((Node)bestPath.pop(), (Node)bestPath.peek()).getType() == "Construction Road") {   // if the edge is a construction road
                            maxConstruction++;
                        }
                        while (bestPath.size() > 0 && trypath.size() > 0 && !G.areAdjacent((Node)bestPath.peek(), (Node)trypath.peek())) {  // while the best path and try path are not adjacent
                            Node n = (Node) bestPath.pop(); // get the node
                            Edge tmpE = G.getEdge(n, (Node)bestPath.peek());    // get the edge
                            n.markNode(false);  // unmark the node
                            if (tmpE.getType() == "Private Road") { // if the edge is a private road
                                maxPrivate++;
                            } else if (tmpE.getType() == "Construction Road") { // if the edge is a construction road
                                maxConstruction++;
                            }
                        }
                        if (bestPath.size() == 0 || trypath.size() == 0) {  // if the best path or try path is empty
                            return null;
                        }
                    }
                    if (G.getEdge((Node)bestPath.peek(), (Node)trypath.peek()).getType() == "Private Road") {   // if the edge is a private road
                        maxPrivate--;
                    } else if (G.getEdge((Node)bestPath.peek(), (Node)trypath.peek()).getType() == "Construction Road") {   // if the edge is a construction road
                        maxConstruction--;
                    }
                    bestPath.push(trypath.pop());   // push the try path onto the best path
                }   
            }
            return null;
        }
        catch (Exception e) {
            System.out.println("Error finding path");
            return null;
        }
    }
}
