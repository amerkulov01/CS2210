
public class BinarySearchTree implements BinarySearchTreeADT {

    private BNode root; //parameter 'r' is the root of the tree

    public BinarySearchTree() { // constructor that creates a tree whose root is a leaf node
        root = new BNode();
    }

    private BNode locate(BNode r, Location Key) { // helper method to find a pel object storing key in binary search key. returns the root if node does not exist, or not if it does exist
        if (r.isLeaf()) { // if the node is a leaf, return the root
            return r;
        } else {
            if (r.getData().getLocus().compareTo(Key) == 0) { // if the node is not a leaf, and the key is equal to the key of the node, return the node
                return r;
            }
            if (r.getData().getLocus().compareTo(Key) > 0) { // if the key is less than the key of the node, return the left child of the node
                return locate(r.leftChild(), Key);
            }
            if (r.getData().getLocus().compareTo(Key) < 0) { // if the key is greater than the key of the node, return the right child of the node
                return locate(r.rightChild(), Key);
            }
        }
        return r;
    }

    private BNode findSmallest(BNode r) {
        if (r.isLeaf()) {
            return null;
        } else {
            while (r.leftChild().isLeaf() == false) {
                r = r.leftChild();
            }
            return r;
        }
    }

    public Pel get (BNode r, Location key) { // returns the Pel object storing the given key, if the key is stored in the tree. Returns null otherwise.
        BNode getPel = locate(r, key); // calls the locate method to find the node with the key
        if (!getPel.isLeaf()) { // if the node is not a leaf, return the pel object stored in the node
            return getPel.getData();
        } else {
            return null; // if the node is a leaf, return null
        }
    }

    public void put (BNode r, Pel newData) throws DuplicatedKeyException{ // inserts the given data in the tree if no data item with the same key is already there. If a node already stores the same key, the algorithm throws a DuplicatedKeyException.
        BNode node = locate(r, newData.getLocus()); // calls the locate method to find the node with the key
        if ((node.leftChild() != null)||(node.rightChild() != null)) { // if the node is not a leaf, throw a DuplicatedKeyException
            throw new DuplicatedKeyException(null);
        } else {
            node.setContent(newData);
            BNode rightChild = new BNode(); // creates a new node for the right child of the node
            BNode leftChild = new BNode();
            node.setRightChild(rightChild); // sets the right child of the node to the new node
            node.setLeftChild(leftChild);
            leftChild.setParent(node);  // sets the parent of the left child to the node
            rightChild.setParent(node);
        }
    }

    public void remove (BNode r, Location key) throws InexistentKeyException{ // removes the data item with the given key, if the key is stored in the tree. Throws an InexistentKeyException otherwise.

        BNode removeNode = locate(r, key); // calls the locate method to find the node with the key
        if (removeNode.isLeaf()) {
            throw new InexistentKeyException(null);
        } else {
            BNode rRight = removeNode.rightChild(); // sets the right child of the node to rRight
            BNode rLeft = removeNode.leftChild(); // sets the left child of the node to rLeft
            BNode rParent = removeNode.parent(); // sets the parent of the node to rParent
            if (rLeft.isLeaf()) {
                if (rParent == null) {
                    this.root = rRight;
                    rRight.setParent(null); // sets the parent of the right child to null
                } else {
                    if (rParent.leftChild() == removeNode) { // if the node is the left child of the parent, set the left child of the parent to the right child of the node
                        rParent.setLeftChild(rRight);
                        rRight.setParent(rParent);
                    } else {
                        rParent.setRightChild(rRight); // if the node is the right child of the parent, set the right child of the parent to the right child of the node
                        rRight.setParent(rParent);
                    }
                }
            }
            if (rRight.isLeaf()) {
                if (rParent == null) {
                    this.root = rLeft;
                    rLeft.setParent(null); // sets the parent of the left child to null
                } else {
                    if (rParent.leftChild() == removeNode) { // if the node is the left child of the parent, set the left child of the parent to the left child of the node
                        rParent.setLeftChild(rLeft);
                        rLeft.setParent(rParent);
                    } else {
                        rParent.setRightChild(rLeft); // if the node is the right child of the parent, set the right child of the parent to the left child of the node
                        rLeft.setParent(rParent);
                    }
                }
            } else {
                while (rRight.leftChild().isLeaf() == false) { // if the right child of the node is not a leaf, set the right child of the node to the left child of the right child of the node
                    rRight = rRight.leftChild();
                }
                removeNode.setContent(rRight.getData()); // set the content of the node to the content of the right child of the node
                remove(rRight, rRight.getData().getLocus()); // remove the right child of the node
            }
        }
    }

    public Pel successor (BNode r, Location key) { // returns the Pel object with the smallest key larger than the given key. returns null if the given key has no successor.
        BNode node = locate(r, key); // calls the locate method to find the node with the key
        if (r.isLeaf()) {
            return null;
        } else {
            if ((node.isLeaf() == false)&&(node.rightChild().isLeaf()==false)) {
                return findSmallest(node.rightChild()).getData();
            } else {
                BNode parent = node.parent();
                while ((parent != r)&&(node == parent.rightChild())) {
                    node = parent;
                    parent = parent.parent();
                }
                if (node == r) {
                    return null;
                } else {
                    return parent.getData();
                }
            }
        }
    }

    

    public Pel predecessor (BNode r, Location key) { // returns the Pel object with the largest key smaller than the given key. returns null if the given key has no predecessor.
        BNode node = locate(r, key); // calls the locate method to find the node with the key
        if ((node.isLeaf() == false)&&(node.leftChild().isLeaf()==false)) {
            return findSmallest(node.leftChild()).getData();
        } else {
            BNode parent = node.parent();
            while ((parent != r)&&(node == parent.leftChild())) {
                node = parent;
                parent = parent.parent();
            }
            return parent.getData();
        }
    }



    public Pel smallest (BNode r) throws EmptyTreeException { // returns the Pel object with the smallest key in the tree. Throws an EmptyTreeException if the tree does not contain any data
        BNode smallest = findSmallest(r); // calls the findSmallest method to find the node with the smallest key
        if (smallest == null) { // if the smallest node is null, throw an EmptyTreeException
            throw new EmptyTreeException(null);
        } else {    
            return smallest.getData();  // return the data stored in the smallest node
        }
    }

    public Pel largest (BNode r) throws EmptyTreeException { // returns the Pel object with the largest key in the tree. Throws an EmptyTreeException if the tree does not contain any data
        if (r.isLeaf()) {   // if the node is a leaf node, throw an EmptyTreeException
            throw new EmptyTreeException(null);
        }
        while (r.rightChild().isLeaf() == false) { // if the right child of the node is not a leaf, set the node to the right child of the node
            r = r.rightChild();
        }
        return r.getData(); // return the data stored in the node
    }

    public BNode getRoot() {    // returns the root of the tree
        return root;
    }

}
