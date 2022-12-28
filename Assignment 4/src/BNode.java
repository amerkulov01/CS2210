

public class BNode {

    private Pel value;      // the value of the node
    private BNode left;     // the left child of the node
    private BNode right;    // the right child of the node
    private BNode parent;   // the parent of the node

    public BNode(Pel value, BNode left, BNode right, BNode parent) { // Constructor for BNode class that stores a Pel object and references to the left and right children and the parent node.
        this.value = value; // this.value refers to the value of the node
        this.left = left;   // this.left refers to the left child of the node
        this.right = right; // this.right refers to the right child of the node
        this.parent = parent;   // this.parent refers to the parent of the node
    }

    public BNode () {   // constructor for the class that initializes a leaf node with null references to the left and right children and the parent node.
        this.value = null;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public BNode parent() { // returns the parent of the node
        return parent;
    }

    public void setParent(BNode newParent) { // sets the parent of the node
        this.parent = newParent;
    }

    public void setLeftChild(BNode p) { // sets the left child of the node
        this.left = p;
    }

    public void setRightChild(BNode p) { // sets the right child of the node
        this.right = p;
    }

    public void setContent(Pel value) { //stores the given pel object in this node
        this.value = value;
    }

    public boolean isLeaf() {   // returns true if the node is a leaf node
        if (this.left == null && this.right == null) {
            return true;
        }
        return false;
    }

    public Pel getData() {  // returns the pel object stored in this node
        return value;
    }

    public BNode leftChild() {  // returns the left child of the node
        return left;
    }

    public BNode rightChild() { // returns the right child of the node
        return right;
    }
}
