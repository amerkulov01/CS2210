// Andrei Merkulov, 251145994
// CS2210, Assignment 5

public class Node {

    private int id;
    private boolean mark;

    public Node(int id) { // constructor for Node class
        this.id = id;
        mark = false;
    }

    public void markNode(boolean mark) {    // method to mark a node
        this.mark = mark;
    }

    public boolean getMark() { // method to get the mark of a node
        return mark;
    }

    public int getId() {    // method to get the id of a node
        return id;
    }
}
